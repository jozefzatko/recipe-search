package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import sk.zatko.recipe_search.backend.ejb.rest.RestCallService;
import sk.zatko.recipe_search.backend.elasticsearch.model.categories.ElasticResult;


@Stateless
public class RecipeCategoriesServiceImpl extends ElasticService implements RecipeCategoriesService {

	@EJB
	RestCallService restCallService;
	
	public RecipeCategoriesServiceImpl() {
		super();
	}
	
	@Override
	public ElasticResult getCategories(int count) {
		
		String bestCategoriesQuery = prepareQuery(count);
		String response;
		
		String uri = this.elasticNodeUri + this.indexName + "_search?search_type=count";
		
		try {
			
			response = elasticRestCall("POST", uri, bestCategoriesQuery);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return new Gson().fromJson(response, ElasticResult.class);
	}
	
	private String prepareQuery(int count) {
		
		return "{\r\n" + 
				"	\"size\": " + count + ",\r\n" + 
				"	\"aggregations\": {\r\n" + 
				"		\"categories\": {\r\n" + 
				"			\"terms\": {\r\n" + 
				"				\"field\": \"rec_categories\",\r\n" + 
				"				\"size\": " + count + "\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
	}

}
