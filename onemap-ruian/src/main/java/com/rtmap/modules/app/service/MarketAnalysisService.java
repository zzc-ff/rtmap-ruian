package com.rtmap.modules.app.service;

import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.MarketVo;

import java.util.List;
import java.util.Map;

public interface MarketAnalysisService {
    OverviewCompareEntity MarketAnalysis(MarketVo marketVo);

    List<TrendEntity> marketTrend(String dataType, MarketVo marketVo);

    Map<String,List<PropertyProportionEntity>> proportionAnalysis(MarketVo marketVo);

    List<WeatherEntity> weather(MarketVo marketVo);

    SortEntity sort(String dataType, MallVo mallVo);

    List<MarketIndustryAnalysis> industryAnalysis(MarketVo marketVo);
}
