<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin index</title>
</head>
<body>
<h1>ADMIN LOGIN</h1>
<form action="/admin" method="post">
    <input type="hidden" name="action" value="login">
    <label for="username">USERNAME</label>
    <input id="username" type="text" name="username" value="admin"><br>
    <label for="password">PASSWORD</label>
    <input id="password" type="password" name="password" value="123"><br>
    <input type="submit" value="LOGIN">
</form>
${requestScope.message}
</body>
</html>
