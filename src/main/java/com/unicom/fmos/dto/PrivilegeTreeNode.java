package com.unicom.fmos.dto;

import com.unicom.fmos.entity.sys.Privilege;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/13.
 */
public class PrivilegeTreeNode {
    private List<ResourceTreeNode> menuTree;
    private List<Privilege> privilege;

    public PrivilegeTreeNode() {
    }

    public PrivilegeTreeNode(List<ResourceTreeNode> menuTree, List<Privilege> privilege) {
        this.menuTree = menuTree;
        this.privilege = privilege;
    }

    public List<ResourceTreeNode> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(List<ResourceTreeNode> menuTree) {
        this.menuTree = menuTree;
    }

    public List<Privilege> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<Privilege> privilege) {
        this.privilege = privilege;
    }
}
