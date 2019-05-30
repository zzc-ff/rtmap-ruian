package com.rtmap.modules.app.service.impl;


import com.rtmap.modules.app.entity.BusinessMapEntity;
import com.rtmap.modules.app.entity.RequestEntity;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.service.MallBusinessMapService;
import com.rtmap.modules.app.utils.HttpUtil;
import com.rtmap.modules.app.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallBusinessMapImpl implements MallBusinessMapService {
    @Autowired
    TimeUtils timeUtils;
    @Autowired
    HttpUtil httpUtil;
    @Value("${common.request.url}")
    String urlHeader;

    @Override
    public List<BusinessMapEntity> saleData(MallVo mallVo) {

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setGroupId(mallVo.getGroupId());
        requestEntity.setMarketId(mallVo.getMarketId());
        requestEntity.setMallId(mallVo.getMallId());
        requestEntity.setFloorId(mallVo.getFloorId());
        requestEntity.setDateType(mallVo.getDateType());
        requestEntity.setStartTime(mallVo.getStartTime());
        requestEntity.setEndTime(mallVo.getEndTime());

        String URL = urlHeader + "113";
        List<BusinessMapEntity> list = httpUtil.httpUtil(URL,requestEntity,BusinessMapEntity.class);

        return list;
    }
}
