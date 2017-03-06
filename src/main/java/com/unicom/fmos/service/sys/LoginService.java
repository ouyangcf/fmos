package com.unicom.fmos.service.sys;

import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.sys.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhaojb on 2016/12/6.
 */
public interface LoginService {
    Result checkUser(User user, HttpServletRequest request, HttpSession session);
}
