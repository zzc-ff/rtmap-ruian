package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class MallEntity {
    /**
     * mall Id
     */
    private String mallId;
    /**
     * mall name
     */
    private String mallName;
    /**
     * mall 对应的buildId
     */
    private String buildId;
}
