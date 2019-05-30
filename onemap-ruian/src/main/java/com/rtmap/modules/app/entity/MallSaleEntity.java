package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class MallSaleEntity {
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
