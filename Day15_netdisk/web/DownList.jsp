<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZZW
  Date: 2017/3/25
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${requestScope.list}" var="r">
    <h1>文件名${r.realname}</h1><br>
    <h3>上传时间${r.uploadtime}<br>
        上传IP${r.ip}<br>
        文件描述${r.description}<br>
        <a href="${pageContext.request.contextPath}/DownServlet?id=${r.id}">下载</a>
    </h3>
    <br>
    <hr>
</c:forEach>
</body>
</html>
