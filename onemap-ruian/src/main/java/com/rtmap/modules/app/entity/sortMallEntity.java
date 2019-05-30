package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class sortMallEntity {
    /**
     * mall id
     */
    private String mallId;
    /**
     * 排序
     */
    private int sort;
    /**
     * 指标数据
     */
    private long resultData;
}
