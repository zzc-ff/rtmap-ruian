package com.rtmap.devtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.controller.AppMallSaleAnalysisController;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;
import com.rtmap.modules.app.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MallSaleAnalysisTest {

    @Autowired
    AppMallSaleAnalysisController sale;


    /**
     * 概况
     */
    @Test
    public void overview(){
        //日、上月、本月、本年
        //日
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setDateType("D");
        mallVo.setStartTime("20190101");
        R rDay = sale.overview(mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo));
        log.info("返回参数: {}",JSON.toJSONString(rDay));
        //上月
        mallVo.setDateType("M");
        R rMonth = sale.overview(mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo));
        log.info("返回参数: {}",JSON.toJSONString(rMonth));
        //本月
        mallVo.setDateType("DM");
        R rNMonth = sale.overview(mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo));
        log.info("返回参数: {}",JSON.toJSONString(rNMonth));
        //年
        mallVo.setDateType("Y");
        R Year = sale.overview(mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo));
        log.info("返回参数: {}",JSON.toJSONString(Year));
    }

    /**
     * 趋势图
     */
    @Test
    public void tend(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setDateType("D");
        mallVo.setStartTime("20180101");
        mallVo.setEndTime("20200101");
        R rSaleAmount = sale.trend(Constants.SALEAMOUNT, mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo));
        log.info("返回参数: {}",JSON.toJSONString(rSaleAmount));

        R rTradBills = sale.trend(Constants.TRADEBILLS, mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo));
        log.info("返回参数: {}",JSON.toJSONString(rTradBills));
    }


    /**
     * 楼层业态分析
     * floor 楼层业态分析
     * industry 业态楼层分析
     */
    @Test
    public void floorIndustry(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setDateType("D");
        mallVo.setStartTime("20180101");
        mallVo.setEndTime("20191231");

        R floor = sale.floorIndustry(Constants.FLOOR, mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo, SerializerFeature.WriteNullStringAsEmpty));
        log.info("返回参数: {}",JSON.toJSONString(floor));

        R industry = sale.floorIndustry(Constants.INDUSTRY, mallVo);
        log.info("请求参数: {}", JSON.toJSONString(mallVo,SerializerFeature.WriteNullStringAsEmpty));
        log.info("返回参数: {}",JSON.toJSONString(industry));

    }

    @Test
    public void  rankList(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        parameterVo.setDateType("Y");
        parameterVo.setStartTime("20190101");
        R r = sale.rankingList(Constants.SALEAMOUNT, parameterVo);
        log.info("请求参数: {}", JSON.toJSONString(parameterVo,SerializerFeature.WriteNullStringAsEmpty));
        log.info("返回参数: {}",JSON.toJSONString(r));
    }
}
