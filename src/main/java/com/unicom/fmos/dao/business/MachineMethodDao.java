package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.MachineMethod;

import java.util.List;

public interface MachineMethodDao {
    int insert(MachineMethod method);
    int update(MachineMethod method);
    List<MachineMethod> select(MachineMethod method);
}