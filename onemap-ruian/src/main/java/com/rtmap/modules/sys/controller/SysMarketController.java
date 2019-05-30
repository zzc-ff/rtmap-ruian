package com.rtmap.modules.sys.controller;

import com.rtmap.common.utils.R;
import com.rtmap.modules.sys.entity.SysMarketEntity;
import com.rtmap.modules.sys.entity.SysUserGroupEntity;
import com.rtmap.modules.sys.entity.SysUserMarketEntity;
import com.rtmap.modules.sys.service.SysUserMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目管理
 *
 *
 */
@RestController
@RequestMapping("/sys/market")
public class SysMarketController extends AbstractController {

    @Autowired
    private SysUserMarketService sysUserMarketService;

    /**
     * market信息
     */
    @GetMapping("/info/getMarketList")
    public R getMarketList(){

        List<SysMarketEntity> MarketList =sysUserMarketService.getMarketList();

        logger.info("MarketList ==>> {}",MarketList);

        return R.ok().put("list", MarketList);
    }

    /**
     * portal获取market信息
     */
    @GetMapping("/info/getUserMarketList")
    public R getUserMarketList(){

        //获取所有的market以及是否有权限状态
        List<SysUserGroupEntity> userGroupList =sysUserMarketService.getUserGroupList(getUserId());

        //获取所有的market以及是否有权限状态
        List<SysUserMarketEntity> userMarketList =sysUserMarketService.getUserMarketList(getUserId());

        logger.info("userMarketList ==>> {}",userMarketList);

        return R.ok().put("userMarketList", userMarketList).put("userGroupList",userGroupList);
    }


}
