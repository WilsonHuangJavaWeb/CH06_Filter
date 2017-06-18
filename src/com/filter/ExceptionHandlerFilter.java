package com.filter;

import com.exception.AccountException;
import com.exception.BusinessException;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Created by WilsonHuang on 2017/6/18.
 */
@WebFilter(filterName = "ExceptionHandlerFilter")
public class ExceptionHandlerFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(req, resp);//doFilter，執行下一個Servlet或Filter

        } catch (Exception e) {//捕捉例外

            Throwable rootCause = e;//根例外

            while (rootCause.getCause() != null) {//迴圈，直到尋找到根例外為止
                rootCause = rootCause.getCause();
            }
            String message = rootCause.getMessage();//例外原因
            message = message == null ? "例外：" + rootCause.getClass().getName() : message;

            req.setAttribute("message", message);//在request中傳遞例外原因
            req.setAttribute("e", e);//在request中傳遞例外

            if (rootCause instanceof AccountException) {//如果是AccountException，則轉到accountException.jsp
                req.getRequestDispatcher("/accountException.jsp").forward(req, resp);
            } else if (rootCause instanceof BusinessException) {//如果是BusinessException，則轉到businessException.jsp
                req.getRequestDispatcher("/businessException.jsp").forward(req, resp);
            } else {//如果是其他例外， 則轉到exception.jsp
                req.getRequestDispatcher("/exception.jsp").forward(req, resp);
            }

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
