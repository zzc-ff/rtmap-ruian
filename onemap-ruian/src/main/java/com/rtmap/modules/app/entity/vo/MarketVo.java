package com.rtmap.modules.app.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MarketVo {
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
     * nowTime 当前时间 格式： yyyyMMdd / yyyyMM01 / yyyy0101
     */
    @NotBlank(message = "startTime 不能为空")
    private String startTime;
    /**
     * 时间偏移量   天偏移量  -1 往前推一天 1 往未来推一天       月偏移量  同理
     */
    private int offsetTime;

    /**
     * 结束时间按
     */
    private String endTime;
    /**
     * 时间类型   D-日期 、W-星期、M-月、Q-季 、Y-年  DM(非完整月的统计计算 如:5.1-5.22 )
     */
    @NotBlank(message = "dateType 不能为空")
    private String dateType;
    /**
     * 楼层Id
     */
    private String floorId;


}
