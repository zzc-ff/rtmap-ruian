package com.rtmap.devtest;

import com.alibaba.fastjson.JSON;
import com.rtmap.DynamicDataSourceTest;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.controller.AppMarketController;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.MarketVo;
import com.rtmap.modules.app.service.GroupService;
import com.rtmap.modules.sys.service.SysUserMarketService;
import com.rtmap.modules.sys.service.SysUserService;
import com.rtmap.service.DynamicDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketTest {


    Logger logger = LoggerFactory.getLogger(DynamicDataSourceTest.class);

    @Autowired
    SysUserService sysUserService;

    @Autowired
    GroupService groupService;

    @Autowired
    AppMarketController appMarketController;


    /**
     * 20-项目驾驶舱-最近30天销售与交易
     */
    @Test
    public void MarketSaleAndTrade(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("D");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20190201");
        marketVo.setOffsetTime(-60);

        R r = appMarketController.saleAndTrade(marketVo);
        logger.info("resultListJson ==>> {}", JSON.toJSONString(r));
    }

    /**
     * 21-项目驾驶舱-最近30天车流与客流
     */
    @Test
    public void MarketCarAndCustomer(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("D");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20190115");
        marketVo.setOffsetTime(-30);

        R r = appMarketController.carAndCustomer(marketVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 22-项目驾驶舱-本年MALL、街区销售达成
     */
    @Test
    public void MarketAllYearAchieved(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("Y");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20190101");

        R r = appMarketController.AllYearAchieved(marketVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 23-项目驾驶舱-上月mall销售
     */
    @Test
    public void MarketLastMonthSale(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("M");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20190201");
        marketVo.setOffsetTime(-1);

        R r = appMarketController.lastMonthSale(marketVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 24-项目驾驶舱-上月mall客流、车流、客单价
     */
    @Test
    public void MarketCustomerAndCarAndPrice(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("M");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20190101");
        marketVo.setOffsetTime(-1);

        R r = appMarketController.customerAndCarAndPrice(marketVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 25-项目驾驶舱-当前MALL、街区会员
     */
    @Test
    public void MarketMemberCountAndIncrease(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("D");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20181201");
        marketVo.setOffsetTime(-1);

        R r = appMarketController.memberCountAndIncrease(marketVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 今日快报
     */
    @Test
    public void todayNews(){
        MarketVo marketVo = new MarketVo();
        marketVo.setGroupId("集团-1");
        marketVo.setMarketId("项目-11");
        R r = appMarketController.todayNews(marketVo);
        logger.info("入参 ==>> {}",JSON.toJSONString(marketVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 年度快报
     */
    @Test
    public void marketYearNews(){
        MarketVo marketVo = new MarketVo();
        marketVo.setGroupId("集团-1");
        marketVo.setMarketId("项目-11");
        R r = appMarketController.marketYearNews(marketVo);
        logger.info("入参 ==>> {}",JSON.toJSONString(marketVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 地图获取所有的 market 下的  mall
     */
    @Test
    public void queryAllMallOfMarket(){
        MarketVo marketVo = new MarketVo();
        marketVo.setGroupId("集团-1");
        marketVo.setMarketId("项目-11");
        R r = appMarketController.queryAllMallOfMarket(marketVo);
        logger.info("入参 ==>> {}",JSON.toJSONString(marketVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 实时销售
     */
    @Test
    public void realTimeSaleAmount(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setFloorId("楼层-1");
        mallVo.setDateType("M");
        mallVo.setStartTime("20190101");
        R r = appMarketController.realTimeSaleAmount(mallVo);
        logger.info("入参 ==>> {}",JSON.toJSONString(mallVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     *上月销售坪效
     */
    @Test
    public void saleEffect(){
        MallVo mallVo = new MallVo();
        mallVo.setGroupId("集团-1");
        mallVo.setMarketId("项目-11");
        mallVo.setMallId("地块-111");
        mallVo.setFloorId("楼层-1");
        mallVo.setDateType("M");
        mallVo.setStartTime("20190101");
        R r = appMarketController.saleEffect(mallVo);
        logger.info("入参 ==>> {}",JSON.toJSONString(mallVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 昨日关注-显示market底下销售额排名前十
     */
    @Test
    public void yesterdayMallSale(){
        MarketVo marketVo = new MarketVo();
        marketVo.setGroupId("集团-1");
        marketVo.setMarketId("项目-11");
        marketVo.setDateType("D");
        marketVo.setStartTime("20190101");
        R r = appMarketController.yesterdayMallSale(marketVo);
        logger.info("入参 ==>> {}",JSON.toJSONString(marketVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }



}
