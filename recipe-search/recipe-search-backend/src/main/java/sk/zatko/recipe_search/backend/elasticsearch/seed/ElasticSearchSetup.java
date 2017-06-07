package sk.zatko.recipe_search.backend.elasticsearch.seed;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import sk.zatko.recipe_search.backend.properties.Property;

public class ElasticSearchSetup {

	private static final Logger logger = Logger.getLogger(ElasticSearchSetup.class);
	
	private String recipesFileLocation = "recipes";
	
	private String indexName = "recipes/";
	private String documentName = "recipe";
	private String mappingFile = "src//main//resources//elasticsearch//mappings.json";
	
	public ElasticSearchSetup() {
		
		try {
			
			this.recipesFileLocation = Property.getProperty("RECIPES_FILE_LOCATION");
			
			this.indexName = Property.getProperty("ELASTIC_INDEX_NAME");
			this.documentName = Property.getProperty("ELASTIC_DOCUMENT_NAME");
			this.mappingFile = Property.getProperty("ELASTIC_MAPPING_FILE");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ElasticSearchSetup elasticSearchSetup = new ElasticSearchSetup();
		
		logger.info("Indexing data...");
		
		elasticSearchSetup.index();
		
		logger.info("Indexing was successfull");
		
		System.exit(0);
	}
	
	private void index() {
	
		ElasticSearchIndexer indexer = new ElasticSearchIndexer();
		File folder = new File(this.recipesFileLocation);
		
		try {
			
			if (indexer.indexExists(indexName)) {
				indexer.deleteIndex(indexName);
			}
			indexer.createIndex(this.indexName, this.mappingFile);
			
			indexDataFromFile(indexer, folder, indexName, this.documentName);
			
		} catch (IOException e) {
			logger.error("Error while indexing data", e);
		}		
	}
	
	private void indexDataFromFile(ElasticSearchIndexer indexer, File folder, String indexName, String documentName) throws ClientProtocolException, IOException {
		
		for (final File fileEntry : folder.listFiles()) {
			
			if (fileEntry.isDirectory()) {
				indexDataFromFile(indexer, fileEntry, indexName, documentName);
	        }
			
			byte[] encoded = Files.readAllBytes(Paths.get(fileEntry.getAbsolutePath()));
			String fileContent = new String(encoded, Charset.defaultCharset());
			
			indexToElasticSearch(indexer, fileContent, this.indexName, this.documentName,
					fileEntry.getName().substring(0, fileEntry.getName().length() - ".json".length()));
	    }
		
	}
	
	private void indexToElasticSearch(ElasticSearchIndexer indexer, String json, String indexName, String documentName, String id) {
		
		try {
			indexer.indexRecord(indexName, documentName, json, Integer.parseInt(id));
			
			logger.info("Indexing deal with id: " + documentName + " was successfull");
			
		} catch (IOException e) {
			
			logger.error("Failed to create index with id: " + documentName, e);
			logger.info(json);
		}
	}
}
