package com.unicom.fmos.entity.sys;

import java.sql.Timestamp;

/**
 * Created by zhaojb on 2017/1/7.
 */
public class DirectionaryHead {
    private Integer lineId;
    private String directionaryGroup;
    private String createUser;
    private Timestamp createTime;
    private Integer active;
    private String activeUser;
    private Timestamp activeTime;
    private String activeMemo;
    private String updateUser;
    private Timestamp updateTime;
    private String updateMemo;

    public DirectionaryHead() {
    }

    public DirectionaryHead(Integer lineId, String directionaryGroup, String createUser, Timestamp createTime, Integer active, String activeUser, Timestamp activeTime, String activeMemo, String updateUser, Timestamp updateTime, String updateMemo) {
        this.lineId = lineId;
        this.directionaryGroup = directionaryGroup;
        this.createUser = createUser;
        this.createTime = createTime;
        this.active = active;
        this.activeUser = activeUser;
        this.activeTime = activeTime;
        this.activeMemo = activeMemo;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.updateMemo = updateMemo;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getDirectionaryGroup() {
        return directionaryGroup;
    }

    public void setDirectionaryGroup(String directionaryGroup) {
        this.directionaryGroup = directionaryGroup;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public String getActiveMemo() {
        return activeMemo;
    }

    public void setActiveMemo(String activeMemo) {
        this.activeMemo = activeMemo;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateMemo() {
        return updateMemo;
    }

    public void setUpdateMemo(String updateMemo) {
        this.updateMemo = updateMemo;
    }
}
