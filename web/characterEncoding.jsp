<%--
  Created by IntelliJ IDEA.
  User: WilsonHuang
  Date: 2017/6/17
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>字元編碼Filter</title>
    <style>
        body, input, textarea {
            font-size: 12px;
        }

        textarea {
            width: 400px;
            height: 50px;
        }
    </style>
</head>
<body>
<pre>
        <b>您輸入了</b>:${param.text}
    </pre>
<form action="${param.request.requestURL}" method="post">
    <textarea name="text">${param.text}</textarea>
    <br/><input type="submit">
</form>
</body>
</html>
