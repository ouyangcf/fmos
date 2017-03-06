package com.unicom.fmos.service.sys;

import com.unicom.fmos.entity.business.*;

import java.util.List;

/**
 * Created by unicom on 2017/1/24.
 */
public interface DeviceInstanceService {
    String infos();

    String add(Instance instance);

    String update(Instance instance);

    String del(List<Integer> list);

    String search(Instance instance);

    String models();

    String modellineids();

    String instanceanme();

    String instancenumber();

    String availabletime(MachineAvailabletime machineAvailabletime);

    String availableupdate(MachineAvailabletime machineAvailabletime);

    String baseinfo(Instance instance);

    String feature(Instance instance);

    String baseinfoupdate(List<MachineBaseinfoDetail> machineBaseinfoDetails);

    String featureupdate(List<MachineFeatureDetail> machineBaseinfoDetails);

    String infossp();

    String infosbyid();

    String searchbyid(Instance instance);
}
