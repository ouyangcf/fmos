package com.unicom.fmos.entity.sys;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private BigDecimal lineId;

    private BigDecimal roleLineId;

    private String userId;

    private String userName;

    private String userPassword;

    private String salt;

    private String createUser;

    private Date createTime;

    private BigDecimal active;

    private String activeUser;

    private Date activeTime;

    private String activeMemo;

    private String updateUser;

    private Date updateTime;

    private String updateMemo;

    private String areaCode;

    private String departCode;

    private String positionCode;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode == null ? null : departCode.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }


    public String getCredentialsSalt(){
        return userId + salt;
    }
}