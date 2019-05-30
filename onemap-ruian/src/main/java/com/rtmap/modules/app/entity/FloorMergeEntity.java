package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class FloorMergeEntity {
    /**
     * 楼层 Id
     */
    private String floorId;
    /**
     * 楼层名称
     */
    private String floorName;
    /**
     * 指标数据
     */
    private long resultData;
}
