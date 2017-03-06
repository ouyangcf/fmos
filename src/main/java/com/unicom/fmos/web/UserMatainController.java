package com.unicom.fmos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.unicom.fmos.dto.SearchDto;
import com.unicom.fmos.entity.sys.Privilege;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.PrivilegeService;
import com.unicom.fmos.service.sys.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhaojb on 2016/12/25.
 */
@Controller
@RequestMapping(value = "/user/businiess/user")
public class UserMatainController {

    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(value = "/userIdDirectionary", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String userDirectionary() {
        return userService.getuserIdDirectionary();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/roleDirectionary", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getRoleDirectionary() {
        return userService.getRoleDirectionary();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUser(@RequestBody List<User> users, HttpServletRequest request, HttpSession session) {
        User user = users.get(0);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        User loginUser = (User) session.getAttribute("user");
        user.setCreateUser(loginUser.getUserName());
        return userService.addUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody List<User> users, HttpServletRequest request, HttpSession session) {
        User user = users.get(0);
        User loginUser = (User) session.getAttribute("user");
        user.setUpdateUser(loginUser.getUserName());
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        user.setUpdateMemo(loginUser.getUserName() + "手动更新");
        return userService.update(user);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestParam(required = false, value = "idArr[]") List<Integer> list, HttpServletRequest request, HttpSession session) {
        User user = new User();
        user.setActive(new BigDecimal(0));
        user.setActiveTime(new Timestamp(System.currentTimeMillis()));
        User loginUser = (User) session.getAttribute("user");
        user.setActiveUser(loginUser.getUserName());
        user.setActiveMemo(loginUser.getUserName() + "手动删除");
        return userService.delUsers(list,user);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchUser(@RequestBody List<SearchDto> searchDtos) {
        User user = new User();
        SearchDto searchDto = searchDtos.get(0);
        if (searchDto.getRoleName() != null) {
            user.setUserId(searchDto.getRoleName());
        }
        if (searchDto.getCreateTime() != null) {
            user.setCreateTime(Timestamp.valueOf(searchDto.getCreateTime()));
        }
        if (searchDto.getActiveTime() != null) {
            user.setActiveTime(Timestamp.valueOf(searchDto.getActiveTime()));
        }
        String result = userService.selectRole(user);
        return result;
    }

    @RequestMapping(value = "/prvltree", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPrivilegeOfRole(User user) {
        List<User> users = userService.getUserByCondition(user);
        Privilege privilege = new Privilege();
        privilege.setRoleLineId(users.get(0).getRoleLineId());
        String result = privilegeService.getPrivilegeTreeByRole(privilege);
        System.out.println(result);
        return result;
    }

}
