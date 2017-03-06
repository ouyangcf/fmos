package com.unicom.fmos.entity.sys;

import java.math.BigDecimal;
import java.util.Date;

public class Privilege {
    private BigDecimal lineId;

    private BigDecimal roleLineId;

    private BigDecimal resourceLineId;

    private BigDecimal checked;

    private String createUser;

    private Date createTime;

    private BigDecimal active;

    private String activeUser;

    private Date activeTime;

    private String activeMemo;

    private String updateUser;

    private Date updateTime;

    private String updateMemo;

    public BigDecimal getLineId() {
        return lineId;
    }

    public void setLineId(BigDecimal lineId) {
        this.lineId = lineId;
    }

    public BigDecimal getRoleLineId() {
        return roleLineId;
    }

    public void setRoleLineId(BigDecimal roleLineId) {
        this.roleLineId = roleLineId;
    }

    public BigDecimal getResourceLineId() {
        return resourceLineId;
    }

    public void setResourceLineId(BigDecimal resourceLineId) {
        this.resourceLineId = resourceLineId;
    }

    public BigDecimal getChecked() {
        return checked;
    }

    public void setChecked(BigDecimal checked) {
        this.checked = checked;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getActive() {
        return active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser == null ? null : activeUser.trim();
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getActiveMemo() {
        return activeMemo;
    }

    public void setActiveMemo(String activeMemo) {
        this.activeMemo = activeMemo == null ? null : activeMemo.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateMemo() {
        return updateMemo;
    }

    public void setUpdateMemo(String updateMemo) {
        this.updateMemo = updateMemo == null ? null : updateMemo.trim();
    }
}