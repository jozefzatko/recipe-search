package sk.zatko.recipe_search.backend.elasticsearch.model.recipe_fulltext_search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("rec_id")
    @Expose
    private Integer recId;
    @SerializedName("rec_url")
    @Expose
    private String recUrl;
    @SerializedName("rec_name")
    @Expose
    private String recName;
    @SerializedName("rec_author")
    @Expose
    private String recAuthor;
    @SerializedName("rec_categories")
    @Expose
    private List<String> recCategories = null;
    @SerializedName("rec_ready_in_time")
    @Expose
    private String recReadyInTime;
    @SerializedName("rec_servings_count")
    @Expose
    private String recServingsCount;
    @SerializedName("rec_calories")
    @Expose
    private String recCalories;
    @SerializedName("rec_ingredients")
    @Expose
    private List<String> recIngredients = null;
    @SerializedName("rec_directions")
    @Expose
    private List<String> recDirections = null;
    @SerializedName("rec_related")
    @Expose
    private List<RecRelated> recRelated = null;
    @SerializedName("rec_reviews")
    @Expose
    private List<RecReview> recReviews = null;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getRecUrl() {
        return recUrl;
    }

    public void setRecUrl(String recUrl) {
        this.recUrl = recUrl;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecAuthor() {
        return recAuthor;
    }

    public void setRecAuthor(String recAuthor) {
        this.recAuthor = recAuthor;
    }

    public List<String> getRecCategories() {
        return recCategories;
    }

    public void setRecCategories(List<String> recCategories) {
        this.recCategories = recCategories;
    }

    public String getRecReadyInTime() {
        return recReadyInTime;
    }

    public void setRecReadyInTime(String recReadyInTime) {
        this.recReadyInTime = recReadyInTime;
    }

    public String getRecServingsCount() {
        return recServingsCount;
    }

    public void setRecServingsCount(String recServingsCount) {
        this.recServingsCount = recServingsCount;
    }

    public String getRecCalories() {
        return recCalories;
    }

    public void setRecCalories(String recCalories) {
        this.recCalories = recCalories;
    }

    public List<String> getRecIngredients() {
        return recIngredients;
    }

    public void setRecIngredients(List<String> recIngredients) {
        this.recIngredients = recIngredients;
    }

    public List<String> getRecDirections() {
        return recDirections;
    }

    public void setRecDirections(List<String> recDirections) {
        this.recDirections = recDirections;
    }

    public List<RecRelated> getRecRelated() {
        return recRelated;
    }

    public void setRecRelated(List<RecRelated> recRelated) {
        this.recRelated = recRelated;
    }

    public List<RecReview> getRecReviews() {
        return recReviews;
    }

    public void setRecReviews(List<RecReview> recReviews) {
        this.recReviews = recReviews;
    }

}
