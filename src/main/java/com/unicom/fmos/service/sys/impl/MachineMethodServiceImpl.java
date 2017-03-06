package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.MachineMethodDao;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.business.MachineMethod;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.MachineMethodService;
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
 * Created by zhaojb on 2017/1/29.
 */
@Service
public class MachineMethodServiceImpl implements MachineMethodService {

    @Autowired
    private MachineMethodDao machineMethodDao;

    @Transactional
    @Override
    public String updateMethod(List<MachineMethod> methods) {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            for (MachineMethod machineMethod : methods) {
                MachineMethod condition = new MachineMethod();
                condition.setModelLineId(machineMethod.getModelLineId());
                condition.setDirectionaryLineId(machineMethod.getDirectionaryLineId());
                List<MachineMethod> machineMethodList = machineMethodDao.select(condition);
                if (machineMethodList.size() == 0) {
                    machineMethod.setCreateUser(user.getUserName());
                    machineMethod.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    machineMethodDao.insert(machineMethod);
                }
                else {
                    MachineMethod method = machineMethodList.get(0);
                    method.setActive(machineMethod.getActive());
                    method.setUpdateUser(user.getUserName());
                    method.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    machineMethodDao.update(method);
                }
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "修改失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "修改成功", null);
        return UtilFactory.toJson(goodResult);
    }
}
