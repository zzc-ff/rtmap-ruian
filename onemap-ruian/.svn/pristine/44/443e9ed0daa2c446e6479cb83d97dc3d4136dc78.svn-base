package com.rtmap.modules.app.dao;

import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.MarketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketDao {

    OverviewEntity queryNatureData(MarketVo marketVo);

    OverviewEntity queryUncompleteMonth(MarketVo marketVo);

    List<SortMarketEntity> querySaleAmount(MallVo mallVo);

    List<SortMarketEntity> queryCarNum(MallVo mallVo);

    List<SortMarketEntity> queryCustomerNum(MallVo mallVo);

    List<SortMarketEntity> queryMemIncrease(MallVo mallVo);

    List<MarketIndustryAnalysis> queryIndustryAnalysis(MarketVo marketVo);

    List<MallEntity> queryAllMallOfMarket(MarketVo marketVo);

    String queryCityCode(MarketVo marketVo);

    RealTimeEntity lastYearData(MarketVo marketVo);

    String queryMarketPlanSale(MarketVo marketVo);
}
