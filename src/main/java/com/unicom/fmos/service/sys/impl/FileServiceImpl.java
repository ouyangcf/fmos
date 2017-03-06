package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.FileDao;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.business.File;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.FileService;
import com.unicom.fmos.utils.PasswordHelper;
import com.unicom.fmos.utils.UtilFactory;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by unicom on 2017/2/8.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public String insert(File file) {
        try {
            fileDao.insertSelective(file);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }


    @Override
    public String insertOfUpdate(File insertFile) {
        try {
            File fileCondition = new File();
            fileCondition.setFileSource(2);
            fileCondition.setSourceLineId(insertFile.getSourceLineId());
            List<File> resultFiles = fileDao.select(fileCondition);
            if (resultFiles.isEmpty()) {
                fileDao.insertSelective(insertFile);
            } else {
                File file = resultFiles.get(0);
                file.setFileBody(insertFile.getFileBody());
                Subject subject = SecurityUtils.getSubject();
                User user = (User) subject.getSession().getAttribute("user");
                file.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                file.setUpdateUser(user.getUserName());
                file.setUpdateMemo(user.getUserName() + "手动更新");
                fileDao.updateByPrimaryKeySelective(file);
            }

        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String update(File file) {
        try {
            fileDao.updateByPrimaryKeySelective(file);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "更新失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "更新成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String infos(File file) {
        return UtilFactory.toJson(fileDao.select(file));
    }

    @Override
    @Transactional
    public String del(List<Integer> list) {
        Map<String,Object> map = new HashMap<>();
        map.put("idList", list);
        try {
            fileDao.del(map);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String preivew(File file) {
        return null;
    }
}
