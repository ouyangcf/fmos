package com.unicom.fmos.service.sys;


import com.unicom.fmos.entity.sys.Role;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/21.
 */
public interface RoleService {
    String addRole(Role role);
    String getRoles();
    String getRoleData();
    String delRoles(List<Integer> idList, Role role);
    String editRole(Role role);
    String selectRole(Role role);
    String selectRoleByPrivlAndCondition(Role role);
}
