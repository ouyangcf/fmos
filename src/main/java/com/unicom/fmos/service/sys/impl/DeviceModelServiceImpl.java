package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.MachineBaseinfoHeadDao;
import com.unicom.fmos.dao.business.MachineFeatureHeadDao;
import com.unicom.fmos.dao.business.ModelDao;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.business.Instance;
import com.unicom.fmos.entity.business.MachineBaseinfoHead;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.business.Model;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.DeviceModelService;
import com.unicom.fmos.utils.UtilFactory;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by unicom on 2017/1/24.
 */
@Service
public class DeviceModelServiceImpl implements DeviceModelService {
    @Autowired
    private ModelDao modelDao;
    @Autowired
    private MachineBaseinfoHeadDao machineBaseinfoHeadDao;
    @Autowired
    private MachineFeatureHeadDao machineFeatureHeadDao;

    @Override
    public String infos() {
        List<Model> models = modelDao.infos();
        Model model = new Model();
        model.setModelName("");
        model.setModelModel("");
        model.setModelGroup("");
        model.setModelType(null);
        model.setModelNumber("");
        model.setNumberialController(null);
        models.add(0,model);
        String result = UtilFactory.toJson(models);
        return result;
    }

    @Override
    public String infosbyuserid() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        Model modelCondition = new Model();
        modelCondition.setCreateUserLineId(user.getLineId().intValue());
        List<Model> models = modelDao.infosbyuserid(modelCondition);
        Model model = new Model();
        model.setModelName("");
        model.setModelModel("");
        model.setModelGroup("");
        model.setModelType(null);
        model.setModelNumber("");
        model.setNumberialController(null);
        models.add(0,model);
        String result = UtilFactory.toJson(models);
        return result;
    }

    @Override
    public String add(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        model.setCreateUser(user.getUserName());
        model.setCreateTime(new Timestamp(System.currentTimeMillis()));
        model.setCreateUserLineId(user.getLineId().intValue());
        try {
            modelDao.insertSelective(model);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String update(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        model.setUpdateUser(user.getUserName());
        model.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        model.setUpdateMemo(user.getUserName() + "手动更新");
        model.setCreateUserLineId(user.getLineId().intValue());
        try {
            modelDao.updateByPrimaryKeySelective(model);
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
                Model model = new Model();
                model.setLineId(id);
                model.setActive(false);
                model.setActiveUser(user.getUserName());
                model.setActiveTime(new Timestamp(System.currentTimeMillis()));
                model.setActiveMemo(user.getUserName() + "手动删除");
                modelDao.updateByPrimaryKeySelective(model);
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String search(Model model) {
        return UtilFactory.toJson(modelDao.selectSelective(model));
    }

    @Override
    public String searchbyid(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        model.setCreateUserLineId(user.getLineId().intValue());
        return UtilFactory.toJson(modelDao.selectSelectiveById(model));
    }

    @Override
    @Transactional
    public String featurechecked(List<MachineFeatureHead> list) {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            for (MachineFeatureHead feature : list) {
                MachineFeatureHead condition = new MachineFeatureHead();
                condition.setModelLineId(feature.getModelLineId());
                condition.setDirectionaryDetailLineId(feature.getDirectionaryDetailLineId());
                List<MachineFeatureHead> machinefeatureList = machineFeatureHeadDao.select(condition);
                if (machinefeatureList.size() == 0) {
                    feature.setCreateUser(user.getUserName());
                    feature.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    machineFeatureHeadDao.insertSelective(feature);
                }
                else {
                    MachineFeatureHead updateFeature = machinefeatureList.get(0);
                    updateFeature.setActive(feature.getActive());
                    updateFeature.setUpdateUser(user.getUserName());
                    updateFeature.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    machineFeatureHeadDao.updateByPrimaryKeySelective(updateFeature);
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
    public String basicinfochecked(List<MachineBaseinfoHead> list) {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            for (MachineBaseinfoHead basicinfo : list) {
                MachineBaseinfoHead condition = new MachineBaseinfoHead();
                condition.setModelLineId(basicinfo.getModelLineId());
                condition.setDirectionaryDetailLineId(basicinfo.getDirectionaryDetailLineId());
                List<MachineBaseinfoHead> basicList = machineBaseinfoHeadDao.select(condition);
                if (basicList.size() == 0) {
                    basicinfo.setCreateUser(user.getUserName());
                    basicinfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    machineBaseinfoHeadDao.insertSelective(basicinfo);
                }
                else {
                    MachineBaseinfoHead updateInfo = basicList.get(0);
                    updateInfo.setActive(basicinfo.getActive());
                    updateInfo.setUpdateUser(user.getUserName());
                    updateInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    machineBaseinfoHeadDao.updateByPrimaryKeySelective(updateInfo);
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
        List<Model> models = modelDao.infos();
        return UtilFactory.toJson(models);
    }
}
