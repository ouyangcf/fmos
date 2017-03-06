package com.unicom.fmos.dto;

/**
 * Created by zhaojb on 2016/12/25.
 */
public class SearchDto {
    private String roleName;
    private String createTime;
    private String activeTime;

    public SearchDto() {
    }

    public SearchDto(String roleName, String createTime, String activeTime) {
        this.roleName = roleName;
        this.createTime = createTime;
        this.activeTime = activeTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }
}
