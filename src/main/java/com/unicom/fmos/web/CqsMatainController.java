package com.unicom.fmos.web;

import com.unicom.fmos.dto.ChannelQualityDto;
import com.unicom.fmos.entity.business.ChannelqualityPrivilege;
import com.unicom.fmos.entity.sys.Role;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.NewDepartService;
import com.unicom.fmos.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by unicom on 2017/1/17.
 */
@Controller
@RequestMapping("/user/businiess/cqsmatain")
public class CqsMatainController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private NewDepartService newDepartService;

    @RequestMapping(value = "/searchrole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestBody List<Role> roleList) {
        Role role = roleList.get(0);
        String result = roleService.selectRoleByPrivlAndCondition(role);
        return result;
    }

    @RequestMapping(value = "/channelquality", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
        public String channelqualityfirst(@RequestBody List<ChannelQualityDto> channelQualityDtoList) {
        ChannelQualityDto channelQualityDto = channelQualityDtoList.get(0);
        if (channelQualityDto.getLayout() == 0) {
            return newDepartService.selectFirstLayout();
        }
        if (channelQualityDto.getLayout() == 1) {
            return newDepartService.selectSecondLayout(channelQualityDto);
        }
        if (channelQualityDto.getLayout() == 2) {
            return newDepartService.selectThirdLayout(channelQualityDto);
        }
        return null;
    }

    @RequestMapping(value = "/cqspvlsave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String cqspvlsave(@RequestBody List<ChannelqualityPrivilege> channelqualityPrivilegeList, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        return newDepartService.updateCqsPrivilegeBatch(channelqualityPrivilegeList, userName);
    }
}
