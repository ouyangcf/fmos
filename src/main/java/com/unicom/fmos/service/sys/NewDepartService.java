package com.unicom.fmos.service.sys;

import com.unicom.fmos.dto.ChannelQualityDto;
import com.unicom.fmos.entity.business.ChannelqualityPrivilege;

import java.util.List;

/**
 * Created by unicom on 2017/1/17.
 */
public interface NewDepartService {
    String selectFirstLayout();
    String selectSecondLayout(ChannelQualityDto channelQualityDto);
    String selectThirdLayout(ChannelQualityDto channelQualityDto);
    String updateCqsPrivilegeBatch(List<ChannelqualityPrivilege> channelqualityPrivilegeList, String username);
}
