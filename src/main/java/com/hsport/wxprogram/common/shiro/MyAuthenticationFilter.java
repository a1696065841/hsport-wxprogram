package com.hsport.wxprogram.common.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果是OPTIONS请求，直接放行
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String method = httpServletRequest.getMethod();
        System.out.println(method);
        if("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
