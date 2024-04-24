<%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-23
  Time: 오전 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<c:set var="sessionId" value="${sessionScope.user_id}"></c:set>


<script>
    if(${empty sessionId || sessionId==''}){
        alert('아이디 또는 패스워드가 잘못 입력했습니다');
        location.href="login.do";
    }else{
        location.href="loginsuccess.do";
    }



</script>

</body>
</html>
