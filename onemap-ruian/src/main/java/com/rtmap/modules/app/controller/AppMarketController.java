package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.R;
import com.rtmap.common.utils.RedisUtils;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.MarketVo;
import com.rtmap.modules.app.service.MarketService;
import com.rtmap.modules.app.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * 项目驾驶舱
 */
@RestController
@Slf4j
@RequestMapping("/market")
@Api("项目驾驶舱")
public class AppMarketController {

    @Autowired
    MarketService marketService;
    @Autowired
    ValidUtils validUtils;
    @Autowired
    RedisUtils redisUtils;

    /**
     * 今日快报
     */
    @RequestMapping("/todayNews")
    @ApiOperation("集团今日快报")
    public R todayNews(MarketVo marketVo){
        //参数： groupId marketId
        if (validUtils.notBlank(marketVo.getGroupId(),marketVo.getMarketId(),marketVo.getStartTime())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }

        //销售额、交易笔数、客流量、车流量、交易会员数、会员消费额占比
        RealTimeEntity realTimeEntity = new RealTimeEntity();

        //销售额、交易笔数
        //key: 销售标记 + ~ + groupId + ~ + 日期   获取数据格式: 交易笔数,销售额
        String saleKey = "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
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
        String customerKey = "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
        String customerNum = redisUtils.get(customerKey);

        if (StringUtils.isBlank(customerNum)){
            realTimeEntity.setCustomerNum(0);
        }else{
            realTimeEntity.setCustomerNum(Long.valueOf(customerNum));
        }

        // 车流数据
        //key: 车流标记 + ~ + groupId + ~ + 日期   获取数据格式: 客流人次
        String carKey = "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
        String carNum = redisUtils.get(carKey);

        if (StringUtils.isBlank(carNum)){
            realTimeEntity.setCarNum(0);
        }else{
            realTimeEntity.setCarNum(Long.valueOf(carNum));
        }

        //会员数据
        //key: 会员标记 + ~ + groupId + ~ + 日期   获取数据格式: 会员人数，会员消费额
        String memberKey =  "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
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
        List<MallRealSaleEntity> list = new ArrayList<>();
        List<MallEntity>  mallList = marketService.queryAllMallOfMarket(marketVo);

        if (mallList != null && mallList.size() != 0){
            for (MallEntity mall:mallList){
                MallRealSaleEntity m = new MallRealSaleEntity();
                m.setMallId(mall.getMallId());
                m.setMallName(mall.getMallName());
                String key = "ShopBill"+"~"+mall.getMallId()+"~"+marketVo.getStartTime();
                String mallSaleAmount = redisUtils.get(key);
                if (mallSaleAmount == null){
                    m.setSellAmount(0);
                }else{
                    String[] mallSaleAmountArray = mallSaleAmount.split(",");
                    m.setSellAmount(Long.valueOf(mallSaleAmountArray[0]));
                }
                list.add(m);
            }
        }

        return R.ok().put("marketData", realTimeEntity).put("mallList",list);
    }

    /**
     * 我的关注-昨日销售额
     */
    @RequestMapping("/yesterdayMallSale")
    @ApiOperation("集团今日快报")
    public R yesterdayMallSale(@Valid MarketVo marketVo){
        //没有关注功能，项目昨日销售额前10 的店铺
        List<FocusShopEntity> list = marketService.yesterdayMallSale(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 获取项目年度快报
     * 项目年度销售额、年度客流人次、车流、 会员人数
     * 同比、         同比、        同比、消费额占比
     * 项目达成率
     */
    @RequestMapping("/mall/marketYearNews")
    @ApiOperation("获取项目列表以及项目底下mall数量")
    public R marketYearNews(MarketVo marketVo){
        if (validUtils.notBlank(marketVo.getGroupId(),marketVo.getMarketId(),marketVo.getStartTime())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }

        //获取实时数据
        RealTimeCompareEntity realTimeCompareEntity = new RealTimeCompareEntity();

        //销售额、交易笔数
        //key: 销售标记 + ~ + groupId + ~ + 日期   获取数据格式: 交易笔数,销售额
        String saleKey = "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
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
        String customerKey = "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
        String customerNum = redisUtils.get(customerKey);

        if (StringUtils.isBlank(customerNum)){
            realTimeCompareEntity.setCustomerNum(0);
        }else{
            realTimeCompareEntity.setCustomerNum(Long.valueOf(customerNum));
        }

        // 车流数据
        //key: 车流标记 + ~ + groupId + ~ + 日期   获取数据格式: 客流人次
        String carKey = "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
        String carNum = redisUtils.get(carKey);

        if (StringUtils.isBlank(carNum)){
            realTimeCompareEntity.setCarNum(0);
        }else{
            realTimeCompareEntity.setCarNum(Long.valueOf(carNum));
        }

        //会员数据
        //key: 会员标记 + ~ + groupId + ~ + 日期   获取数据格式: 会员人数，会员消费额
        String memberKey =  "ShopBill"+"~"+marketVo.getMarketId()+"~"+marketVo.getStartTime();
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
        RealTimeEntity lastYearData = marketService.lastYearData(marketVo);

        if (lastYearData != null) {
            double carSimilar = realTimeCompareEntity.getCarSimilar() * 1.0 / lastYearData.getCarNum();
            realTimeCompareEntity.setCarSimilar(carSimilar);

            double customerSimilar = realTimeCompareEntity.getCustomerNum() * 1.0 /lastYearData.getCustomerNum();
            realTimeCompareEntity.setCustomerSimilar(customerSimilar);

            double memSaleRatio = realTimeCompareEntity.getMemSaleAmount() * 1.0 / lastYearData.getSellAmount();
            realTimeCompareEntity.setMemSaleRatio(memSaleRatio);

            //获取年度计划销售额
            if (realTimeCompareEntity.getSellAmount() != 0) {
                String planSaleAmount = marketService.queryMarketPlanSale(marketVo);
                if (planSaleAmount != null) {
                    realTimeCompareEntity.setSaleReach(realTimeCompareEntity.getSellAmount() * 1.0 / Long.valueOf(planSaleAmount));
                }
            }
        }

        return R.ok().put("marketRealTimeData",realTimeCompareEntity);
    }

    /**
     * 获取项目下所有的mall,用于地图切换mall
     */
    @RequestMapping("/getAllMallOfMarket")
    @ApiOperation("获取项目下所有的mall")
    public R queryAllMallOfMarket(MarketVo marketVo){
        if (validUtils.notBlank(marketVo.getGroupId(),marketVo.getMarketId())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }
        List<MallEntity> list = marketService.queryAllMallOfMarket(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 获取mall下楼层的所有的店铺的实时销售额
     */
    @RequestMapping("/realTimeSaleAmount")
    public R realTimeSaleAmount(MallVo mallVo){
        //参数： groupId marketId mallId floorId(存放地图上的floor)
        if (validUtils.notBlank(mallVo.getGroupId(),mallVo.getMarketId(),mallVo.getMallId(),mallVo.getFloorId(),mallVo.getStartTime())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }
        // 获取每个店铺的 poi_no shopId shopName 实时销售额
        List<MarketRealSaleAmountEntity> list = marketService.realTimeSaleAmount(mallVo);
       if (list!= null && list.size() !=0){
           for (MarketRealSaleAmountEntity m: list){
               //销售额、交易笔数
               //key: 销售标记 + ~ + groupId + ~ + 日期   获取数据格式: 交易笔数,销售额
               String saleKey = "ShopBill"+"~"+m.getShopId()+"~"+mallVo.getStartTime();
               String saleData = redisUtils.get(saleKey);

               if (StringUtils.isBlank(saleData)){
                   m.setSellAmount(0);
               }else{
                   String[] saleArray = saleData.split(",");
                   m.setSellAmount(Long.valueOf(saleArray[0]));
               }
           }
       }
        return R.ok().put("list",list);
    }

    /**
     * 获取mall下当前楼层所有的店铺的上月销售坪效
     */
    @RequestMapping("/saleEffect")
    public R saleEffect(@Valid MallVo mallVo){
        if (validUtils.notBlank(mallVo.getFloorId())){
            throw new RRException("参数异常，请联系管理人员！",400);
        }
        return marketService.saleEffect(mallVo);
    }


    /**
     * 最近30天销售与交易
     */
    @RequestMapping("/saleAndTrade")
    @ApiOperation("项目最近30天销售与交易数据")
    public R saleAndTrade(@Valid MarketVo marketVo){

        log.info("requestJson ==>> {}", JSON.toJSONString(marketVo));
        List<SaleAndTradeEntity> list =  marketService.saleAndTrade(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 最近30天车流与客流
     */
    @RequestMapping("/carAndCustomer")
    @ApiOperation("项目最近30天车流与客流数据")
    public R carAndCustomer(@Valid MarketVo marketVo){
        log.info("requestJson ==>> {}", JSON.toJSONString(marketVo));
        List<CarAndCustomerEntity> list = marketService.carAndCustomer(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 本年MALL、街区销售达成
     */
    @RequestMapping("/mall/allYearAchieved")
    @ApiOperation("本年MALL、街区销售达成")
    public R AllYearAchieved(@Valid MarketVo marketVo){
        log.info("requestJson ==>> {}", JSON.toJSONString(marketVo));
        List<MallYearAchievedEntity> list = marketService.AllYearAchieved(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 上月mall销售
     */
    @RequestMapping("/mall/lastMonthSale")
    @ApiOperation("上月mall销售")
    public R lastMonthSale(@Valid MarketVo marketVo){
        log.info("requestJson ==>> {}", JSON.toJSONString(marketVo));
        List<MallLastMonthEntity> list = marketService.lastMonthSale(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 上月mall客流、车流、客单价
     */
    @RequestMapping("/mall/customerAndCarAndPrice")
    @ApiOperation("上月mall客流、车流、客单价")
    public R customerAndCarAndPrice(@Valid MarketVo marketVo){
        log.info("requestJson ==>> {}", JSON.toJSONString(marketVo));
        List<MallCustomerCarPriceEntity> list = marketService.customerAndCarAndPrice(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 当前mall、街区会员
     */
    @RequestMapping("/mall/memberCountAndIncrease")
    @ApiOperation("当前mall、街区会员")
    public R memberCountAndIncrease(@Valid MarketVo marketVo){
        log.info("requestJson ==>> {}", JSON.toJSONString(marketVo));
        List<MallMemberEntity> list = marketService.memberCountAndIncrease(marketVo);
        return R.ok().put("list",list);
    }


}
