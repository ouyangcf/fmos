package com.unicom.fmos.service.sys;

import com.unicom.fmos.entity.sys.User;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/25.
 */
public interface UserService {
    String getuserIdDirectionary();
    String getRoleDirectionary();
    String getUsers();
    String addUser(User user);
    String update(User user);
    String delUsers(List<Integer> idList, User user);
    String selectRole(User user);
    List<User> getUserByCondition(User user);
}
