package com.unicom.fmos.web;

import com.unicom.fmos.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.utils.UtilFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhaojb on 2016/12/6.
 */
@Controller
@RequestMapping("/user/log")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/in", method = RequestMethod.POST, produces ="application/json;charset=UTF-8")
    @ResponseBody
    public String login(User user, HttpServletRequest request, HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserId(), user.getUserPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                user.setUserPassword(null);
                User u = userService.getUserByCondition(user).get(0);
                u.setUserPassword("");
                System.out.println("登陆成功");
                subject.getSession().setAttribute("user", u);
                Result goodResult = new Result(1, "1", "", null);
                return UtilFactory.toJson(goodResult);
            } else {
                Result badResult = new Result(-1, "-1", "用户名或密码错误", null);
                return UtilFactory.toJson(badResult);
            }
        }
        catch (IncorrectCredentialsException e) {
            Result badResult = new Result(-1, "-1", "用户名或密码错误", null);
            return UtilFactory.toJson(badResult);
        }
        catch (UnknownAccountException e) {
            Result badResult = new Result(-1, "-1", "用户名或密码错误", null);
            return UtilFactory.toJson(badResult);
        }
    }
}
