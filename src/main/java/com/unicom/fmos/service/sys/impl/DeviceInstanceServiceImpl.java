package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.*;
import com.unicom.fmos.dao.sys.DirectionaryDao;
import com.unicom.fmos.dto.DirectionaryDto;
import com.unicom.fmos.dto.IdKeyValueDto;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.business.*;
import com.unicom.fmos.entity.sys.DirectionaryDetail;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.DeviceInstanceService;
import com.unicom.fmos.utils.UtilFactory;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by unicom on 2017/1/24.
 */
@Service
public class DeviceInstanceServiceImpl implements DeviceInstanceService {

    @Autowired
    private InstanceDao instanceDao;
    @Autowired
    private ModelDao modelDao;
    @Autowired
    private DirectionaryDao directionaryDao;
    @Autowired
    private MachineAvailabletimeDao machineAvailabletimeDao;
    @Autowired
    private MachineBaseinfoHeadDao machineBaseinfoHeadDao;
    @Autowired
    private MachineBaseinfoDetailDao machineBaseinfoDetailDao;
    @Autowired
    private MachineFeatureHeadDao machineFeatureHeadDao;
    @Autowired
    private MachineFeatureDetailDao machineFeatureDetailDao;

    @Override
    public String infos() {
        List<Instance> instances = instanceDao.infos();
        Instance instance = new Instance();
        instance.setModelLineId(null);
        instance.setInstanceName("");
        instance.setInstanceNumber("");
        instances.add(0,instance);
        String result = UtilFactory.toJson(instances);
        return result;
    }

    @Override
    public String infosbyid() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        Instance instanceCondition = new Instance();
        instanceCondition.setCreateUserLineId(user.getLineId().intValue());
        List<Instance> instances = instanceDao.infosbyid(instanceCondition);
        Instance instance = new Instance();
        instance.setModelLineId(null);
        instance.setInstanceName("");
        instance.setInstanceNumber("");
        instances.add(0,instance);
        String result = UtilFactory.toJson(instances);
        return result;
    }

    @Override
    public String add(Instance instance) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        instance.setCreateUser(user.getUserName());
        instance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        instance.setCreateUserLineId(user.getLineId().intValue());
        try {
            instanceDao.insertSelective(instance);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String update(Instance instance) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        instance.setUpdateUser(user.getUserName());
        instance.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        instance.setUpdateMemo(user.getUserName() + "手动更新");
        instance.setCreateUserLineId(user.getLineId().intValue());
        try {
            instanceDao.updateByPrimaryKeySelective(instance);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    @Transactional
    public String del(List<Integer> list) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        try {
            for (Integer id : list) {
                Instance instance = new Instance();
                instance.setLineId(id);
                instance.setActive(false);
                instance.setActiveUser(user.getUserName());
                instance.setActiveTime(new Timestamp(System.currentTimeMillis()));
                instance.setActiveMemo(user.getUserName() + "手动删除");
                instanceDao.updateByPrimaryKeySelective(instance);
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String search(Instance instance) {
        Instance condition = new Instance();
        if (instance.getInstanceName() != null) {
            condition.setInstanceName(instance.getInstanceName());
        }
        if (instance.getInstanceNumber() != null) {
            condition.setInstanceNumber(instance.getInstanceNumber());
        }
        if (instance.getCreateUser() != null) {
            condition.setCreateUser(instance.getCreateUser());
        }
        List<Instance> instances = instanceDao.selectByCondition(condition);
        return UtilFactory.toJson(instances);
    }

    @Override
    public String searchbyid(Instance instance) {
        Instance condition = new Instance();
        if (instance.getInstanceName() != null) {
            condition.setInstanceName(instance.getInstanceName());
        }
        if (instance.getInstanceNumber() != null) {
            condition.setInstanceNumber(instance.getInstanceNumber());
        }
        if (instance.getCreateUser() != null) {
            condition.setCreateUser(instance.getCreateUser());
        }
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        condition.setCreateUserLineId(user.getLineId().intValue());
        List<Instance> instances = instanceDao.selectByConditionWithUserId(condition);
        return UtilFactory.toJson(instances);
    }

    @Override
    public String models() {
        List<DirectionaryDto> models = modelDao.getModelModels();
        DirectionaryDto directionaryDto = new DirectionaryDto();
        directionaryDto.setId(null);
        directionaryDto.setName("全部");
        models.add(0,directionaryDto);
        return UtilFactory.toJson(models);
    }

    @Override
    public String modellineids() {
        List<DirectionaryDto> directionaryDtos = directionaryDao.modellineids();
        return UtilFactory.toJson(directionaryDtos);
    }

    @Override
    public String instanceanme() {
        List<DirectionaryDto> directionaryDtos = directionaryDao.instanceanme();
        return UtilFactory.toJson(directionaryDtos);
    }

    @Override
    public String instancenumber() {
        List<DirectionaryDto> directionaryDtos = directionaryDao.instancenumber();
        return UtilFactory.toJson(directionaryDtos);
    }

    @Override
    public String availabletime(MachineAvailabletime machineAvailabletime) {
        MachineAvailabletime condition = new MachineAvailabletime();
        condition.setInstanceLineId(machineAvailabletime.getInstanceLineId());
        condition.setActive(true);
        List<MachineAvailabletime> result = machineAvailabletimeDao.select(condition);
        if (result.size() == 0) {
            MachineAvailabletime machinetime = new MachineAvailabletime();
            machinetime.setAvailableMonth(0);
            machinetime.setAvailableWeek(0);
            machinetime.setAvailableDay(0);
            return UtilFactory.toJson(machinetime);
        } else {
            return UtilFactory.toJson(result.get(0));
        }
    }

    @Override
    public String availableupdate(MachineAvailabletime machineAvailabletime) {
        try {
            MachineAvailabletime condition = new MachineAvailabletime();
            condition.setInstanceLineId(machineAvailabletime.getInstanceLineId());
            condition.setActive(true);
            List<MachineAvailabletime> result = machineAvailabletimeDao.select(condition);
            //更新
            if (result.size() != 0) {
                MachineAvailabletime updateEntity = result.get(0);
                updateEntity.setAvailableMonth(machineAvailabletime.getAvailableMonth());
                updateEntity.setAvailableWeek(machineAvailabletime.getAvailableWeek());
                updateEntity.setAvailableDay(machineAvailabletime.getAvailableDay());
                Subject subject = SecurityUtils.getSubject();
                User user = (User) subject.getSession().getAttribute("user");
                updateEntity.setUpdateUser(user.getUserName());
                updateEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                updateEntity.setUpdateMemo(user.getUserName() + "手动更新");
                machineAvailabletimeDao.updateByPrimaryKeySelective(updateEntity);
            } else {
                MachineAvailabletime insertEntity = new MachineAvailabletime();
                insertEntity.setInstanceLineId(machineAvailabletime.getInstanceLineId());
                insertEntity.setAvailableMonth(machineAvailabletime.getAvailableMonth());
                insertEntity.setAvailableWeek(machineAvailabletime.getAvailableWeek());
                insertEntity.setAvailableDay(machineAvailabletime.getAvailableDay());
                Subject subject = SecurityUtils.getSubject();
                User user = (User) subject.getSession().getAttribute("user");
                insertEntity.setCreateUser(user.getUserName());
                insertEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
                machineAvailabletimeDao.insertSelective(insertEntity);
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String baseinfo(Instance instance) {
        //先找出这个instance
        Instance instanceCondition = new Instance();
        instanceCondition.setLineId(instance.getLineId());
        Instance instanceResult = instanceDao.select(instanceCondition).get(0);
        //找出这个型号所具备的属性
        MachineBaseinfoHead machineBaseinfoHeadCondition = new MachineBaseinfoHead();
        machineBaseinfoHeadCondition.setActive(true);
        machineBaseinfoHeadCondition.setModelLineId(instanceResult.getModelLineId());
        List<MachineBaseinfoHead> machineBaseinfoHeads = machineBaseinfoHeadDao.select(machineBaseinfoHeadCondition);
        //找出这个属性的名称以及实际的值
        List<IdKeyValueDto> idKeyValueDtos = new ArrayList<>();
        for (MachineBaseinfoHead machineBaseinfoHead : machineBaseinfoHeads) {
            //根据id扎到字典的值
            DirectionaryDetail directionaryDetailCondition = new DirectionaryDetail();
            directionaryDetailCondition.setLineId(machineBaseinfoHead.getDirectionaryDetailLineId());
            directionaryDetailCondition.setActive(1);
            DirectionaryDetail directionaryDetail = directionaryDao.selectDirectionaryDetailByCondition(directionaryDetailCondition).get(0);
            //根据instance找到属性的值
            MachineBaseinfoDetail machineBaseinfoDetailCondition = new MachineBaseinfoDetail();
            machineBaseinfoDetailCondition.setInstanceLineId(instanceResult.getLineId());
            machineBaseinfoDetailCondition.setBaseinfoHeadLineId(machineBaseinfoHead.getLineId());
            List<MachineBaseinfoDetail> machineBaseinfoDetailResults = machineBaseinfoDetailDao.select(machineBaseinfoDetailCondition);
            IdKeyValueDto keyValueDto = new IdKeyValueDto();
            keyValueDto.setId(machineBaseinfoHead.getLineId().toString());
            keyValueDto.setName(directionaryDetail.getDirectionaryKey());
            if (machineBaseinfoDetailResults.size() == 0) {
                keyValueDto.setValue("无");
            } else {
                keyValueDto.setValue(machineBaseinfoDetailResults.get(0).getInfoValue());
            }
            idKeyValueDtos.add(keyValueDto);
        }
        return UtilFactory.toJson(idKeyValueDtos);
    }

    @Override
    public String feature(Instance instance) {
        //先找出这个instance
        Instance instanceCondition = new Instance();
        instanceCondition.setLineId(instance.getLineId());
        Instance instanceResult = instanceDao.select(instanceCondition).get(0);
        //找出这个型号所具备的属性
        MachineFeatureHead machineBaseinfoHeadCondition = new MachineFeatureHead();
        machineBaseinfoHeadCondition.setActive(true);
        machineBaseinfoHeadCondition.setModelLineId(instanceResult.getModelLineId());
        List<MachineFeatureHead> machineBaseinfoHeads = machineFeatureHeadDao.select(machineBaseinfoHeadCondition);
        //找出这个属性的名称以及实际的值
        List<IdKeyValueDto> idKeyValueDtos = new ArrayList<>();
        for (MachineFeatureHead machineBaseinfoHead : machineBaseinfoHeads) {
            //根据id扎到字典的值
            DirectionaryDetail directionaryDetailCondition = new DirectionaryDetail();
            directionaryDetailCondition.setLineId(machineBaseinfoHead.getDirectionaryDetailLineId());
            directionaryDetailCondition.setActive(1);
            DirectionaryDetail directionaryDetail = directionaryDao.selectDirectionaryDetailByCondition(directionaryDetailCondition).get(0);
            //根据instance找到属性的值
            MachineFeatureDetail machineBaseinfoDetailCondition = new MachineFeatureDetail();
            machineBaseinfoDetailCondition.setInstanceLineId(instanceResult.getLineId());
            machineBaseinfoDetailCondition.setFeatureHeadLineId(machineBaseinfoHead.getLineId());
            List<MachineFeatureDetail> machineBaseinfoDetailResults = machineFeatureDetailDao.select(machineBaseinfoDetailCondition);
            IdKeyValueDto keyValueDto = new IdKeyValueDto();
            keyValueDto.setId(machineBaseinfoHead.getLineId().toString());
            keyValueDto.setName(directionaryDetail.getDirectionaryKey());
            if (machineBaseinfoDetailResults.size() == 0) {
                keyValueDto.setValue("0.0");
            } else {
                keyValueDto.setValue(machineBaseinfoDetailResults.get(0).getInfoValue().toString());
            }
            idKeyValueDtos.add(keyValueDto);
        }
        return UtilFactory.toJson(idKeyValueDtos);
    }

    @Override
    @Transactional
    public String baseinfoupdate(List<MachineBaseinfoDetail> machineBaseinfoDetails) {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            for (MachineBaseinfoDetail basicinfo : machineBaseinfoDetails) {
                MachineBaseinfoDetail condition = new MachineBaseinfoDetail();
                condition.setInstanceLineId(basicinfo.getInstanceLineId());
                condition.setBaseinfoHeadLineId(basicinfo.getBaseinfoHeadLineId());
                List<MachineBaseinfoDetail> basicList = machineBaseinfoDetailDao.select(condition);
                if (basicList.size() == 0) {
                    basicinfo.setCreateUser(user.getUserName());
                    basicinfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    machineBaseinfoDetailDao.insertSelective(basicinfo);
                }
                else {
                    MachineBaseinfoDetail updateInfo = basicList.get(0);
                    updateInfo.setInfoValue(basicinfo.getInfoValue());
                    updateInfo.setActive(basicinfo.getActive());
                    updateInfo.setUpdateUser(user.getUserName());
                    updateInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    machineBaseinfoDetailDao.updateByPrimaryKeySelective(updateInfo);
                }
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "修改失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "修改成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    @Transactional
    public String featureupdate(List<MachineFeatureDetail> machineBaseinfoDetails) {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            for (MachineFeatureDetail basicinfo : machineBaseinfoDetails) {
                MachineFeatureDetail condition = new MachineFeatureDetail();
                condition.setInstanceLineId(basicinfo.getInstanceLineId());
                condition.setFeatureHeadLineId(basicinfo.getFeatureHeadLineId());
                List<MachineFeatureDetail> basicList = machineFeatureDetailDao.select(condition);
                if (basicList.size() == 0) {
                    basicinfo.setCreateUser(user.getUserName());
                    basicinfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    machineFeatureDetailDao.insertSelective(basicinfo);
                }
                else {
                    MachineFeatureDetail updateInfo = basicList.get(0);
                    updateInfo.setInfoValue(basicinfo.getInfoValue());
                    updateInfo.setActive(basicinfo.getActive());
                    updateInfo.setUpdateUser(user.getUserName());
                    updateInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    machineFeatureDetailDao.updateByPrimaryKeySelective(updateInfo);
                }
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "修改失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "修改成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String infossp() {
        List<Instance> instances = instanceDao.infos();
        return UtilFactory.toJson(instances);
    }
}
