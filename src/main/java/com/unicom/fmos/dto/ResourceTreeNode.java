package com.unicom.fmos.dto;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/13.
 */
public class ResourceTreeNode {
    private Integer id;
    private String name;
    private String path;
    private String number;
    private String menuIcon;
    private List<ResourceTreeNode> children;

    public ResourceTreeNode() {
    }

    public ResourceTreeNode(Integer id, String name, String path, String number, String menuIcon, List<ResourceTreeNode> children) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.number = number;
        this.menuIcon = menuIcon;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ResourceTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTreeNode> children) {
        this.children = children;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
}
