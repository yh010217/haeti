<%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-23
  Time: 오전 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인알림페이지</title>
</head>
<body>
<%=session.getAttribute("result")%>

<script>
    if(${sessionScope.result==1}){
        location.href="loginmain.jsp";
    }
    else if (${sessionScope.result==0}){
        alert('다시 한번 입력해주세요!')

    }

</script>

</body>
</html>
