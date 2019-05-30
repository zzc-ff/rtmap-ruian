package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.R;
import com.rtmap.common.utils.RedisUtils;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.GroupVo;
import com.rtmap.modules.app.service.GroupService;
import com.rtmap.modules.app.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * APP集团项目驾驶舱
 *
 *
 */
@RestController
@Slf4j
@RequestMapping("/group")
@Api("APP集团项目驾驶舱接口")
public class AppGroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    ValidUtils validUtils;
    @Autowired
    RedisUtils redisUtils;

    /**
     * 今日快报
     * 实时数据
     */
    @RequestMapping("/todayNews")
    public R todayNews(GroupVo groupVo){
        if (validUtils.notBlank(groupVo.getGroupId(),groupVo.getStartTime())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }

        //销售额、交易笔数、客流量、车流量、交易会员数、会员消费额占比
        RealTimeEntity realTimeEntity = new RealTimeEntity();

        //销售额、交易笔数
        //key: 销售标记 + ~ + groupId + ~ + 日期   获取数据格式: 交易笔数,销售额
        String saleKey = "ShopBill"+"~"+groupVo.getGroupId()+"~"+groupVo.getStartTime();
        String saleData = redisUtils.get(saleKey);

        if (StringUtils.isBlank(saleData)){
            realTimeEntity.setSellAmount(0);
            realTimeEntity.setTradeAmount(0);
        }else{
            String[] saleArray = saleData.split(",");
            realTimeEntity.setSellAmount(Long.valueOf(saleArray[0]));
            realTimeEntity.setTradeAmount(Long.valueOf(saleArray[1]));
        }

        // 客流数据
        //key: 客流标记 + ~ + groupId + ~ + 日期   获取数据格式: 客流人次
        String customerKey = "ShopBill"+"~"+groupVo.getGroupId()+"~"+groupVo.getStartTime();
        String customerNum = redisUtils.get(customerKey);

        if (StringUtils.isBlank(customerNum)){
            realTimeEntity.setCustomerNum(0);
        }else{
            realTimeEntity.setCustomerNum(Long.valueOf(customerNum));
        }

        // 车流数据
        //key: 车流标记 + ~ + groupId + ~ + 日期   获取数据格式: 客流人次
        String carKey = "ShopBill"+"~"+groupVo.getGroupId()+"~"+groupVo.getStartTime();
        String carNum = redisUtils.get(carKey);

        if (StringUtils.isBlank(carNum)){
            realTimeEntity.setCarNum(0);
        }else{
            realTimeEntity.setCarNum(Long.valueOf(carNum));
        }

        //会员数据
        //key: 会员标记 + ~ + groupId + ~ + 日期   获取数据格式: 会员人数，会员消费额
        String memberKey =  "ShopBill"+"~"+groupVo.getGroupId()+"~"+groupVo.getStartTime();
        String memberData = redisUtils.get(memberKey);
        if (StringUtils.isBlank(memberData)){
            realTimeEntity.setMemberNum(0);
            realTimeEntity.setMemSaleAmount(0);
        }else{
            String[] memberArray = memberData.split(",");
            realTimeEntity.setMemberNum(Long.valueOf(memberArray[0]));
            realTimeEntity.setMemSaleAmount(Long.valueOf(memberArray[1]));
        }

        if (realTimeEntity.getSellAmount() !=0) {
            realTimeEntity.setMemSaleRatio(realTimeEntity.getMemSaleAmount() * 1.0 / realTimeEntity.getSellAmount());
        }
        //每个项目的销售额
        List<MarketRealSaleEntity> list = groupService.queryMarketOfGroup(groupVo.getGroupId());

        if (list != null && list.size() != 0){
            for (MarketRealSaleEntity m:list){
                String key = "ShopBill"+"~"+m.getMarketId()+"~"+groupVo.getStartTime();
                String marketSaleAmount = redisUtils.get(key);
                if (marketSaleAmount == null){
                    m.setSellAmount(0);
                }else{
                    String[] marketSaleAmountArray = marketSaleAmount.split(",");
                    m.setSellAmount(Long.valueOf(marketSaleAmountArray[0]));
                }
            }
        }

        return R.ok().put("groupData", realTimeEntity).put("marketList",list);
    }



    /**
     * 获取集团年度快报
     * 集团年度销售额、年度客流人次、车流、 会员人数
     * 同比、         同比、        同比、消费额占比
     * 集团达成率
     */
    @RequestMapping("/market/groupYearNews")
    public R groupYearNews(GroupVo groupVo){
        if (validUtils.notBlank(groupVo.getStartTime(),groupVo.getGroupId())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }

        //获取实时数据
        RealTimeCompareEntity realTimeCompareEntity = new RealTimeCompareEntity();

        //销售额、交易笔数
        //key: 销售标记 + ~ + groupId + ~ + 日期   获取数据格式: 交易笔数,销售额
        String saleKey = "ShopBill"+"~"+"groupId"+"~"+"20190528";
        String saleData = redisUtils.get(saleKey);

        if (StringUtils.isBlank(saleData)){
            realTimeCompareEntity.setSellAmount(0);
            realTimeCompareEntity.setTradeAmount(0);
        }else{
            String[] saleArray = saleData.split(",");
            realTimeCompareEntity.setSellAmount(Long.valueOf(saleArray[0]));
            realTimeCompareEntity.setTradeAmount(Long.valueOf(saleArray[1]));
        }

        // 客流数据
        //key: 客流标记 + ~ + groupId + ~ + 日期   获取数据格式: 客流人次
        String customerKey = "ShopBill"+"~"+"groupId"+"~"+"20190528";
        String customerNum = redisUtils.get(customerKey);

        if (StringUtils.isBlank(customerNum)){
            realTimeCompareEntity.setCustomerNum(0);
        }else{
            realTimeCompareEntity.setCustomerNum(Long.valueOf(customerNum));
        }

        // 车流数据
        //key: 车流标记 + ~ + groupId + ~ + 日期   获取数据格式: 客流人次
        String carKey = "ShopBill"+"~"+"groupId"+"~"+"20190528";
        String carNum = redisUtils.get(carKey);

        if (StringUtils.isBlank(carNum)){
            realTimeCompareEntity.setCarNum(0);
        }else{
            realTimeCompareEntity.setCarNum(Long.valueOf(carNum));
        }

        //会员数据
        //key: 会员标记 + ~ + groupId + ~ + 日期   获取数据格式: 会员人数，会员消费额
        String memberKey =  "ShopBill"+"~"+"groupId"+"~"+"20190528";
        String memberData = redisUtils.get(memberKey);
        if (StringUtils.isBlank(memberData)){
            realTimeCompareEntity.setMemberNum(0);
            realTimeCompareEntity.setMemSaleAmount(0);
        }else{
            String[] memberArray = memberData.split(",");
            realTimeCompareEntity.setMemberNum(Long.valueOf(memberArray[0]));
            realTimeCompareEntity.setMemSaleAmount(Long.valueOf(memberArray[1]));
        }

        if (realTimeCompareEntity.getSellAmount() != 0){
            realTimeCompareEntity.setMemSaleRatio(realTimeCompareEntity.getMemSaleAmount() * 1.0 / realTimeCompareEntity.getSellAmount());
        }

        //获取去年数据计算同比
        RealTimeEntity lastYearData = groupService.lastYearData(groupVo);

        if (lastYearData != null) {
           double carSimilar = realTimeCompareEntity.getCarSimilar() * 1.0 / lastYearData.getCarNum();
            realTimeCompareEntity.setCarSimilar(carSimilar);

           double customerSimilar = realTimeCompareEntity.getCustomerNum() * 1.0 /lastYearData.getCustomerNum();
            realTimeCompareEntity.setCustomerSimilar(customerSimilar);

            double memSaleRatio = realTimeCompareEntity.getMemSaleAmount() * 1.0 / lastYearData.getSellAmount();
            realTimeCompareEntity.setMemSaleRatio(memSaleRatio);

            //获取年度计划销售额
            if (realTimeCompareEntity.getSellAmount() != 0) {
                String planSaleAmount = groupService.queryGroupPlanSale(groupVo);
                if (planSaleAmount != null) {
                    realTimeCompareEntity.setSaleReach(realTimeCompareEntity.getSellAmount() * 1.0 / Long.valueOf(planSaleAmount));
                }
            }
        }

        return R.ok().put("groupRealTimeData",realTimeCompareEntity);
    }

    /**
     * 获取集团下所有的项目
     */
    @RequestMapping("/getAllMarketOfGroup")
    public R getAllMarketOfGroup(GroupVo groupVo){
        if (validUtils.notBlank(groupVo.getGroupId())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }
        List<MarketRealSaleEntity> list = groupService.queryMarketOfGroup(groupVo.getGroupId());
        List<MarketEntity> marketList = new ArrayList<>();
        if (list != null && list.size() != 0){
            for (MarketRealSaleEntity m : list){
                MarketEntity market = new MarketEntity();
                market.setMarketId(m.getMarketId());
                market.setMarketName(m.getMarketName());
                marketList.add(market);
            }
        }
        return R.ok().put("list",list);
    }

    /**
     * 每个项目的实时销售额和
     */
    @RequestMapping("/marketRealData")
    public R marketRealData(GroupVo groupVo){
        if (validUtils.notBlank(groupVo.getGroupId(),groupVo.getStartTime())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }
        //获取集团下所有的market
        List<MarketRealSaleEntity> allMarketList = groupService.queryMarketOfGroup(groupVo.getGroupId());

        //redis 获取数据
        List<MarketRealEntity> list = new ArrayList<>();
        if (allMarketList != null && allMarketList.size() != 0){
            for (MarketRealSaleEntity market : allMarketList) {
                MarketRealEntity m = new MarketRealEntity();
                m.setMarketId(market.getMarketId());
                m.setMarketName(market.getMarketName());
                m.setLat(market.getLat());
                m.setLng(market.getLng());

                //销售额、交易笔数
                //key: 销售标记 + ~ + groupId + ~ + 日期   获取数据格式: 交易笔数,销售额
                String saleKey = "ShopBill"+"~"+market.getMarketId()+"~"+"20190528";
                String saleData = redisUtils.get(saleKey);

                if (StringUtils.isBlank(saleData)){
                    m.setSellAmount(0);
                    m.setTradeAmount(0);
                }else{
                    String[] saleArray = saleData.split(",");
                    m.setSellAmount(Long.valueOf(saleArray[0]));
                    m.setTradeAmount(Long.valueOf(saleArray[1]));
                }
                list.add(m);
            }
        }

        return R.ok().put("list",list);
    }

    /**
     * 最近30天销售与交易
     */
    @RequestMapping("/saleAndTrade")
    public R saleAndTrade(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<SaleAndTradeEntity> list = groupService.saleAndTrade(groupVo);
        return R.ok().put("list",list);
    }

    /**
     * 最近30天车流与客流
     */
    @RequestMapping("/carAndCustomer")
    public R carAndCustomer(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<CarAndCustomerEntity> list = groupService.carAndCustomer(groupVo);
        return R.ok().put("list",list);

    }

    /**
     * 上月项目销售
     */
    @RequestMapping("/market/lastMonthSale")
    @ApiOperation("上月项目销售")
    public R lastMonthSale(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<MarketLastMonthEntity> list = groupService.lastMonthSale(groupVo);
        return R.ok().put("list",list);

    }

    /**
     * 上月项目客流、车流、客单价
     */
    @RequestMapping("/market/customerAndCarAndPrice")
    @ApiOperation("上月项目客流、车流、客单价")
    public R customerAndCarAndPrice(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<MarketCustomerCarPriceEntity> list =  groupService.customerAndCarAndPrice(groupVo);
        return R.ok().put("list",list);
    }

    /**
     * 当前项目会员
     */
    @RequestMapping("/market/memberCountAndIncrease")
    @ApiOperation("当前项目会员与新增会员")
    public R memberCountAndIncrease(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<MarketMemberEntity> list = groupService.memberCountAndIncrease(groupVo);
        return R.ok().put("list",list);
    }

    /**
     * 项目年度销售额
     */
    @RequestMapping("/market/allYearSale")
    @ApiOperation("项目年度销售额")
    public R AllYearSale(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<MarketYearSaleEntity> list =  groupService.AllYearSale(groupVo);
        return R.ok().put("list",list);

    }

    /**
     * MALL、街区年度销售达成
     *
     * 直接走统一查询的大接口
     */
    @RequestMapping("/market/allYearAchieved")
    @ApiOperation("MALL、街区年度销售达成")
    public R AllYearAchieved(@Valid GroupVo groupVo){
        log.info("groupVoJson ==>> {}", JSON.toJSONString(groupVo));
        List<MallYearAchievedEntity> list =  groupService.AllYearAchieved(groupVo);
        return R.ok().put("list",list);
    }





}
