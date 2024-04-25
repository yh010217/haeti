<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FULL8-005
  Date: 2024-04-22
  Time: 오후 5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="/header.jsp"/>


<c:set var="dto" value="${requestScope.dto}"/>
<c:set var="path" value="${requestScope.path}\\"/>
<c:set var="images" value="${dto.img_paths}"/>

<ul>
    <c:forEach var="image" items="${images}">
        <li>이미지 : <img src="upload/${dto.prod_no}/${image}"></li>
    </c:forEach>
</ul>
<c:out value="제목 : ${dto.title}"/><br>
<c:out value="작성 날짜 : ${dto.write_date}"/><br>
<c:out value="가격 : ${dto.cost}"/><br>
<c:out value="출판사 : ${dto.category}"/><br>
<c:out value="본문 : ${dto.content}"/><br>


<button type="button" id="rep_show_button">댓글 확인</button>
<ul id="review_list">

</ul>

<a href="chatting.do?prod_no=${dto.prod_no}&buyer=${sessionScope.user_id}&iam=buyer">채팅</a><br>
<%-- &buyer=${sessionScope.user_no} 를 썼었는데, 그냥 chatting.jsp 에서 세션으로 받을 수 있을듯--%>
<a href="prod_modify.do?prod_no=${dto.prod_no}">수정</a><br>
<a href="prod_delete.do?prod_no=${dto.prod_no}">삭제</a><br>

<script src="js/review_show.js"></script>
<script>init_data(${dto.prod_no})</script>

<jsp:include page="/footer.jsp"/>

</body>
</html>
