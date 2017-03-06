package com.unicom.fmos.dto;

/**
 * Created by unicom on 2017/1/17.
 */
public class ChannelQualityDto {
    private String name;
    private Integer layout;
    private Boolean isParent;
    private Integer checkType;
    private Boolean checked;

    public ChannelQualityDto(String name, Integer layout, Boolean isParent, Integer checkType, Boolean checked) {
        this.name = name;
        this.layout = layout;
        this.isParent = isParent;
        this.checkType = checkType;
        this.checked = checked;
    }

    public ChannelQualityDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLayout() {
        return layout;
    }

    public void setLayout(Integer layout) {
        this.layout = layout;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
