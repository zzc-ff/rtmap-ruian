package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class MapRequestEntity {
    /**
     * mall id
     */
    private String mallId;
    /**
     * 楼层
     */
    private String floor;
}
