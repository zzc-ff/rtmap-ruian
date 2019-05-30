package com.rtmap.modules.app.service;

import com.rtmap.modules.app.entity.OverviewCompareEntity;
import com.rtmap.modules.app.entity.SortEntity;
import com.rtmap.modules.app.entity.TrendEntity;
import com.rtmap.modules.app.entity.WeatherEntity;
import com.rtmap.modules.app.entity.vo.MallVo;

import java.util.List;

public interface MallAnalysisService {
    OverviewCompareEntity mallAnalysis(MallVo mallVo);

    List<TrendEntity> mallTrend(String dataType,MallVo mallVo);

    SortEntity sort(String dataType, MallVo mallVo);
}
