package com.unicom.fmos.web;

import com.unicom.fmos.entity.business.MachineBaseinfoHead;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.business.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.unicom.fmos.entity.sys.*;
import com.unicom.fmos.service.sys.DirectionaryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhaojb on 2017/1/7.
 */
@Controller
@RequestMapping(value = "/user/businiess/directionary")
public class DirectionaryController {

    @Autowired
    private DirectionaryService directionaryService;

    @RequestMapping(value = "/keysAndValues", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getKeys(@RequestBody DirectionaryDetail detail, HttpServletRequest request, HttpSession session) {
        return directionaryService.getKeysAndValues(detail);
    }

    @RequestMapping(value = "/heads", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getHeads() {
        return directionaryService.getHeads();
    }

    @RequestMapping(value = "/adddetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addDetail(@RequestBody DirectionaryDetail detail, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        detail.setCreateUser(user.getUserName());
        detail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return directionaryService.addDirectionaryDetail(detail);
    }

    @RequestMapping(value = "/addhead", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addHead(@RequestBody DirectionaryHead head, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        head.setCreateUser(user.getUserName());
        head.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return directionaryService.addDirectionaryHead(head);
    }

    @RequestMapping(value = "/editdetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String editDetail(@RequestBody DirectionaryDetail detail, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        detail.setUpdateUser(user.getUserName());
        detail.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        detail.setUpdateMemo(user.getUserName() + "手动更新");
        return directionaryService.updateDirectionaryDetail(detail);
    }

    @RequestMapping(value = "/edithead", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String edithead(@RequestBody DirectionaryHead head, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        head.setUpdateUser(user.getUserName());
        head.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        head.setUpdateMemo(user.getUserName() + "手动更新");
        return directionaryService.updateDirectionaryHead(head);
    }

    @RequestMapping(value = "/getdetails", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getdetails(@RequestBody DirectionaryDetail detail, HttpServletRequest request, HttpSession session) {
        return directionaryService.getDetailsByCondition(detail);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestBody DirectionaryDetail detail, HttpServletRequest request, HttpSession session) {
        DirectionaryDetail condition = new DirectionaryDetail();
        if (detail.getDirectionaryKey() != null) {
            condition.setDirectionaryKey(detail.getDirectionaryKey());
        }
        if (detail.getDirectionaryValue() != null) {
            condition.setDirectionaryValue(detail.getDirectionaryValue());
        }
        if (detail.getCreateTime() != null) {
            condition.setCreateTime(detail.getCreateTime());
        }
        if (detail.getActiveTime() != null) {
            condition.setActiveTime(detail.getActiveTime());
        }
        return directionaryService.searchDirecitonaryDetail(condition);
    }

    @RequestMapping(value = "/deldetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestParam(required = false, value = "idArr[]") List<Integer> list, HttpServletRequest request, HttpSession session) {
        DirectionaryDetail detail = new DirectionaryDetail();
        detail.setActive(0);
        detail.setActiveTime(new Timestamp(System.currentTimeMillis()));
        User user = (User) session.getAttribute("user");
        detail.setActiveUser(user.getUserName());
        detail.setActiveMemo(user.getUserName() + "手动删除");
        return directionaryService.delDetails(list,detail);
    }

    @RequestMapping(value = "/delhead", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delHead(@RequestBody DirectionaryHead directionaryHead, HttpServletRequest request, HttpSession session) {
        directionaryHead.setActive(0);
        directionaryHead.setActiveTime(new Timestamp(System.currentTimeMillis()));
        User user = (User) session.getAttribute("user");
        directionaryHead.setActiveUser(user.getUserName());
        directionaryHead.setActiveMemo(user.getUserName() + "手动删除");
        return directionaryService.delHead(directionaryHead);
    }

    @RequestMapping(value = "/machinetype", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String machinetype(HttpServletRequest request, HttpSession session) {
        return directionaryService.machinetypes();
    }

    @RequestMapping(value = "/machinename", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String machinename(HttpServletRequest request, HttpSession session) {
        return directionaryService.machinename();
    }

    @RequestMapping(value = "/machinemodel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String machinemodel(HttpServletRequest request, HttpSession session) {
        return directionaryService.machinemodel();
    }

    @RequestMapping(value = "/machinenumber", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String machinenumber(HttpServletRequest request, HttpSession session) {
        return directionaryService.machinenumber();
    }

    @RequestMapping(value = "/machinegroup", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String machinegroup(HttpServletRequest request, HttpSession session) {
        return directionaryService.machinegroup();
    }

    @RequestMapping(value = "/codemachinetype", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String codemachinetype(HttpServletRequest request, HttpSession session) {
        return directionaryService.codemachinetype();
    }

    @RequestMapping(value = "/controltype", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String controltype(HttpServletRequest request, HttpSession session) {
        return directionaryService.controltype();
    }

    @RequestMapping(value = "/preciselevel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String preciselevel(HttpServletRequest request, HttpSession session) {
        return directionaryService.preciselevel();
    }

    @RequestMapping(value = "/mainpower", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String mainpower(HttpServletRequest request, HttpSession session) {
        return directionaryService.mainpower();
    }

    @RequestMapping(value = "/mainvel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String mainvel(HttpServletRequest request, HttpSession session) {
        return directionaryService.mainvel();
    }

    @RequestMapping(value = "/hasKnife", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String hasKnife(HttpServletRequest request, HttpSession session) {
        return directionaryService.hasKnife();
    }

    @RequestMapping(value = "/hasnumber", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String hasnumber(HttpServletRequest request, HttpSession session) {
        return directionaryService.hasnumber();
    }

    @RequestMapping(value = "/deviceSource", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deviceSource(HttpServletRequest request, HttpSession session) {
        return directionaryService.deviceSource();
    }


    @RequestMapping(value = "/managerlevel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String managerlevel(HttpServletRequest request, HttpSession session) {
        return directionaryService.managerlevel();
    }

    @RequestMapping(value = "/methodById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String methodById(@RequestBody List<Model> models, HttpServletRequest request, HttpSession session) {
        return directionaryService.methodById(models.get(0).getLineId());
    }

    @RequestMapping(value = "/baseinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String baseinfo(@RequestBody List<MachineBaseinfoHead> baseinfoHeads, HttpServletRequest request, HttpSession session) {
        return directionaryService.baseinfo(baseinfoHeads);
    }

    @RequestMapping(value = "/feature", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String feature(@RequestBody List<MachineFeatureHead> featureHeads, HttpServletRequest request, HttpSession session) {
        return directionaryService.feature(featureHeads);
    }
}
