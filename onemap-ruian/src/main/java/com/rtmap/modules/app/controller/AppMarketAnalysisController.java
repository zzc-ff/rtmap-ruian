package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.MarketVo;
import com.rtmap.modules.app.service.MarketAnalysisService;
import com.rtmap.modules.app.utils.ValidUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * APP 项目综合分析
 *
 */
@RestController
@Slf4j
@RequestMapping("/marketAnalysis")
@Api("APP集团项目驾驶舱接口")
public class AppMarketAnalysisController {

    @Autowired
    MarketAnalysisService marketAnalysisService;
    @Autowired
    ValidUtils validUtils;
    /**
     * 经营概况
     * @return
     */
    @RequestMapping("/overview")
    public R overview(@Valid MarketVo marketVo){
        log.info("marketVo ==>> {}", JSON.toJSONString(marketVo));
        OverviewCompareEntity overviewEntity = marketAnalysisService.MarketAnalysis(marketVo);
        return R.ok().put("list", overviewEntity);
    }

    /**
     * 项目综合分析-趋势图
     * 销售额  saleAmount
     * 客流量   customerNum
     * 交易笔数 tradeAmount
     * 车流量   carNum
     * 会员消费额   memSaleAmount
     * 会员交易笔数 memTradeBills
     * 会员客单价   memPerPrice
     * @return
     */
    @RequestMapping("/trend/{dataType}")
    public R trend(@PathVariable("dataType")String dataType,@Valid MarketVo marketVo){
        if (validUtils.notBlank(dataType)){
            throw new RRException("参数异常，请联系管理员!",400);
        }
        List<TrendEntity> list = marketAnalysisService.marketTrend(dataType,marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 项目综合分析-获取时间段内的天气
     *
     */
    @RequestMapping("/weather")
    public R weather(MarketVo marketVo){
        if (validUtils.notBlank(marketVo.getMarketId(),marketVo.getGroupId(),marketVo.getStartTime(),marketVo.getEndTime())){
            throw new RRException("参数异常，请联系管理员!",400);
        }
        List<WeatherEntity>  list = marketAnalysisService.weather(marketVo);
        return R.ok().put("list",list);
    }


    /**
     * 项目综合分析-占比分析
     */
    @RequestMapping("/proportionAnalysis")
    public R proportionAnalysis(@Valid MarketVo marketVo){
        if (validUtils.notBlank(marketVo.getEndTime())){
            throw new RRException("参数异常，请联系管理员!",400);
        }
        Map<String,List<PropertyProportionEntity>> list = marketAnalysisService.proportionAnalysis(marketVo);
        return R.ok().put("list",list);
    }

    /**
     * 本年排名
     *
     */
    @RequestMapping("/sort/{dataType}")
    public R sort(@PathVariable("dataType") String dataType,MallVo mallVo){
        if (validUtils.notBlank(dataType,mallVo.getGroupId(),mallVo.getDateType(),mallVo.getStartTime())){
            throw new RRException("参数异常，请联系管理员!",400);
        }
        SortEntity sortEntity = marketAnalysisService.sort(dataType,mallVo);
        return R.ok().put("list",sortEntity);
    }

    /**
     * 业态分析
     */
    @RequestMapping("/industryAnalysis")
    public R industryAnalysis(@Valid MarketVo marketVo){
       List<MarketIndustryAnalysis> list = marketAnalysisService.industryAnalysis(marketVo);
        return R.ok().put("list",list);
    }





}
