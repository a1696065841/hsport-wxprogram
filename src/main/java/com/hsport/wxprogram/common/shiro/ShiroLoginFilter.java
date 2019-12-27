package com.hsport.wxprogram.common.shiro;

import com.alibaba.fastjson.JSONObject;
import com.hsport.wxprogram.common.util.AjaxResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShiroLoginFilter extends FormAuthenticationFilter {

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (isAjax(request)) {
            System.out.println(1111111);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            AjaxResult resultData = new AjaxResult();
            resultData.setSuccess(false);
            httpServletResponse.setContentType("utf-8");
            resultData.setMessage("鸡毛嘟嘟嘟嘟嘟嘟嘟嘟独孤!");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(resultData));
        } else {
            //saveRequestAndRedirectToLogin(request, response);
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            System.out.println(222222);
            AjaxResult resultData = new AjaxResult();
            resultData.setSuccess(false);
            resultData.setMessage("鸡毛嘟嘟嘟嘟嘟嘟嘟嘟独孤!");
            httpServletResponse.setContentType("utf-8");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(resultData));
            httpServletResponse.setStatus(1);
        }
        return false;
    }

    private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
