package com.rtmap.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rtmap.modules.sys.entity.SysMarketEntity;
import com.rtmap.modules.sys.entity.SysUserGroupEntity;
import com.rtmap.modules.sys.entity.SysUserMarketEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 用户与项目对应关系
 *
 *
 */
@Mapper
public interface SysUserMarketDao extends BaseMapper<SysUserMarketEntity> {

    /**
     * 获取所有的market
     */
    List<SysMarketEntity> getMarketList();

    /**
     * 获取用户是否有权限的market
     */
    List<SysUserMarketEntity> getUserMarketList(Long userId);

    /**
     * 根据userID数组，查询权限内的marketIdList
     */
    List<String> queryMarketIdList(Long userId);

    /**
     *根据userId ，查询是否有权限点击 group
     */
    List<SysUserGroupEntity> getUserGroupList(Long userId);
}
