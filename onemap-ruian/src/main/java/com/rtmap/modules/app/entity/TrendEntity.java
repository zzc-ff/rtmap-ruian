package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class TrendEntity {
    /**
     * 时间
     */
    private String days;
    /**
     * 是否为工作日
     * 1:假日 2:工作日 3:周末
     */
    private String isWorkDay;
    /**
     * 指标量
     */
    private long resultData;
}
