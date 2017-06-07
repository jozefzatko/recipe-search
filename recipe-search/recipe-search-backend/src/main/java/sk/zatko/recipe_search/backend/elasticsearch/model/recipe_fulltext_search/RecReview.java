package sk.zatko.recipe_search.backend.elasticsearch.model.recipe_fulltext_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecReview {

    @SerializedName("rev_author")
    @Expose
    private String revAuthor;
    @SerializedName("rev_author_ref")
    @Expose
    private String revAuthorRef;
    @SerializedName("rev_rating")
    @Expose
    private String revRating;
    @SerializedName("rev_date")
    @Expose
    private String revDate;
    @SerializedName("rev_text")
    @Expose
    private String revText;
    @SerializedName("rev_review_url")
    @Expose
    private String revReviewUrl;

    public String getRevAuthor() {
        return revAuthor;
    }

    public void setRevAuthor(String revAuthor) {
        this.revAuthor = revAuthor;
    }

    public String getRevAuthorRef() {
        return revAuthorRef;
    }

    public void setRevAuthorRef(String revAuthorRef) {
        this.revAuthorRef = revAuthorRef;
    }

    public String getRevRating() {
        return revRating;
    }

    public void setRevRating(String revRating) {
        this.revRating = revRating;
    }

    public String getRevDate() {
        return revDate;
    }

    public void setRevDate(String revDate) {
        this.revDate = revDate;
    }

    public String getRevText() {
        return revText;
    }

    public void setRevText(String revText) {
        this.revText = revText;
    }

    public String getRevReviewUrl() {
        return revReviewUrl;
    }

    public void setRevReviewUrl(String revReviewUrl) {
        this.revReviewUrl = revReviewUrl;
    }

}
