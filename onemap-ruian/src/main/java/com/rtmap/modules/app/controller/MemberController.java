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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CuiZheng
 * @title: MemberController
 * @description: 会员分析
 * @date 2019/5/14 16:15
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Value("${common.request.url}")
    private String middlegrouUrl;

    /**
    　* @Description: 会员分析-会员整体情况
    　* @param   mall id
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/14 18:39
    **/
    @RequestMapping("/memberOverView")
    public R memberOverView(@RequestParam  String mallId,@RequestParam  String dateType){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();
        paramMap.put("mall_id",mallId);
        int sqlId=0;

        if(dateType.equals("D")){
            sqlId=38;
            Date yesterday=DateUtil.yesterday();
            paramMap.put("yesterday",DateUtil.format(yesterday,"yyyyMMdd"));
            paramMap.put("nearday",DateUtil.format(DateUtil.offsetDay(yesterday,-1),"yyyyMMdd"));
            paramMap.put("theDayOfLastYear",DateUtil.format(DateUtil.offset(yesterday,DateField.YEAR,-1),"yyyyMMdd"));
            paramMap.put("date_type","D");
        }else if(dateType.equals("M")){
            if(DateUtil.today().equals(DateUtil.beginOfMonth(new Date()))){
                sqlId=38;
                //如果是当月第一天，则拿上月整月去对比
                paramMap.put("date_type","M");
                //上月第一天
                Date beaginOfLastMonth=DateUtil.beginOfMonth(DateUtil.yesterday());
                //上上月第一天
                Date nearMonth=DateUtil.offset(beaginOfLastMonth,DateField.MONTH,-1);
                //去年同月第一天
                String beaginOfMonthLastYear=DateUtil.format(DateUtil.offset(beaginOfLastMonth,DateField.YEAR,-1),"yyyyMMdd");

                paramMap.put("yesterday",DateUtil.format(beaginOfLastMonth,"yyyyMMdd"));
                paramMap.put("nearday",DateUtil.format(nearMonth,"yyyyMMdd"));
                paramMap.put("theDayOfLastYear",beaginOfMonthLastYear);

            }else{
                paramMap.put("date_type","D");
                sqlId=93;
                //如果不是第一天，则按当月累加值对比
                //当月起始和结束时间
                Date startTime1=DateUtil.beginOfMonth(new Date());
                Date endTime1=DateUtil.yesterday();
                //上月起始和结束时间
                Date startTime2=DateUtil.offset(startTime1,DateField.MONTH,-1);
                Date endTime2=DateUtil.offset(endTime1,DateField.MONTH,-1);
                //去年同月起始和结束时间
                Date startTime3=DateUtil.offset(startTime1,DateField.YEAR,-1);
                Date endTime3=DateUtil.offset(endTime1,DateField.YEAR,-1);

                paramMap.put("startTime1",DateUtil.format(startTime1,"yyyyMMdd"));
                paramMap.put("endTime1",DateUtil.format(endTime1,"yyyyMMdd"));

                paramMap.put("startTime2",DateUtil.format(startTime2,"yyyyMMdd"));
                paramMap.put("endTime2",DateUtil.format(endTime2,"yyyyMMdd"));

                paramMap.put("startTime3",DateUtil.format(startTime3,"yyyyMMdd"));
                paramMap.put("endTime3",DateUtil.format(endTime3,"yyyyMMdd"));
            }
        }else if(dateType.equals("Y")){
            if(DateUtil.today().equals(DateUtil.beginOfYear(new Date()))){
                //如果今天是1月1日，则整年数据进行对比
                sqlId=94;
                paramMap.put("date_type","Y");

                Date beginOfLastYear=DateUtil.offset(DateUtil.date(),DateField.YEAR,-1);
                Date beginOfLastTwoYear=DateUtil.offset(DateUtil.date(),DateField.YEAR,-2);

                paramMap.put("yesterday",DateUtil.format(beginOfLastYear,"yyyyMMdd"));
                paramMap.put("nearday",DateUtil.format(beginOfLastTwoYear,"yyyyMMdd"));
            }else{
                paramMap.put("date_type","D");
                sqlId=95;

                Date startTime1=DateUtil.beginOfYear(DateUtil.date());
                Date endTime1=DateUtil.date();
                Date startTime2=DateUtil.offset(startTime1,DateField.YEAR,-1);
                Date endTime2=DateUtil.offset(endTime1,DateField.YEAR,-1);

                paramMap.put("startTime1",DateUtil.format(startTime1,"yyyyMMdd"));
                paramMap.put("endTime1",DateUtil.format(endTime1,"yyyyMMdd"));

                paramMap.put("startTime2",DateUtil.format(startTime2,"yyyyMMdd"));
                paramMap.put("endTime2",DateUtil.format(endTime2,"yyyyMMdd"));
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
                    int mem_num=0;
                    List<Map<String,Object>> list= (List<Map<String, Object>>) result.getExtend().get("data");
                    if(list!=null&&list.size()>0){
                        for(Map<String,Object> memberInfo:list){
                            curVal+=(int)memberInfo.get("curVal");
                            nearVal+=(int)memberInfo.get("nearVal");
                            lastVal+=memberInfo.get("lastVal")!=null?(int)memberInfo.get("lastVal"):0;
                            mem_num+=(int)memberInfo.get("mem_num");
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
                    resultMap.put("mem_num",mem_num);
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 会员趋势
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/14 20:24
    **/
    @RequestMapping("/memberTrend")
    public R memberTrend(@RequestParam  String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("date_type",dateType);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        String responseStr=HttpRequest.post(middlegrouUrl+39).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("memberTrend",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 会员消费&客单价
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/14 20:40
    **/
    @RequestMapping("/memberConsumeAndPerBill")
    public R memberConsumeAndPerBill(@RequestParam  String mallId,String dateType,String startTime,String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("date_type",dateType);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);

        String responseStr=HttpRequest.post(middlegrouUrl+40).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("memberConsumeAndPerBill",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 会员消费-饼图
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/14 20:54
    **/
    @RequestMapping("/memberAndCunstomerBill")
    public R memberAndCunstomerBill(@RequestParam  String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("date_type",dateType);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);

        String responseStr=HttpRequest.post(middlegrouUrl+41).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("memberBill",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }

    /**
    　* @Description: 会员消费&总消费曲线
    　* @param
    　* @return
    　* @throws
    　* @author CuiZheng
    　* @date 2019/5/15 13:38
    **/
    @RequestMapping("/memberBillAndTotalBill")
    public R memberBillAndTotalBill(@RequestParam  String mallId,@RequestParam String dateType,@RequestParam String startTime,@RequestParam String endTime){
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> resultMap=new HashMap<>();

        paramMap.put("mall_id",mallId);
        paramMap.put("date_type",dateType);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);

        String responseStr=HttpRequest.post(middlegrouUrl+51).body(JSON.toJSONString(paramMap))
                .header(Header.ACCEPT_CHARSET,"UTF-8")
                .header(Header.CONTENT_TYPE,"application/json").execute().body();
        if(StringUtils.isNotBlank(responseStr)){
            MiddlgrouResult result=JSON.parseObject(responseStr,MiddlgrouResult.class);
            if(result!=null&&result.getExtend()!=null){
                resultMap.put("memberBillAndTotalBill",result.getExtend().get("data"));
            }
        }
        return R.ok(resultMap);
    }
}
