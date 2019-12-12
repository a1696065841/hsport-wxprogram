package com.hsport.wxprogram.common.shiro;


import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
		System.out.println("--------------------shiro filter-------------------");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		//注意过滤器配置顺序 不能颠倒
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/region/selectCity", "anon");
		filterChainDefinitionMap.put("/login/login", "anon");
		filterChainDefinitionMap.put("/region/selectAreaByCityID/*","anon");
		//拦截其他所有接口
	//	filterChainDefinitionMap.put("/**", "authc");
		//配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		shiroFilterFactoryBean.setLoginUrl("/user/unlogin");
		// 登录成功后要跳转的链接 自行处理。不用shiro进行跳转
		// shiroFilterFactoryBean.setSuccessUrl("user/index");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauth");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * shiro 用户数据注入
	 * @return
	 */
	@Bean
	public MyRealm shiroRealm(){
		MyRealm shiroRealm = new MyRealm();
		return shiroRealm;
	}

	/**
	 * 配置管理层。即安全控制层
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return  securityManager;
	}
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	/**
	 * 开启shiro aop注解支持 使用代理方式所以需要开启代码支持
	 *  一定要写入上面advisorAutoProxyCreator（）自动代理。不然AOP注解不会生效
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

//
//	@Value("${spring.redis.shiro.host}")
//	private String host;
//	@Value("${spring.redis.shiro.port}")
//	private int port;
//	@Value("${spring.redis.shiro.timeout}")
//	private int timeout;
//	@Value("${spring.redis.shiro.password}")
//	private String password;
//
//
//	/**
//	 * 权限规则配置
//	 **/
//	@Bean
//	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//		filters.put("authc", new MyFormAuthorizationFilter());
//
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//
//		//swagger资源不拦截
//		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//		filterChainDefinitionMap.put("/swagger-resources/**/**", "anon");
//		filterChainDefinitionMap.put("/v2/api-docs", "anon");
//		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
//		filterChainDefinitionMap.put("/configuration/security", "anon");
//		filterChainDefinitionMap.put("/configuration/ui", "anon");
//
//		filterChainDefinitionMap.put("/login/login", "anon");
//		filterChainDefinitionMap.put("/login/unauth", "anon");
//		filterChainDefinitionMap.put("/login/logout", "anon");
//		filterChainDefinitionMap.put("/login/register","anon");
//		filterChainDefinitionMap.put("/**", "authc");
//
//		shiroFilterFactoryBean.setLoginUrl("/login/unauth");
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		return shiroFilterFactoryBean;
//	}
//
//
//	/**
//	 * shiro安全管理器（权限验证核心配置）
//	 **/
//	@Bean
//	public DefaultWebSecurityManager securityManager() {
//		//刚才改的返回值类型SecurityManager请注意!!!!
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setRealm(myRealm());
//		securityManager.setSessionManager(sessionManager());
//		securityManager.setCacheManager(cacheManager());
//
//		return securityManager;
//	}
//
//	/**
//	 * 会话管理
//	 **/
//	@Bean
//	public SessionManager sessionManager() {
//		MySessionManager sessionManager = new MySessionManager();
//		sessionManager.setSessionIdUrlRewritingEnabled(false); //取消登陆跳转URL后面的jsessionid参数
//		sessionManager.setSessionDAO(sessionDAO());
//		sessionManager.setGlobalSessionTimeout(-1);//不过期
//		return sessionManager;
//	}
//
//	/**
//	 * 使用的是shiro-redis开源插件 缓存依赖
//	 **/
//	@Bean
//	public RedisManager redisManager() {
//		RedisManager redisManager = new RedisManager();
//		redisManager.setHost(host+":"+port);
//		redisManager.setTimeout(timeout);
//		redisManager.setPassword(password);
//		return redisManager;
//	}
//
//	/**
//	 * 使用的是shiro-redis开源插件 session持久化
//	 **/
//	public RedisSessionDAO sessionDAO() {
//		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//		redisSessionDAO.setRedisManager(redisManager());
//		return redisSessionDAO;
//	}
//
//
//	/**
//	 * 缓存管理
//	 **/
//	@Bean
//	public CacheManager cacheManager() {
//		RedisCacheManager redisCacheManager = new RedisCacheManager();
//		redisCacheManager.setRedisManager(redisManager());
//		return redisCacheManager;
//	}
//
//
//	/**
//	 * 权限管理
//	 **/
//	@Bean
//	public MyRealm myRealm() {
//
//		return new MyRealm();
//	}
}

