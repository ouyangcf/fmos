package com.unicom.fmos.dto;

/**
 * Created by zhaojb on 2016/12/25.
 */
public class TypeHeadDto {

    private Integer id;
    private String name;

    public TypeHeadDto() {
    }

    public TypeHeadDto(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
