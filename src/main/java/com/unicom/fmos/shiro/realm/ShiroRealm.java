package com.unicom.fmos.shiro.realm;

import com.unicom.fmos.dao.sys.RoleDao;
import com.unicom.fmos.dao.sys.UserDao;
import com.unicom.fmos.entity.sys.Role;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaojb on 2017/1/20.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        User condition = new User();
        condition.setUserId(userId);
        User user = userDao.selectUser(condition).get(0);
        Role role = roleDao.selectById(user.getRoleLineId().intValue());
        roles.add(role.getRoleName());
        Set<String> permissions = new HashSet<>();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userId = (String)authenticationToken.getPrincipal();
        User userCondition = new User();
        userCondition.setUserId(userId);
        List<User> userList = userService.getUserByCondition(userCondition);
        if (userList.size() == 0) {
            throw new UnknownAccountException();
        }
        User userResult = userList.get(0);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userResult.getUserId(), //用户名
                userResult.getUserPassword(),
                getName()  //realm name
        );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userResult.getUserId()+userResult.getSalt()));
        return authenticationInfo;
    }
}
