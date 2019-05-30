package com.rtmap.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.rtmap.modules.app.dao.GroupDao;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.GroupVo;
import com.rtmap.modules.app.service.GroupService;
import com.rtmap.modules.app.utils.Constants;
import com.rtmap.modules.app.utils.HttpUtil;
import com.rtmap.modules.app.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    @Autowired
    TimeUtils timeUtils;
    @Value("${common.request.url}")
    String urlHeader;
    @Autowired
    HttpUtil httpUtil;
    @Resource
    GroupDao groupDao;


    @Override
    public List<SaleAndTradeEntity> saleAndTrade(GroupVo groupVo) {

        String startTime = timeUtils.getDay(groupVo.getStartTime(),groupVo.getOffsetTime());

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setStartTime(startTime);
        requestEntity.setEndTime(groupVo.getStartTime());
        requestEntity.setGroupId(groupVo.getGroupId());

        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));

        String URL = urlHeader + "13";
        log.info("URL ==>> {}",URL);

        return httpUtil.httpUtil(URL,requestEntity,SaleAndTradeEntity.class);
    }

    @Override
    public List<CarAndCustomerEntity> carAndCustomer(GroupVo groupVo)   {
        String startTime = timeUtils.getDay(groupVo.getStartTime(),groupVo.getOffsetTime());

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setStartTime(startTime);
        requestEntity.setEndTime(groupVo.getStartTime());
        requestEntity.setGroupId(groupVo.getGroupId());
        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));

        //获取车流数据
        String carURL = urlHeader + "14";
        log.info("URL ==>> {}",carURL);

        return httpUtil.httpUtil(carURL, requestEntity,CarAndCustomerEntity.class);
    }

    @Override
    public List<MarketLastMonthEntity> lastMonthSale(GroupVo groupVo)  {

        String lastMonth = timeUtils.getLastMonth(groupVo.getStartTime(),groupVo.getOffsetTime());

        //获取消费额、销售坪效、会员消费额
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setStartTime(lastMonth);
        requestEntity.setGroupId(groupVo.getGroupId());

        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));

        String URL = urlHeader + "15";
        log.info("URL ==>> {}",URL);

        return httpUtil.httpUtil(URL, requestEntity,MarketLastMonthEntity.class);
    }


    @Override
    public List<MarketCustomerCarPriceEntity> customerAndCarAndPrice(GroupVo groupVo)  {
        String lastMonth = timeUtils.getLastMonth(groupVo.getStartTime(),groupVo.getOffsetTime());

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setStartTime(lastMonth);
        requestEntity.setGroupId(groupVo.getGroupId());

        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));
        //获取项目车流
        String URL = urlHeader + "16";
        log.info("URL ==>> {}",URL);

        return httpUtil.httpUtil(URL, requestEntity,MarketCustomerCarPriceEntity.class);
    }

    @Override
    public List<MarketYearSaleEntity> AllYearSale(GroupVo groupVo) {

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setGroupId(groupVo.getGroupId());
        requestEntity.setStartTime(groupVo.getStartTime());

        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));

        String URL = urlHeader + "17";
        log.info("URL ==>> {}",URL);

        return  httpUtil.httpUtil(URL,requestEntity,MarketYearSaleEntity.class);
    }

    @Override
    public List<MallYearAchievedEntity> AllYearAchieved(GroupVo groupVo) {

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setStartTime(groupVo.getStartTime());
        requestEntity.setGroupId(groupVo.getGroupId());

        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));

        String URL = urlHeader + "18";
        log.info("URL ==>> {}",URL);

        return httpUtil.httpUtil(URL, requestEntity,MallYearAchievedEntity.class);

    }

    @Override
    public List<MarketMemberEntity> memberCountAndIncrease(GroupVo groupVo) {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setDateType(groupVo.getDateType());
        requestEntity.setStartTime(groupVo.getStartTime());
        requestEntity.setGroupId(groupVo.getGroupId());

        log.info("requestJson ==>> {}",JSON.toJSONString(requestEntity));

        String URL = urlHeader + "19";
        log.info("URL ==>> {}",URL);

        return httpUtil.httpUtil(URL, requestEntity,MarketMemberEntity.class);

    }
    // 111
    @Override
    public RealTimeEntity lastYearData(GroupVo groupVo) {
        //去年今天
        String endTime = timeUtils.getNowOfLastYear(groupVo.getStartTime(),-1);
        //去年第一天
        String startTime = timeUtils.getLastYearFirstDay(groupVo.getStartTime(),-1);
        groupVo.setStartTime(startTime);
        groupVo.setEndTime(endTime);
        groupVo.setDateType(Constants.D);

        //去年数据
        RealTimeEntity realTimeEntity =  groupDao.lastYearData(groupVo);

        return realTimeEntity;
    }

    @Override
    public List<MarketRealSaleEntity> queryMarketOfGroup(String groupId) {
        return groupDao.queryMarketOfGroup(groupId);
    }

    @Override
    public String queryGroupPlanSale(GroupVo groupVo) {
        return groupDao.queryGroupPlanSale(groupVo);
    }
}
