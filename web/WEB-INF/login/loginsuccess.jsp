<%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-24
  Time: 오전 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>loginsuccess</title>
</head>
<body>
<%=session.getAttribute("user_id")%>님 환영합니다! <br>

<div class="logout_btn">
    <form method="post" action="logout.do">
        <input type="submit" value="로그아웃" class="btn">
    </form>
</div>
</body>
</html>
