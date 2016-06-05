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
<hr>${sessionScope.word.partOfSpeech}
<form action="/word" method="post">
    <input type="hidden" name="action" value="modify">
    <input type="hidden" name="id" value="${sessionScope.word.id}">
    <label for="english">ENGLISH</label>
    <input id="english" type="text" name="english" value="${sessionScope.word.english}"> <br>
    <label for="chinese">CHINESE</label>
    <input id="chinese" type="text" name="chinese" value="${sessionScope.word.chinese}"><br>
    <label for="phonetic">PHONETIC</label>
    <input id="phonetic" type="text" name="phonetic" value="${sessionScope.word.phonetic}"><br>
    <label for="partOfSpeech">PART OF SPEECH</label>
    <select id="partOfSpeech" name="partOfSpeech">
        <option value="n." ${sessionScope.word.partOfSpeech eq 'n.'?'selected':''}>n.</option>
        <option value="adj." ${sessionScope.word.partOfSpeech eq 'adj.'?'selected':''}>adj.</option>
        <option value="adv." ${sessionScope.word.partOfSpeech eq 'adv.'?'selected':''}>adv.</option>
    </select>
    <br>
    <label for="category">CATEGORY</label>
    <input id="category" type="text" name="category" value="${sessionScope.word.category}"><br>
    <input type="submit" value="MODIFY">
</form>
</body>
</html>
