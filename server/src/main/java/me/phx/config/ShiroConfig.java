package me.phx.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public FilterRegistrationBean delegatingFilterRegistration() {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName("shiroFilterFactoryBean");
        delegatingFilterProxy.setTargetFilterLifecycle(true);

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(delegatingFilterProxy);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());

        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/");

        Map<String, String> filterChainMappings = new HashMap<>();
        filterChainMappings.put("/login", "anon");
        filterChainMappings.put("/logout", "logout");
        filterChainMappings.put("/house/**", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainMappings);
        return filterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(iniRealm());
        securityManager.setSessionManager(new DefaultWebSessionManager());
        return securityManager;
    }
//
//    @Bean
//    public Realm iniRealm() {
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        return iniRealm;
//    }

    @Bean
    public Realm jdbcRealm(DataSource dataSource) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setSaltStyle(JdbcRealm.SaltStyle.COLUMN);
        return jdbcRealm;
    }

    // Enable Shiro Annotations for Spring-configured beans.  Only run after
    // the lifecycleBeanProcessor has run:
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public PointcutAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
