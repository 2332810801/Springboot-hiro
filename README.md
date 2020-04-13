
# shiro概述

> 1. Apache Shiro是Java的一个安全框架
> 2. Shiro是一个强大的简单易用的Java安全框架，主要用来更便捷的认证、授权、加密、会话管理、与Web集成、缓存等
> 3. Shiro使用起来小而简单
> 3. spring中有spring security ,是一个权限框架，它和spring依赖过于紧密，没有shiro使用简单。 
> 4. shiro不依赖于spring,shiro不仅可以实现web应用的权限管理，还可以实现c/s系统，分布式系统权限管理，
> 5. shiro属于轻量框架，越来越多企业项目开始使用shiro.

## shiro核心概念
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413110839579.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
1. Authentication：身份认证/登录，验证用户是不是拥有相应的身份；
2. Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；
3. Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；
4. Cryptography：加密，保护数据的安全性
5. Web Support：Web支持，可以非常容易的集成到Web环境；
6. Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；
7. Concurrency：shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；
8. Testing：提供测试支持；
9. Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
10. Remember Me：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。 

## 主要概念
### 1. Subject 当前的操作用户

>1.  可以是人 爬虫 当前跟软件交互的东西
> 2. 在shiro当中我们可以统称"用户" 在代码的任何地方，你都能轻易的获得Shiro Subject。
> 3. 一旦获得Subject，你就可以立即获得你希望用Shiro为当前用户做的90%的事情 ，登录、退、访问会话、执行授权检查等 

### 2. SecurityManager

> 1. SecurityManager则管理所有用户的安全操作
> 2. 引用了多个内部嵌套安全组件,是Shiro框架的核心
> 3. 你可以把它看成DispatcherServlet前端控制器。
> 4. 用于调度各种Shiro框架的服务

### 3. Realms

> 1. Realms则是用户的信息认证器和用户的权限认证器
> 2. 执行认证（登录）和授权（访问控制）时,Shiro会从应用配置的Realm中查找很多内容
> 3. Realm 可以理解为读取用户信息、角色及权限的 DAO
> 4. SecurityManager要验证用户身份与权限，那么它需要从Realm获取相应的信息进行比较以确定用户身份是否合法；
> 5. 可以把Realm看成DataSource，即安全数据源。

### 4. Shiro架构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413111313245.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
1. subject:主体 主体可以是用户也可以是程序，主体要访问系统，系统需要对主体进行认证、授权。
2. authenticator: 认证器 主体进行认证最终通过authenticator进行的。 
3. authorizer: 授权器 主体进行授权最终通过authenticator进行的。 
4. sessionManager:会话管理 web应用中一般是用web容器对session进行管理，shiro也提供一套session管理的方式。 
5. sessionDao: 通过sessionDao管理session数据， 
6. cacheManager: 缓存管理器 主要对session和授权数据进行缓存，比如将授权数据通过cacheManager进行缓存管理，和 ehcache整合对缓存数据进行管理。 
7. realm: 领域 相当于数据源，通过realm存取认证、授权相关数据。 
8. cryptography: 密码管理 提供了一套加密/解密的组件，方便开发。比如 提供常用的散列、加/解密等功能。


# Springboot整合shiro
1. 新建一个springboot项目
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041311170641.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

2. 导入springboot-web依赖![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413112117284.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
3. 编写controller和前端登录页面
需要整合thymeleaf 加入thymeleaf的依赖

```java
 <!--thymeleaf依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-java8time</artifactId>
        </dependency>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413112606529.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
4. 编写前端页面
在templates目录下编写 login.html，add.html,delete.html,index.html
导入thymeleaf的dtd

```sql
xmlns:th="http://www.thymeleaf.org"
```
login.html
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413112958245.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<form th:action="login">
    <input type="text" name="username" value="">
    <input type="password" name="password" value="">
    <input type="submit" value="登录">
</form>
</body>
</html>
```
index.html
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413113328703.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
add.html
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413113344235.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
delete.html
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041311340116.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
5. 编写controller，跳转到登录页面的方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413113302280.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
@RequestMapping({"/","tologin"})
    public String tologin(){
        return "login";
    }
```
6. 运行启动 浏览器输入localhost:8080
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413113505479.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
7. 导入springboot整合shiro和mybatis连接数据库的依赖
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413115738443.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
		<!--shiro-整合spring的包-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.2</version>
        </dependency>
          <!--shiro整合thymeleaf-->
        <dependency>
            <groupId>com.github.theborakompanioni</groupId>
            <artifactId>thymeleaf-extras-shiro</artifactId>
            <version>2.0.0</version>
        </dependency>
         <!--mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>
```
8. 创建数据库 进行下一步的认证授权
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413113834127.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413113848213.png)

```java
##配置数据驱动信息  （key固定）
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql:///spring_shiro
spring.datasource.username = root
spring.datasource.password =123456

```

在application.properties中添加数据库信息
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041311503158.png)

9. 编写mapper,service,pojo
pojo：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413115313891.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
mapper:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413120027893.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
service:
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041312021446.png)
Impl:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413120235130.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
controller：测试是否能够查询到
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413120454976.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041312065545.png)
10. 编写shiroconfig配置类
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413114012436.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
11. 编写realm类 继承AuthorizingRealm类 并实现方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041311460678.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
12. 在shiroConfig中编写代码
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413121017371.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
@Configuration
public class shiroConfig {

    //3. shiroFilterfactaryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);//设置安全管理器
        shiroFilterFactoryBean.setLoginUrl("/toLogin");//没有认证后跳到的页面
        /**
         * shiro的内置过滤器
         anon：无需认证就可以访问 默认
         authc：必须认证了才能访问
         user：必须拥有记住我功能才能访问
         perms：必须拥有对某个的权限才能访问
         role：拥有某个角色权限才能访问
         */
        //添加shiro的内置过滤器  设置要拦截的url
        Map<String,String>  filterChainDefinitionMap=new LinkedHashMap<>();//拦截
        filterChainDefinitionMap.put("/add","authc");// /add请求必须认证才能访问
        filterChainDefinitionMap.put("/del","authc");//del必须认证才能访问
        // filterChainDefinitionMap.put("user/**","authc");//支持通配符
        //授权
        filterChainDefinitionMap.put("/add","perms[user:add]");//没有这个user:add权限的会被拦截下来
        filterChainDefinitionMap.put("/del","perms[user:delete]");//没有这个user:delete权限的会被拦截下来
        //未授权的跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized");
        //设置注销的url
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);//把设置好的过滤设置到ShiroFilterFactoryBean
        return shiroFilterFactoryBean;
    }

    //2. DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm对象  userRealm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }
    //1. 创建realm对象 自定义的·类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect：用来整合shiro-thymeleaf
    @Bean
    public ShiroDialect getshiroDialect(){
        return new  ShiroDialect();
    }

}
```
13. 在UserRealm编写代码

```java
public class UserRealm extends AuthorizingRealm {

    @Autowired
    InfoService service;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权 doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpInfo = new SimpleAuthorizationInfo();
        //获取当前用户的对象
        Subject subject=SecurityUtils.getSubject();
        Info user = (Info)subject.getPrincipal();//获取用户信息
        simpInfo.addStringPermission(user.getPerm());//获取数据库权限
        return simpInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证 doGetAuthorizationInfo");
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken userToken=(UsernamePasswordToken)authenticationToken;//获取登录的信息
        //获取用户名 密码  数据库取
        System.out.println(userToken.getUsername());
        Info query = service.queryByName(userToken.getUsername());
        System.out.println(query);
        if(query==null){//没有这个用户
            return null;
        }
        Session session=subject.getSession();//获取用户的session
        session.setAttribute("loginuser",query);

        if(!userToken.getUsername().equals(query.getUsername())){//判断登录的用户名密码 匹配数据库是否正确
            return null;//抛出异常
        }
        //密码认证，shiro做
        return new SimpleAuthenticationInfo(query,query.getPassword(),"");
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413121308303.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
14. 改写controller
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413121651845.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            //获取当前的用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登录数据
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);//执行登录的方法 没有异常就成功了
            return "index";
        } catch (UnknownAccountException e) {
            /**
             * 异常信息
             * UnknownAccountException ：用户名不存在
             * IncorrectCredentialsException：密码错误
             */
            e.printStackTrace();
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "login";
    }

    @RequestMapping("/add")
    public String add(){//跳转页面
        return "add";
    }
    @RequestMapping("/del")
    public String delete(){//跳转页面
        return "delete";
    }
     @RequestMapping("/Unauthorized")
    public String Unauthorized(){//没有权限跳转的url
        return "Unauthorized";
    }
    //注销
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginuser",null);//清空session
        return "login";
    }
```
15. 编写未授权的页面
Unauthorized.html
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413122947542.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
16. 运行：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413123114542.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413123136360.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
root用户只有user:delete权限 所以只能看到删除按钮
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413123226797.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413123236830.png)
dj用户只有user:add权限 只能看到添加
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413123314632.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413123324726.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
ss用户什么权限也没有 所以什么按钮也看不见

其中还有加密方式 比如md5 可以自行添加 就不一一介绍了 

重点：shiroConfig类和UserRealm类配置好 核心**
