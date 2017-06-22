package com.filter;

import com.response.HttpCharacterResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by WilsonHuang on 2017/6/19.
 */
@WebFilter(filterName = "OutputReplaceFilter")
public class OutputReplaceFilter implements Filter {

    private Log log = LogFactory.getLog(this.getClass());

    private Properties properties = new Properties();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpCharacterResponseWrapper responseWrapper = new HttpCharacterResponseWrapper((HttpServletResponse) resp);

        chain.doFilter(req, responseWrapper);

        String output = responseWrapper.getCharArrayWriter().toString();

        for (Object obj : properties.keySet()) {
            String key = (String) obj;
            output = output.replace(key, properties.getProperty(key));
        }

        PrintWriter out = resp.getWriter();
        out.write(output);
        out.println("<!--建立時間：" + new Date() + "-->");
    }

    public void init(FilterConfig config) throws ServletException {
        String file = config.getInitParameter("file");
        String realPath = config.getServletContext().getRealPath(file);
        try {
            properties.load(new FileInputStream(realPath));
        } catch (Exception e) {
            log.info("properties讀取失敗");
        }
    }

}
