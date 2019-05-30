

package com.rtmap;

import com.alibaba.fastjson.JSON;
import com.rtmap.common.utils.PageUtils;
import com.rtmap.common.utils.R;
import com.rtmap.modules.app.controller.AppGroupController;
import com.rtmap.modules.app.controller.AppMarketAnalysisController;
import com.rtmap.modules.app.controller.AppMarketController;
import com.rtmap.modules.app.entity.vo.GroupVo;
import com.rtmap.modules.app.entity.vo.MapVo;
import com.rtmap.modules.app.entity.vo.MarketVo;
import com.rtmap.modules.app.service.GroupService;
import com.rtmap.modules.app.service.MapService;
import com.rtmap.modules.sys.controller.AbstractController;
import com.rtmap.modules.sys.entity.SysMarketEntity;
import com.rtmap.modules.sys.entity.SysUserGroupEntity;
import com.rtmap.modules.sys.entity.SysUserMarketEntity;
import com.rtmap.modules.sys.service.SysUserMarketService;
import com.rtmap.modules.sys.service.SysUserService;
import com.rtmap.service.DynamicDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多数据源测试
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest extends AbstractController {

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceTest.class);

    @Autowired
    private DynamicDataSourceTestService dynamicDataSourceTestService;

    @Autowired
    private SysUserMarketService sysUserMarketService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    GroupService groupService;

    @Test
    public void test(){
        Long id = 1L;

        dynamicDataSourceTestService.updateUser(id);
        dynamicDataSourceTestService.updateUserBySlave1(id);
        dynamicDataSourceTestService.updateUserBySlave2(id);
    }

    /**
     * 获取所有的项目
     */
    @Test
    public void getMarketListTest(){
        List<SysMarketEntity > marketList = sysUserMarketService.getMarketList();

        logger.info("marketList ==>> {}", R.ok().put("marketList",marketList));
    }

    /**
     * 检验增加user与market关系
     */
/*    @Test
    public void saveOrUpdateUserMarket(){
        List<String> marketList = new ArrayList<>();
        marketList.add("1");
        marketList.add("2");
        marketList.add("3");

        sysUserMarketService.saveOrUpdate(1L,marketList);

       logger.info("user与market关系插入成功！");
    }*/

    /**
     * 分页查询用户列表
     */
    @Test
    public void queryUserListByPage(){
        Map<String,Object> params = new HashMap<>();
//        params.put("username","admin");
//        params.put("createUserId",1L);

//        params.put("curPage","1");
//        params.put("limit","1");
        PageUtils pageUtils = sysUserService.queryPage(params);
        logger.info("TotalPage ==>> {}",pageUtils.getTotalPage());
        logger.info("TotalCount ==>> {}",pageUtils.getTotalCount());
        logger.info("CurrPage ==>> {}", pageUtils.getCurrPage());
        logger.info("PageSize ==>> {}",pageUtils.getPageSize());
        logger.info("List ==>> {}",pageUtils.getList());



    }

    /**
     * portal页查询用户是否有权限查看项目
     */
    @Test
    public void queryUserMarket(){

        List<SysUserMarketEntity> userMarketList = sysUserMarketService.getUserMarketList(1L);

        List<SysUserGroupEntity> userGroupList = sysUserMarketService.getUserGroupList(3L);

        logger.info("userMarketList ==>> {}",userMarketList);

        logger.info("userGroupList ==>> {}",userGroupList);
    }

    /**
     * 获取有权限的marketIdList
     */
    @Test
    public void queryUserMarketIdList(){

        List<String> list = sysUserMarketService.queryMarketIdList(1L);
        logger.info("userMarketList ==>> {}",list);
    }




    @Autowired
    MapService mapService;

    @Test
    public void testMapGeoJson(){
        MapVo mapVo = new MapVo();
        mapVo.setBuildid("1");
        mapVo.setFloor("F1");
        String s = mapService.queryMapGeoJson(mapVo);
        logger.info("geoJson ==>> {}",s);
    }


    @Autowired
    AppMarketAnalysisController appMarketAnalysisController;

    /**
     * 项目综合分析-概况
     */
    @Test
    public void overview(){
        MarketVo marketVo = new MarketVo();
        marketVo.setDateType("D");
        marketVo.setMarketId("P_11");
        marketVo.setStartTime("20190101");
        appMarketAnalysisController.overview(marketVo);
    }



}
