package com.dj.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author joker_dj
 * @create 2020-04-13日 1:32
 */
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
