# 构建pom工程，概括各主要依赖的合作版本
# 构建微服务模块
## 写payment8001 步骤
1. 建module -->  第1个模块：cloud-provider-payment8001
2. 改pom
3. 写yml
4. 主启动
5. 写业务
   1. 建表
   2. 写entity
   3. 写dao
   4. 写service
   5. 写controller
## 热部署
1. 导依赖 DevTool
2. 导插件 父类pom中插件
3. 开启自动编译 --> compile中 选中ADBC四个选项
4. shift + option + cmd + / 打开registry ； 选中 compile.automake.allow... 和 actionSystem.assertFocusAccess...
5. 重启idea
## 写cloud-consumer-order80
1. 步骤同第1个模块 
2. 重点：使用RestTemplate调用服务提供方的服务；注意写上各种注解
## 工程重构
1. 在重构工程中复制公用的包路径和实例类
2. maven clean 和 install
3. 改造其他module
   1. 删除共享代码
   2. 添加公用包的坐标
## Eureka配置
1. 服务器端注册
   1. 导包 spring-cloud-starter-netflix-eureka-server
   2. 写yml
   3. 写主启动 ： @EnableEurekaServer  //一定标注是服务器端 ！！
   4. 测试 启动后访问 localhost:7001
2. 服务提供方注册改造
   1. 导client包  spring-cloud-starter-netflix-eureka-client
   2. 主启动加注解 @EnableEurekaClient  //作为服务注册方
   3. 改yml  ： 声明为服务提供方，注册导服务中心
3. 集群eureka服务器环境： 互相注册，互相守望
   1. 新建类似cloud-eureka-server7002，pom同7001
   2. 修改hosts映射配置  /etc/hosts文件 -->  127.0.0.1  eureka7001.com / 127.0.0.1  eureka7002.com
   3. 修改yml文件  互相注册，互相守望
   4. 测试访问 访问测试 eureka7001.com/7001 /  eureka7002.com/7002
4. 服务消费者环境，只需增加defaultZone中的注册连接为两个服务器，再测试
5. 集群服务提供者模块，复制创建cloud-provider-payment8002
   1. 注意修改yml中的端口号
   2. 在8002和8001 controller中的PaymentController 增加显示serverport的代码
   3. 测试
   4. 开启负载均衡
      1. 对于服务提供者是集群时，消费者访问连接只写服务注册名，至于哪台机器响应，不确定
      2. 同时要在服务提供者配置类ApplicationContextConfig中restful方法中开启@LoadBalanced  //开启负载均衡注解
         - 通过getRestTemplate开启负载均衡功能
   5. 完善主机信息的配置 acurator功能
      1. 在payment8001/8002的yml文件中，给eureka下加instanceid:payment8001/8002；这样注册主页上将显示为主机名
         - 可通过访问http://localhost:8001/actuator/health看服务器是否假死
   6. 服务发现功能
      1. 在服务提供者主程序加@EnableDiscoveryClient //启动服务发现
      2. 在PaymentController添加DiscoveryClient;//服务发现对象；并添加响应函数，输出服务提供信息
6. 自我保护机制：如发现与微服务暂时短线，不会立即删除服务，会保留服务注册信息   cap - ap 模式 保证可用性
   1. 手动关闭：
   2. 服务器端：在cloud-eureka-server7001/2端修改yml如下
   > eureka:
      server:
         enable-self-preservation: false 服务心跳没有了，及时删除注册信息； 默认为true 默认开启自我保护的
         eviction-interval-timer-in-ms: 2000 心跳间隔时间缩小到2s
   3. 客户端：cloud-provider-payment8001中yml改动：
   > eureka:
      instance:
      leased-renewal-interval-in-seconds: 30 客户端向服务器发送心跳的时间间隔  默认30s
      leased-expiration-duration-in-seconds: 90  服务端收到最后一次心跳后等待的时间上限，默认90s；超时将删除服务
## zookeeper
### 服务提供方配置
1. 建module -->  cloud-provider-payment8004
2. 改pom 引入zookepper支持
3. 写yml ：指定注册到zookeeper 服务器  ！！== 注册为临时节点，没心跳就删除节点  为cp模式  保证一致性
4. 主启动：  @EnableDiscoveryClient //注解用于使用consul或zookepper作为注册中时注册服务
5. 写业务 写controlller打印 serverPort信息
### zookeeper 服务消费方配置
1. 建module -->  cloud-consumerzk-order80
2. 改pom 同上
3. 写yml ：指定注册到zookeeper 服务器的 地址 
4. 主启动： 同上
5. 写业务
   1. 写config ： 使用RestTemplate调用服务提供方的服务
   2. 配置写controlller打印 serverPort信息  ///注意PAYMENT_URL要和服务提供方注册名大小写一致


## consul
### 服务提供方配置
- 写yml ：指定注册到consul 服务器
### 服务消费方配置 同上

##负载均衡 Load Banance
1. 服务端LB Nginx :服务端分流
2. 进程内LB ribbon： 本地的；到注册中心上查询服务可用列表，根据指定分流策略实行远程调用；ribbon只是一个类库，继承消费方进程
3. Ribbon： 负载均衡+RestTemplate调用
4. cloud-consumer-order80 中存在ribbon相关的代码，会带来服务无法发现的问题；如需重用，需注释掉，包括pom依赖

## OpenFeign : 
1. 接口调用绑定
   1. 写service接口，并绑定feign；
   2. 主启动加注解
   3. 写controller 
2. 超时时间自定义 见yml参数
3. 日志增强 -- feign接口的调用情况和监控、日志输出   
   1. 配置bean
   2. 加yml参数
## Hystrix
### 概念
1. 服务降级  fallback:服务临时出现问题时给出临时解决方法，而不宕机
2. 服务熔断  break ：保险丝，达到最大访问量时拒绝访问
3. 服务限流  flowlimit ： 秒杀高并发的工作
### 服务提供模块
1. 标准步骤 导包-写yml-写主程序-写业务-测试
2. 服务端服务降级处理： 增加服务超时fallback兜底方法； 并主启动@EnableHystrix //开启Hystrix服务
### 服务提供模块
1. 同上
2. 客户端fallback
   1. yml开启Hystrix
   2. 主启动加@EnableHystrix
   3. 配置使用全局fallback
      1. 写全局兜底方法
      2. 在类头上加@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //使用全局的fallback
   4. 在feign定义的接口上实现
      1. 建fallback类，实现该接口
      2. 在接口类上绑定该fallback兜底类   ？？测试没通过？？
### 服务熔断
1. 修改cloud-provider-hystrix-payment8001；添加熔断判断代码和fallback
2. 工作流程图：可参考官网图片
### dashboard
1. 建dashboard  cloud-consumer-hystrix-dashboard9001；测试成功
   1. yml中 加proxy-stream-allow-list: "localhost" 属性  解决dashboard不能正确连接的问题
2. 服务监控的服务器pom都要包含actuator依赖
3. 在被监控服务器8001/2主启动程序加适配代码  解决dashboard不能正确连接的问题

## 网关
### Gateway
1. 概念：使用Webflux中的reactor-netty响应式编程组件，底层用了netty通讯框架；基于异步非阻塞模型
2. Webflux： 异步非阻塞框架
3. Gateway核心：请求转发 + 执行过滤连
   1. router：转发前后的精细化控制
   2. predicate: 匹配条件
   3. filter：拦截器
4. 创建步骤
   1. 建模  cloud-gateway-gateway9527
   2. 增加yml中网关配置
   3. 配置路由地址两种方式
      1. yml中写：通过注册服务名，负载均衡，测试ok； 通过localhost 测试报错
      2. 写注册类 转发到百度 测试ok
   4. predicate的多种过滤条件使用
   5. filter使用
      1. 使用yml定义，类似于predicate
      2. 自定义filter
         1. 创建filter类 继承2个接口： GlobalFilter, Ordered
         2. 设置过滤条件即可
## 配置中心
### 服务端配置步骤
1. 创建module  cloud-config-center3344
2. 导包、写yml、主程序
3. 改hosts文件  加 映射 127.0.0.1	config-3344.com
4. 测试 访问地址  config-3344.com：3344/main/application.yml 读不到文件内容
   1. http://config-3344.com:3344/config/dev/main  测试ok
## 客户端配置步骤
1. 同上建立3355
2. 写bootstrap代替application.yml，优先从3344加载配置文件； 
3. 测试  访问地址 http://localhost:3355/configInfo  见controller
4. 动态刷新：需要手动刷新
   1. pom里要加acuator监控依赖
   2. 暴露监控端点
   3. controller加注解开启 @RefreshScope  //开启动态监控
   4. 修改github内容后，需要运维人员手动发送post请求 ：curl -X POST "http://localhost:3355/actuator/refresh" ，刷新
## Bus
### 理论
1. 配合config组件实现配置动态刷新
2. 整合了java时间处理机制和消息中间件的功能
3. 支持RabbitMQ/Kafka
4. 作用：管理和传播分布式系统间的消息
5. 机制：springClient会关注MQ中同一个主题（默认为bus）
      当一个服务刷新数据时，将该消息放入topic， 这样其他监听同一服务的都会收到通知
### 测试步骤
1. 建模     cloud-config-client3366
2. 安装RabbitMQ - 安装erlang环境和 RabbitMQ  访问地址 //localhost:15672
   1. 相关命令见博客园 
      1. 在安装目录中sbin目录下开启服务
      2. 开启网页管理后 即可登陆网页管理页面，表示安装成功
3. 两种消息通知的方式
   1. 中央广播：bus通知config server, 有服务器广播：更符合应用模式
   2. 小道消息：bus通知各应用客服端，有各应用服务对外扩散：服务器迁移时有更多修改
4. 本例采用中央广播的方式构建
   1. 在配置服务器端增加广播支持
      1. 3344中增加spring-cloud-starter-bus-amqp依赖
      2. yml中增加 RabbitMQ支持和刷新端口
   2. 客户端3355/3366 也要改代码
      1. 增加spring-cloud-starter-bus-amqp依赖
      2. yml中增加 RabbitMQ支持和刷新端口
   3. 测试
      1. 修改github上的配置文件内容
      2. 配置服务器 刷新一次，全局广播 curl -X POST "http://localhost:3344/actuator/bus-refresh" 刷新失败！！
      3. 配置中心测试  http://config-3344.com:3344/config/dev/main
      4. 客户端测试  //localhost:3355/configInfo   //localhost:3366/configInfo
      5. 定点广播：curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355" 只对3355广播
## cloud stream
1. 统一管辖 屏蔽底层消息中间件的差异，同一消息的编程模型 RabbitMQ/Kafka；目前只支持前两种；RocketMQ\ActiveMQ\ 
2. 发布-订阅模式
3. 使用步骤
   1. 建模 cloud-stream-rabbitmq-provider8801 导包spring-cloud-starter-stream-rabbit 注意用eureka-lient
      1. 在controller中使用 streamBridge 发送消息，其中包含bindingname和消息
      2. 改yml
         1. bindings: sms-out-0:  名字指定为  方法名 + out/in + index
         2. function:destination: sms   名字为方法名
   2. 消费端 cloud-stream-rabbitmq-consumer8802 更改 
      1. yml中增加绑定和函数名
         bindings: sms1-in-0:   function: sms1
      2. 主程序中添加打印功能的bean
   3. 测试 访问http://127.0.0.1:8801/sendsms1  rabbitmq网页管理中会动态显示访问量 测试ok
   4. 同时有两个时，测试失败
4. 重复消费：同一个应用下的不同消费者8802/3，通过分组来决定是否重复消费；若在同一组，则竞争，不重复；不同组，则重复广播
5. 持久化：只要分组配置还在，即使该消费者下线再上线，也可以收到属于他分组的消息；

## Nacos
1. 支持ap/cp切换
### Nacos安装
1. 安装  官网下载安装包解压即可  修改startup.sh中start mode = standalone启动
2. 测试：访问 http://localhost:8848/nacos/ 账号密码nacos 登陆成功即可
### 创建服务提供者
1. 建模 cloudalibaba-provider-payment9001/9002 两个服务提供者
2. 改yml
3. 写controller
### 创建消费者 cloudalibaba-consumer-nacos-order83
1. 建模，改yml，写配置，加controller
2. 注意：需要在配置类ApplicationContextConfig 加@LoadBalanced  否则报错；有可能使用翻墙软件会报错！！
### 创建config模块 
1. 建模 cloudalibaba-config-nacos-client3377  可替代eureka springcloud-config/bus功能
   1. 配置yml和bootstrap两个配置文件；需手动增加bootstrap依赖
   2. 在nacos服务器后台上增加 nacos-config-client-dev.yaml
   3. 运行测试 访问 http://localhost:3377/config/info; 支持动态刷新配置文件
2. 分类配置
   1. namespace区分部署环境，默认public，主要用来隔离不同开发环境，开发、测试、生产环境
   2. group；可以把不同微服务划分到一个组；默认组为DEFAULT_GROUP
   3. service: 一个service可以包含多个集群；默认cluster是default；cluster指定微服务的虚拟分区，如不同地方的机房
   4. 测试：通过修改application.yml中的开发环境配置，可实现下载对应的配置文件信息
      1. 可在配置服务器上定义多个命名空间、分组；两个yml文件中的文件名和路径组合要和服务器一起，否则启动时注入config.info报错
3. 集群和持久化
   1. nacos自带继承的是derby嵌入式数据库，临时数据都存在此。集群配置服务器下，无法同步配置数据
   2. 切换到mysql数据库
      1. 找到nacos安装目录下config文件夹中nacos-sql.sql文件，执行此内脚本建立对应数据库表
      2. 找到同目录下application.properties文件，配置数据源，内置注释代码，自定义即可；
      3. 重启nacos服务，再登陆即可新增配置，并写入mysql
   3. 模拟生产环境，推荐使用linux
      - 需要1各nginx虚拟ip和3各nacos注册中心、1个mysql
        1. 准备工作
           - 数据库建表--> 和上面第2步相同
           - nacos 安装，见上
           - nginx安装 ： brew安装 测试 http://localhost:8080/
        2. 配置
           - nacos集群配置： 改写cluster.config  增加三份映射 192.168.27.177：3333/4444/5555
             1. 不能翻墙；
             2. 减少启动文件中java虚拟机的堆内存大小；sudo vim startup.sh /
                - heap size: -Xms128g -Xmx512g 在此设置下可同时启动三个
           - nginx集群配置
             - /usr/local/etc/nginx/nginx.conf 中增加3处更改，增加nacos节点： 注释为cluster for nacos
             - nginx测试 http://192.168.27.177:1111/nacos/#/login
             - 问题： nacos节点闪退 导致9002注册不成功！！
## sentinel 类似hystrix  熔断限流框架
1. 安装控制台程序 
   1. 从官网下载jar包
   2. java -jar xxx.jar 启动时保证8080端口不被占用
   3. 测试 ：http://localhost:8080/#/login  因为要注册到nacos，所以此时切换为单机版，集群测试节点有闪退
2. 建模 cloudalibaba-sentinel-service8401
   1. pom中增加sentinel相关包
   2. yml中要注册到nacos和sentinel
   3. 写限流测试controller
   4. 启动测试 访问8401接口，sentinel中即可监控和设置流控等；监控中需要提前访问一次接口刷新下才能显示
3. sentinel流控、降级等功能
   1. 流控：见官网解释
   2. 降级：满足一定条件时熔断
   3. 热点限流 ： 针对热点关键词限流 
      1. 在controller中增加兜底方法
      2. 通过热点规则中的参数例外项来设置 热点词拥有额外大的访问量阀值
      3. 注意：此兜底方法只负责兜底配置中出现的异常条件；不负责兜底接口中的异常，即运行出错不管
   4. 系统自适应限流：对应用入口流量进行控制
   5. 按资源名称设置流控  资源名即是接口上方@SentinelResource中的value值
      1. @SentinelResource 可设置资源名称  和接口的自定义兜底方法；但不支持private方法
   6. 按访问地址设置流控： 资源名也可以是@GetMapping中的地址
   7. 自定义兜底方法逻辑类，解决兜底方法与业务代码耦合，代码膨胀的问题
      1. 自定义一个global处理兜底方法类CustomerBlockHandler
      2. 在controller中@SentinelResource中参数增加兜底类和对应兜底方法两个参数，即可实现跳转
   8. 三个核心api
      1. SphU 定义资源
      2. Tracer 定义统计
      3. ContextUtil 定义上下文
4. 整合
   1. 建模 
      1. cloudalibaba-provider-payment9003
      2. cloudalibaba-provider-payment9004
      3. cloudalibaba-consumer-nacos-order84
   2. 测试 http://localhost:84/consumer/fallback/1-3-4-5
      1. 与Ribbon整合
         1. @SentinelResource中增加参数fallback = "handlerFallback" ；对运行异常兜底
         2. @SentinelResource中增加参数blockHandler = "blockHandler" ；对sentinel配置违规异常兜底
         3. 两个都配，sentinel配置违规还是违规管
         4. exceptionsToIgnore：此设置的错误不再降级，直接报错，走error page
      2. 与feign整合，一般消费侧，此处为 cloudalibaba-consumer-nacos-order84
         1. 导feign依赖3个、改yml增加sentinel对feign支持、主启动开启feign客户端、
         2. 使用ribbon实现调用服务端  -- 通过RestTemplate调用
         3. 使用feign调用  -- 需要增加调用接口和 统一兜底方法类 PaymentFallbackService
5. 持久化
   1. 基于cloudalibaba-sentinel-service8401改造
      1. 实现：访问8401后，sentinel就能监控，此时流控配置自动写入nacos；只要nacos配置不删除，就会一直保存，不受8401重启影响
      2. 步骤：
         1. 导包 sentinel-datasource-nacos
         2. yml中添加数据源  -- 写入nacos
         3. 在nacos web后台中添加dataID的json串
            "resource":"/rateLimit/byUrl",     ：资源名称
            "limitApp":"default",               来源应用
            "grade":1,                 阀值类型：0 表线程数  1: 表QPS
            "count":1,                 单机阀值  
            "stragtegy":0,             流控模式 0 直接 1 关联 2 链路
            "controlBehavior":0,       流控效果 0 快速失效 2 warm up  3 排队等待
            "clusterMode":false        是否集群
         4. 测试： 当访问http://localhost:8401/ratelimit/byUrl 时，nacos中json串配置自动导入到sentinel，并生效
## Seata 解决分布式事务问题 --》 全局数据一致性问题
1. 术语
   1. 全局唯一事务ID
   2. 三组件
      1. Transaction Coordinator: TC  协调全局事务的提交或回滚
      2. Transaction Manager: TM  控制全局事务的边界，负责开启全局事务，并最终发起全局事务的提交或回滚的决议
      3. Resource Manager: RM  控制分支事务
   3. 使用@GlobalTransaction在业务方法上   @Transaction 是本地的
2. 安装
   1. 官网下载，解压
   2. 修改conf目录下file.conf:  service / store模块 
      1. 自定义事务组名称  新版没找到
      2. 日志存储模式 db
      3. 数据库连接信息  修改用户名和密码，库名称为seata; 注意mysql的driver是 .cj.
   3. 创建数据库seata
   4. 修改registry.conf  改为绑定nacos；确认端口地址和用户名、密码
   5. 案例结构: 
      1. 3个服务组成事务  order ->  storage -> account -> order  
      2. 下订单 -- 减库存 -- 扣款 -- 订单成功
      3. 建模 seata-order-service2001;导包
      4. yml + 2个conf
      5. domain + dao + mapper sql语句和数据库字段映射
      6. service
      7. config
            
   

