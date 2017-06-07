package sk.zatko.recipe_search.backend.elasticsearch.model.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aggregations {

    @SerializedName("categories")
    @Expose
    private Categories categories;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
