package com.unicom.fmos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.unicom.fmos.entity.sys.Privilege;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.PrivilegeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by zhaojb on 2016/12/23.
 */
@Controller
@RequestMapping(value = "/user/businiess/privilege")
public class PrivilegeMatainController {

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(value = "/prvltree", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPrivilegeOfRole(Privilege privilege) {
        String prvlTree = privilegeService.getPrivilegeTreeByRole(privilege);
        System.out.println(prvlTree);
        return prvlTree;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String privilegeSave(@RequestBody List<Privilege> privileges, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return privilegeService.savePrivilege(privileges, user.getUserName());
    }

}
