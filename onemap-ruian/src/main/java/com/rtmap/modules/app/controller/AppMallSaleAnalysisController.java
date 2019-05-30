package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.PageUtils;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.entity.FloorIndustryEntity;
import com.rtmap.modules.app.entity.IndustryFloorEntity;
import com.rtmap.modules.app.entity.MallSaleCompareEntity;
import com.rtmap.modules.app.entity.TrendEntity;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;
import com.rtmap.modules.app.service.MallSaleAnalysisService;
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
@RequestMapping("/mallSaleAnalysis")
public class AppMallSaleAnalysisController {
    @Autowired
    MallSaleAnalysisService sale;
    @Autowired
    MallShopAnalysisService mallShopAnalysisService;
    @Autowired
    ValidUtils validUtils;

    /**
     * 概况 计算 同比环比
     *
     * 查询时间类型
     * 天 D
     * 自然月 M
     * 年   Y
     * 非完整月  DM
     */
    @RequestMapping("/overview")
    public R overview(@Valid MallVo mallVo){
        log.info("marketVo ==>> {}", JSON.toJSONString(mallVo));
        MallSaleCompareEntity mallSaleCompareEntity = sale.mallSaleAnalysis(mallVo);
        return R.ok().put("list", mallSaleCompareEntity);
    }

    /**
     * mall-销售分析-趋势图
     *
     * 销售额  saleAmount
     * 日均销售坪效  saleEffect
     * 客单价  perCustomerPrice
     * 交易笔数 tradeBills
     * 会员消费额   memSaleAmount
     * 会员交易笔数 memTradeBills
     * 会员客单价   memPerPrice
     * @return
     */
    @RequestMapping("/trend/{dataType}")
    public R trend(@PathVariable("dataType")String dataType,@Valid MallVo mallVo){
        if (validUtils.notBlank(dataType)){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        List<TrendEntity> list = sale.mallSaleTrend(dataType,mallVo);
        return R.ok().put("list",list);
    }

    /**
     * 楼层业态分析
     * floor 楼层业态分析
     * industry 业态楼层分析
     */
    @RequestMapping("/floorIndustry/{dataType}")
    public R floorIndustry(@PathVariable("dataType") String dataType,@Valid MallVo mallVo){
        if (validUtils.notBlank(dataType,mallVo.getEndTime())){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        if (Constants.FLOOR.equals(dataType)){
            List<FloorIndustryEntity> list = sale.queryFloor(mallVo);
            return R.ok().put("list",list);
        }
        if (Constants.INDUSTRY.equals(dataType)){
            List<IndustryFloorEntity> list = sale.queryIndustry(mallVo);
            return R.ok().put("list",list);
        }
        return R.ok();
    }


    /**
     * 这块由于需求改动，接口移动到 mall销售分析
     * 店铺排行榜
     *     * 销售额  saleAmount
     *      * 日均销售坪效  saleEffect
     *      * 客单价  perCustomerPrice
     *      * 交易笔数 tradeBills
     */
    @RequestMapping("/rankingList/{dataType}")
    public R rankingList(@PathVariable("dataType") String dataType,@Valid ParameterVo parameterVo){
        if (validUtils.notBlank(dataType)){
            throw new RRException("参数异常,请联系管理员!",400);
        }
        PageUtils pageUtils = mallShopAnalysisService.rankingList(dataType,parameterVo);
        return R.ok().put("page",pageUtils);
    }


}
