package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.MachineBaseinfoHeadDao;
import com.unicom.fmos.dao.business.MachineFeatureHeadDao;
import com.unicom.fmos.entity.business.MachineBaseinfoHead;
import com.unicom.fmos.entity.business.MachineFeature;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.business.Model;
import com.unicom.fmos.service.sys.DirectionaryService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicom.fmos.dao.sys.DirectionaryDao;
import com.unicom.fmos.dto.DirectionaryDto;
import com.unicom.fmos.dto.DirectionaryKeyValueDto;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.dto.TypeHeadDto;
import com.unicom.fmos.entity.sys.DirectionaryDetail;
import com.unicom.fmos.entity.sys.DirectionaryHead;
import com.unicom.fmos.utils.UtilFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2017/1/7.
 */
@Service
public class DirectionaryServiceImpl implements DirectionaryService {

    @Autowired
    private DirectionaryDao directionaryDao;
    @Autowired
    private MachineBaseinfoHeadDao machineBaseinfoHeadDao;
    @Autowired
    private MachineFeatureHeadDao machineFeatureHeadDao;

    @Override
    public String getKeysAndValues(DirectionaryDetail directionaryDetail) {
        List<DirectionaryDetail> directionaryDetails =
                directionaryDao.selectDirectionaryDetailByCondition(directionaryDetail);
        DirectionaryKeyValueDto directionaryKeyValueDto =
                new DirectionaryKeyValueDto();
        List<DirectionaryDto> keys = new ArrayList<>();
        List<DirectionaryDto> vals = new ArrayList<>();
        for (DirectionaryDetail detail : directionaryDetails) {
            DirectionaryDto key = new DirectionaryDto(detail.getDirectionaryKey(), detail.getDirectionaryKey());
            DirectionaryDto val = new DirectionaryDto(detail.getDirectionaryValue(), detail.getDirectionaryValue());
            keys.add(key);
            vals.add(val);
        }
        directionaryKeyValueDto.setKeys(keys);
        directionaryKeyValueDto.setVals(vals);
        return UtilFactory.toJson(directionaryKeyValueDto);
    }

    @Override
    public String getHeads() {
        List<DirectionaryHead> directionaryHeads = directionaryDao.selectAllDirectionaryHead();
        List<TypeHeadDto> headDtos = new ArrayList<>();
        for (DirectionaryHead directionaryHead : directionaryHeads) {
            TypeHeadDto typeHeadDto = new TypeHeadDto();
            typeHeadDto.setId(directionaryHead.getLineId());
            typeHeadDto.setName(directionaryHead.getDirectionaryGroup());
            headDtos.add(typeHeadDto);
        }
        return UtilFactory.toJson(headDtos);
    }

    @Override
    public String addDirectionaryDetail(DirectionaryDetail directionaryDetail) {
        try {
            directionaryDao.insertDirectionaryDetail(directionaryDetail);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String updateDirectionaryDetail(DirectionaryDetail directionaryDetail) {
        try {
            directionaryDao.updateDirectionaryDetail(directionaryDetail);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新成功" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新失败", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String getDetailsByCondition(DirectionaryDetail directionaryDetail) {
        List<DirectionaryDetail> directionaryDetails = directionaryDao.selectDirectionaryDetailByCondition(directionaryDetail);
        DirectionaryDetail detail = new DirectionaryDetail();
        detail.setDirectionaryKey("");
        detail.setDirectionaryValue("");
        directionaryDetails.add(0,detail);
        return UtilFactory.toJson(directionaryDetails);
    }

    @Override
    public String updateDirectionaryHead(DirectionaryHead directionaryHead) {
        try {
            directionaryDao.updateDirectionaryHead(directionaryHead);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新成功" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新失败", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String addDirectionaryHead(DirectionaryHead directionaryHead) {
        try {
            directionaryDao.insertDirectionaryHead(directionaryHead);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String delDetails(List<Integer> idList, DirectionaryDetail directionaryDetail) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("activeuser", directionaryDetail.getActiveUser());
        map.put("idList", idList);
        map.put("activememo", directionaryDetail.getActiveMemo());
        map.put("activetime", directionaryDetail.getActiveTime());
        try {
            directionaryDao.delDetail(map);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String delHead(DirectionaryHead directionaryHead) {
        try {
            directionaryDao.delDirectionaryHead(directionaryHead);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新成功" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新失败", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String searchDirecitonaryDetail(DirectionaryDetail directionaryDetail) {
        List<DirectionaryDetail> directionaryDetails = directionaryDao.searchDirectionaryDetail(directionaryDetail);
        return UtilFactory.toJson(directionaryDetails);
    }

    @Override
    public String machinetypes() {
        DirectionaryHead head = new DirectionaryHead();
        head.setDirectionaryGroup("机床");
        List<DirectionaryDto> directionaryDtos = directionaryDao.getDirectionaryDetailByDirectionaryHead(head);
        DirectionaryDto directionaryDto = new DirectionaryDto();
        directionaryDto.setId(null);
        directionaryDto.setName("全部");
        directionaryDtos.add(0,directionaryDto);
        return UtilFactory.toJson(directionaryDtos);
    }

    @Override
    public String machinename() {
        return UtilFactory.toJson(directionaryDao.getmachinename());
    }

    @Override
    public String machinemodel() {
        return UtilFactory.toJson(directionaryDao.getmachinemodel());
    }

    @Override
    public String machinenumber() {
        return UtilFactory.toJson(directionaryDao.getmachinenumber());
    }

    @Override
    public String machinegroup() {
        return UtilFactory.toJson(directionaryDao.getmachinegroup());
    }

    @Override
    public String methodById(Integer lineId) {
        Model model = new Model();
        model.setLineId(lineId);
        String result = UtilFactory.toJson(directionaryDao.getMethodByLineId(model));
        return result;
    }

    @Override
    public String codemachinetype() {
        List<DirectionaryDto> types = directionaryDao.getcodemachinetype();
        List<DirectionaryDto> mainParams = directionaryDao.getcodemainparams();
        List<DirectionaryDto> controltype = directionaryDao.getcontroltype();
        List<DirectionaryDto> preciselevel = directionaryDao.getpreciselevel();
        List<DirectionaryDto> mainpower = directionaryDao.getmainpower();
        List<DirectionaryDto> mainvel = directionaryDao.getmainvel();
        List<DirectionaryDto> hasKnife = directionaryDao.gethasKnife();
        List<DirectionaryDto> hasnumber = directionaryDao.gethasnumber();
        List<DirectionaryDto> deviceSource = directionaryDao.getdeviceSource();
        List<DirectionaryDto> managerlevel = directionaryDao.getmanagerlevel();
        List<DirectionaryDto> method = directionaryDao.getmethod();
        Map<String, List<DirectionaryDto>> map = new HashMap<>();
        map.put("types",types);
        map.put("params", mainParams);
        map.put("controltype", controltype);
        map.put("preciselevel", preciselevel);
        map.put("mainpower", mainpower);
        map.put("mainvel", mainvel);
        map.put("hasKnife", hasKnife);
        map.put("hasnumber", hasnumber);
        map.put("deviceSource", deviceSource);
        map.put("managerlevel", managerlevel);
        map.put("method", method);
        return UtilFactory.toJson(map);
    }

    @Override
    public String controltype() {
        return UtilFactory.toJson(directionaryDao.getcontroltype());
    }

    @Override
    public String preciselevel() {
        return UtilFactory.toJson(directionaryDao.getpreciselevel());
    }

    @Override
    public String mainpower() {
        return UtilFactory.toJson(directionaryDao.getmainpower());
    }

    @Override
    public String mainvel() {
        return UtilFactory.toJson(directionaryDao.getmainvel());
    }

    @Override
    public String hasKnife() {
        return UtilFactory.toJson(directionaryDao.gethasKnife());
    }

    @Override
    public String hasnumber() {
        return UtilFactory.toJson(directionaryDao.gethasnumber());
    }

    @Override
    public String deviceSource() {
        return UtilFactory.toJson(directionaryDao.getdeviceSource());
    }

    @Override
    public String managerlevel() {
        return UtilFactory.toJson(directionaryDao.getmanagerlevel());
    }

    @Override
    public String baseinfo(List<MachineBaseinfoHead> baseinfoHeads) {
        List<DirectionaryDto> basicInfo = directionaryDao.baseinfo();
        MachineBaseinfoHead condition = baseinfoHeads.get(0);
        condition.setActive(true);
        List<MachineBaseinfoHead> checkedList = machineBaseinfoHeadDao.select(condition);
        Map<String, Object> map = new HashMap<>();
        map.put("baseinfo",basicInfo);
        map.put("checkedList",checkedList);
        return UtilFactory.toJson(map);
    }

    @Override
    public String feature(List<MachineFeatureHead> featureHeads) {
        List<DirectionaryDto> basicInfo = directionaryDao.feature();
        MachineFeatureHead condition = featureHeads.get(0);
        condition.setActive(true);
        List<MachineFeatureHead> checkedList = machineFeatureHeadDao.select(condition);
        Map<String, Object> map = new HashMap<>();
        map.put("baseinfo",basicInfo);
        map.put("checkedList",checkedList);
        return UtilFactory.toJson(map);
    }
}
