package com.unicom.fmos.dao.business;

import com.unicom.fmos.dto.DirectionaryDto;
import com.unicom.fmos.entity.business.Instance;
import com.unicom.fmos.entity.business.MachineFeatureHead;

import java.util.List;

/**
 * Created by unicom on 2017/2/3.
 */
public interface MachineFeatureHeadDao {
    int insertSelective(MachineFeatureHead machineFeatureHead);
    int updateByPrimaryKeySelective(MachineFeatureHead machineFeatureHead);
    List<MachineFeatureHead> select(MachineFeatureHead condition);

    List<DirectionaryDto> getModelFeature(Instance instance);
}
