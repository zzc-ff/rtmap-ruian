package com.rtmap.modules.app.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.rtmap.common.utils.MiddlgrouResult;
import com.rtmap.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CuiZheng
 * @title: CarFlowController
 * @description: 车流
 * @date 2019/5/16 10:05
 */
@RestController
@RequestMapping("/carFlow")
public class CarFlowController {


    @Value("${common.request.url}")
    private String middlegrouUrl;

    /**
    　* @Description: 车辆日均驻留时长
    　* @param 
    　* @return 
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 11:00
    **/
    @RequestMapping("/carAvgPartTime")
    public R carAvgPartTime(@RequestParam String mallId,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        String responseStr=HttpRequest.post(middlegrouUrl+52).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("carAvgPartTime",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 车辆平均停留时长曲线
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 11:22
    **/
    @RequestMapping("/carAvgPartTrend")
    public R carAvgPartTrend(@RequestParam String mallId,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        String responseStr=HttpRequest.post(middlegrouUrl+53).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("carAvgPartTrend",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }


    /**
    　* @Description: 车辆到访次数分布
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 12:00
    **/
    @RequestMapping("/carArriveCount")
    public R carArriveCount(@RequestParam String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("dateType",dateType);
        String responseStr=HttpRequest.post(middlegrouUrl+54).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("carArriveCount",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 平均到访频次
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 12:06
    **/
    @RequestMapping("/carAvgArriveCount")
    public R carAvgArriveCount(@RequestParam String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("dateType",dateType);
        String responseStr=HttpRequest.post(middlegrouUrl+55).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("carAvgArriveCount",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }


    /**
    　* @Description: 车流趋势分析
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 13:28
    **/
    @RequestMapping("/carFlowTrend")
    public R carFlowTrend(@RequestParam String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("dateType",dateType);
        String responseStr=HttpRequest.post(middlegrouUrl+56).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                int inCarCount=0;
                int outCarCount=0;
                List<Map<String,Object>> list= (List<Map<String, Object>>) result.getExtend().get("data");
                if(list!=null&&list.size()>0){
                    for(Map<String,Object> carInfo:list){
                        inCarCount+=(int)carInfo.get("out_car_num");
                        outCarCount+=(int)carInfo.get("in_car_num");
                    }
                }
                resultMap.put("inCarCount",inCarCount);
                resultMap.put("outCarCount",outCarCount);
                resultMap.put("carFlowTrend",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }


    /**
    　* @Description: 车位周转率
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 13:38
    **/
    @RequestMapping("/carTurnOverPercent")
    public R carTurnOverPercent(@RequestParam String mallId,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        String responseStr=HttpRequest.post(middlegrouUrl+57).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                int carNum=0;
                int partNum=0;
                List<Map<String,Object>> list= (List<Map<String, Object>>) result.getExtend().get("data");
                if(list!=null&&list.size()>0){
                    for(Map<String,Object> carInfo:list){
                        carNum+=(int)carInfo.get("in_car_num");
                        partNum+=(int)carInfo.get("parking_place_num");
                    }
                }
                double carNumSum=carNum;
                double partNumSum=partNum;
                resultMap.put("turnOverPercent",carNumSum/partNumSum);
                resultMap.put("carTurnOverPercent",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
     　* @Description: 车位利用率
     　* @param
     　* @return
     　* @throws
     　* @author CuiZheng
     　* @date 2019/5/16 13:38
     **/
    @RequestMapping("/carPartUtilization")
    public R carPartUtilization(@RequestParam String mallId,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        String responseStr=HttpRequest.post(middlegrouUrl+58).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                int in_notout_car_num=0;
                int in_out_car_num=0;
                int out_car_num=0;
                int parking_place_num=0;
                List<Map<String,Object>> list= (List<Map<String, Object>>) result.getExtend().get("data");
                if(list!=null&&list.size()>0){
                    for(Map<String,Object> carInfo:list){
                        in_notout_car_num+=(int)carInfo.get("in_notout_car_num");
                        in_out_car_num+=(int)carInfo.get("in_out_car_num");
                        out_car_num+=(int)carInfo.get("out_car_num");
                        parking_place_num+=(int)carInfo.get("parking_place_num");
                    }
                }
                double in_notout_car_num_sum=in_notout_car_num;
                double in_out_car_num_sum=in_notout_car_num;
                double out_car_num_sum=out_car_num;
                double parking_place_num_sum=parking_place_num;
                resultMap.put("carPartUtilizationPercent",(in_notout_car_num_sum+in_out_car_num_sum-out_car_num_sum)/parking_place_num_sum);
                resultMap.put("carPartUtilization",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 停车付费
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/16 13:47
    **/
    @RequestMapping("/carPartPay")
    public R carPartPay(@RequestParam String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("dateType",dateType);
        String responseStr=HttpRequest.post(middlegrouUrl+59).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("carPartPay",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 车流概况
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/21 18:22
    **/
    @RequestMapping("/carFlowOverview")
    public R carFlowOverview(@RequestParam String mallId,@RequestParam String dateType){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();
        paramMap.put("mall_id",mallId);
        int sqlId=0;

        if(dateType.equals("D")){
            sqlId=96;
            Date yesterday=DateUtil.yesterday();
            paramMap.put("yesterday",DateUtil.format(yesterday,"yyyyMMdd"));
        }else if(dateType.equals("M")){
            if(DateUtil.today().equals(DateUtil.beginOfMonth(new Date()))){
                sqlId=97;
                //如果是当月第一天，则拿上月整月去对比
                //上月第一天
                Date beaginOfLastMonth=DateUtil.beginOfMonth(DateUtil.yesterday());
                Date endOfLastMonth=DateUtil.endOfMonth(DateUtil.yesterday());

                paramMap.put("yesterday",DateUtil.format(endOfLastMonth,"yyyyMMdd"));
                paramMap.put("startTime",DateUtil.format(beaginOfLastMonth,"yyyyMMdd"));
                paramMap.put("endTime",DateUtil.format(endOfLastMonth,"yyyyMMdd"));

            }else{
                sqlId=97;
                //如果不是第一天，则按当月累加值对比
                paramMap.put("yesterday",DateUtil.format(DateUtil.yesterday(),"yyyyMMdd"));
                paramMap.put("startTime",DateUtil.format(DateUtil.beginOfMonth(new Date()),"yyyyMMdd"));
                paramMap.put("endTime",DateUtil.format(DateUtil.yesterday(),"yyyyMMdd"));
            }
        }else if(dateType.equals("Y")){
            if(DateUtil.today().equals(DateUtil.beginOfYear(new Date()))){
                //如果今天是1月1日，则整年数据进行对比
                sqlId=98;

                Date beginOfLastYear=DateUtil.offset(DateUtil.date(),DateField.YEAR,-1);

                paramMap.put("yesterday",DateUtil.format(DateUtil.yesterday(),"yyyyMMdd"));
                paramMap.put("startTime",DateUtil.format(beginOfLastYear,"yyyyMMdd"));
                paramMap.put("endTime",DateUtil.format(DateUtil.yesterday(),"yyyyMMdd"));
            }else{
                sqlId=98;

                Date startTime=DateUtil.beginOfYear(DateUtil.date());
                Date endTime=DateUtil.yesterday();

                paramMap.put("yesterday",DateUtil.format(DateUtil.yesterday(),"yyyyMMdd"));
                paramMap.put("startTime",DateUtil.format(startTime,"yyyyMMdd"));
                paramMap.put("endTime",DateUtil.format(endTime,"yyyyMMdd"));
            }
        }

        String responseStr=HttpRequest.post(middlegrouUrl+sqlId).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                int curVal=0;
                int nearVal=0;
                int lastVal=0;
                BigDecimal avg_dura=new BigDecimal(0);
                List<Map<String,Object>> list= (List<Map<String, Object>>) result.getExtend().get("data");
                if(list!=null&&list.size()>0){
                    for(Map<String,Object> memberInfo:list){
                        curVal+=(int)memberInfo.get("curVal");
                        nearVal+=(int)memberInfo.get("nearVal");
                        lastVal+=memberInfo.get("lastVal")!=null?(int)memberInfo.get("lastVal"):0;
                        avg_dura=avg_dura.add((BigDecimal)memberInfo.get("stay_dura_avg"));
                    }
                }
                double curVal_d=curVal;
                double nearVal_d=nearVal;
                double lastVal_d=lastVal;
                resultMap.put("curVal",curVal);
                if(!dateType.equals("Y")){
                    resultMap.put("tongbi",(curVal_d-lastVal_d)/lastVal_d);
                }
                resultMap.put("huanbi",(curVal_d-nearVal_d)/nearVal_d);
                resultMap.put("stay_dura_avg",avg_dura);
            }
        }
        return R.ok(resultMap);
    }
}
