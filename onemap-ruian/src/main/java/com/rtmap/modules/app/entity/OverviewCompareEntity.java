package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class OverviewCompareEntity {

    /**
     * 销售额
     */
    private long saleAmount;
    /**
     * 销售额环比
     */
    private double saleRing;
    /**
     * 销售额同比
     */
    private double saleSimilar;

    /**
     * 客流量
     */
    private long customerNum;

    /**
     * 客流量环比
     */
    private double customerRing;

    /**
     * 客流量同比
     */
    private double customerSimilar;
    /**
     * 车流量
     */
    private long carNum;
    /**
     * 车流量环比
     */
    private double carRing;
    /**
     * 车流量同比
     */
    private double carSimilar;
    /**
     * 新增会员数
     */
    private long memberIncrease;
    /**
     * 新增会员数环比
     */
    private double memberRing;
    /**
     * 新增会员数同比
     */
    private double memberSimilar;

}
