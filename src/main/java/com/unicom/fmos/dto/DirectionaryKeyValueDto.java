package com.unicom.fmos.dto;

import java.util.List;

/**
 * Created by zhaojb on 2017/1/7.
 */
public class DirectionaryKeyValueDto {
    private List<DirectionaryDto> keys;
    private List<DirectionaryDto> vals;

    public DirectionaryKeyValueDto() {
    }

    public DirectionaryKeyValueDto(List<DirectionaryDto> keys, List<DirectionaryDto> vals) {
        this.keys = keys;
        this.vals = vals;
    }

    public List<DirectionaryDto> getKeys() {
        return keys;
    }

    public void setKeys(List<DirectionaryDto> keys) {
        this.keys = keys;
    }

    public List<DirectionaryDto> getVals() {
        return vals;
    }

    public void setVals(List<DirectionaryDto> vals) {
        this.vals = vals;
    }
}
