package com.unicom.fmos.service.sys;


import com.unicom.fmos.entity.sys.Privilege;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/24.
 */
public interface PrivilegeService {
    String getPrivilegeTreeByRole(Privilege privilege);
    String savePrivilege(List<Privilege> privileges, String user);
}
