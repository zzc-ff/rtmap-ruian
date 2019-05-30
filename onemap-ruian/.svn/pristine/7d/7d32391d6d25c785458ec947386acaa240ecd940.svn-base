package com.rtmap.modules.app.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ParameterVo {
    /**
     * groupId
     */
    @NotBlank(message = "groupId 不能为空")
    private String groupId;
    /**
     * 项目Id
     */
    @NotBlank(message = "marketId 不能为空")
    private String marketId;
    /**
     * 项目Id
     */
    @NotBlank(message = "mallId 不能为空")
    private String mallId;
    /**
     * floor Id
     */
    private String floorId;
    /**
     * 业态 Id
     */
    private String industryId;
    /**
     * 店铺Id
     */
    private String shopId;
    /**
     * nowTime 当前时间 格式： yyyyMMdd / yyyyMM01 / yyyy0101
     */
    @NotBlank(message = "startTime 不能为空")
    private String startTime;
    /**
     * nowTime 当前时间 格式： yyyyMMdd / yyyyMM01 / yyyy0101
     */
    private String endTime;
    /**
     * 时间偏移量   天偏移量  -1 往前推一天 1 往未来推一天       月偏移量  同理
     */
    private int offsetTime;
    /**
     * 时间类型   D-日期 、W-星期、M-月、Q-季 、Y-年
     */
    @NotBlank(message = "dateType 不能为空")
    private String dateType;

    /**
     * 当前页
     */
    private int page = 1;
    /**
     * 每页数据条数
     */
    private int pageSize = 10;

    /**
     * 排序字段
     * saleAmount
     * saleEffect
     * perCustomerPrice
     * tradeBills
     */
    private String word;
    /**
     * 升降排序
     * desc 大到小
     * asc 小到大
     */
    private String lift;

}
