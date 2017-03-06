package com.unicom.fmos.dto;

/**
 * Created by zhaojb on 2017/1/7.
 */
public class DirectionaryDto {
    private String id;
    private String name;

    public DirectionaryDto() {
    }

    public DirectionaryDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
