package com.rtmap.devtest;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.controller.AppGroupController;
import com.rtmap.modules.app.entity.vo.GroupVo;
import com.rtmap.modules.app.service.GroupService;
import com.rtmap.modules.sys.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTest {

    Logger logger = LoggerFactory.getLogger(GroupTest.class);

    @Autowired
    SysUserService sysUserService;

    @Autowired
    GroupService groupService;


    @Autowired
    AppGroupController appGroupController;

    /**
     * 今日快报
     */
    @Test
    public void  todayNews(){
        //参数： groupId startTime
        GroupVo groupVo = new GroupVo();
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190528");
        R r = appGroupController.todayNews(groupVo);
        logger.info("r ==>> {}",JSON.toJSONString(r));
    }


    /**
     * 13-集团驾驶舱-最近30天销售&交易
     */
    @Test
    public void sellAndTrade(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("D");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190101");
        groupVo.setOffsetTime(-30);
        R r = appGroupController.saleAndTrade(groupVo);
        logger.info("resultListJson ==>> {}", JSON.toJSONString(r));
    }

    /**
     * 14-集团驾驶舱-最近30天车流&客流
     */
    @Test
    public void carAndCustomer(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("D");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190115");
        groupVo.setOffsetTime(-30);
        R r = appGroupController.carAndCustomer(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 15-集团驾驶舱-上月销售
     */
    @Test
    public void lastMonthSale(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("M");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190201");
        groupVo.setOffsetTime(-1);
        R r = appGroupController.lastMonthSale(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 16-集团驾驶舱-上月项目客流、车流、客单价
     */
    @Test
    public void customerAndCarAndPrice(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("M");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190201");
        groupVo.setOffsetTime(-1);
        R r = appGroupController.customerAndCarAndPrice(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }



    /**
     * 17-集团驾驶舱-项目年度销售额
     */
    @Test
    public void
    AllYearSale(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("Y");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190101");
        R r = appGroupController.AllYearSale(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 18-集团驾驶舱-mall 年销售达成
     */
    @Test
    public void AllYearAchieved(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("Y");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190101");
        R r = appGroupController.AllYearAchieved(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 19-集团驾驶舱-当前会员
     */
    @Test
    public void memberCountAndIncrease(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("D");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20190101");
        R r = appGroupController.memberCountAndIncrease(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     *集团年度快报
     */
    @Test
    public void groupYearNews(){
        GroupVo groupVo = new GroupVo();
        groupVo.setDateType("D");
        groupVo.setGroupId("集团-1");
        groupVo.setStartTime("20180101");
        groupVo.setEndTime("20202131");
        R r = appGroupController.groupYearNews(groupVo);
        logger.info("参数 ==>> {}",JSON.toJSONString(groupVo));
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

    /**
     * 10、每个项目的实时销售额和
     */
    @Test
    public void marketRealData(){
        GroupVo groupVo = new GroupVo();
        groupVo.setGroupId("111");
        R r = appGroupController.marketRealData(groupVo);
        logger.info("resultListJson ==>> {}",JSON.toJSONString(r));
    }

}
