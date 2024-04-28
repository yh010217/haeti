<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: seeyh
  Date: 2024-04-29
  Time: 오전 3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/my_prod_chat.css">

</head>
<body>
<c:set var="prod_buyer" value="${requestScope.prod_buyer}"/>

<div id="chat_container">
    <c:forEach items="${prod_buyer}" var="item">

        <%-- 디자인은 잘 모르겠는데,,, 일단 이 밑에가 가는 a 태그입니다 --%>
        <a href="chatting.do?prod_no=${item[0]}&buyer=${item[1]}&iam=seller">
            <table class="one_chat">
                <td><img src="img/chatting_icon.png" alt="채팅이미지" class="go_chat"></td>
                <td><span>${item[1]}</span></td>
            </table>
        </a>
    </c:forEach>
</div>

</body>
</html>
