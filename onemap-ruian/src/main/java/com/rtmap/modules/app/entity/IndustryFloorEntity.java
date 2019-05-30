package com.rtmap.modules.app.entity;

import lombok.Data;

import java.util.List;

@Data
public class IndustryFloorEntity {
    /**
     * 一级业态
     */
    private String industryId;

    /**
     * 一级业态名称
     */
    private String industryName;
    /**
     * floor list
     */
    private List<IndustryEntity> list;
}
