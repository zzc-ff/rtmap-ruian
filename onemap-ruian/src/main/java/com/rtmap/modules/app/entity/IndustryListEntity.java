package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class IndustryListEntity {
    /**
     * 一级业态
     */
    private String industryId;

    /**
     * 一级业态名称
     */
    private String industryName;
    /**
     * 英文名称
     */
    private String englishName;
}
