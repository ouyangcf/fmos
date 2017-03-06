package com.unicom.fmos.entity.business;

public class NewDepart {
    private String departId;

    private String departName;

    private String newUpperChannel;

    private String newAreaName;

    private String newBasicCell;

    private String cucChnlCode;

    private String memo;

    private String newTeamName;

    private String remark;

    private String newUpperChannelCode;

    private String newAreaCode;

    private String newBasicCellCode;

    private String newTeamCode;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName == null ? null : departName.trim();
    }

    public String getNewUpperChannel() {
        return newUpperChannel;
    }

    public void setNewUpperChannel(String newUpperChannel) {
        this.newUpperChannel = newUpperChannel == null ? null : newUpperChannel.trim();
    }

    public String getNewAreaName() {
        return newAreaName;
    }

    public void setNewAreaName(String newAreaName) {
        this.newAreaName = newAreaName == null ? null : newAreaName.trim();
    }

    public String getNewBasicCell() {
        return newBasicCell;
    }

    public void setNewBasicCell(String newBasicCell) {
        this.newBasicCell = newBasicCell == null ? null : newBasicCell.trim();
    }

    public String getCucChnlCode() {
        return cucChnlCode;
    }

    public void setCucChnlCode(String cucChnlCode) {
        this.cucChnlCode = cucChnlCode == null ? null : cucChnlCode.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getNewTeamName() {
        return newTeamName;
    }

    public void setNewTeamName(String newTeamName) {
        this.newTeamName = newTeamName == null ? null : newTeamName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getNewUpperChannelCode() {
        return newUpperChannelCode;
    }

    public void setNewUpperChannelCode(String newUpperChannelCode) {
        this.newUpperChannelCode = newUpperChannelCode == null ? null : newUpperChannelCode.trim();
    }

    public String getNewAreaCode() {
        return newAreaCode;
    }

    public void setNewAreaCode(String newAreaCode) {
        this.newAreaCode = newAreaCode == null ? null : newAreaCode.trim();
    }

    public String getNewBasicCellCode() {
        return newBasicCellCode;
    }

    public void setNewBasicCellCode(String newBasicCellCode) {
        this.newBasicCellCode = newBasicCellCode == null ? null : newBasicCellCode.trim();
    }

    public String getNewTeamCode() {
        return newTeamCode;
    }

    public void setNewTeamCode(String newTeamCode) {
        this.newTeamCode = newTeamCode == null ? null : newTeamCode.trim();
    }
}