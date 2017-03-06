package com.unicom.fmos.dto;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/25.
 */
public class UserDirectionaryDto {
    private List<TypeHeadDto> userIdList;
    private List<TypeHeadDto> roleList;

    public UserDirectionaryDto() {
    }

    public UserDirectionaryDto(List<TypeHeadDto> userIdList, List<TypeHeadDto> roleList) {
        this.userIdList = userIdList;
        this.roleList = roleList;
    }

    public List<TypeHeadDto> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<TypeHeadDto> userIdList) {
        this.userIdList = userIdList;
    }

    public List<TypeHeadDto> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TypeHeadDto> roleList) {
        this.roleList = roleList;
    }
}
