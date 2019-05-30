package com.rtmap.devtest;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.controller.AppMallIndustryAnalysisController;
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
public class MallIndustryTest {
    @Autowired
    AppMallIndustryAnalysisController appMallIndustryAnalysisController;
    /**
     * 本年排行榜
     *
     * 销售额  saleAmount
     * 日均销售坪效  saleEffect
     * 客单价  perCustomerPrice
     * 交易笔数 tradeBills
     */
    @Test
    public void industrySort(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        parameterVo.setDateType("Y");
        parameterVo.setStartTime("20190101");

        R r = appMallIndustryAnalysisController.sort(Constants.SALEAMOUNT, parameterVo);

        log.info("出参 ==>> {}",JSON.toJSONString(r));
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
    }

    /**
     * 获取所有的楼层
     */
    @Test
    public void industryList(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("1");
        parameterVo.setMarketId("2");
        parameterVo.setMallId("3");
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
        R r = appMallIndustryAnalysisController.industryList(parameterVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 同比 环比
     */
    @Test
    public void compare(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        parameterVo.setIndustryId("1");
        parameterVo.setDateType("D");
        parameterVo.setStartTime("20190101");
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
        R r = appMallIndustryAnalysisController.industryCompare(parameterVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 时间段内数据和
     */
    @Test
    public void industryData(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        parameterVo.setIndustryId("业态-1");
        parameterVo.setDateType("D");
        parameterVo.setStartTime("20180101");
        parameterVo.setEndTime("20200101");
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
        R r = appMallIndustryAnalysisController.industryData(parameterVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 时间段内每天的数据
     */
    @Test
    public void trend(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        parameterVo.setIndustryId("业态-1");
        parameterVo.setDateType("D");
        parameterVo.setStartTime("20180101");
        parameterVo.setEndTime("20200101");
        R r = appMallIndustryAnalysisController.trend(Constants.SALEAMOUNT, parameterVo);
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }
}
