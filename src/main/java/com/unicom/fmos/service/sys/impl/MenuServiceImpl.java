package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.service.sys.MenuService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicom.fmos.dao.sys.PrivilegeDao;
import com.unicom.fmos.dao.sys.ResourceMenuDao;
import com.unicom.fmos.dto.ResourceTreeNode;
import com.unicom.fmos.dto.Result;
import com.unicom.fmos.entity.sys.ResourceMenu;
import com.unicom.fmos.utils.UtilFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by zhaojb on 2016/12/11.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private ResourceMenuDao resourceMenuDao;
    @Autowired
    private PrivilegeDao privilegeDao;

    @Override
    public String menuResourceAdd(ResourceMenu menu) {
        try {
            resourceMenuDao.insertResourceMenu(menu);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String getMenuTree() {
        String json = UtilFactory.toJson(resourceMenuDao.getTree());
        return json;
    }

    @Override
    @Transactional
    public String delMenuBatch(ResourceMenu menu) {
        List<ResourceTreeNode> tree = resourceMenuDao.selectBranchById(menu.getLineId().intValue());
        List<Integer> idList = new ArrayList<Integer>();
        ResourceTreeNode topNode = tree.get(0);
        String username = menu.getActiveUser();
        getId(topNode,idList);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("activeuser", username);
        map.put("idList", idList);
        map.put("activememo", username + "手动删除");
        map.put("activetime", new Timestamp(System.currentTimeMillis()));
        try {
            resourceMenuDao.delResourceMenuBranch(map);
            privilegeDao.delPrivilege(map);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "删除失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "删除成功", null);
        return UtilFactory.toJson(goodResult);
    }

    private void getId(ResourceTreeNode topNode, List<Integer> idList) {
        if (topNode != null) {
            idList.add(topNode.getId());
            if (topNode.getChildren() != null) {
                for (ResourceTreeNode node : topNode.getChildren())
                    getId(node, idList);
            }
        }
    }

    private List<ResourceMenu> toResourceMenuList(List<Integer> idList, String username) {
        List<ResourceMenu> menuList = new ArrayList<>();
        for (Integer id : idList) {
            ResourceMenu menu = new ResourceMenu();
            BigDecimal lineId = new BigDecimal(id);
            BigDecimal active = new BigDecimal(0);
            menu.setLineId(lineId);
            menu.setActive(active);
            menu.setActiveUser(username);
            menu.setActiveTime(new Timestamp(System.currentTimeMillis()));
            menu.setActiveMemo("因"+username+"而级联删除");
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public String menuResourceEdit(ResourceMenu menu) {
        try {
            resourceMenuDao.updateMenu(menu);
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "新增失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "新增成功", null);
        return UtilFactory.toJson(goodResult);
    }

    @Override
    public String menuResourceSort(List<ResourceMenu> menus) {
        try {
            for(ResourceMenu menu : menus) {
                resourceMenuDao.updateMenu(menu);
            }
        } catch (SqlSessionException sqlSessionException) {
            Result badResult = new Result(-1, "-1", "修改失败" + sqlSessionException.getMessage(), null);
            return UtilFactory.toJson(badResult);
        }
        Result goodResult = new Result(1, "1", "修改成功", null);
        return UtilFactory.toJson(goodResult);
    }
}
