package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.PageUtils;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;
import com.rtmap.modules.app.service.MallShopAnalysisService;
import com.rtmap.modules.app.utils.Constants;
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
@RequestMapping("/mallShopAnalysis")
public class AppMallShopAnalysisController {

    @Autowired
    MallShopAnalysisService mallShopAnalysisService;
    @Autowired
    ValidUtils validUtils;
    /**
     * 波士顿图-所有的店铺数据
     */
    @RequestMapping("/bostonShopList")
    public R bostonShopList(@Valid ParameterVo parameterVo){
        return mallShopAnalysisService.bostonShopList(parameterVo);
    }



    /**
     * 店铺分析-单店铺-同比环比
     *
     * 默认取销售额最大的店铺 ？？？？？
     */
    @RequestMapping("/shopCompare")
    public R shopCompare(@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getShopId())){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        MallSaleCompareEntity mallSaleCompareEntity = mallShopAnalysisService.shopCompare(parameterVo);
        return R.ok().put("list",mallSaleCompareEntity);
    }


    /**
     * 店铺分析-时间段内指标数据和
     * 销售额
     * 日均销售坪效
     * 交易笔数
     * 客单价
     */
    @RequestMapping("/shopData")
    public R shopData(@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getShopId())){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        MallSaleEntity list = mallShopAnalysisService.shopData(parameterVo);
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
    public R trend(@PathVariable("dataType") String dataType,@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(dataType,parameterVo.getShopId(),parameterVo.getEndTime())){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        log.info("mallVo ==>> {}", JSON.toJSONString(parameterVo));
        List<TrendEntity> list = mallShopAnalysisService.trend(dataType,parameterVo);
        return R.ok().put("list", list);
    }


    /**
     * 店铺排名表 分页
     *
     * 全的店铺分析表，带四种排序
     */
    @RequestMapping("/shopListWithSort")
    public R shopListWithSort(@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(parameterVo.getWord(),parameterVo.getLift())){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        PageUtils pageUtils = mallShopAnalysisService.shopListWithSort(parameterVo);
        return R.ok().put("list",pageUtils);
    }



}
