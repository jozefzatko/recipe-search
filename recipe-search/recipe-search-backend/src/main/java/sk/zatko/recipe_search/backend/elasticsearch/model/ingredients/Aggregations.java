package sk.zatko.recipe_search.backend.elasticsearch.model.ingredients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aggregations {

    @SerializedName("ingredients")
    @Expose
    private Ingredients ingredients;

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

}
