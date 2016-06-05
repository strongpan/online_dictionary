<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>online dictionary</title>
</head>
<body>

<form action="/user" method="post">
    <input type="hidden" name="action" value="userquery">
    <label for="queryword">请输入要查询的单词 : </label><br>
    <input id="queryword" type="text" name="queryword" placeholder="word to query">
    <input type="submit" value="Query">
</form>

<div>
    <h1>${sessionScope.message}</h1>

    <h2>${sessionScope.queryresult.english}</h2>
    ${sessionScope.queryresult.chinese} <br>
    ${sessionScope.queryresult.phonetic} <br>
    ${sessionScope.queryresult.partOfSpeech} <br>
    ${sessionScope.queryresult.category}<br>

</div>


<hr>
<a href="admin/index.jsp">ADMIN LOGIN</a>
</body>
</html>
