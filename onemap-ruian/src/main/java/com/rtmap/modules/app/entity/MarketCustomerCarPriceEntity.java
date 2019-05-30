package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class MarketCustomerCarPriceEntity {
    /**
     * 日期
     */
    private String days;
    /**
     * 项目 Id
     */
    private String marketId;
    /**
     * 项目名称
     */
    private String marketName;
    /**
     * 客流
     */
    private int customerNum;
    /**
     * 车流
     */
    private int carNum;
    /**
     * 客单价
     */
    private int perCustomerPrice;
}
