package com.unicom.fmos.dao.sys;


import com.unicom.fmos.dto.InsertUpdateDto;
import com.unicom.fmos.entity.sys.Privilege;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2016/12/23.
 */
public interface PrivilegeDao {
    List<Privilege> getPrivilegeByRoleId(Integer lineId);
    void insertOrUpdate(InsertUpdateDto insertUpdateDto);
    int delPrivilege(Map<String, Object> map);
    List<Privilege> selectPrivilegeByCondition(Privilege privilege);
    int insertSelective(Privilege privilege);
    int updateByPrimaryKeySelective(Privilege privilege);
}
