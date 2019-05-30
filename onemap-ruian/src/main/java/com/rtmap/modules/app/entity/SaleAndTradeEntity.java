package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class SaleAndTradeEntity {
    /**
     * 日期
     */
    private String days;
    /**
     * 销售额
     */
    private long sellAmount;
    /**
     * 交易笔数
     */
    private int tradeBills;
}
