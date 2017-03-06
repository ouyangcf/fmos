package com.unicom.fmos.service.sys;

import com.unicom.fmos.entity.sys.ResourceMenu;

import java.util.List;


/**
 * Created by zhaojb on 2016/12/11.
 */
public interface MenuService {
    String getMenuTree();
    String menuResourceAdd(ResourceMenu menu);
    String menuResourceEdit(ResourceMenu menu);
    String delMenuBatch(ResourceMenu menu);
    String menuResourceSort(List<ResourceMenu> menus);
}
