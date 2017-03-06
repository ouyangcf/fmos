package com.unicom.fmos.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by unicom on 2017/1/13.
 */
public class InsertUpdateDto {

    private BigDecimal roleId;
    private BigDecimal resourceId;
    private BigDecimal isChecked;
    private String createMan;
    private Date createTime;

    public InsertUpdateDto() {
    }

    public InsertUpdateDto(BigDecimal roleId, BigDecimal resourceId, BigDecimal isChecked, String createMan, Date createTime) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.isChecked = isChecked;
        this.createMan = createMan;
        this.createTime = createTime;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigDecimal resourceId) {
        this.resourceId = resourceId;
    }

    public BigDecimal getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(BigDecimal isChecked) {
        this.isChecked = isChecked;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
