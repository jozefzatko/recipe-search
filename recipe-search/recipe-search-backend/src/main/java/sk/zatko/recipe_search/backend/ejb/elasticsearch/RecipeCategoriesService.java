package sk.zatko.recipe_search.backend.ejb.elasticsearch;

import javax.ejb.Local;

import sk.zatko.recipe_search.backend.elasticsearch.model.categories.ElasticResult;

@Local
public interface RecipeCategoriesService {

	/**
	 * Get most frequent categories of all recipes
	 */
	public ElasticResult getCategories(int count);
}
