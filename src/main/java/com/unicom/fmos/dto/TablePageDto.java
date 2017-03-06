package com.unicom.fmos.dto;

/**
 * Created by zhaojb on 2016/12/21.
 */
public class TablePageDto {
    private String sort;
    private String order;
    private Integer offset;
    private Integer limit;

    public TablePageDto() {
    }

    public TablePageDto(String sort, String order, Integer offset, Integer limit) {
        this.sort = sort;
        this.order = order;
        this.offset = offset;
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "TablePageDto{" +
                "sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
