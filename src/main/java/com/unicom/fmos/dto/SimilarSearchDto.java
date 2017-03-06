package com.unicom.fmos.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhaojb on 2017/2/5.
 */
public class SimilarSearchDto {

    private Integer instanceLineId;
    private Boolean sameModel;
    private Boolean sameGroup;
    private Integer compared;
    private BigDecimal threshold;
    private List<Integer> featureSeled;

    public SimilarSearchDto() {
    }

    public Integer getInstanceLineId() {
        return instanceLineId;
    }

    public void setInstanceLineId(Integer instanceLineId) {
        this.instanceLineId = instanceLineId;
    }

    public Boolean getSameModel() {
        return sameModel;
    }

    public void setSameModel(Boolean sameModel) {
        this.sameModel = sameModel;
    }

    public Boolean getSameGroup() {
        return sameGroup;
    }

    public void setSameGroup(Boolean sameGroup) {
        this.sameGroup = sameGroup;
    }

    public Integer getCompared() {
        return compared;
    }

    public void setCompared(Integer compared) {
        this.compared = compared;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public List<Integer> getFeatureSeled() {
        return featureSeled;
    }

    public void setFeatureSeled(List<Integer> featureSeled) {
        this.featureSeled = featureSeled;
    }

    public SimilarSearchDto(Integer instanceLineId, Boolean sameModel, Boolean sameGroup, Integer compared, BigDecimal threshold, List<Integer> featureSeled) {
        this.instanceLineId = instanceLineId;
        this.sameModel = sameModel;
        this.sameGroup = sameGroup;
        this.compared = compared;
        this.threshold = threshold;
        this.featureSeled = featureSeled;
    }
}
