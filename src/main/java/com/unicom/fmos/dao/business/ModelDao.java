package com.unicom.fmos.dao.business;

import com.unicom.fmos.dto.DirectionaryDto;
import com.unicom.fmos.entity.business.Model;

import java.util.List;

public interface ModelDao {
    List<Model> infos();
    int insertSelective(Model model);
    int updateByPrimaryKeySelective(Model model);
    List<Model> selectSelective(Model model);

    List<DirectionaryDto> getModelModels();

    List<Model> selectSameModel(Model modelSameModelCondition);

    List<Model> selectSameGroup(Model modelSameModelCondition);

    List<Model> infosbyuserid(Model modelCondition);

    List<Model> selectSelectiveById(Model model);
}