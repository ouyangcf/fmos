package com.unicom.fmos.service.sys;

import com.unicom.fmos.entity.business.File;

import java.util.List;

/**
 * Created by unicom on 2017/2/8.
 */
public interface FileService {
    String insert(File file);
    String update(File file);

    String infos(File file);

    String del(List<Integer> list);

    String insertOfUpdate(File insertFile);

    String preivew(File file);
}
