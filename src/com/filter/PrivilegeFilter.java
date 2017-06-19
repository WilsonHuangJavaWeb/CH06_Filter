package com.filter;

import com.exception.AccountException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ki264 on 2017/6/19.
 */
@WebFilter(filterName = "PrivilegeFilter")
public class PrivilegeFilter implements Filter {

    private Properties properties = new Properties();//儲存所有的許可權

    public void destroy() {
        properties = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/", "");

        String action = req.getParameter("action");
        action = action == null ? "" : action;

        String uri = requestURI + "?action=" + action;


        String role = (String) request.getSession(true).getAttribute("role");
        role = role == null ? "guest" : role;

        boolean authentificated = false;
        for (Object o : properties.keySet()) {
            String key = ((String) o);

            if (uri.matches(key.replace("?", "\\?").replace(".", "\\.").replace("*", ".*"))) {

                if (role.equals(properties.get(key))) {
                    authentificated = true;
                    break;
                }
            }
        }
        if (!authentificated) {
            throw new RuntimeException(new AccountException("您無權存取該頁面。請以適合的身分登入後檢視。"));
        }

        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

        //從初始化參數中獲得權限設定檔的位置
        String file = config.getInitParameter("file");
        String realPath = config.getServletContext().getRealPath(file);

        try {
            properties.load(new FileInputStream(realPath));
        } catch (Exception e) {
            config.getServletContext().log("讀取權限控制檔失敗。", e);
        }
    }

}
