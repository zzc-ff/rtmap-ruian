package com.rtmap.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rtmap.modules.app.entity.vo.MapVo;
import com.rtmap.modules.app.service.MapService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *  地图数据相关接口
 *
 *
 */
@RestController
@Slf4j
@RequestMapping("/map")
@Api("地图数据相关接口")
public class AppMapController {

    @Autowired
    MapService mapService;
    /**
     * 获取建筑物信息
     * @param mapVo
     * @return
     */
    @PostMapping("/getBuildInfo")
    public JSONObject queryBuildInfo(@RequestBody MapVo mapVo){
        log.info("mapVo : {}",JSON.toJSONString(mapVo));
       String buildInfo = "{\"result\":{\"req\":\"build_detail\",\"error_code\":\"0\",\"error_msg\":\"Success\"},\"build_detail\":{\"buildid\":\"862300010010300227\",\"name_chn\":\"新天地南北里\",\"name_en\":\"\",\"name_qp\":\"\",\"name_jp\":\"\",\"desc\":\"\",\"long\":\"121.4815305501\",\"lat\":\"31.2262966621\",\"country\":\"中国\",\"province\":\"上海\",\"city\":\"上海\",\"district\":\"黄浦区\",\"type\":\"03\",\"address\":\"上海市黄浦区太仓路15号\",\"gcj02_long\":\"121.4815305501\",\"gcj02_lat\":\"31.2262966621\",\"floorinfo\":[{\"floor\":\"F1\",\"floor_alias\":\"F1\",\"desc\":\"会议室\"},{\"floor\":\"F2\",\"floor_alias\":\"F2\",\"desc\":\"办公室\"},{\"floor\":\"F3\",\"floor_alias\":\"F3\",\"desc\":\"办公区\"}],\"leveldata_detail\":\"\",\"theme\":\"\",\"buildTypeName\":\"写字楼\",\"angle\":\"254.59\"}}";
        JSONObject jsonObject1 = JSON.parseObject(buildInfo);
        return jsonObject1;
    }

    /**
     * 获取楼层地图
     * @param mapVo
     * @return
     */
    @PostMapping("/mapGeoJson")
    public String queryMapGeoJson(@RequestBody MapVo mapVo){
        log.info("mapVo : {}",JSON.toJSONString(mapVo));
        return mapService.queryMapGeoJson(mapVo);
    }

}
