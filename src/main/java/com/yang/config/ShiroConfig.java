package com.yang.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YANG HANG
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /**
         * anon:无需认证就可以访问
         * authc:必须认证才能访问
         * user：必须拥有记住我功能才能用
         * perms：必须拥有某个资源的权限才能访问
         * role：必须拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权
        // filterMap.put("/captcha","anon");
        // filterMap.put("/api/**","anon");
        // filterMap.put("/css/**","anon");
        // filterMap.put("/images/**","anon");
        // filterMap.put("/js/**","anon");
        // filterMap.put("/lib/**","anon");
        // filterMap.put("/athlete/*","perms[perm:admin]");
        // filterMap.put("/teacher/*","perms[perm:admin]");
        // filterMap.put("/athlete/add","perms[perm:ath]");
        // filterMap.put("/athlete/list","perms[perm:ath]");
        // filterMap.put("/teacher/update","perms[perm:tea]");
        // filterMap.put("/teacher/delete","perms[perm:tea]");
        //
        // //拦截
        // filterMap.put("/athlete/*","authc");
        filterMap.put("/login","authc");
        //
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        //
        // //如果没有权限跳转到登陆页面
        // shiroFilterFactoryBean.setLoginUrl("http://www.baidu.com");
        // shiroFilterFactoryBean.setUnauthorizedUrl("http://www.baidu.com");
        // //设置未授权的页面
        // shiroFilterFactoryBean.setUnauthorizedUrl("/404");

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm对象
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建 Realm 对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
