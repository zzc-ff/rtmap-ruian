package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class SortEntity {
    /**
     * 当前项目排名
     */
    private int marketSort;
    /**
     * 项目较去年排名对比  正数 上升x   0  不变  负数 下降x
     */
    private int changeNum;
    /**
     * 总数据量
     */
    private int total;
}
