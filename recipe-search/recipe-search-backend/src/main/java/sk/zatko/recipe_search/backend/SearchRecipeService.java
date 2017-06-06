package sk.zatko.recipe_search.backend;

import javax.ejb.Local;

@Local
public interface SearchRecipeService {

	public String findRecipe(String query);
}
