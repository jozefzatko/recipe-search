package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import javax.ejb.Local;

import sk.zatko.recipe_search.backend.elasticsearch.model.ingredients.ElasticResult;

@Local
public interface RecipeIngredientsService {

	/**
	 * Get most frequent ingredients from all recipes
	 */
	public ElasticResult getIngredients(int count);
}
