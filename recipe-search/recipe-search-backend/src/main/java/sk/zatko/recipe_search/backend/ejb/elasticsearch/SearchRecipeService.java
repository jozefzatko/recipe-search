package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import javax.ejb.Local;

import sk.zatko.recipe_search.backend.elasticsearch.model.recipe_fulltext_search.ElasticResult;

@Local
public interface SearchRecipeService {

	/**
	 * Full-text weighted search in the database of recipes
	 */
	public ElasticResult findRecipe(String query);
}
