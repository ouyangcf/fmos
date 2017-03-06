package com.unicom.fmos.dao.sys;

import com.unicom.fmos.dto.DirectionaryDto;
import com.unicom.fmos.dto.TypeHeadDto;
import com.unicom.fmos.entity.business.Model;
import com.unicom.fmos.entity.sys.DirectionaryDetail;
import com.unicom.fmos.entity.sys.DirectionaryHead;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2017/1/7.
 */
public interface DirectionaryDao {
    List<DirectionaryDetail> selectAllDirectionaryDetail();
    List<DirectionaryHead> selectAllDirectionaryHead();
    int insertDirectionaryDetail(DirectionaryDetail directionaryDetail);
    int insertDirectionaryHead(DirectionaryHead directionaryHead);
    int updateDirectionaryDetail(DirectionaryDetail directionaryDetail);
    List<DirectionaryDetail> selectDirectionaryDetailByCondition(DirectionaryDetail directionaryDetail);
    List<DirectionaryDetail> selectDirectionaryDetail(DirectionaryDetail directionaryDetail);
    int delDirectionaryDetail(DirectionaryDetail directionaryDetail);
    int delDirectionaryHead(DirectionaryHead directionaryHead);
    int updateDirectionaryHead(DirectionaryHead directionaryHead);
    int delDetail(Map<String, Object> map);
    List<DirectionaryDetail> searchDirectionaryDetail(DirectionaryDetail directionaryDetail);

    List<DirectionaryDto> getDirectionaryDetailByDirectionaryHead(DirectionaryHead head);
    List<DirectionaryDto> getmachinename();
    List<DirectionaryDto> getmachinemodel();
    List<DirectionaryDto> getmachinenumber();
    List<DirectionaryDto> getmachinegroup();
    List<DirectionaryDto> getcodemachinetype();
    List<DirectionaryDto> getcodemainparams();

    List<DirectionaryDto> getmanagerlevel();

    List<DirectionaryDto> getdeviceSource();

    List<DirectionaryDto> gethasnumber();

    List<DirectionaryDto> gethasKnife();

    List<DirectionaryDto> getmainvel();

    List<DirectionaryDto> getmainpower();

    List<DirectionaryDto> getpreciselevel();

    List<DirectionaryDto> getcontroltype();

    List<DirectionaryDto> getmethod();

    List<DirectionaryDto> getMethodByLineId(Model model);

    List<DirectionaryDto> modellineids();

    List<DirectionaryDto> instanceanme();

    List<DirectionaryDto> instancenumber();

    List<DirectionaryDto> feature();

    List<DirectionaryDto> baseinfo();
}
