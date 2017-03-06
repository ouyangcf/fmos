package com.unicom.fmos.web;

import com.unicom.fmos.entity.sys.ResourceMenu;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhaojb on 2016/12/11.
 */
@Controller
@RequestMapping("/user/businiess/menu")
public class MenuMatinController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menuTree", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String menuTree() {
        String menuJson = menuService.getMenuTree();
        return menuJson;
    }

    @RequestMapping(value = "/menuresourceadd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String menuResourceAdd(ResourceMenu menu, HttpServletRequest request, HttpSession session) {
        menu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        User user = (User) session.getAttribute("user");
        menu.setCreateUser(user.getUserName());

        return menuService.menuResourceAdd(menu);
    }

    @RequestMapping(value = "/menuresourceedit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String menuResourceEdit(ResourceMenu menu, HttpServletRequest request, HttpSession session) {
        menu.setUpdateTime(new Timestamp((System.currentTimeMillis())));
        User user = (User) session.getAttribute("user");
        menu.setUpdateUser(user.getUserName());
        menu.setUpdateMemo(user.getUserName() + "手动更新");
        return menuService.menuResourceEdit(menu);
    }

    @RequestMapping(value = "/menuresourcedel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String menuResourceDel(ResourceMenu menu, HttpServletRequest request, HttpSession session) {
        menu.setActive(new BigDecimal(0));
        menu.setActiveTime(new Timestamp(System.currentTimeMillis()));
        User user = (User) session.getAttribute("user");
        menu.setActiveUser(user.getUserName());
        menu.setActiveMemo(user.getUserName() + "手动删除");
        return menuService.delMenuBatch(menu);
    }

    @RequestMapping(value = "/menuresourcesort", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String menuresourceSort(@RequestBody List<ResourceMenu> menus, HttpServletRequest request, HttpSession session) {
        for (ResourceMenu menu : menus) {
            menu.setUpdateTime(new Timestamp((System.currentTimeMillis())));
            User user = (User) session.getAttribute("user");
            menu.setUpdateUser(user.getUserName());
            menu.setUpdateMemo(user.getUserName() + "手动排序");

        }
        return menuService.menuResourceSort(menus);
    }
}
