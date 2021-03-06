package com.rtmap.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.modules.sys.entity.SysMarketEntity;
import com.rtmap.modules.sys.entity.SysUserGroupEntity;
import com.rtmap.modules.sys.entity.SysUserMarketEntity;

import java.util.List;

public interface SysUserMarketService extends IService<SysUserMarketEntity> {

    void saveOrUpdate(Long userId, List<String> marketIdList);


    List<SysMarketEntity> getMarketList();

    List<SysUserMarketEntity> getUserMarketList(Long userId);

    List<String> queryMarketIdList(Long userId);

    List<SysUserGroupEntity> getUserGroupList(Long userId);
}
