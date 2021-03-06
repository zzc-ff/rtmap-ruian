package com.rtmap.modules.app.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.rtmap.modules.app.entity.RequestEntity;
import io.jsonwebtoken.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class HttpUtil {

    public <T> List<T> httpUtil(String url, RequestEntity requestEntity,Class<T> clazz){
        String body = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, Constants.TYPEJSON)
                .body(JSON.toJSONString(requestEntity)).execute().body();

        log.info("HttpUtil: Original data's body ==>> {}",body);
        List<T> list = new ArrayList<>();
        try {
            JSONObject resultJson = JSON.parseObject(body);

            if (Constants.SUCCESSCODE.equals(resultJson.getString("code"))){

                JSONArray resultJsonList = resultJson.getJSONObject("extend").getJSONArray("data");
                String jsonString = JSON.toJSONString(resultJsonList);

                if (resultJsonList == null || resultJsonList.size() == 0){
                    return list;
                }

                list = JSON.parseArray(jsonString, clazz);
                log.info("HttpUtil: Transformed List ==>> {}",JSON.toJSONString(list));
                return list;

            }

            return list;
        }catch (Exception e){
            return list;
        }
    }


    public <T> List<T> httpUtilPlus(String url, RequestEntity requestEntity){
        String body = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, Constants.TYPEJSON)
                .body(JSON.toJSONString(requestEntity)).execute().body();

        log.info("HttpUtil: Original data's body ==>> {}",body);

        //当调用出现问题返回一个 空的 list
        List<T> list = new ArrayList<>();

        try {
            JSONObject resultJson = JSON.parseObject(body,Feature.InitStringFieldAsEmpty,Feature.OrderedField);
            log.info("HttpUtil: Transformed JSONObject ==>> {}",JSON.toJSONString(resultJson));

            if (Constants.SUCCESSCODE.equals(resultJson.getString("code"))){

                JSONArray resultJsonList = resultJson.getJSONObject("extend").getJSONArray("data");
                String jsonString = JSON.toJSONString(resultJsonList);

                if (resultJsonList == null || resultJsonList.size() == 0){
                    return list;
                }

                // Feature.InitStringFieldAsEmpty  String  null 字段转为 ""   OrderedField 按原来的循序排列字段
                list = JSON.parseObject(jsonString, new TypeReference<ArrayList<T>>() {}, Feature.InitStringFieldAsEmpty,Feature.OrderedField);
                log.info("HttpUtil: Transformed List ==>> {}",JSON.toJSONString(list));

                return list;
            }
            return list;
        }catch (Exception e){
            log.info("HttpUtil: http error ==>> {}",e.getMessage());
            return list;
        }
    }
}
