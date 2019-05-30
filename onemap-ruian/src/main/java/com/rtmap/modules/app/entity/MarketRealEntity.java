package com.rtmap.modules.app.entity;

import lombok.Data;

/**
 * 集团驾驶舱地图使用
 */
@Data
public class MarketRealEntity {
    /**
     * 项目Id
     */
    private String marketId;
    /**
     * 项目名称
     */
    private String marketName;
    /**
     * 销售额
     */
    private long sellAmount;
    /**
     * 交易笔数
     */
    private long tradeAmount;
    /**
     * 项目坐标经度
     */
    private double lat;
    /**
     * 项目坐标纬度
     */
    private double lng;
}
