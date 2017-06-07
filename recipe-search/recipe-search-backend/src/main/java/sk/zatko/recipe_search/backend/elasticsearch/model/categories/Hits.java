package sk.zatko.recipe_search.backend.elasticsearch.model.categories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hits {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("max_score")
    @Expose
    private Integer maxScore;
    @SerializedName("hits")
    @Expose
    private List<Object> hits = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public List<Object> getHits() {
        return hits;
    }

    public void setHits(List<Object> hits) {
        this.hits = hits;
    }

}
