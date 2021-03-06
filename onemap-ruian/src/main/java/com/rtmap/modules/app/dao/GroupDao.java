package com.rtmap.modules.app.dao;

import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.GroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupDao {
    List<SaleAndTradeEntity> querySellData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<CarAndCustomerEntity> queryCarNum(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<MarketLastMonthEntity> querySaleAndEffect(String lastMonth);

    RealTimeEntity lastYearData(GroupVo groupVo);

    List<MarketRealSaleEntity> queryMarketOfGroup(String groupId);

    String queryGroupPlanSale(GroupVo groupVo);
}
