

package com.rtmap.modules.app.controller;


import com.rtmap.common.utils.R;
import com.rtmap.modules.app.annotation.Login;
import com.rtmap.modules.app.annotation.LoginUser;
import com.rtmap.modules.app.dao.UserDao;
import com.rtmap.modules.app.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * APP测试接口
 *
 *
 */
@RestController
@RequestMapping("/app")
@Api("APP测试接口")
public class AppTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }


    @Autowired
    UserDao userDao;
    @GetMapping("insert")
    public R insert() throws Exception{
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = s.parse("2019-09-28");
        Calendar   calendar   =   new GregorianCalendar();
        calendar.setTime(parse);
        for (int i = 0;i<5000;i++){
            Date time = calendar.getTime();
            userDao.usdf(time);
            calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        }

        return R.ok();
    }

}
