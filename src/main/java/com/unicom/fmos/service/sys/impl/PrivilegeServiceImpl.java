package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.service.sys.PrivilegeService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicom.fmos.dao.sys.PrivilegeDao;
import com.unicom.fmos.dao.sys.ResourceMenuDao;
import com.unicom.fmos.dto.PrivilegeTreeNode;
import com.unicom.fmos.dto.ResourceTreeNode;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.sys.Privilege;
import com.unicom.fmos.utils.UtilFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhaojb on 2016/12/24.
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private ResourceMenuDao resourceMenuDao;

    @Override
    public String getPrivilegeTreeByRole(Privilege privilege) {
        PrivilegeTreeNode privilegeTreeNode = new PrivilegeTreeNode();
        Integer roleLineId = privilege.getRoleLineId().intValue();
        List<ResourceTreeNode> menuTree = resourceMenuDao.getTree();
        List<Privilege> privileges = privilegeDao.getPrivilegeByRoleId(roleLineId);
        privilegeTreeNode.setMenuTree(menuTree);
        privilegeTreeNode.setPrivilege(privileges);
        return UtilFactory.toJson(privilegeTreeNode);
    }

    @Override
    @Transactional
    public String savePrivilege(List<Privilege> privileges, String user) {
        try {
            for (Privilege privilege : privileges) {
                Privilege condition = new Privilege();
                condition.setRoleLineId(privilege.getRoleLineId());
                condition.setResourceLineId(privilege.getResourceLineId());
                List<Privilege> privilegeList = privilegeDao.selectPrivilegeByCondition(condition);
                if (privilegeList.size() == 0) {
                    Privilege newPrivilege = new Privilege();
                    newPrivilege.setRoleLineId(privilege.getRoleLineId());
                    newPrivilege.setResourceLineId(privilege.getResourceLineId());
                    newPrivilege.setChecked(privilege.getChecked());
                    newPrivilege.setCreateUser(user);
                    newPrivilege.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    privilegeDao.insertSelective(newPrivilege);
                } else {
                    Privilege oldPrivilege = privilegeList.get(0);
                    oldPrivilege.setChecked(privilege.getChecked());
                    oldPrivilege.setUpdateUser(user);
                    oldPrivilege.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    oldPrivilege.setUpdateMemo(user + "手动更新");
                    privilegeDao.updateByPrimaryKeySelective(oldPrivilege);
                }
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "保存失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "保存成功", null);
        return UtilFactory.toJson(goodResult);
    }
}
