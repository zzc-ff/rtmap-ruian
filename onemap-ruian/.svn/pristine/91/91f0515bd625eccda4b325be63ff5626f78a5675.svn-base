package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;
import com.rtmap.modules.app.service.MallFloorAnalysisService;
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
@RequestMapping("/mallFloorAnalysis")
public class AppMallFloorAnalysisController {

    @Autowired
    MallFloorAnalysisService mallFloorAnalysisService;
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
        List<FloorRankEntity> list = mallFloorAnalysisService.sort(dataType,parameterVo);
        return R.ok().put("list",list);
    }

    /**
     * 获取所有的楼层
     */
    @RequestMapping("/floorList")
    public R floorList( ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getGroupId(),parameterVo.getMarketId(),parameterVo.getMallId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        List<FloorListEntity> list = mallFloorAnalysisService.floorList(parameterVo);
        return R.ok().put("list",list);
    }

    /**
     * ------废弃接口-------
     * 楼层分析-对比分析
     *     * 销售额  saleAmount
     *      * 日均销售坪效  saleEffect
     *      * 客单价  perCustomerPrice
     *      * 交易笔数 tradeBills
     */
    @RequestMapping("/Contrast/{dataType}")
    public R Contrast(@PathVariable("dataType") String dataType,@Valid MallVo mallVo){
        if (validUtils.notBlank(dataType,mallVo.getGroupId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        log.info("mallVo ==>> {}", JSON.toJSONString(mallVo));
        List<FloorMergeEntity> list = mallFloorAnalysisService.Contrast(dataType,mallVo);
        return R.ok().put("list", list);
    }

    /**------废弃接口-------
     * 楼层分析-占比分析
     */
    @RequestMapping("/Proportion")
    public R Proportion(MallVo mallVo){
        List<FloorMergeEntity> list = mallFloorAnalysisService.Proportion(mallVo);
        return R.ok().put("list",list);
    }

    /**
     * 楼层分析-单楼层-同比环比
     */
    @RequestMapping("/floorCompare")
    public R floorCompare(@Valid MallVo mallVo){
        if (validUtils.notBlank(mallVo.getFloorId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        MallSaleCompareEntity list = mallFloorAnalysisService.floorCompare(mallVo);
        return R.ok().put("list",list);
    }

    /**
     * 楼层分析-单楼层-时间段内指标数据和
     * 销售额
     * 日均销售坪效
     * 客单价
     */
    @RequestMapping("/floorData")
    public R floorData(@Valid MallVo mallVo){
        if (validUtils.notBlank(mallVo.getFloorId())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        MallSaleEntity list = mallFloorAnalysisService.floorData(mallVo);
        return R.ok().put("list",list);
    }




    /**
     * 楼层分析-单楼层-时间段内每天的数据
     *     * 销售额  saleAmount
     *      * 日均销售坪效  saleEffect
     *      * 客单价  perCustomerPrice
     *      * 交易笔数 tradeBills
     */
    @RequestMapping("/trend/{dataType}")
    public R trend(@PathVariable("dataType") String dataType,@Valid MallVo mallVo){
        if (validUtils.notBlank(dataType,mallVo.getFloorId(),mallVo.getEndTime())){
            throw new RRException("参数异常，请联系管理员",400);
        }
        log.info("mallVo ==>> {}", JSON.toJSONString(mallVo));
        List<TrendEntity> list = mallFloorAnalysisService.trend(dataType,mallVo);
        return R.ok().put("list", list);
    }



}
