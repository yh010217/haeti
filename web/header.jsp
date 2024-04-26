<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bk
  Date: 2024/04/22
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/header.css">
</head>
<body>

<header>
    <div class="top">
        <a class="logo" href="index.do"><img src="img/logo.png" alt="logo"></a>
        <form method="post" action="/mainserch.do">
            <input type="text" class="search_txt" name="search_txt" id="search_txt"
                   placeholder="  찾고 싶은 매물을 이름으로 검색하세요">
            <input type="button" class="img-search">
        </form>

        <c:if test="${empty sessionScope.user_id}">
            <div class="login">
                <a href="login.do">로그인 / 회원가입</a></li>
            </div>
        </c:if>
        <c:if test="${!empty sessionScope.user_id}">
            <div class="logout">
                <a href="logout.do">로그인 / 회원가입</a></li>
            </div>
        </c:if>


        <div class="hambuger-menu">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
        </div>

    </div>


    <nav>
        <ul class="navbar_menu">
            <li><a href="index.do">전체매물</a></li>
            <li><a href="#">거리별</a></li>
            <li><a href="categorylist.do">출판사별</a></li>
            <li><a href="favregion.do">관심지역 매물</a></li>
            <li><a href="prodmap.do">매물지도</a></li>
        </ul>
    </nav>

</header>

</body>
</html>
