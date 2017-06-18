<%--
  Created by IntelliJ IDEA.
  User: WilsonHuang
  Date: 2017/6/18
  Time: 23:30
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
    ${message}
</div>
<form action="">
    <table>
        <tr>
            <td>帳號</td>
            <td><input type="text" name="account"></td>
        </tr>
        <tr>
            <td>密碼</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="登入"></td>
        </tr>

    </table>
</form>
