package com.unicom.fmos.dao.sys;

import com.unicom.fmos.dto.ResourceTreeNode;
import com.unicom.fmos.entity.sys.ResourceMenu;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2016/12/11.
 */
public interface ResourceMenuDao {
    List<ResourceTreeNode> getTree();
    List<ResourceTreeNode> selectTreeNodeById(int parentId);
    List<ResourceTreeNode> selectBranchById(int id);
    int insertResourceMenu(ResourceMenu menu);
    int delResourceMenuBranch(Map<String, Object> map);
    int updateMenuBatch(Map<String, Object> map);
    int updateMenu(ResourceMenu menu);
}
