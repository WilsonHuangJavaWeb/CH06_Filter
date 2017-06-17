package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by WilsonHuang on 2017/6/17.
 * <p>
 * 字元編碼Filter實作
 */
public class CharacterEncodingFilter implements Filter {

    private String characterEncoding;//編碼方式，設定在web.xml中
    private boolean enabled;//是否啟用該Filter，設定在web.xml中


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化時載入參數
        //取得編碼方式
        characterEncoding = filterConfig.getInitParameter("characterEncoding");
        //取得是否啟用
        enabled = "true".equalsIgnoreCase(filterConfig.getInitParameter("enabled").trim());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (enabled || characterEncoding != null) {//如果啟用該Filter
            servletRequest.setCharacterEncoding(characterEncoding);//設定request編碼方式
            servletResponse.setCharacterEncoding(characterEncoding);//設定response編碼方式
        }
        filterChain.doFilter(servletRequest, servletResponse);//doFilter，執行下一個Filter或Servlet
    }

    @Override
    public void destroy() {
        characterEncoding = null;//銷毀時清空資源
    }
}
