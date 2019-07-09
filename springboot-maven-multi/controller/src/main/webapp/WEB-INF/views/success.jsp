<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录成功</title>
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
<div class="center">
    <h1>${name}登录成功</h1>
    <span class="btn btn-default"> <a href="/hello" role="button"> [退出]</a></span>
</div>
</body>

</html>