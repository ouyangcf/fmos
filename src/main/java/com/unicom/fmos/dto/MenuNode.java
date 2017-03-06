package com.unicom.fmos.dto;

import java.util.List;

/**
 * Created by zhaojb on 2016/12/11.
 */
public class MenuNode {
    private String text;
    private List<MenuNode> nodes;
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public MenuNode() {
    }

    public MenuNode(String text, List<MenuNode> nodes, String href) {
        this.text = text;
        this.nodes = nodes;
        this.href = href;
    }

    public MenuNode(String text) {
        this.text = text;
    }

    public MenuNode(List<MenuNode> nodes) {
        this.nodes = nodes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MenuNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuNode> nodes) {
        this.nodes = nodes;
    }
}
