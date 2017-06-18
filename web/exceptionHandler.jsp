<%@ page import="com.exception.BusinessException" %>
<%@ page import="com.exception.AccountException" %><%--
  Created by IntelliJ IDEA.
  User: WilsonHuang
  Date: 2017/6/18
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String action = request.getParameter("action");

    if ("businessException".equals(action)) {
        throw new BusinessException("業務操作失敗");
    } else if ("accountException".equals(action)) {
        throw new AccountException("需要登入後再進行此操作。");
    } else if ("exception".equals(action)) {
        Integer.parseInt("");
    }
%>
<html>
<head>
    <title>exceptionHandler</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<a href="${pageContext.request.requestURI}?action=businessException">test BusinessException</a>
<br/>
<a href="${pageContext.request.requestURI}?action=accountException">test AccountException</a>
<br/>
<a href="${pageContext.request.requestURI}?action=exception">test Exception</a>
<br/>
</body>
</html>
