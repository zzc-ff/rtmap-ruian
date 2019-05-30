**项目说明** 
<br>
**项目结构** 
```
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─Application 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源

```
**技术选型：** 
- 核心框架：Spring Boot 2.1
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.0
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x 
<br> 

**版本更新记录:**
```
V0.0.1
    
V0.0.2
更新日期:20190529
    更新内容:
        1)集团驾驶舱确定redis数据格式，接入数据
        2)车流修改
        3)集团驾驶舱地图buildId poi 绑定
V0.0.3
更新日期:20190530
    更新内容:
        1)项目驾驶舱redis数据接入数据
        2)业态查询sql修改
        3)项目驾驶舱地图buildId、poi绑定
        4)地图上瑞安楼层与地图楼层关联

```



