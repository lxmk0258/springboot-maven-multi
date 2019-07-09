<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录页面</title>
    <style>
        .center {
            margin: auto;
            width: 15%;
            border: 3px solid #73AD21;
            padding: 5px;
            text-align: center;
        }

    </style>
</head>
<body>
<div>
    <div class="center">
        <form action="/login">
            <input type="text" name="username" value="${username}" placeholder="用户名"> <br>
            <input type="password" name="password" value="" placeholder="密码"><br>
            <input type="submit" name="" value="登录">
        </form>
        <c:if test="${status==0}">
            <div class="alert alert-warning"  role="alert"><span style="color:red">账号或密码错误</span></div>
        </c:if>
    </div>

</div>
</body>
</html>