package com.unicom.fmos.entity.business;

import java.math.BigDecimal;
import java.util.Date;

public class ChannelqualityPrivilege {
    private BigDecimal lineId;

    private BigDecimal roleLineId;

    private String nodeName;

    private BigDecimal checked;

    private BigDecimal checkedType;

    private BigDecimal layout;

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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public BigDecimal getChecked() {
        return checked;
    }

    public void setChecked(BigDecimal checked) {
        this.checked = checked;
    }

    public BigDecimal getCheckedType() {
        return checkedType;
    }

    public void setCheckedType(BigDecimal checkedType) {
        this.checkedType = checkedType;
    }

    public BigDecimal getLayout() {
        return layout;
    }

    public void setLayout(BigDecimal layout) {
        this.layout = layout;
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