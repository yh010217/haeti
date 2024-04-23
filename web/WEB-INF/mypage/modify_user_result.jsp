<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="result" value="${requestScope.modify_result}"/>
<c:choose>
    <c:when test="${result>0}">
        <script>
            alert("수정 완료");
            location.href="mypage.do";
        </script>
    </c:when>
    <c:otherwise>
        <script>
            alert("수정 실패");
            location.href="modify_user.do";
        </script>
    </c:otherwise>
</c:choose>
</body>
</html>
