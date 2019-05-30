package com.rtmap.modules.app.entity;

import lombok.Data;

@Data
public class MallMemberEntity {
    /**
     * mall id
     */
    private String mallId;
    /**
     * mall 名称
     */
    private String mallName;
    /**
     * 当前mall注册会员人数
     */
    private int memberRegisterNum;
    /**
     * 当前mall昨日新增会员
     */
    private int memberIncreaseNum;
}
