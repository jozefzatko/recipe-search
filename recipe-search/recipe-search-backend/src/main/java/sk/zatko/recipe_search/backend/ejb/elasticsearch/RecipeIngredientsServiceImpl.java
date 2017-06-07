package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import sk.zatko.recipe_search.backend.ejb.rest.RestCallService;
import sk.zatko.recipe_search.backend.elasticsearch.model.ingredients.ElasticResult;

@Stateless
public class RecipeIngredientsServiceImpl extends ElasticService implements RecipeIngredientsService {

	@EJB
	RestCallService restCallService;
	
	public RecipeIngredientsServiceImpl() {
		super();
	}
	
	@Override
	public ElasticResult getIngredients(int count) {
		
		String topIngredientsQuery = prepareQuery(count);
		String  response;
		
		String uri = this.elasticNodeUri + this.indexName + "_search?search_type=count";
		
		try {
			
			response = elasticRestCall("POST", uri, topIngredientsQuery);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return new Gson().fromJson(response, ElasticResult.class);
	}

	private String prepareQuery(int count) {
		
		return "{\r\n" + 
				"	\"size\": " + count +  ",\r\n" + 
				"	\"aggregations\": {\r\n" + 
				"		\"ingredients\": {\r\n" + 
				"			\"terms\": {\r\n" + 
				"				\"field\": \"rec_ingredients.analyzed\",\r\n" + 
				"				\"size\": " + count +  "\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
	}

	
}
