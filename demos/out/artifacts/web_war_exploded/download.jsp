<%--
  Created by IntelliJ IDEA.
  User: liudong
  Date: 2020/10/24
  Time: 9:50 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Download Page</title>
</head>
<body>

<form action="download" method="post" enctype="multipart/form-data">

    <input type="file" name="filename">
    <input type="submit" name="提交">

</form>
</body>
</html>
