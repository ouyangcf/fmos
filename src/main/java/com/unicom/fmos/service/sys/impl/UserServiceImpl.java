package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.service.sys.UserService;
import com.unicom.fmos.shiro.realm.ShiroRealm;
import com.unicom.fmos.utils.PasswordHelper;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicom.fmos.dao.sys.UserDao;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.dto.TypeHeadDto;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.utils.UtilFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2016/12/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String getuserIdDirectionary() {
        List<TypeHeadDto> userIdList = userDao.getUserIdKey();
        return UtilFactory.toJson(userIdList);
    }

    @Override
    public String getUsers() {
        List<User> users = userDao.selectAll();
        User emptyUser = new User();
        emptyUser.setUserId("");
        emptyUser.setUserPassword("");
        emptyUser.setUserName("");
        emptyUser.setAreaCode("");
        emptyUser.setDepartCode("");
        emptyUser.setPositionCode("");
        emptyUser.setRoleLineId(null);
        users.add(0,emptyUser);
        return UtilFactory.toJson(users);
    }

    @Override
    public String getRoleDirectionary() {
        return UtilFactory.toJson(userDao.getRoleDirectionary());
    }

    @Override
    public String addUser(User user) {
        try {
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userDao.add(user);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String update(User user) {
        try {
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userDao.update(user);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    @Transactional
    public String delUsers(List<Integer> idList, User user) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("activeuser", user.getActiveUser());
        map.put("idList", idList);
        map.put("activememo", user.getActiveMemo());
        map.put("activetime", user.getActiveTime());
        try {
            userDao.delRole(map);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String selectRole(User user) {
        List<User> users = new ArrayList<>();
        try {
            users = userDao.selectUser(user);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        return UtilFactory.toJson(users);
    }

    @Override
    public List<User> getUserByCondition(User user) {
        List<User> users = userDao.selectByConfidition(user);
        return users;
    }
}
