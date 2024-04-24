<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-23
  Time: 오전 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>해티</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<c:set var="list" value="${requestScope.list}"></c:set>

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


<%--페이지번호--%>
<div>
<c:if test="${start_page>1}">
    <a href="index.do?curr=${start_page-1}&search=${search}&search_txt=${search_txt}">이전</a>
</c:if>

<c:forEach var="i" begin="${start_page}" end="${end_page}" step="1">
    <c:choose>
        <c:when test="${i==currpage}">
            <c:out value="${i}"/>
        </c:when>
        <c:otherwise>
            <a href="index.do?curr=${i}&search=${search}&search_txt=${search_txt}"><c:out value="${i}"/></a>
        </c:otherwise>
    </c:choose>
</c:forEach>

<c:if test="${end_page < total_page}">
    <a href="index.do?curr=${end_page+1}&search=${search}&search_txt=${search_txt}">다음</a>
</c:if>
</div>

</body>
</html>
