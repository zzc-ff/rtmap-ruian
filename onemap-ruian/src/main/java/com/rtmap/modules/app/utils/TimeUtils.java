package com.rtmap.modules.app.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class TimeUtils {

    //日
    /**
     * 根据日期计算这个日期前多少天是哪天
     * 整数往后推,负数往前移动
     */
    public String getDay(String time,int days) {
        Calendar calendar = new GregorianCalendar();
        Date date = DateUtil.parse(time);
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);//把日期往后增加一天.整数往后推,负数往前移动
        return DateUtil.format(calendar.getTime(),"yyyyMMdd");
    }

    /**
     *昨日
     */
    public String getYesterday() {
        DateTime yesterday = DateUtil.yesterday();
        return DateUtil.format(yesterday,"yyyyMMdd");
    }



    //月
    /**
     * 根据日期推算本月第一天
     *
     */
    public String getfirstDayOfMonth(String time) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(DateUtil.parse(time));
        //无论是几号，设置为1号，正常出来是 最后一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.format(calendar.getTime(),"yyyyMMdd");
    }

    /**
     * 根据 月 位移
     *
     */
    public String getLastMonth(String time,int months) {
        DateTime parse = DateUtil.parse(time);
        DateTime dateTime = DateUtil.offsetMonth(parse, months);
        return DateUtil.format(dateTime,"yyyyMMdd");
    }

    /**
     * 根据日期推算上月  返回上月第一天
     *
     */
    public String getLastMonthWithFirstDay(String time,int months) {
        DateTime dateTime = DateUtil.parse(time);
        Date newDate = DateUtil.offset(dateTime, DateField.DAY_OF_MONTH, months);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(newDate);
        //无论是几号，设置为1号，正常出来是 最后一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.format(calendar.getTime(),"yyyyMMdd");
    }

     //年
    /**
     * 根据日期推算本年第一天
     *
     */
    public String getYear(String time) {
        DateTime parse = DateUtil.parse(time);
        parse.setField(DateField.DAY_OF_YEAR,1);
        return DateUtil.format(parse,"yyyyMMdd");
    }

    /**
     * 根据日期推算去年第一天
     *
     */
    public String getLastYearFirstDay(String time,int offset) {
        DateTime dateTime = DateUtil.parse(time);
        DateTime lastYear = DateUtil.offset(dateTime, DateField.YEAR, offset);
        lastYear.setField(DateField.DAY_OF_YEAR,1);
        return DateUtil.format(lastYear,"yyyyMMdd");
    }

    /**
     * 根据日期推算 上年
     *
     */
    public String getLastYear(String time,int years) {
        DateTime dateTime = DateUtil.parse(time);
        DateTime offset = DateUtil.offset(dateTime, DateField.YEAR, years);
        return DateUtil.format(offset,"yyyyMMdd");
    }

    /**月计算
     * 根据日期推算 上年 本月
     *
     */
    public String getMonthOfLastYear(String time) {
        Calendar calendar=Calendar.getInstance();
        calendar.clear();
        calendar.setTime(DateUtil.parse(time));
        int year = calendar.get(Calendar.YEAR);
        int lastYear = year - 1;
        calendar.set(Calendar.YEAR,lastYear);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.format(calendar.getTime(),"yyyyMMdd");
    }

    /**日计算
     * 根据日期推算 上年 今日
     *
     */
    public String getTodayOfLastYear() {
        Calendar calendar=Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int lastYear = year - 1;
        calendar.set(Calendar.YEAR,lastYear);
        return DateUtil.format(calendar.getTime(),"yyyyMMdd");
    }

    /**日计算
     * 根据日期推算 上年 今日
     *
     */
    public String getNowOfLastYear(String time,int offset) {
        DateTime parse = DateUtil.parse(time);
        parse.offset(DateField.YEAR,offset);
        return DateUtil.format(parse,"yyyyMMdd");
    }

    /**
     *今日
     */
    public String getToday() {
        return DateUtil.format(new Date(),"yyyyMMdd");
    }

    /**
     *判断当前时间是否为月的第一天
     */
    public boolean isFistDay(String time){
        DateTime parse = DateUtil.parse(time);
        int field = parse.getField(DateField.DAY_OF_MONTH);
        return field == 1;
    }

    /**
     * 计算时间段内的天数
     * @param nowTime
     * @param endTime
     * @return
     */
    public long getdays(String nowTime, String endTime) {
        DateTime start = DateUtil.parse(nowTime);
        DateTime end = DateUtil.parse(endTime);
        return DateUtil.between(start,end, DateUnit.DAY);
    }

    /**
     * 判断时间的月是 否 在 本年本月内
     * @param nowTime
     * @return
     */
    public boolean isCurrentMonth(String nowTime) {
        Date date = DateUtil.date();
            //获得年的部分
        int currentYear = DateUtil.year(date);

        //获得月份，从0开始计数
        int currentMonth =  DateUtil.month(date) + 1;

        DateTime parse = DateUtil.parse(nowTime);

        int year = DateUtil.year(parse);
        int month = DateUtil.month(parse) + 1;

        return currentYear == year && currentMonth == month  ;
    }

}
