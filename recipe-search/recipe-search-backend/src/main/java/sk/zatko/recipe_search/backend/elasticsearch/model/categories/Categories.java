package sk.zatko.recipe_search.backend.elasticsearch.model.categories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("doc_count_error_upper_bound")
    @Expose
    private Integer docCountErrorUpperBound;
    @SerializedName("sum_other_doc_count")
    @Expose
    private Integer sumOtherDocCount;
    @SerializedName("buckets")
    @Expose
    private List<Bucket> buckets = null;

    public Integer getDocCountErrorUpperBound() {
        return docCountErrorUpperBound;
    }

    public void setDocCountErrorUpperBound(Integer docCountErrorUpperBound) {
        this.docCountErrorUpperBound = docCountErrorUpperBound;
    }

    public Integer getSumOtherDocCount() {
        return sumOtherDocCount;
    }

    public void setSumOtherDocCount(Integer sumOtherDocCount) {
        this.sumOtherDocCount = sumOtherDocCount;
    }

    public List<Bucket> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }

}
