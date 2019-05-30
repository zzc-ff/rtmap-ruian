package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;
import com.rtmap.modules.app.service.MallFloorAnalysisService;
import com.rtmap.modules.app.service.MallIndustryAnalysisService;
import com.rtmap.modules.app.utils.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/mallIndustryAnalysis")
public class AppMallIndustryAnalysisController {

    @Autowired
    MallIndustryAnalysisService mallIndustryAnalysisService;
    @Autowired
    ValidUtils validUtils;

    /**
     * 本年排行榜
     *
     * 销售额  saleAmount
     * 日均销售坪效  saleEffect
     * 客单价  perCustomerPrice
     * 交易笔数 tradeBills
     */
    @RequestMapping("/sort/{dataType}")
    public R sort(@PathVariable("dataType") String dataType,@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(dataType)){
            throw new RRException("参数异常，请联系管理员",400);
        }
        List<IndustryRankEntity> list = mallIndustryAnalysisService.sort(dataType,parameterVo);
        return R.ok().put("list",list);
    }

    /**
     * 获取所有的业态
     */
    @RequestMapping("/industryList")
    public R industryList(ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getGroupId(),parameterVo.getMarketId(),parameterVo.getMallId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        List<IndustryListEntity> list = mallIndustryAnalysisService.industryList(parameterVo);
        return R.ok().put("list",list);
    }

    /**--- 废弃接口----
     * 业态分析-对比分析
     *     * 销售额  saleAmount
     *      * 日均销售坪效  saleEffect
     *      * 客单价  perCustomerPrice
     *      * 交易笔数 tradeBills
     */
    @RequestMapping("/Contrast/{dataType}")
    public R Contrast(@PathVariable("dataType") String dataType,@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(dataType)){
            throw new RRException("参数异常，请联系管理员",400);
        }
        log.info("mallVo ==>> {}", JSON.toJSONString(parameterVo));
        List<IndustryMergeEntity> list = mallIndustryAnalysisService.Contrast(dataType,parameterVo);
        return R.ok().put("list", list);
    }

    /**---废弃接口----
     * 业态分析-占比分析
     */
    @RequestMapping("/Proportion")
    public R Proportion(@Valid ParameterVo parameterVo){
        List<IndustryMergeEntity> list = mallIndustryAnalysisService.Proportion(parameterVo);
        return R.ok().put("list",list);
    }

    /**
     * 业态分析-单楼层-同比环比
     */
    @RequestMapping("/industryCompare")
    public R industryCompare(@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getIndustryId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        MallSaleCompareEntity list = mallIndustryAnalysisService.industryCompare(parameterVo);
        return R.ok().put("list",list);
    }

    /**
     * 楼层分析-单楼层-时间段内指标数据和
     * 销售额
     * 日均销售坪效
     * 客单价
     */
    @RequestMapping("/industryData")
    public R industryData(@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getEndTime(),parameterVo.getIndustryId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        MallSaleEntity list = mallIndustryAnalysisService.industryData(parameterVo);
        return R.ok().put("list",list);
    }


    /**
     * 业态分析-单楼层-时间段内每天的数据
     *     * 销售额  saleAmount
     *      * 日均销售坪效  saleEffect
     *      * 客单价  perCustomerPrice
     *      * 交易笔数 tradeBills
     */
    @RequestMapping("/trend/{dataType}")
    public R trend(@PathVariable("dataType") String dataType,@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(dataType,parameterVo.getIndustryId(),parameterVo.getEndTime())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        log.info("mallVo ==>> {}", JSON.toJSONString(parameterVo));
        List<TrendEntity> list = mallIndustryAnalysisService.trend(dataType,parameterVo);
        return R.ok().put("list", list);
    }

}
