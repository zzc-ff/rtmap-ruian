package com.rtmap.modules.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.rtmap.modules.app.dao.MapDao;
import com.rtmap.modules.app.entity.vo.MapVo;
import com.rtmap.modules.app.service.MapService;
import com.rtmap.modules.app.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MapServiceImpl implements MapService {

    @Autowired
    HttpUtil httpUtil;
    @Value("${common.request.url}")
    String urlHeader;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MapDao mapDao;

    @Override
    public String queryMapGeoJson(MapVo mapVo) {

        //map:项目id:mall id(buildId)：楼层
        String key = "map:"+mapVo.getMarketId()+":"+mapVo.getBuildid()+":"+mapVo.getFloor();

        if (redisTemplate.hasKey(key)){
            log.info("redis exists key");
            return String.valueOf(redisTemplate.opsForValue().get(key));
        }else {
            String geoJson = mapDao.queryMapGeoJson(mapVo);
            if (!StrUtil.isEmpty(geoJson)){
                redisTemplate.opsForValue().set(key,geoJson,24,TimeUnit.HOURS);
            }
            return geoJson;
        }

    }
}
