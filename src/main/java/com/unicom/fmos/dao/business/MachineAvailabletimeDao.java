package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.MachineAvailabletime;

import java.util.List;

public interface MachineAvailabletimeDao {
    List<MachineAvailabletime> select(MachineAvailabletime condition);
    int insertSelective(MachineAvailabletime condition);
    int updateByPrimaryKeySelective(MachineAvailabletime condition);
}