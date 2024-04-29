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
<c:set var="uploadPath" value="${requestScope.uploadPath}\\"/>
<c:set var="img" value="${requestScope.img}"/>

<div id="wrap">
    <h1>마이페이지</h1>
    <div id="top">
        <div class="tr">
            <div class="info1">
                <p>${dto.name}</p>
                <p>${dto.teacher_school}</p>
                <a href="modify_user.do">내 정보 수정</a>
            </div>
            <div class="info2">
                <div class="nick_id">
                    <p>${dto.nick_name}</p>
                    <p>${dto.user_id}</p>
                </div>
            </div>
            <div class="info3">
                <ul>
                    <li>
                        <a href="#">고객센터</a>
                    </li>
                    <li>
                        <a href="logout.do">로그아웃</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="middle">
        <div class="sales">
            <h2>판매 중인 상품</h2>
            <a href="sales_list.do">더보기></a>
        </div>
        <div class="sales_info">
            <c:choose>
                <c:when test="${(empty prodDTO.title)}">
                    <p>판매 중인 상품이 없습니다.</p>
                </c:when>
                <c:otherwise>
                    <%--<img src="upload/${prodDTO.prod_no}/${img}" alt="upload/${prodDTO.prod_no}/${img}">
                    <p>${prodDTO.title}</p>
                    <p>${prodDTO.write_date}</p>
                    <p>${prodDTO.cost}</p>--%>
                    <div class="tr">
                        <div class="td td1">
                            <img src="upload/${prodDTO.prod_no}/${img}" alt="upload/${prodDTO.prod_no}/${img}">
                        </div>
                        <div class="td td2">
                            <ul>
                                <li>${prodDTO.title}</li>
                                <li>${prodDTO.write_date}</li>
                                <li>${prodDTO.cost}</li>
                            </ul>
                        </div>
                        <div class="td td3">
                            <a href="my_prod_chatting.do?prod_no=${prodDTO.prod_no}">
                                <img src="img/chatting_icon.png" alt="채팅">
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <%--<div id="bottom">
        <div class="btm1">
            <a href="purchase_list.do"><div class="purchase"><p class="btm_p">구매내역</p></div></a>
            <a href="sales_list.do"><div class="sales_list"><p class="btm_p">판매내역</p></div></a>
        </div>
        <div class="btm2">
            <a href="#"><div class="chat"><p class="btm_p">채팅</p></div></a>
            <a href="#"><div class="review"><p class="btm_p">구매리뷰</p></div></a>
        </div>
    </div>--%>
    <div id="bottom">
        <a href="purchase_list.do">
            <div class="btm purchase">구매내역</div>
        </a>
        <a href="sales_list.do">
            <div class="btm sale">판매내역</div>
        </a>
        <a href="#">
            <div class="btm chat">채팅</div>
        </a>
        <a href="#">
            <div class="btm review">구매리뷰</div>
        </a>
    </div>
</div>
</body>
</html>
