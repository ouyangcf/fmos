package com.unicom.fmos.dao.business;

import com.unicom.fmos.dto.ChannelQualityDto;

import java.util.List;

public interface NewDepartDao {
    List<ChannelQualityDto> selectFirstLayout();
    List<ChannelQualityDto> selectSecondLayout(ChannelQualityDto channelQualityDto);
    List<ChannelQualityDto> selectThirdLayout(ChannelQualityDto channelQualityDto);
}