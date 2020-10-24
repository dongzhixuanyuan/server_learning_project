<%--
  Created by IntelliJ IDEA.
  User: liudong
  Date: 2020/8/29
  Time: 11:28 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在Form表单中使用验证码</title>
    <script type="text/javascript">
        function changeImage(obj, createTypeFlag, imageId) {
            document.getElementById(obj.id).src = "${pageContext.request.contextPath}/verifycode?createTypeFlag=" + createTypeFlag + "&id=" + (obj.id) +"&"+Math.random()
        }

    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/formCheck" method="post">

    数字字母混合验证码:<input type="text" name="validateCode1"/>
    <img alt="验证码看不清，换一张" src="${pageContext.request.contextPath}/verifycode?createTypeFlag=nl&id=validateCode1"
         id="validateCode1"
         onclick="changeImage(this,'nl',validateCodeImg1)">
    <br/>


    中文验证码:<input type="text" name="validateCode2"/>
    <img alt="验证码看不清，换一张" src="${pageContext.request.contextPath}/verifycode?createTypeFlag=ch&id=validateCode2"
         id="validateCode2"
         onclick="changeImage(this,'ch',validateCodeImg2)">
    <br/>

    英文验证码:<input type="text" name="validateCode3"/>
    <img alt="验证码看不清，换一张" src="${pageContext.request.contextPath}/verifycode?createTypeFlag=l&id=validateCode3"
         id="validateCode3"
         onclick="changeImage(this,'l',validateCodeImg3)">
    <br/>

    数字验证码:<input type="text" name="validateCode4"/>
    <img alt="验证码看不清，换一张" src="${pageContext.request.contextPath}/verifycode?createTypeFlag=n&id=validateCode4"
         id="validateCode4"
         onclick="changeImage(this,'n',validateCodeImg4)">
    <br/>
    <input type="submit" value="提交">
</form>

</body>
</html>
