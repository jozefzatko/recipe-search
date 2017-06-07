package sk.zatko.recipe_search.backend.elasticsearch.model.recipe_fulltext_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecRelated {

    @SerializedName("rel_id")
    @Expose
    private String relId;
    @SerializedName("rel_name")
    @Expose
    private String relName;
    @SerializedName("rel_ref")
    @Expose
    private String relRef;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getRelRef() {
        return relRef;
    }

    public void setRelRef(String relRef) {
        this.relRef = relRef;
    }

}
