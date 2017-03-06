package com.unicom.fmos.entity.sys;

import java.math.BigDecimal;
import java.util.Date;

public class ResourceMenu {
    private BigDecimal lineId;

    private String menuIcon;

    private String menuNumber;

    private BigDecimal sortId;

    private String menuName;

    private String path;

    private BigDecimal isParent;

    private BigDecimal parentLineId;

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

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public String getMenuNumber() {
        return menuNumber;
    }

    public void setMenuNumber(String menuNumber) {
        this.menuNumber = menuNumber == null ? null : menuNumber.trim();
    }

    public BigDecimal getSortId() {
        return sortId;
    }

    public void setSortId(BigDecimal sortId) {
        this.sortId = sortId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public BigDecimal getIsParent() {
        return isParent;
    }

    public void setIsParent(BigDecimal isParent) {
        this.isParent = isParent;
    }

    public BigDecimal getParentLineId() {
        return parentLineId;
    }

    public void setParentLineId(BigDecimal parentLineId) {
        this.parentLineId = parentLineId;
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