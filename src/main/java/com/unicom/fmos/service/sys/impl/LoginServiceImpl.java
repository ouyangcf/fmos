package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.service.sys.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicom.fmos.dao.sys.UserDao;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.sys.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhaojb on 2016/12/6.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public Result checkUser(User user, HttpServletRequest request, HttpSession session) {
        List<User> users = userDao.selectByConfidition(user);
        if (users.size() == 0) {
            Result badResult = new Result(-1, "-1", "用户名或密码错误", null);
            return badResult;
        } else {
            Result goodResult = new Result(1, "1", "", null);
            session.invalidate();
            session = request.getSession();
            session.setAttribute("user", users.get(0));
            return goodResult;
        }
    }
}
