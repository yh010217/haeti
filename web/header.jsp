<%--
  Created by IntelliJ IDEA.
  User: bk
  Date: 2024/04/22
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
<c:set var= "user_id" value="${sessionScope.user_id}"/>
<header>
    <div class="top">
        <a class="logo" href="index.do"><img src="img/logo.png" alt="logo"></a>
        <form method="post" action="index.do?search=title">
            <input type="text" class="search_txt" name="search_txt" id="search_txt"
                   placeholder="  찾고 싶은 매물을 이름으로 검색하세요">
            <button type="submit" class="img-search"></button>
        </form>
      
         <c:choose>
             <c:when test="${empty user_id || user_id==null || user_id==''}">
                 <div class="login">
                     <a href="login.do">로그인 </a>
                     <a href="join.do">회원가입</a>
                 </div>
             </c:when>
             <c:when test="${'admin'.equals(user_id)}">
             <div class="login">
                 <a href="logout.do">로그아웃</a>
             </div>
             <div class="adminpage">
                 <a href="admin.do">관리자페이지</a>
             </div>
         </c:when>
             <c:otherwise>
                 <div class="login">
                     <a href="logout.do">로그아웃</a>
                 </div>
                 <div class="mypage">
                     <a href="mypage.do">마이페이지</a>
                 </div>
             </c:otherwise>
         </c:choose>

        <div class="hambuger-menu">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
        </div>

    </div>


    <nav>
        <ul class="navbar_menu">
            <li><a href="index.do">전체매물</a></li>
            <li><a href="proddistance.do">거리별</a></li>
            <li><a href="categorylist.do">출판사별</a></li>
            <li><a href="favregion.do">관심지역 매물</a></li>
            <li><a href="prodmap.do">매물지도</a></li>
        </ul>
    </nav>

</header>

</body>
</html>
