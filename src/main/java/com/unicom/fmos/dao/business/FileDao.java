package com.unicom.fmos.dao.business;

import com.unicom.fmos.entity.business.File;

import java.util.List;
import java.util.Map;

/**
 * Created by unicom on 2017/2/8.
 */
public interface FileDao {
    int insertSelective(File file);
    int updateByPrimaryKeySelective(File file);
    List<File> select(File file);
    int del(Map<String, Object> map);
    List<File> selectByPrimaryKey(Integer lineId);
    List<File> selectWithBLOBs(File file);
}
