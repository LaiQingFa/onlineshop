# 电子商城练习demo

## 技术选型

* Spring、SpringMVC、Mybatis
* JSP、JSTL、jQuery、jQuery plugin、EasyUI、KindEditor（富文本编辑器）、CSS+DIV
* Redis（缓存服务器）
* Solr（搜索）
* httpclient（调用系统服务）
* Mysql5.7
* Nginx（web服务器）

## 建立的工程
* onlineshop-parent  
	公司级别的父工程，所有工程必须继承这个(pom包)，在其pom.xml文件中，定义了所有用到的jar包所有版本信息
* onlineshop-common
	 通用工程，每一个工程中有很多工具类，文件或图片以及上传下载的处理，json转java对象，java对象转json。
	将所有工具类打成一个jar包，让其他项目以来这个jar包这个工程也需要继承taotao-parent工程。注意：创建maven项目的时候选用jar
* onlineshop-manager
	后台管理工程，这是一个聚合工程.依赖于onlineshop-common,同时四个工程onlineshop-pojo(jar包),
	onlineshop-mapper(jar包),onlineshop-service(jar包),onlineshop-controller(war包)继承于这个聚合工程.
* onlineshop-manager-pojo  	
	maven module 工程内部一个模块，可以打成一个jar包,不需要修改，onlineshop-manager-pojo模块不依赖任何jar包
* onlineshop-manager-mapper  
    onlineshop-manager工程内部一个模块，依赖于onlineshop-manager-pojo  
* onlineshop-manager-service  
	onlineshop-manager工程内部一个模块，依赖于onlineshop-manager-manager  
* onlineshop-manager-web  
	onlineshop-manager工程内部一个模块，依赖于onlineshop-manager-service,注意：创建的时候会报错，原因需要在webapp下创建文件夹WEB-INF并在这个文件夹下创建web.xml文件

	
### 测试聚合工程onlineshop-manager  
	在onlineshop-manager-web的webapp下创建一个index.jsp，在onlineshop-manager中添加maven的tomcat7插件。  
	1、需要把taotao-parent工程安装到本地仓库。Install  
	右键  run asmaven Install  
	2、需要把taotao-common安装到本地仓库。  
	启动工程：右键 聚合工程onlineshop-manager-->run as-->maven build  
	使用maven命令：  
	clean tomcat7:run
	
## 数据库  
	在互联网行业的项目中尽可能的减少表的管理查询。使用冗余解决表的关联问题。有利于分库分表，大量的关联可能导致数据库奔溃，多保持单表查询，便于以后分库分表  
	Sku：最小库存量单位。就是商品id。就是商品最细力度的划分。每个sku都唯一对应一款商品，商品的颜色、配置都已经唯一确定
	
## 整合的思路
### Dao层
	使用mybatis框架。创建SqlMapConfig.xml。
	创建一个applicationContext-dao.xml
	1、配置数据源
	2、需要让spring容器管理SqlsessionFactory，单例存在。
	3、把mapper的代理对象放到spring容器中。使用扫描包的方式加载mapper的代理对象。
### Service层
	1、事务管理
	2、需要把service实现类对象放到spring容器中管理
### 表现层
	处理器，适配器，视图解析器  三大组件，不可见的
	1、配置注解驱动
	2、配置视图解析器
	3、需要扫描controller
### Web.xml
	1、spring容器的配置
	2、Springmvc前端控制器的配置
	3、Post乱码过滤器

### spring与springmvc父子容器	
	Spring容器（service,dao）   子容器springmvc(controller),子容器可以访问父容器中的对象，父容器不能访问子容器对象
	
	
	
	
	
	
	
	
	