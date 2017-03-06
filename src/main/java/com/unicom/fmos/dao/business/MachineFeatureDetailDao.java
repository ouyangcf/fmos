package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.MachineFeatureDetail;

import java.util.List;

/**
 * Created by unicom on 2017/2/3.
 */
public interface MachineFeatureDetailDao {
    List<MachineFeatureDetail> select(MachineFeatureDetail condition);

    int insertSelective(MachineFeatureDetail basicinfo);

    int updateByPrimaryKeySelective(MachineFeatureDetail updateInfo);
}
