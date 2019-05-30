package com.rtmap.devtest;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.controller.AppMallFloorAnalysisController;
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
public class MallFloorTest {
    @Autowired
    AppMallFloorAnalysisController appMallFloorAnalysisController;

    /**
     * 本年排行榜
     *
     * 销售额  saleAmount
     * 日均销售坪效  saleEffect
     * 客单价  perCustomerPrice
     * 交易笔数 tradeBills
     */
    @Test
    public void sort(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        parameterVo.setDateType("Y");
        parameterVo.setStartTime("20190101");
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
        R r = appMallFloorAnalysisController.sort(Constants.SALEAMOUNT, parameterVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 获取所有的楼层
     */
    @Test
    public void floorList(){
        ParameterVo parameterVo = new ParameterVo();
        parameterVo.setGroupId("集团-1");
        parameterVo.setMarketId("项目-11");
        parameterVo.setMallId("地块-111");
        log.info("入参==>> {}", JSON.toJSONString(parameterVo));
        R r = appMallFloorAnalysisController.floorList(parameterVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 同比 环比
     */
    @Test
    public void compare(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setFloorId("1");
        mallVo.setDateType("D");
        mallVo.setStartTime("20190101");
        log.info("入参==>> {}", JSON.toJSONString(mallVo));
         R r = appMallFloorAnalysisController.floorCompare(mallVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 时间段内数据和
     */
    @Test
    public void floorData(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setFloorId("楼层-1");
        mallVo.setDateType("D");
        mallVo.setStartTime("20180101");
        mallVo.setEndTime("20200101");
        log.info("入参==>> {}", JSON.toJSONString(mallVo));
         R r = appMallFloorAnalysisController.floorData(mallVo);
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 时间段内每天的数据
     */
    @Test
    public void trend(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setFloorId("楼层-1");
        mallVo.setDateType("D");
        mallVo.setStartTime("20180101");
        mallVo.setEndTime("20200101");
        R r = appMallFloorAnalysisController.trend(Constants.SALEAMOUNT, mallVo);
        log.info("入参==>> {}", JSON.toJSONString(mallVo));
        log.info("出参 ==>> {}",JSON.toJSONString(r));
    }
}
