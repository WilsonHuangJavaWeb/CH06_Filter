package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by WilsonHuang on 2017/6/16.
 */
public class ImageRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化不做任何事情
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;//request物件
        HttpServletResponse response = (HttpServletResponse) servletResponse;// response物件

        String referer = request.getHeader("referer");//連結來源位址

        if (referer == null || !referer.contains(request.getServerName())) {//若來自其他網站
            request.getRequestDispatcher("/images/error.gif").forward(request, response);//顯示錯誤圖片
        } else {
            filterChain.doFilter(request, response);//圖片正常顯示
        }
    }

    @Override
    public void destroy() {
        //銷毀時不做任何事情
    }
}
