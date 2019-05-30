package com.rtmap.modules.app.service;

import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;

import java.util.List;

public interface MallIndustryAnalysisService {

    List<IndustryListEntity> industryList(ParameterVo parameterVo);

    List<IndustryMergeEntity> Contrast(String dataType, ParameterVo parameterVo);

    List<IndustryMergeEntity> Proportion(ParameterVo parameterVo);

    MallSaleCompareEntity industryCompare(ParameterVo parameterVo);

    List<TrendEntity> trend(String dataType, ParameterVo parameterVo);

    List<IndustryRankEntity> sort(String dataType, ParameterVo parameterVo);

    MallSaleEntity industryData(ParameterVo parameterVo);
}
