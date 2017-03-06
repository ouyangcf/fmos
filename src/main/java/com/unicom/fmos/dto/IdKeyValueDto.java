package com.unicom.fmos.dto;

/**
 * Created by unicom on 2017/2/4.
 */
public class IdKeyValueDto {
    private String id;
    private String name;
    private String value;

    public IdKeyValueDto(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public IdKeyValueDto() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
