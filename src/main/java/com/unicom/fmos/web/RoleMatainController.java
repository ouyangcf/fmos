package com.unicom.fmos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.unicom.fmos.dto.SearchDto;
import com.unicom.fmos.entity.sys.Role;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhaojb on 2016/12/21.
 */
@Controller
@RequestMapping(value = "/user/businiess/rolematain")
public class RoleMatainController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add_role", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addRole(@RequestBody Role role, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        role.setCreateUser(user.getUserName());
        role.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String editRole(@RequestBody Role role, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        role.setUpdateUser(user.getUserName());
        role.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        role.setUpdateMemo(user.getUserName() + "手动更新");
        return roleService.editRole(role);
    }

    @RequestMapping(value = "/getRoles", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getRoles() {
        return roleService.getRoles();
    }

    @RequestMapping(value = "/getRoleData", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getRoleData() {
        return roleService.getRoleData();
    }

    @RequestMapping(value = "/delRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestParam(required = false, value = "idArr[]") List<Integer> list,HttpServletRequest request, HttpSession session) {
        Role role = new Role();
        role.setActive(0);
        role.setActiveTime(new Timestamp(System.currentTimeMillis()));
        User user = (User) session.getAttribute("user");
        role.setActiveUser(user.getUserName());
        role.setActiveMemo(user.getUserName() + "手动删除");
        return roleService.delRoles(list,role);
    }

    @RequestMapping(value = "/searchRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestBody List<SearchDto> searchDtos) {
        Role role = new Role();
        SearchDto searchDto = searchDtos.get(0);
        if (searchDto.getRoleName() != null) {
            role.setRoleName(searchDto.getRoleName());
        }
        if (searchDto.getCreateTime() != null) {
            role.setCreateTime(Timestamp.valueOf(searchDto.getCreateTime()));
        }
        if (searchDto.getActiveTime() != null) {
            role.setActiveTime(Timestamp.valueOf(searchDto.getActiveTime()));
        }
        String result = roleService.selectRole(role);
        return result;
    }
}
