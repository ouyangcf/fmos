package com.unicom.fmos.dao.business; 

import com.unicom.fmos.entity.business.BiddingSuituation;

public interface BiddingSuituationDao {

    int insertSelective(BiddingSuituation record);

    int updateByPrimaryKeySelective(BiddingSuituation record);

}