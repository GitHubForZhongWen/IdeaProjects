<%--
  Created by IntelliJ IDEA.
  User: ZZW
  Date: 2017/3/25
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>我的网盘</h1>
<hr>
<a href="${pageContext.request.contextPath}/upload.jsp">上传文件</a>
<a href="${pageContext.request.contextPath}/DownListServlet">下载文件</a>
</body>
</html>
