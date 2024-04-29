<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FULL8-005
  Date: 2024-04-24
  Time: 오후 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/prod/prod_modify.css">
</head>
<body>


<c:set var="dto" value="${requestScope.dto}"/>
<c:set var="path" value="${requestScope.path}\\"/>
<c:set var="images" value="${dto.img_paths}"/>

<jsp:include page="/header.jsp"/>

<div class="width84">


    <h6>상품 수정</h6>


    <form method="post" action="prod_modify_result.do?prod_no=${dto.prod_no}" enctype="multipart/form-data">

        <div class="row">

            <div id="my_slide" class="col-4">

                <div class="slide-inner">
                    <div class="slide-items">
                        <% int i = 1; %>
                        <c:forEach var="image" items="${images}">


                            <div id="list<%=i%>" class="slide-item">

                                <div class="prod_image_modify_container">


                                    <div id="image_container<%=i%>" class="prod_modify_image prod_detail_images">
                                        <label for="remove_image<%=i%>" class="remove_label before_blur">
                                            <img id="white_image<%=i%>" src="upload/${dto.prod_no}/${image}">
                                        </label>
                                    </div>


                                    <label for="modify_image<%=i%>" class="modify_label">이미지 변경</label>
                                    <input type="file" class="picture_image" name="picture_image<%=i%>"
                                           id="modify_image<%=i%>"
                                           onchange="setPreview(event,<%=i%>);"><br>

                                    <%--<label for="remove_image<%=i%>">이미지 삭제</label>--%>
                                    <input type="checkbox" class="image_remove_checkbox" name="remove_image<%=i%>"
                                           id="remove_image<%=i%>"
                                           value="remove"><br>
                                    <% i++; %>
                                </div>
                            </div>

                        </c:forEach>
                        <c:forEach var="fi" begin="<%=i%>" end="5" step="1">

                            <div class="slide-item">

                                <div class="prod_image_modify_container">
                                    <div id="image_container${fi}" class="prod_modify_image prod_detail_images">
                                        <img id="white_image${fi}"
                                             src="img/white_background.jpg">
                                    </div>

                                    <label for="modify_image${fi}" class="modify_label">이미지 변경</label>
                                    <input type="file" class="picture_image" name="picture_image${fi}"
                                           id="modify_image${fi}"
                                           onchange="setPreview(event,${fi});"><br>

                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <button class="slide-prev" type="button">
                        <img src="img/carousel-prev-icon.png">
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="slide-next" type="button">
                        <img src="img/carousel-next-icon.png">
                    </button>

                </div>
            </div>

            <div class="col-8">

                <div class="blue_div">
                    <div class="blue_div_ele">
                        <label for="title">제목</label>
                        <input type="text" id="title" name="title" class="blue_input"
                               value="${dto.title}"><br>
                    </div>
                    <div class="blue_div_ele">

                        <label for="cost">가격</label>
                        <input type="number" id="cost" name="cost" class="blue_input"
                               value="${dto.cost}"><br>
                    </div>
                    <div class="blue_div_ele">

                        <label for="category_id">카테고리</label>
                        <select id="category_id" name="category_id" class="blue_input">
                            <option value="1"
                                    <c:if test="${dto.category_id eq 1}"><c:out value="selected"/></c:if>
                            >천재교과서
                            </option>
                            <option value="2"
                                    <c:if test="${dto.category_id eq 2}"><c:out value="selected"/></c:if>
                            >지학사
                            </option>
                            <option value="3"
                                    <c:if test="${dto.category_id eq 3}"><c:out value="selected"/></c:if>
                            >비상교과서
                            </option>
                        </select>
                    </div>
                    <div class="blue_div_ele">

                        <label for="user_region">판매지역</label>
                        <input type="text" id="user_region" value="${requestScope.region}" class="blue_input"
                               readonly/>
                    </div><%--
                    <span class="create_prod_user">
                            <c:if test="${!empty sessionScope.user_id}">${sessionScope.user_id}</c:if>
                            <c:if test="${empty sessionScope.user_id}">로그인 후 이용해주세요</c:if>
                        </span>--%>
                </div>
            </div>



        </div>

        <div class="row">
            <textarea name="content" rows="6"><c:out value="${dto.content}"/></textarea>
        </div>

        <button type="reset" class="yellow_button">취소</button>
        <button type="submit" class="blue_button">완료</button>
    </form>
</div>




<jsp:include page="/footer.jsp"/>

<script>

    let setPreview = function (event, num) {
        let reader = new FileReader();
        reader.onload = function (event) {
            let img_container = document.getElementById('image_container' + num);
            let white_img = document.getElementById('white_image' + num);
            img_container.removeChild(white_img);
            let image = document.createElement('img');
            image.src = event.target.result;
            image.id = 'white_image' + num;
            img_container.appendChild(image);
        }
        reader.readAsDataURL(event.target.files[0]);
    }
</script>
<script src="js/slide_mine.js"></script>
<script>init_slide_num(5);</script>
<script src="js/remove_blur.js"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
