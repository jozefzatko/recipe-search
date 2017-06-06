package sk.zatko.recipe_search.webapp.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="searchBean")
@RequestScoped
public class SearchBean {

	private String query;

	public String search() {
		
		return "search?faces-redirect=true&includeViewParams=true";
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
