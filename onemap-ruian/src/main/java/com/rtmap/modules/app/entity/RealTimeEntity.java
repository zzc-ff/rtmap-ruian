package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class RealTimeEntity {

    /**
     * 销售额
     */
    private long sellAmount;
    /**
     * 交易笔数
     */
    private long tradeAmount ;
    /**
     * 客流量、
     */
    private long customerNum;
    /**
     * 车流量
     *
     */
    private long carNum;
    /**
     * 交易会员数
     */
    private long memberNum ;
    /**
     *会员交易额
     */
    private long memSaleAmount;
    /**
     * 消费额占比
     */
    private double memSaleRatio;
}
