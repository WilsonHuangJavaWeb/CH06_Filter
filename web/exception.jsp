<%--
  Created by IntelliJ IDEA.
  User: WilsonHuang
  Date: 2017/6/18
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .error {
            font-size: 12px;
            padding: 5px;
            border: 1px solid red;
            background: url("images/error.gif") 8px 5px no-repeat #ffeeff;
            padding-left: 30px;
        }

    </style>
</head>
<body>
<div class="error">
    ${message}<a href="javascript:history.go(-1);">返回</a>
</div>

</body>
</html>
