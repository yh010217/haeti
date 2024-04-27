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
    <link rel="stylesheet" href="css/prod/prod_detail.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

<jsp:include page="/header.jsp"/>


<c:set var="dto" value="${requestScope.dto}"/>
<c:set var="path" value="${requestScope.path}\\"/>
<c:set var="images" value="${dto.img_paths}"/>


<div class="width84">


    <div id="top_buttons">
        <button class="top_button">
            <a href="prod_modify.do?prod_no=${dto.prod_no}">수정</a><br>
        </button>
        <button class="top_button">
            <a href="prod_delete.do?prod_no=${dto.prod_no}">삭제</a><br>
        </button>
    </div>

    <h6>상품 상세 정보</h6>


    <div class="row width80">

        <div id="carouselExample" class="carousel slide col-4">
            <div class="carousel-inner">

                <c:forEach var="image" items="${images}">

                    <div class="prod_image_detail_container carousel-item active">
                        <div class="prod_detail_images">
                            <img src="upload/${dto.prod_no}/${image}">
                                <%-- detail 아니면 밑에 뭔가 더 있어야 될듯 --%>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <%--슬라이더 틀--%>

            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                <img src="img/carousel-prev-icon.png">
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                <img src="img/carousel-next-icon.png">
                <%--<span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <div class="col-8">
            <div class="prod_detail_twcc">
                <h4><c:out value="${dto.title}"/></h4> <br>
                <span class="cost_text"><c:out value="가격 : ${dto.cost}원"/></span><br>
                <span class="category_text"><c:out value="${dto.category}"/></span><br>
                <span class="date_text"><c:out value="${dto.write_date}작성"/></span><br>
            </div>

            <span class="seller_id">판매자 : ${requestScope.seller_id}</span>

            <button id="chatting_button">
                <a href="chatting.do?prod_no=${dto.prod_no}&buyer=${sessionScope.user_id}&iam=buyer">채팅하기</a>
            </button>
        </div>


    </div>
    <div class="row">
        <div class="content_row">
            <span>본문 내용</span>
            <div class="content_box">
                <span><c:out value="${dto.content}"/></span>
            </div>
        </div>
    </div>

    <button type="button" id="rep_show_button">댓글 확인</button>
    <ul id="review_list">

    </ul>

    <div id="review_create_box"></div>

</div>


<jsp:include page="/footer.jsp"/>

<script src="js/review_show.js"></script>
<script>init_data(${dto.prod_no})</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
