<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-23
  Time: 오후 7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="css/admin_user.css">
</head>
<body>

<ul class="menu_user">
  <li class="font_mid">관리자 페이지</li>
  <li><a href="adminuserlist.do">회원관리</a></li>
  <li><a href="adminprodlist.do">상품관리</a></li>
  <li><a href="#">카테고리 관리</a></li>
  <li><a href="#">고객센터</a></li>
</ul>

<%--구분선 추가--%>
<div class="line"></div>

<form class ="user_search" method="post" action="adminuserlist.do">
  <select name="search">
    <option value="user_id">아이디</option>
    <option value="name">이름</option>
    <option value="tel">전화번호</option>
  </select>
  <input type="text" name="search_txt">
  <input type="submit" value="검색">
</form>


<div class="line"></div>


<p class = "mem">회원수 : ${total_data}</p>


<table class="user_table">
  <thead>
  <tr>
    <th>번호</th><th>아이디</th><th>이름</th><th>전화번호</th><%--<th>가입일</th>--%>
  </tr>
  </thead>
  <tbody>
  <c:if test="${(empty list)}">
    <tr>
      <td>해당 자료가 없습니다.</td>
    </tr>
  </c:if>

  <c:if test="${!(empty list)}">
    <c:forEach var="item" items="${list}">
      <tr>
        <td>
            ${item.user_no}
        </td>
        <td>
          <a href="modify_user.do">${item.user_id}</a>
        </td>
        <td>
          <a href="modify_user.do">${item.name}</a>
        </td>
        <td>
            ${item.tel}
        </td>
        <%--<td>
            ${item.join_date}
        </td>--%>
      </tr>

    </c:forEach>
  </c:if>
  </tbody>
</table>


<%--페이지 번호--%>
<div>
<c:if test="${start_page>1}">
  <a href="adminuserlist.do?curr=${start_page-1}&search=${search}&search_txt=${search_txt}">이전</a>
</c:if>

<c:forEach var="i" begin="${start_page}" end="${end_page}" step="1">
  <c:choose>
    <c:when test="${i==currpage}">
      <c:out value="${i}"/>
    </c:when>
    <c:otherwise>
      <a href="adminuserlist.do?curr=${i}&search=${search}&search_txt=${search_txt}"><c:out value="${i}"/></a>
    </c:otherwise>
  </c:choose>
</c:forEach>

<c:if test="${end_page < total_page}">
  <a href="adminuserlist.do?curr=${end_page+1}&search=${search}&search_txt=${search_txt}">다음</a>
</c:if>
</div>

</body>
</html>
