package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.MachineBaseinfoHead;

import java.util.List;

/**
 * Created by unicom on 2017/2/3.
 */
public interface MachineBaseinfoHeadDao {
    int insertSelective(MachineBaseinfoHead machineBaseinfoHead);
    int updateByPrimaryKeySelective(MachineBaseinfoHead machineBaseinfoHead);
    List<MachineBaseinfoHead> select(MachineBaseinfoHead condition);
}
