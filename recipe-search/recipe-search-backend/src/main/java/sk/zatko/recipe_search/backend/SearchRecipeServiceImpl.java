package sk.zatko.recipe_search.backend;

import javax.ejb.Stateless;

@Stateless
public class SearchRecipeServiceImpl implements SearchRecipeService {

	@Override
	public String findRecipe(String query) {
		return query;
	}

}
