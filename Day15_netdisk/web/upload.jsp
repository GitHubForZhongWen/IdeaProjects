<%--
  Created by IntelliJ IDEA.
  User: ZZW
  Date: 2017/3/25
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
    选择文件<input type="file" name="file1"/><hr>
    描述信息<textarea rows="5" cols="45" name="description"></textarea><hr>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
