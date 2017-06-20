package com.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ki264 on 2017/6/20.
 */
@WebFilter(filterName = "GZipFilter")
public class GZipFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String acceptEncoding = request.getHeader("Accept-Encoding");
        System.out.println("Accept-Encoding：" + acceptEncoding);

        if (acceptEncoding != null && acceptEncoding.toLowerCase().indexOf("gzip") != -1) {

            GZipResponseWrapper gZipResponseWrapper = new GZipResponseWrapper(response);
            chain.doFilter(request, gZipResponseWrapper);

            gZipResponseWrapper.finishResponse();
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
