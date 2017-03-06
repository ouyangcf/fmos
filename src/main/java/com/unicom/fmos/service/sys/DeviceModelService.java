package com.unicom.fmos.service.sys;

import com.unicom.fmos.entity.business.MachineBaseinfoHead;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.business.Model;

import java.util.List;

/**
 * Created by unicom on 2017/1/24.
 */
public interface DeviceModelService {
    String infos();

    String add(Model model);

    String update(Model model);

    String del(List<Integer> list);

    String search(Model model);

    String featurechecked(List<MachineFeatureHead> list);

    String basicinfochecked(List<MachineBaseinfoHead> list);

    String infossp();

    String infosbyuserid();

    String searchbyid(Model model);
}
