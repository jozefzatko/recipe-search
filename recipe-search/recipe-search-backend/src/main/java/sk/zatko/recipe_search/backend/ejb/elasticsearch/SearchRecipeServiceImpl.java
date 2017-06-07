package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import sk.zatko.recipe_search.backend.ejb.rest.RestCallService;
import sk.zatko.recipe_search.backend.elasticsearch.model.recipe_fulltext_search.ElasticResult;

@Stateless
public class SearchRecipeServiceImpl extends ElasticService implements SearchRecipeService {

	@EJB
	RestCallService restCallService;
	
	public SearchRecipeServiceImpl() {
		super();
	}
	
	@Override
	public ElasticResult findRecipe(String expression) {
		
		String fullTextQuery = prepareFulltextQuery(expression);
		String  response;
		
		String uri = this.elasticNodeUri + this.indexName + "_search";
		
		try {
			
			response = elasticRestCall("POST", uri, fullTextQuery);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return new Gson().fromJson(response, ElasticResult.class);
	}
	
	private String prepareFulltextQuery(String expression) {
		
		return "{\r\n" + 
				"	\"query\": {\r\n" + 
				"		\"multi_match\" : {\r\n" + 
				"		\"query\" : \" " + expression +  " \",\r\n" + 
				"		\"fields\" : [\r\n" + 
				"			\"rec_name.original^100\",\r\n" + 
				"			\"rec_author^10\",\r\n" + 
				"			\"rec_ingredients.original^5\",\r\n" + 
				"			\"rec_directions.analyzed^3\",\r\n" + 
				"			\"rev_author^1\",\r\n" + 
				"			\"rev_text^1\"\r\n" + 
				"			] \r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
	}

}
