<%--
  Created by IntelliJ IDEA.
  User: WilsonHuang
  Date: 2017/6/16
  Time: 04:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>防盜連</title>
    <meta http-equiv="content-type" content="text/css;charset=utf-8">
</head>
<body>
<img width="200" src="images/winter.jpg">
<a href="${pageContext.request.requestURI}">刷新</a>S
<a href="javascript:window.open('images/winter.jpg')"
   onclick="window.open('images/winter.jpg');return false;">直接訪問</a>
<hr/>
request.getHeader("referer"):${header['referer']}
</body>
</html>
