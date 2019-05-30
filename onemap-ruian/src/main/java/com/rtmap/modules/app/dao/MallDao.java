package com.rtmap.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.entity.vo.MarketVo;
import com.rtmap.modules.app.entity.vo.ParameterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MallDao extends BaseMapper<ShopRankEntity> {

    OverviewEntity queryNatureData(MallVo mallVo);

    OverviewEntity queryUncompleteMonth(MallVo mallVo);

    MallSaleEntity querySaleNatureData(MallVo mallVo);

    MallSaleEntity querySaleUncompleteMonth(MallVo mallVo);

    List<FloorIndustryEntity> queryFloor(MallVo mallVo);

    List<IndustryFloorEntity> queryIndustry(MallVo mallVo);

    MallSaleEntity queryFloorUncompleteMonth(MallVo mallVo);

    MallSaleEntity queryFloorNatureData(MallVo mallVo);

    MallSaleEntity queryIndustryUncompleteMonth(ParameterVo parameterVo);

    MallSaleEntity queryIndustryNatureData(ParameterVo parameterVo);

    List<ShopRankEntity> querySaleAmountRank(Page<ShopRankEntity> page,@Param("param") ParameterVo parameterVo);

    List<ShopRankEntity> querySaleEffectRank(Page<ShopRankEntity> page,@Param("param") ParameterVo parameterVo);

    List<ShopRankEntity> queryPerCustomerPriceRank(Page<ShopRankEntity> page,@Param("param") ParameterVo parameterVo);

    List<ShopRankEntity> queryTradeBillsRank(Page<ShopRankEntity> page,@Param("param") ParameterVo parameterVo);

    MallSaleEntity queryShopUncompleteMonth(ParameterVo parameterVo);

    MallSaleEntity queryShopNatureData(ParameterVo parameterVo);

    List<ShopEntity> bostonShopList(ParameterVo parameterVo);

    List<ShopListEntity> shopListWithSort(Page<ShopListEntity> page,@Param("param") ParameterVo parameterVo);

    List<SortMarketEntity> getSaleAmount(MallVo mallVo);

    List<SortMarketEntity> getCarNum(MallVo mallVo);

    List<SortMarketEntity> getCustomberNum(MallVo mallVo);

    List<SortMarketEntity> getMemIncrease(MallVo mallVo);
}
