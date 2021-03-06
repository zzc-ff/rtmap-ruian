package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class ShopListEntity {
    /**
     * 店铺Id
     */
    private String shopId;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 楼层id
     */
    private String floorId;
    /**
     * 楼层名称
     */
    private String floorName;
    /**
     * 一级业态
     */
    private String industryId;

    /**
     * 一级业态名称
     */
    private String industryName;
    /**
     * 销售额
     */
    private long saleAmount;
    /**
     * 日均销售坪效
     */
    private long  saleEffect;
    /**
     * 交易笔数
     */
    private long  tradeBills;
    /**
     * 客单价
     */
    private long perCustomerPrice;
}
