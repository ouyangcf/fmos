package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.ChannelqualityPrivilegeDao;
import com.unicom.fmos.dao.business.NewDepartDao;
import com.unicom.fmos.dto.ChannelQualityDto;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.business.ChannelqualityPrivilege;
import com.unicom.fmos.service.sys.NewDepartService;
import com.unicom.fmos.utils.UtilFactory;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by unicom on 2017/1/17.
 */
@Service
public class NewDepartServiceImpl implements NewDepartService{

    @Autowired
    private NewDepartDao newDepartDao;
    @Autowired
    private ChannelqualityPrivilegeDao channelqualityPrivilegeDao;

    @Override
    public String selectFirstLayout() {
        List<ChannelQualityDto> channelQualityDtos = newDepartDao.selectFirstLayout();
        for (ChannelQualityDto channelQualityDto : channelQualityDtos) {
            ChannelqualityPrivilege channelqualityPrivilege = new ChannelqualityPrivilege();
            channelqualityPrivilege.setNodeName(channelQualityDto.getName());
            List<ChannelqualityPrivilege> channelqualityPrivileges = channelqualityPrivilegeDao.selectSelective(channelqualityPrivilege);
            if (channelqualityPrivileges.size() != 0) {
                BigDecimal zero = new BigDecimal(0);
                if (channelqualityPrivileges.get(0).getChecked().compareTo(zero) == 0) {
                    channelQualityDto.setChecked(false);
                } else {
                    channelQualityDto.setChecked(true);
                }
            }
        }
        return UtilFactory.toJson(channelQualityDtos);
    }

    @Override
    public String selectSecondLayout(ChannelQualityDto channelQualityDtoCondition) {
        List<ChannelQualityDto> channelQualityDtos = newDepartDao.selectSecondLayout(channelQualityDtoCondition);
        for (ChannelQualityDto channelQualityDto : channelQualityDtos) {
            ChannelqualityPrivilege channelqualityPrivilege = new ChannelqualityPrivilege();
            channelqualityPrivilege.setNodeName(channelQualityDto.getName());
            List<ChannelqualityPrivilege> channelqualityPrivileges = channelqualityPrivilegeDao.selectSelective(channelqualityPrivilege);
            if (channelqualityPrivileges.size() != 0) {
                BigDecimal zero = new BigDecimal(0);
                if (channelqualityPrivileges.get(0).getChecked().compareTo(zero) == 0) {
                    channelQualityDto.setChecked(false);
                } else {
                    channelQualityDto.setChecked(true);
                }
            }
        }
        return UtilFactory.toJson(channelQualityDtos);
    }

    @Override
    public String selectThirdLayout(ChannelQualityDto channelQualityDtoCondition) {
        List<ChannelQualityDto> channelQualityDtos = newDepartDao.selectThirdLayout(channelQualityDtoCondition);
        for (ChannelQualityDto channelQualityDto : channelQualityDtos) {
            ChannelqualityPrivilege channelqualityPrivilege = new ChannelqualityPrivilege();
            channelqualityPrivilege.setNodeName(channelQualityDto.getName());
            List<ChannelqualityPrivilege> channelqualityPrivileges = channelqualityPrivilegeDao.selectSelective(channelqualityPrivilege);
            if (channelqualityPrivileges.size() != 0) {
                BigDecimal zero = new BigDecimal(0);
                if (channelqualityPrivileges.get(0).getChecked().compareTo(zero) == 0) {
                    channelQualityDto.setChecked(false);
                } else {
                    channelQualityDto.setChecked(true);
                }
            }
        }
        return UtilFactory.toJson(channelQualityDtos);
    }

    @Override
    @Transactional
    public String updateCqsPrivilegeBatch(List<ChannelqualityPrivilege> channelqualityPrivilegeList, String username) {
        try{
            for (ChannelqualityPrivilege channelqualityPrivilege : channelqualityPrivilegeList) {
                ChannelqualityPrivilege check = new ChannelqualityPrivilege();
                check.setNodeName(channelqualityPrivilege.getNodeName());
                check.setActive(new BigDecimal(1));
                List<ChannelqualityPrivilege> channelqualityPrivileges = channelqualityPrivilegeDao.selectSelective(check);
                if (channelqualityPrivileges.size() == 0) {
                    channelqualityPrivilege.setCreateUser(username);
                    channelqualityPrivilege.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    channelqualityPrivilegeDao.insertSelective(channelqualityPrivilege);
                } else {
                    ChannelqualityPrivilege update = channelqualityPrivileges.get(0);
                    update.setUpdateUser(username);
                    update.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    update.setUpdateMemo(username + "手动更新");
                    update.setChecked(channelqualityPrivilege.getChecked());
                    update.setCheckedType(channelqualityPrivilege.getCheckedType());
                    update.setLayout(channelqualityPrivilege.getLayout());
                    channelqualityPrivilegeDao.updateSelective(update);
                }
            }
        }catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "保存失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "保存成功", null);
        return UtilFactory.toJson(goodResult);
    }
}
