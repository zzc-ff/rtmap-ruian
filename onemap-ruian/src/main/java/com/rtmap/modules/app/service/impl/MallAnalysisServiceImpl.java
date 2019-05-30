package com.rtmap.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.rtmap.modules.app.dao.MallDao;
import com.rtmap.modules.app.dao.MarketDao;
import com.rtmap.modules.app.entity.*;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.service.MallAnalysisService;
import com.rtmap.modules.app.utils.Constants;
import com.rtmap.modules.app.utils.HttpUtil;
import com.rtmap.modules.app.utils.OverviewRingSimilarUtils;
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
public class MallAnalysisServiceImpl implements MallAnalysisService {

    @Resource
    MallDao mallDao;
    @Autowired
    TimeUtils timeUtils;
    @Autowired
    HttpUtil httpUtil;
    @Value("${common.request.url}")
    String urlHeader;
    @Autowired
    OverviewRingSimilarUtils overview;

    @Override
    public OverviewCompareEntity mallAnalysis(MallVo mallVo) {
        OverviewCompareEntity overviewCompareEntity = new OverviewCompareEntity();
        if (Constants.D.equals(mallVo.getDateType())){

            OverviewEntity todayData = mallDao.queryNatureData(mallVo);

            if (todayData == null){
                return overviewCompareEntity;
            }

            overview.saveData(overviewCompareEntity,todayData);

            String yesterday = timeUtils.getDay(mallVo.getStartTime(), -1);
            mallVo.setStartTime(yesterday);
            OverviewEntity yesterdayData = mallDao.queryNatureData(mallVo);

            if (yesterdayData == null){
                return overviewCompareEntity;
            }
            overview.ring(overviewCompareEntity,todayData,yesterdayData);
            return overviewCompareEntity;
        }

        //非完整月  DM
        if (Constants.DM.equals(mallVo.getDateType())){

            if (timeUtils.isFistDay(mallVo.getStartTime())){
                //第一天
                mallVo.setDateType(Constants.M);
                //接下来按自然月计算
            }else {
                //非第一天，按天统计
                String currentTime = mallVo.getStartTime();
                mallVo.setDateType(Constants.D);
                mallVo.setStartTime(timeUtils.getfirstDayOfMonth(currentTime));
                mallVo.setEndTime(currentTime); // 本月第一天 - 本月当天
                OverviewEntity monthData = mallDao.queryUncompleteMonth(mallVo);

                if (monthData == null){
                    return overviewCompareEntity;
                }
                overview.saveData(overviewCompareEntity,monthData);


                String lastMonth = timeUtils.getLastMonth(currentTime, -1);
                mallVo.setStartTime(timeUtils.getfirstDayOfMonth(lastMonth));// 上月第一天  上月当天
                mallVo.setEndTime(lastMonth);
                OverviewEntity lastMonthData = mallDao.queryUncompleteMonth(mallVo);

                if (lastMonthData == null){
                    return overviewCompareEntity;
                }
                //环比
                overview.ring(overviewCompareEntity,monthData,lastMonthData);

                String monthOfLastYear = timeUtils.getLastYear(currentTime,-1);
                mallVo.setStartTime(timeUtils.getfirstDayOfMonth(monthOfLastYear));
                mallVo.setEndTime(monthOfLastYear);
                OverviewEntity monthOfLastYearData = mallDao.queryUncompleteMonth(mallVo);

                if (monthOfLastYearData == null){
                    return overviewCompareEntity;
                }
                // 同比
                overview.similar(overviewCompareEntity,monthData,monthOfLastYearData);
                return overviewCompareEntity;
            }
        }

        //自然月 M  环比同比
        if (Constants.M.equals(mallVo.getDateType())){
            mallVo.setStartTime(timeUtils.getLastMonth(mallVo.getStartTime(),-1));
            OverviewEntity monthData = mallDao.queryNatureData(mallVo);

            if (monthData == null){
                return overviewCompareEntity;
            }
            overview.saveData(overviewCompareEntity,monthData);

            String lastMonth = timeUtils.getLastMonth(mallVo.getStartTime(), -1);
            mallVo.setStartTime(lastMonth);
            OverviewEntity lastMonthData = mallDao.queryNatureData(mallVo);

            if (lastMonthData == null){
                return overviewCompareEntity;
            }
            //环比
            overview.ring(overviewCompareEntity,monthData,lastMonthData);

            String monthOfLastYear = timeUtils.getMonthOfLastYear(mallVo.getStartTime());
            mallVo.setStartTime(monthOfLastYear);
            OverviewEntity monthOfLastYearData = mallDao.queryNatureData(mallVo);

            if (monthOfLastYearData == null){
                return overviewCompareEntity;
            }
            // 同比
            overview.similar(overviewCompareEntity,monthData,monthOfLastYearData);
            return overviewCompareEntity;
        }
        //年 Y   同比
        if (Constants.Y.equals(mallVo.getDateType())){
            OverviewEntity yearData = mallDao.queryNatureData(mallVo);

            if (yearData == null){
                return overviewCompareEntity;
            }
            overview.saveData(overviewCompareEntity,yearData);
            String lastYear = timeUtils.getLastYear(mallVo.getStartTime(),-1);
            mallVo.setStartTime(lastYear);
            OverviewEntity lastYearData = mallDao.queryNatureData(mallVo);

            if (lastYearData == null){
                return overviewCompareEntity;
            }
            // 同比
            overview.similar(overviewCompareEntity,yearData,lastYearData);

            return overviewCompareEntity;
        }

        return overviewCompareEntity;
    }

    /**
     * mall综合分析-趋势图
     * 销售额  42 saleAmount
     ** 交易笔数 43 tradeAmount
     * 客流量  44  customerNum
     * 车流量 45 carNum
     * 新增会员数 46  memIncrease
     * 会员消费额  47 memSaleAmount
     * 会员交易笔数 48 memTradeBills
     * 会员客单价  49  memPerPrice
     * @return
     */
    @Override
    public List<TrendEntity> mallTrend(String dataType,MallVo mallVo) {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setMallId(mallVo.getMallId());
        requestEntity.setDateType(mallVo.getDateType());
        requestEntity.setStartTime(mallVo.getStartTime());
        requestEntity.setEndTime(mallVo.getEndTime());

        log.info("requestEntity ==>> {}", JSON.toJSONString(requestEntity));

        if (Constants.SALEAMOUNT.equals(dataType)){
            String URL = urlHeader+"42";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.TRADEBILLS.equals(dataType)){
            String URL = urlHeader+"43";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.CUSTOMERNUM.equals(dataType)){
            String URL = urlHeader+"44";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.CARNUM.equals(dataType)){
            String URL = urlHeader+"45";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.MEMINCREASE.equals(dataType)){
            String URL = urlHeader+"46";
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.MEMSALEAMOUNT.equals(dataType)){
            String URL = urlHeader+"47";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.MEMTRADEBILLS.equals(dataType)){
            String URL = urlHeader+"48";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        if (Constants.MEMPERPRICE.equals(dataType)){
            String URL = urlHeader+"49";
            log.info("URL ==>> {}",URL);
            return httpUtil.httpUtil(URL, requestEntity,TrendEntity.class);
        }
        return new ArrayList<>();
    }

    @Override
    public SortEntity sort(String dataType, MallVo mallVo) {
        String time = mallVo.getStartTime();
        if (Constants.SALEAMOUNT.equals(dataType)){
            //当年截至的今天数据
            mallVo.setStartTime(timeUtils.getYear(mallVo.getStartTime()));
            mallVo.setEndTime(time);
            mallVo.setDateType(Constants.D);
            List<SortMarketEntity> yearData = mallDao.getSaleAmount(mallVo);
            //去年第一天截至到去年今天的数据
            mallVo.setStartTime(timeUtils.getLastYearFirstDay(time, -1));
            mallVo.setEndTime(timeUtils.getNowOfLastYear(time, -1));
            List<SortMarketEntity> lastYearData = mallDao.getSaleAmount(mallVo);
            return  getData(yearData,lastYearData,mallVo);
        }
        if (Constants.CARNUM.equals(dataType)){
            //当年截至的今天数据
            mallVo.setStartTime(timeUtils.getYear(mallVo.getStartTime()));
            mallVo.setEndTime(time);
            mallVo.setDateType(Constants.D);
            List<SortMarketEntity> yearData = mallDao.getCarNum(mallVo);
            //去年第一天截至到去年今天的数据
            mallVo.setStartTime(timeUtils.getLastYearFirstDay(time, -1));
            mallVo.setEndTime(timeUtils.getNowOfLastYear(time, -1));
            List<SortMarketEntity> lastYearData = mallDao.getCarNum(mallVo);
            return  getData(yearData,lastYearData,mallVo);
        }
        if (Constants.CUSTOMERNUM.equals(dataType)){
            //当年截至的今天数据
            mallVo.setStartTime(timeUtils.getYear(mallVo.getStartTime()));
            mallVo.setEndTime(time);
            mallVo.setDateType(Constants.D);
            List<SortMarketEntity> yearData = mallDao.getCustomberNum(mallVo);
            //去年第一天截至到去年今天的数据
            mallVo.setStartTime(timeUtils.getLastYearFirstDay(time, -1));
            mallVo.setEndTime(timeUtils.getNowOfLastYear(time, -1));
            List<SortMarketEntity> lastYearData = mallDao.getCustomberNum(mallVo);
            return  getData(yearData,lastYearData,mallVo);
        }
        if (Constants.MEMINCREASE.equals(dataType)){
            //当年截至的今天数据
            mallVo.setStartTime(timeUtils.getYear(mallVo.getStartTime()));
            mallVo.setEndTime(time);
            mallVo.setDateType(Constants.D);
            List<SortMarketEntity> yearData = mallDao.getMemIncrease(mallVo);
            //去年第一天截至到去年今天的数据
            mallVo.setStartTime(timeUtils.getLastYearFirstDay(time, -1));
            mallVo.setEndTime(timeUtils.getNowOfLastYear(time, -1));
            List<SortMarketEntity> lastYearData = mallDao.getMemIncrease(mallVo);
            return  getData(yearData,lastYearData,mallVo);
        }
        return new SortEntity();
    }
    private SortEntity getData(List<SortMarketEntity> yearData,List<SortMarketEntity> lastYearData,MallVo mallVo) {
        SortEntity sortEntity = new SortEntity();
        if (yearData != null && lastYearData != null) {
            int size = yearData.size() >= lastYearData.size() ? lastYearData.size() : yearData.size();
            int yearSort = 0;
            int lastYearSort = 0;

            for (int i = 0; i < size; i++) {
                if (yearData.get(i).getMarketId().equals(mallVo.getMarketId())) {
                    yearSort = yearData.get(i).getSort();
                }
                if (lastYearData.get(i).getMarketId().equals(mallVo.getMarketId())) {
                    lastYearSort = yearData.get(i).getSort();
                }
            }
            sortEntity.setMarketSort(yearSort);
            sortEntity.setChangeNum(yearSort - lastYearSort);
            sortEntity.setTotal(yearData.size());
            return sortEntity;
        }
        log.info("项目排名-今年数据或者去年数据为空！");
        return sortEntity;
    }
}
