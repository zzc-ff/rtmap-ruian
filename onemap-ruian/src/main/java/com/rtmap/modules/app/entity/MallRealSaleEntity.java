package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class MallRealSaleEntity {
    /**
     * mall Id
     */
    private String mallId;
    /**
     * mall name
     */
    private String mallName;
    /**
     * 项目销售额
     */
    private long sellAmount;
}
