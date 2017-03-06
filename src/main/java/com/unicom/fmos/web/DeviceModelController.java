package com.unicom.fmos.web;

import com.unicom.fmos.entity.business.MachineBaseinfoHead;
import com.unicom.fmos.entity.business.MachineBasicinfo;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.business.Model;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.DeviceModelService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by unicom on 2017/1/24.
 */
@Controller
@RequestMapping("/user/businiess/deviceModel")
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;

    @RequestMapping(value = "/infos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infos(HttpServletRequest request, HttpSession session) {
        return deviceModelService.infos();
    }

    @RequestMapping(value = "/infosbyuserid", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infosbyuserid(HttpServletRequest request, HttpSession session) {
        return deviceModelService.infosbyuserid();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody List<Model> models, HttpServletRequest request, HttpSession session) {
        return deviceModelService.add(models.get(0));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody List<Model> models, HttpServletRequest request, HttpSession session) {
        return deviceModelService.update(models.get(0));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestBody List<Model> models, HttpServletRequest request, HttpSession session) {
        return deviceModelService.search(models.get(0));
    }

    @RequestMapping(value = "/searchbyid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchbyid(@RequestBody List<Model> models, HttpServletRequest request, HttpSession session) {
        return deviceModelService.searchbyid(models.get(0));
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestParam(required = false, value = "idArr[]") List<Integer> list, HttpServletRequest request, HttpSession session) {
        return deviceModelService.del(list);
    }

    @RequestMapping(value = "/basicinfochecked", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String basicinfochecked(@RequestBody List<MachineBaseinfoHead> basicinfoList, HttpServletRequest request, HttpSession session) {
        return deviceModelService.basicinfochecked(basicinfoList);
    }

    @RequestMapping(value = "/featurechecked", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String featurechecked(@RequestBody List<MachineFeatureHead> basicinfoList, HttpServletRequest request, HttpSession session) {
        return deviceModelService.featurechecked(basicinfoList);
    }

    @RequestMapping(value = "/infossp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infossp(HttpServletRequest request, HttpSession session) {
        return deviceModelService.infossp();
    }
}
