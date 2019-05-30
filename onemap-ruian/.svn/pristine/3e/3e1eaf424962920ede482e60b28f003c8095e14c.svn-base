package com.rtmap.modules.app.service;

import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.GroupVo;

import java.util.List;

public interface GroupService {
    List<SaleAndTradeEntity> saleAndTrade(GroupVo groupVo) ;

    List<CarAndCustomerEntity> carAndCustomer(GroupVo groupVo) ;

    List<MarketLastMonthEntity> lastMonthSale(GroupVo groupVo) ;

    List<MarketCustomerCarPriceEntity> customerAndCarAndPrice(GroupVo groupVo) ;

    List<MarketYearSaleEntity> AllYearSale(GroupVo groupVo);

    List<MallYearAchievedEntity> AllYearAchieved(GroupVo groupVo);

    List<MarketMemberEntity> memberCountAndIncrease(GroupVo groupVo);

    RealTimeEntity lastYearData(GroupVo groupVo);

    List<MarketRealSaleEntity> queryMarketOfGroup(String groupId);

    String queryGroupPlanSale(GroupVo groupVo);
}
