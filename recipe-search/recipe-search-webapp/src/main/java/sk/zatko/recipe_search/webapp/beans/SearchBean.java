package sk.zatko.recipe_search.webapp.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import sk.zatko.recipe_search.backend.ejb.elasticsearch.RecipeCategoriesService;
import sk.zatko.recipe_search.backend.ejb.elasticsearch.RecipeIngredientsService;
import sk.zatko.recipe_search.backend.ejb.elasticsearch.SearchRecipeService;

@ManagedBean(name="searchBean")
@RequestScoped
public class SearchBean {

	@EJB
	SearchRecipeService searchRecipeService;
	
	@EJB
	RecipeCategoriesService recipeCategoriesService;
	
	@EJB
	RecipeIngredientsService recipeIngredientsService;
	
	private String query;

	public String search() {
		
		return "search?faces-redirect=true&includeViewParams=true";
	}
	
	public sk.zatko.recipe_search.backend.elasticsearch.model.recipe_fulltext_search.ElasticResult getResults() {
		
		return searchRecipeService.findRecipe(this.query);
	}
	
	public sk.zatko.recipe_search.backend.elasticsearch.model.categories.ElasticResult getCategories() {
		
		return recipeCategoriesService.getCategories(10);
	}

	public sk.zatko.recipe_search.backend.elasticsearch.model.ingredients.ElasticResult getIngredients() {
		
		return recipeIngredientsService.getIngredients(10);
	}
	
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
