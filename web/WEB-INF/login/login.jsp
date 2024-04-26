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
    <title>loginform</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<jsp:include page="/header.jsp"/>


<main class="form-singin">
    <form class="writeForm" method="post" action="login_result.do">
        <h1>로그인</h1>
        <div>
            <input type="text"  name="user_id" placeholder="아이디" class="input_btn" required>
        </div>
        <div>
            <input type="password"  name="pwd" placeholder="비밀번호" class="input_btn" required>
        </div>
        <div>
            <input class="login_btn" type="submit" value="로그인">
        </div>
        <ul>
            <li>처음이신가요?<a href="join.do" class="join_btn">회원가입</a></li>
        </ul>


    </form>
</main>
<jsp:include page="/footer.jsp"/>

</body>
</html>
