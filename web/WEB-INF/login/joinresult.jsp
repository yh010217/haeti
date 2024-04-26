<%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-26
  Time: 오전 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>join</title>
</head>
<body>
<c:set var="result" value="${requestScope.join_result}"/>
<c:choose>
    <c:when test="${result>0}">
        <script>
            alert("회원가입 완료!!")
            location.href="login.do"
        </script>
    </c:when>
    <c:otherwise>
        <script>
            alert("회원 가입 실패")
            location.href="join.do"
        </script>
    </c:otherwise>
</c:choose>
</body>
</html>
