package com.filter;

import com.exception.AccountException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private Log log = LogFactory.getLog(this.getClass());
    private Properties properties = new Properties();//儲存所有的許可權

    public void destroy() {
        properties = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;//將ServletRequest轉為HttpServletRequest

        //存取的路徑，例如：admin.jsp
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/", "");
        log.info("requestURI = " + requestURI);

        //獲得action參數，例如：add
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        log.info("action = " + action);

        //拼接成URI，例如：log.do?action=list
        String uri = requestURI + "?action=" + action;
        log.info("uri = " + uri);

        //從session中獲得使用者權限角色
        String role = (String) request.getSession(true).getAttribute("role");
        role = role == null ? "guest" : role;
        log.info("role = " + role);

        boolean authentificated = false;

        //開始檢查使用者角色是否有權限存取URI
        for (Object o : properties.keySet()) {
            String key = ((String) o);

            //使用正則表達式驗證需要將？. 替換一下，並將通配符 * 處理一下
            if (uri.matches(
                    key.replace("?", "\\?")
                            .replace(".", "\\.")
                            .replace("*", ".*"))) {

                if (role.equals(properties.get(key))) {
                    authentificated = true;
                    break;
                }
            }
        }
        if (!authentificated) {
            throw new RuntimeException(new AccountException("您無權存取該頁面。請以適合的身分登入後檢視。"));
        }

        //繼續執行下一個Filter或Servlet
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

        //從初始化參數中獲得權限設定檔的位置
        String file = config.getInitParameter("file");
        String realPath = config.getServletContext().getRealPath(file);

        try {
            properties.load(new FileInputStream(realPath));
            log.info("properties檔讀取成功");
        } catch (Exception e) {
            config.getServletContext().log("讀取權限控制檔失敗。", e);
        }
    }

}
