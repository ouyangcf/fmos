package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.Instance;

import java.util.List;
import java.util.Map;

public interface InstanceDao {
    List<Instance> infos();
    int insertSelective(Instance model);
    int updateByPrimaryKeySelective(Instance model);
    List<Instance> selectSelective(Instance model);

    List<Instance> selectByCondition(Instance instance);

    List<Instance> select(Instance instanceCondition);

    List<Instance> selectInstanceByModelLineId(Map<String, Object> map);

    List<Instance> infosbyid(Instance instanceCondition);

    List<Instance> selectByConditionWithUserId(Instance condition);
}