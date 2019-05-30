package com.rtmap.modules.app.controller;

import com.rtmap.common.exception.RRException;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.entity.BusinessMapEntity;
import com.rtmap.modules.app.entity.vo.MallVo;
import com.rtmap.modules.app.service.MallBusinessMapService;
import com.rtmap.modules.app.utils.ValidUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/mallBusinessMap")
@Api("APP mall 商业地图")
public class AppMallBusinessMapController {

    @Autowired
    MallBusinessMapService mallBusinessMapService;
    @Autowired
    ValidUtils validUtils;
    /**
     * 地图需要的楼层销售额，坪效数据
     */
    @RequestMapping("/saleData")
    public R saleData(@Valid MallVo mallVo){
        if(validUtils.notBlank(mallVo.getFloorId(),mallVo.getEndTime())){
            throw new RRException("参数异常，请联系管理员！",400);
        }
        List<BusinessMapEntity> list = mallBusinessMapService.saleData(mallVo);
        return R.ok().put("list",list);
    }
}
