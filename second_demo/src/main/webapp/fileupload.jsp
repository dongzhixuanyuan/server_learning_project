<%--
  Created by IntelliJ IDEA.
  User: liudong
  Date: 2020/10/24
  Time: 2:28 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>

<form action="fileupload" method="post" enctype="multipart/form-data">
    <input type="file" name="img">
    <input type="submit" name="提交">
</form>
</body>
</html>
