<%@ page import="com.liudong.formduplicate.TokenProccessor" %><%--
  Created by IntelliJ IDEA.
  User: liudong
  Date: 2020/8/30
  Time: 7:46 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模拟表单重复提交</title>
    <script type="text/javascript">
        var isCommited = false

        function doSubmit() {
           var commitBtn =  document.getElementById("submit")
            commitBtn.disabled = "disabled"
            return true
            // if (!isCommited) {
            //     isCommited = true
            //     return true
            // }
            // return false
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/verifyformsubmit"  method="post">
    <input type="hidden" name="<%=TokenProccessor.Companion.getKEY_OF_TOKEN()%>" value="<%=session.getAttribute(TokenProccessor.Companion.getKEY_OF_TOKEN())%>">
    用户名:<input type="text" name="username">
    <input type="submit" value="提交">
</form>
</body>
</html>
