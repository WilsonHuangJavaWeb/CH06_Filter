<%--
  Created by IntelliJ IDEA.
  User: WilsonHuang
  Date: 2017/6/18
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .error {
        font-size: 12px;
        padding: 5px;
        border: 1px solid red;
        background: url("images/error.gif") 8px 5px no-repeat #ffeeff;
        padding-left: 30px;
    }
</style>
<div class="error">
    ${message}<a href="javascript:history.go(-1);">傳回</a>
</div>
