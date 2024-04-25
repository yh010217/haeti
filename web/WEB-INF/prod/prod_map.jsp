<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-25
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="list" value="${requestScope.list}"></c:set>
<c:set var="LatLng" value="${requestScope.LatLng}"></c:set>

<c:if test="${empty list || fn.length(list)==0}">
    <ul>
        <li>해당 자료가 없습니다.</li>
    </ul>
</c:if>
<div class="prod_list">
    <c:if test="${!(empty list)}">
        <c:forEach var="item" items="${list}">
            <ul>
                <li><a href="prod_detail.do?prod_no=${item.prod_no}">
                    <img src="upload/${item.prod_no}/${item.img_paths[0]}"></a></li>
                <li class="title">${item.title}</li>
                <li class="cost">${item.cost}원</li>
                <li class="write_date">${item.write_date}</li>
            </ul>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
