<%@ page import="java.lang.reflect.Member" %>
<%@ page import="com.haeti.dao.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-23
  Time: 오전 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인 성공 화면</title>
</head>
<body>
<%=session.getAttribute("result")%>
<%=session.getAttribute("user_id")%>님 환영합니다!
<%=session.getAttribute("email")%>이 로그인 되었습니다.

<c:set var="dto" value="${sessionScope.dto}"/>

<c:out value="${dto.fav_region}"/>




</body>
</html>
