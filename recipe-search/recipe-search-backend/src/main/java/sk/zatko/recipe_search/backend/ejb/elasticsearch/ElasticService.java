package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import java.io.IOException;

import javax.ejb.EJB;

import sk.zatko.recipe_search.backend.ejb.rest.RestCallService;
import sk.zatko.recipe_search.backend.properties.Property;

public abstract class ElasticService {

	@EJB
	RestCallService restCallService;
	
	protected String elasticNodeUri = "http://localhost:9200/";
	protected String indexName = "recipes/";
	
	public ElasticService() {
		
		try {
			
			this.elasticNodeUri = Property.getProperty("ELASTIC_NODE_URI");
			this.indexName = Property.getProperty("ELASTIC_INDEX_NAME");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected String elasticRestCall(String verb, String uri, String body) throws IOException {
		
		restCallService.openConnection(uri, "POST");
		restCallService.request(body);
		String response = restCallService.getLastResponse();
		restCallService.closeConnection();
		
		return response;
	}
}
