package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class BusinessMapEntity {
    /**
     * 店铺id
     */
    private String shopId;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 业态Id
     */
    private String industryId;
    /**
     * 业态名称
     */
    private String industryName;
    /**
     * 合同开始时间
     */
    private String startTime;
    /**
     * 合同结束时间
     */
    private String expiryTime;
    /**
     *店铺 poi_no
     */
    private String poiNo;
    /**
     * 销售额
     */
    private long saleAmount;
    /**
     * 销售坪效
     */
    private long saleEffect;
}
