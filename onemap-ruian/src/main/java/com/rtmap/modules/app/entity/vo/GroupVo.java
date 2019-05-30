package com.rtmap.modules.app.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GroupVo {
    /**
     * groupId
     */
    @NotBlank(message = "groupId 不能为空")
    private String groupId;
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
     * 时间类型   D-日期 、W-星期、M-月、Q-季 、Y-年
     */
    @NotBlank(message = "dateType 不能为空")
    private String dateType;
    /**
     * 结束时间
     */
    private String endTime;


}
