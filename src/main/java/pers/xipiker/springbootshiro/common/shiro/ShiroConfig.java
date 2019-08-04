package pers.xipiker.springbootshiro.common.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: xipiker
 * @Date: 2019-8-3
 * @Description: ShiroConfig
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用过滤器:
         *  anon:无需认证（登录）可以访问
         *  authc:必须认证才可以访问
         *  user:如果使用rememberMe的功能可以直接访问
         *  perms:该资源必须得到资源权限才可以访问
         *  role:该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
//        filterMap.put("/add", "authc");
//        filterMap.put("/update", "authc");
        filterMap.put("/test", "anon");
        //放行login.html
        filterMap.put("/login", "anon");

        //授权过滤器
        //注意:当授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/add", "perms[user:add]");

        filterMap.put("/*", "authc");
        //拦截成功会默认跳转到login.jsp
        //修改默认拦截成功跳转到login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权拦截器
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
