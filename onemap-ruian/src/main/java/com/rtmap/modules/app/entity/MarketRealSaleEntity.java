package com.rtmap.modules.app.entity;

import lombok.Data;

/**
 * 集团驾驶舱使用
 */
@Data
public class MarketRealSaleEntity {
    /**
     * 项目 Id
     */
    private String marketId;
    /**
     * 项目 name
     */
    private String marketName;
    /**
     * 项目 销售额
     */
    private long sellAmount ;
    /**
     * 项目坐标经度
     */
    private double lat;
    /**
     * 项目坐标纬度
     */
    private double lng;
}
