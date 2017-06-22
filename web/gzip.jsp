<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: ki264
  Date: 2017/6/21
  Time: 上午 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GZIP</title>
    <style>
        body {
            margin-top: 2px;
        }

        table {
            margin-top: 10px;
            border-collapse: collapse;
            border: 1px solid black;
            width: 350px;
        }

        td {
            border: 1px solid black;
            font-size: 12px;
            padding: 2px;
            background: #DDDDFF;
        }

    </style>
</head>
<body>
<%!
    public void test(JspWriter out, String url) throws Exception {
        URLConnection connGzip = new URL(url).openConnection();
        connGzip.setRequestProperty("Accept-Encoding", "gzip");
        int lengthGzip = connGzip.getContentLength();

        URLConnection connCommon = new URL(url).openConnection();
        int lengthCommon = connCommon.getContentLength();

        double rate = new Double(lengthGzip) / lengthCommon;

        out.println("<table>");
        out.println("   <tr>");
        out.println("       <td colspan=3>網址：" + url + "</td>");
        out.println("   </tr>");
        out.println("   <tr>");
        out.println("       <td>壓縮後：" + lengthGzip + "</td>");
        out.println("       <td>壓縮前：" + lengthCommon + "</td>");
        out.println("       <td>比率：" + NumberFormat.getPercentInstance().format(rate) + "</td>");
        out.println("   </tr>");
        out.println("</table>");

    }
%>
<%
    String[] urls = {
            "http://localhost:8080/CH06_Filter/dojo/dojo.js",
            "http://localhots:8080/CH06_Filter/image.jsp",
            "http://localhost:8080/CH06_Filter/winter.jpg",
            "http://www.yahoo.com.tw",
            "http://www.google.com.tw"};

    for (String url : urls) {
        test(out, url);
    }
%>

</body>
</html>
