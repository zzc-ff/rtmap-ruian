package com.rtmap.modules.app.service;

import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;

import java.util.List;

public interface MallFloorAnalysisService {
    List<FloorMergeEntity> Contrast(String dataType, MallVo mallVo);

    List<FloorMergeEntity> Proportion(MallVo mallVo);

    MallSaleCompareEntity floorCompare(MallVo mallVo);

    List<TrendEntity> trend(String dataType, MallVo mallVo);

    List<FloorListEntity> floorList(ParameterVo parameterVo);

    List<FloorRankEntity> sort(String dataType, ParameterVo parameterVo);

    MallSaleEntity floorData(MallVo mallVo);
}
