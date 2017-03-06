package com.unicom.fmos.dao.sys;


import com.unicom.fmos.dto.TypeHeadDto;
import com.unicom.fmos.entity.sys.User;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2016/12/6.
 */
public interface UserDao{
    List<User> selectByConfidition(User entity);
    List<TypeHeadDto> getUserIdKey();
    List<TypeHeadDto> getRoleDirectionary();
    List<User> selectAll();
    int add(User user);
    int update(User user);
    int delRole(Map<String, Object> map);
    List<User> selectUser(User user);
}
