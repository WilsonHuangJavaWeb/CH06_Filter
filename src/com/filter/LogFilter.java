package com.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by WilsonHuang on 2017/6/17.
 */
public class LogFilter implements javax.servlet.Filter {
    private Log log = LogFactory.getLog(this.getClass());// Log 物件
    private String filterName;//目前 Filter 名稱

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterName = filterConfig.getFilterName();//獲得Filter名稱
        log.info("啟動 Filter : " + filterName);//啟動時紀錄記錄檔
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        long startTime = System.currentTimeMillis();
        String requestURI = request.getRequestURI();

        requestURI = request.getQueryString() == null ? requestURI : (requestURI + "?" + request.getQueryString());
        filterChain.doFilter(request, response);

        long endTime = System.currentTimeMillis();
        log.info(request.getRemoteAddr() + " 存取了 " + requestURI + ",總用時 " + (endTime - startTime) + " 毫秒");
    }

    @Override
    public void destroy() {
        log.info(" 關閉 Filter: " + filterName);

    }
}
