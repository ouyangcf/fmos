package com.unicom.fmos.web;

import com.unicom.fmos.entity.business.*;
import com.unicom.fmos.service.sys.DeviceInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by unicom on 2017/1/24.
 */
@Controller
@RequestMapping("/user/business/deviceInstance")
public class DeviceInstanceController {

    @Autowired
    private DeviceInstanceService deviceInstanceService;

    @RequestMapping(value = "/infos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infos(HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.infos();
    }

    @RequestMapping(value = "/infosbyid", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infosbyid(HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.infosbyid();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody List<Instance> instances, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.add(instances.get(0));
    }

    @RequestMapping(value = "/infossp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infossp(HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.infossp();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody List<Instance> instances, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.update(instances.get(0));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestBody List<Instance> instances, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.search(instances.get(0));
    }

    @RequestMapping(value = "/searchbyid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchbyid(@RequestBody List<Instance> instances, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.searchbyid(instances.get(0));
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestParam(required = false, value = "idArr[]") List<Integer> list, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.del(list);
    }

    @RequestMapping(value = "/models", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String models() {
        return deviceInstanceService.models();
    }

    @RequestMapping(value = "/modellineids", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modellineids() {
        return deviceInstanceService.modellineids();
    }

    @RequestMapping(value = "/instanceanme", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String instanceanme() {
        return deviceInstanceService.instanceanme();
    }

    @RequestMapping(value = "/instancenumber", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String instancenumber() {
        return deviceInstanceService.instancenumber();
    }

    @RequestMapping(value = "/availabletime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String availabletime(@RequestBody List<MachineAvailabletime> availabletimes, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.availabletime(availabletimes.get(0));
    }

    @RequestMapping(value = "/availableupdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String availableupdate(@RequestBody List<MachineAvailabletime> availabletimes, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.availableupdate(availabletimes.get(0));
    }

    @RequestMapping(value = "/baseinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String baseinfo(@RequestBody List<Instance> instances, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.baseinfo(instances.get(0));
    }

    @RequestMapping(value = "/feature", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String feature(@RequestBody List<Instance> instances, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.feature(instances.get(0));
    }

    @RequestMapping(value = "/baseinfoupdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String baseinfoupdate(@RequestBody List<MachineBaseinfoDetail> machineBaseinfoDetails, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.baseinfoupdate(machineBaseinfoDetails);
    }

    @RequestMapping(value = "/featureupdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String featureupdate(@RequestBody List<MachineFeatureDetail> machineBaseinfoDetails, HttpServletRequest request, HttpSession session) {
        return deviceInstanceService.featureupdate(machineBaseinfoDetails);
    }
}
