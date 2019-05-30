package com.rtmap.modules.app.utils;

public class Constants {
    /**
     * 统一查询接口 返回成功的code
     */
    public static final String SUCCESSCODE = "000000";
    /**
     * 请求的ContentType类型
     */
    public static final String TYPEJSON = "application/json";


    /**
     * * 销售额  saleAmount
     *      * 客流量   customerNum
     *      * 交易笔数 tradeBills
     *      * 车流量   carNum
     *      *新增会员数  memIncrease
     *      * 会员消费额   memSaleAmount
     *      * 会员交易笔数 memTradeBills
     *      * 会员客单价   memPerPrice
     */
    public static final String SALEAMOUNT = "saleAmount";
    public static final String CUSTOMERNUM = "customerNum";
    public static final String TRADEBILLS = "tradeBills";
    public static final String CARNUM = "carNum";
    public static final String MEMINCREASE = "memIncrease";
    public static final String MEMSALEAMOUNT = "memSaleAmount";
    public static final String MEMTRADEBILLS = "memTradeBills";
    public static final String MEMPERPRICE = "memPerPrice";


    /**
     * mall-销售分析-趋势图
     *
     * 销售额  saleAmount
     * 日均销售坪效  saleEffect  *
     * 客单价  perCustomerPrice *
     * 交易笔数 tradeBills
     * 会员消费额   memSaleAmount
     * 会员交易笔数 memTradeBills
     * 会员客单价   memPerPrice
     */
    public static final String SALEEFFECT = "saleEffect";
    public static final String PERCUSTOMERPRICE = "perCustomerPrice";


    /**
     * 时间类型   D-日期 、W-星期、M-月、Q-季 、Y-年  DM(非完整月的统计计算 如:5.1-5.22 )*/

    public static final String D = "D";
    public static final String W = "W";
    public static final String M = "M";
    public static final String Q = "Q";
    public static final String Y = "Y";
    public static final String DM = "DM";


    /**
     * floor 楼层的业态分析
     * industry  业态的楼层分析
     */
    public static final String FLOOR = "floor";
    public static final String INDUSTRY = "industry";
    /**
     * 项目目前仅限一级业态
     * 业态等级
     */
    public static final int INDUSTRYLEVEL = 1;

}
