package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by WilsonHuang on 2017/6/16.
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("request 被處理之前...");
        filterChain.doFilter(servletRequest, servletResponse);//一定要執行這句，否則不會執行 Servlet 程式碼
        System.out.println("request 被處理之後, response 抵達用戶端瀏覽器之前...");

    }

    @Override
    public void destroy() {

    }
}
