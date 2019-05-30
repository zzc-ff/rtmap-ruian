package com.rtmap.modules.app.entity;

import lombok.Data;

import java.util.List;

@Data
public class FloorIndustryEntity {
    /**
     * 楼层 id
     */
    private String floorId;
    /**
     * 楼层名称
     */
    private String floorName;

    /**
     * 业态 list
     */
    List<IndustryEntity> list;
}
