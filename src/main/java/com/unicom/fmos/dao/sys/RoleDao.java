package com.unicom.fmos.dao.sys;

import com.unicom.fmos.dto.RoleDto;
import com.unicom.fmos.entity.sys.ResourceMenu;
import com.unicom.fmos.entity.sys.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2016/12/21.
 */
public interface RoleDao {
    int addRole(Role role);
    List<RoleDto> getRoles();
    List<Role> getRoleData();
    int delRole(Map<String, Object> map);
    int editRole(Role role);
    List<Role> selectRole(Role role);
    Role selectById(Integer lineId);
    List<Role> selectRoleByPrivl(ResourceMenu resourceMenu);
    List<Role> selectRoleByPrivlAndCondition(Map<String, Object> map);
}
