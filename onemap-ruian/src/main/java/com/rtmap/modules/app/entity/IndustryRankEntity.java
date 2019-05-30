package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class IndustryRankEntity {
    /**
     * 业态 Id
     */
    private String industryId;
    /**
     * 业态 名称
     */
    private String industryName;

    /**
     * 指标 数据
     */
    private long resultData;
}
