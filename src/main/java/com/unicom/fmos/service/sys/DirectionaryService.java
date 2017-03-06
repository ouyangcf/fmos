package com.unicom.fmos.service.sys;



import com.unicom.fmos.entity.business.MachineBaseinfoHead;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.sys.DirectionaryDetail;
import com.unicom.fmos.entity.sys.DirectionaryHead;

import java.util.List;

/**
 * Created by zhaojb on 2017/1/7.
 */
public interface DirectionaryService {
    String getKeysAndValues(DirectionaryDetail directionaryDetail);
    String getHeads();
    String addDirectionaryDetail(DirectionaryDetail directionaryDetail);
    String addDirectionaryHead(DirectionaryHead directionaryHead);
    String updateDirectionaryDetail(DirectionaryDetail directionaryDetail);
    String updateDirectionaryHead(DirectionaryHead directionaryHead);
    String getDetailsByCondition(DirectionaryDetail directionaryDetail);
    String delDetails(List<Integer> idList, DirectionaryDetail directionaryDetail);
    String delHead(DirectionaryHead directionaryHead);
    String searchDirecitonaryDetail(DirectionaryDetail directionaryDetail);
    String machinetypes();
    String machinename();

    String machinemodel();

    String machinenumber();

    String machinegroup();

    String codemachinetype();

    String controltype();

    String preciselevel();

    String mainpower();

    String mainvel();

    String hasKnife();

    String hasnumber();

    String deviceSource();

    String managerlevel();

    String methodById(Integer lineId);

    String baseinfo(List<MachineBaseinfoHead> baseinfoHeads);

    String feature(List<MachineFeatureHead> featureHeads);
}
