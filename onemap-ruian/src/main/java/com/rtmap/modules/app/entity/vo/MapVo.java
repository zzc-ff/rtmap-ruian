package com.rtmap.modules.app.entity.vo;

import lombok.Data;

@Data
public class MapVo {
//    {"buildid":"860100010010300004","key":"K7I23869HD","floor":"F1","maptype":"fn,poi,bk","codeType":0}
    /**
     * buildId   存储的是mall id
     */
    private String buildid;
    /**
     * 项目 id
     */
    private String marketId;
    /**
     * key
     */
    private String key;
    /**
     * 楼层
     */
    private String floor;
    /**
     * maptype
     */
    private String maptype;
    /**
     * codeType
     */
    private int codeType;
}
