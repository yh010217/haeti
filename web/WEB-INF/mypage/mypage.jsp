<%--
  Created by IntelliJ IDEA.
  User: FULL8-009
  Date: 2024-04-22
  Time: 오후 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/mypage.css">
</head>
<body>
<div id="wrap">
    <h1>마이페이지</h1>
    <div id="top">
        <div class="info1">
            <h5>(name) 님</h5>
            <p>(teacher_school)</p>
            <p><a href="modify_user.do">내 정보 수정</a></p>
        </div>
        <div class="info2">
            <div>
                <p>(nick_name)</p>
                <p>(id)</p>
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
            <img src="img/logo.png" alt="판매 중인 상품">
            <p>(상품이름)</p>
            <p>(2024.04.22)</p>
            <p>(판매중 5,000원)</p>
        </div>
    </div>
    <div id="bottom">
        <div class="btm1">
            <a href="purchase_list.do"><div class="purchase"><p class="btm_p">구매내역</p></div></a>
            <a href="#"><div class="sales_list"><p class="btm_p">판매내역</p></div></a>
        </div>
        <div class="btm2">
            <a href="#"><div class="chat"><p class="btm_p">채팅</p></div></a>
            <a href="#"><div class="review"><p class="btm_p">구매리뷰</p></div></a>
        </div>
    </div>
</div>
</body>
</html>
