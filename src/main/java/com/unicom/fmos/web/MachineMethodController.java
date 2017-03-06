package com.unicom.fmos.web;

import com.unicom.fmos.entity.business.MachineMethod;
import com.unicom.fmos.service.sys.MachineMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhaojb on 2017/1/29.
 */
@Controller
@RequestMapping("/user/businiess/machinemethod")
public class MachineMethodController {

    @Autowired
    private MachineMethodService machineMethodService;

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody List<MachineMethod> methods, HttpServletRequest request, HttpSession session) {
        return machineMethodService.updateMethod(methods);
    }
}
