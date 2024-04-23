<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.haeti.dao.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-22
  Time: 오전 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/login.css">
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인폼</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<form name="writeForm" method="post" action="login_result.do">
    <ul>
        <li>
            <label for="user_id">아이디</label>
            <input type="text" id="user_id" name="user_id" required>
        </li>
        <li>
            <label for="pwd">패스워드</label>
            <input type="password" id="pwd" name="pwd" required>
        </li>
        <li>
            <input type="submit" value="로그인">
        </li>
        <li>처음이신가요?<a href="join.do">회원가입</a></li>
    </ul>

</form>

</body>
</html>
