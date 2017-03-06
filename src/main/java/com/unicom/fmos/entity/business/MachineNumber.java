package com.unicom.fmos.entity.business;

import java.util.Date;

public class MachineNumber {
    private Integer lineId;

    private Integer modelLineId;

    private Integer position;

    private String codeProperty;

    private String codeValue;

    private String createUser;

    private Date createTime;

    private Boolean active;

    private String activeUser;

    private Date activeTime;

    private String activeMemo;

    private String updateUser;

    private Date updateTime;

    private String updateMemo;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getModelLineId() {
        return modelLineId;
    }

    public void setModelLineId(Integer modelLineId) {
        this.modelLineId = modelLineId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getCodeProperty() {
        return codeProperty;
    }

    public void setCodeProperty(String codeProperty) {
        this.codeProperty = codeProperty == null ? null : codeProperty.trim();
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue == null ? null : codeValue.trim();
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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