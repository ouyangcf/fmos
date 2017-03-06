package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.service.sys.RoleService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicom.fmos.dao.sys.RoleDao;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.sys.Role;
import com.unicom.fmos.utils.UtilFactory;

import java.util.*;

/**
 * Created by zhaojb on 2016/12/21.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    Environment env;

    @Override
    public String addRole(Role role) {
        try {
            roleDao.addRole(role);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String getRoleData() {
        List<Role> roles = roleDao.getRoleData();
        Role role = new Role();
        role.setRoleName("");
        //add one to let the row get an empty role
        roles.add(0,role);
        return UtilFactory.toJson(roles);
    }

    @Override
    public String getRoles() {
        return UtilFactory.toJson(roleDao.getRoles());
    }

    @Override
    @Transactional
    public String delRoles(List<Integer> idList, Role role) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("activeuser", role.getActiveUser());
        map.put("idList", idList);
        map.put("activememo", role.getActiveMemo());
        map.put("activetime", role.getActiveTime());
        try {
            roleDao.delRole(map);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String editRole(Role role) {
        try {
            Role targetRole = roleDao.selectById(role.getLineId());
            targetRole.setRoleName(role.getRoleName());
            targetRole.setUpdateUser(role.getUpdateUser());
            targetRole.setUpdateTime(role.getUpdateTime());
            targetRole.setUpdateMemo(role.getUpdateMemo());
            roleDao.editRole(targetRole);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String selectRole(Role role) {
        List<Role> roles = new ArrayList<>();
        try {
            roles = roleDao.selectRole(role);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        return UtilFactory.toJson(roles);
    }

    @Override
    public String selectRoleByPrivlAndCondition(Role role) {
        Map<String,Object> mapCondition = new HashMap<>();
        mapCondition.put("roleName",role.getRoleName());
        mapCondition.put("createTime",role.getCreateTime());
        mapCondition.put("activeTime",role.getActiveTime());
        mapCondition.put("menuNumber",env.getProperty("unicom.menuRootNumber"));
        List<Role> roleList = roleDao.selectRoleByPrivlAndCondition(mapCondition);
        return UtilFactory.toJson(roleList);
    }
}
