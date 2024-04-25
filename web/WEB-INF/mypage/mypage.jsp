<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/mypage.css">
</head>
<body>
<c:set var="dto" value="${requestScope.userInfo}"/>
<c:set var="prodDTO" value="${requestScope.prodDTO}"/>
<c:set var="img_paths" value="${requestScope.img_paths}"/>
<div id="wrap">
    <h1>마이페이지</h1>
    <div id="top">
        <div class="info1">
            <h5>${dto.name}</h5>
            <p>${dto.teacher_school}</p>
            <p><a href="modify_user.do">내 정보 수정</a></p>
        </div>
        <div class="info2">
            <div>
                <p>${dto.nick_name}</p>
                <p>${dto.user_id}</p>
            </div>
            <div>
                <a href="#">고객센터</a>
                <a href="#">로그아웃</a>
            </div>
        </div>
    </div>
    <div id="middle">
        <div class="sales">
            <h2>판매 중인 상품</h2>
            <a href="#">더보기></a>
        </div>
        <div class="sales_info">
<%--            <img src="img/logo.png" alt="판매 중인 상품">--%>
            <img src="upload/${img_paths}" alt=${img_paths}>
            <p>${prodDTO.title}</p>
            <p>${prodDTO.write_date}</p>
            <p>${prodDTO.cost}</p>
        </div>
    </div>
    <div id="bottom">
        <div class="btm1">
            <a href="purchase_list.do"><div class="purchase"><p class="btm_p">구매내역</p></div></a>
            <a href="sales_list.do"><div class="sales_list"><p class="btm_p">판매내역</p></div></a>
        </div>
        <div class="btm2">
            <a href="#"><div class="chat"><p class="btm_p">채팅</p></div></a>
            <a href="#"><div class="review"><p class="btm_p">구매리뷰</p></div></a>
        </div>
    </div>
</div>
</body>
</html>
