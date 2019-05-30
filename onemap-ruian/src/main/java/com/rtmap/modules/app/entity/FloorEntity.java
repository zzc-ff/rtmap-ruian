package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class FloorEntity {
    /**
     * 销售额
     */
    private long saleAmount;
    /**
     * 楼层 id
     */
    private String floorId;
    /**
     * 楼层名称
     */
    private String floorName;
}
