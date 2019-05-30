package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class CarAndCustomerEntity {
    /**
     * 时间
     */
    private String days;
    /**
     * 车流
     */
    private int carNum;
    /**
     * 客流
     */
    private int customerNum;
}
