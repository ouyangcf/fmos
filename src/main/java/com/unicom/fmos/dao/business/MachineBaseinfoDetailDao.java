package com.unicom.fmos.dao.business;

import com.unicom.fmos.dto.IdKeyValueDto;
import com.unicom.fmos.entity.business.Instance;
import com.unicom.fmos.entity.business.MachineBaseinfoDetail;

import java.util.List;

/**
 * Created by unicom on 2017/2/3.
 */
public interface MachineBaseinfoDetailDao {
    List<IdKeyValueDto> getmachineBaseinfoByInstanceLineId(Instance instance);

    List<IdKeyValueDto> getmachineFeatureByInstanceLineId(Instance instance);

    List<MachineBaseinfoDetail> select(MachineBaseinfoDetail condition);

    int insertSelective(MachineBaseinfoDetail basicinfo);

    int updateByPrimaryKeySelective(MachineBaseinfoDetail updateInfo);
}
