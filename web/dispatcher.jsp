<%--
  Created by IntelliJ IDEA.
  User: ki264
  Date: 2017/6/19
  Time: 下午 01:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageContext.request.requestURI}</title>
</head>
<body>
您正在訪問"${pageContext.request.requestURI}?${pageContext.request.queryString}"
</body>
</html>
