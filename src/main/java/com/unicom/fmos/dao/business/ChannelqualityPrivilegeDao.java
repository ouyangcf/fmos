package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.ChannelqualityPrivilege;

import java.util.List;

/**
 * Created by unicom on 2017/1/17.
 */
public interface ChannelqualityPrivilegeDao {
    int insert(ChannelqualityPrivilege record);
    int insertSelective(ChannelqualityPrivilege record);
    List<ChannelqualityPrivilege> selectSelective(ChannelqualityPrivilege record);
    int updateSelective(ChannelqualityPrivilege record);
}
