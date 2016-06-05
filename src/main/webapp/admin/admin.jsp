<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin</title>
</head>
<body><c:if test="${sessionScope.username eq null}"><c:redirect url="/admin/index.jsp"/></c:if>
<h1>admin</h1>
${sessionScope.username}
<hr>
<form action="/word" method="post">
    <input type="hidden" name="action" value="create">
    <label for="english">ENGLISH</label>
    <input id="english" type="text" name="english"> <br>
    <label for="chinese">CHINESE</label>
    <input id="chinese" type="text" name="chinese"><br>
    <label for="phonetic">PHONETIC</label>
    <input id="phonetic" type="text" name="phonetic"><br>
    <label for="partOfSpeech">PART OF SPEECH</label>
    <select id="partOfSpeech" name="partOfSpeech">
        <option value="n.">n.</option>
        <option value="adj.">adj.</option>
        <option value="adv.">adv.</option>
    </select>
    <br>
    <label for="category">CATEGORY</label>
    <input id="category" type="text" name="category"><br>
    <input type="submit" value="CREATE">
</form>
<hr>
<table border="1">
    <tr>
        <th>index</th>
        <th>ENGLISH</th>
        <th>CHINESE</th>
        <th>PHONETIC</th>
        <th>PART OF SPEECH</th>
        <th>CATEGORY</th>
        <th colspan="2">OPERATION</th>
    </tr>
    <c:forEach var="word" items="${sessionScope.words}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${word.english}</td>
            <td>${word.chinese}</td>
            <td>${word.phonetic}</td>
            <td>${word.partOfSpeech}</td>
            <td>${word.category}</td>
            <td><a href="/word?action=search&id=${word.id}">EDIT</a></td>
            <td><a href="/word?action=remove&id=${word.id}" onclick="return confirm('DEL')">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>

<hr>
<a href="../index.jsp">GO BACK USER PAGE</a>

</body>
</html>
