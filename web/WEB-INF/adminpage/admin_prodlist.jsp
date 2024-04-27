<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-24
  Time: 오후 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/admin/admin_prod.css">
    <link rel="stylesheet" href="css/prod/index.css">
</head>
<body>

<ul class="menu_prod">
        <li class="font_mid">관리자 페이지</li>
        <li><a href="adminuserlist.do">회원관리</a></li>
        <li><a href="adminprodlist.do">상품관리</a></li>
        <li><a href="#">카테고리 관리</a></li>
        <li><a href="#">고객센터</a></li>
    </ul>

<%--구분선 추가--%>
<div class="line"></div>

<form class ="user_search" method="post" action="adminprodlist.do">
    <select name="search">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="nick_name">닉네임</option>
        <option value="category">카테고리</option>

    </select>
    <input type="text" name="search_txt">
    <input type="submit" value="검색">
</form>


<div class="line"></div>




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
                <li><a href="prod_modify.do?prod_no=${item.prod_no}">
                    <img src="upload/${item.prod_no}/${item.img_paths[0]}"></a></li>
                <li class="title">${item.title}</li>
                <li class="cost">${item.cost}원</li>
                <li class="write_date">${item.write_date}</li>
            </ul>
        </c:forEach>
    </c:if>
</div>

<%--페이지 번호--%>
<div>
<c:if test="${start_page>1}">
    <a href="adminprodlist.do?curr=${start_page-1}&search=${search}&search_txt=${search_txt}">이전</a>
</c:if>

<c:forEach var="i" begin="${start_page}" end="${end_page}" step="1">
    <c:choose>
        <c:when test="${i==currpage}">
            <c:out value="${i}"/>
        </c:when>
        <c:otherwise>
            <a href="adminprodlist.do?curr=${i}&search=${search}&search_txt=${search_txt}"><c:out value="${i}"/></a>
        </c:otherwise>
    </c:choose>
</c:forEach>

<c:if test="${end_page < total_page}">
    <a href="adminprodlist.do?curr=${end_page+1}&search=${search}&search_txt=${search_txt}">다음</a>
</c:if>

</div>
</body>
</html>
