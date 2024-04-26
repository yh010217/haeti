<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/prod/create_prod.css">

</head>
<body>

<jsp:include page="/header.jsp"/>

<div class="width84">

    <h6>상품 등록</h6>

    <div class="width80">
        <form method="post" action="create_prod_result.do" enctype="multipart/form-data">
            <div class="row">
                <div id="carouselExample" class="carousel slide col-4">

                    <div class="carousel-inner">

                        <div class="carousel-item active">
                            <div class="prod_image_modify_container">
                                <div id="image_container1" class="prod_modify_image">
                                    <img id="white_image1" src="img/white_background.jpg">
                                </div>
                                <label for="picture_image1">파일추가</label>
                                <input type="file" class="picture_image" name="picture_image1"
                                       id="picture_image1" onchange="setPreview(event,1);"><br>
                            </div>
                        </div>

                        <div class="carousel-item active">
                            <div class="prod_image_modify_container">
                                <div id="image_container2" class="prod_modify_image">
                                    <img id="white_image2" src="img/white_background.jpg">
                                </div>

                                <label for="picture_image2">파일추가</label>
                                <input type="file" class="picture_image" name="picture_image2"
                                       id="picture_image2" onchange="setPreview(event,2);"><br>
                            </div>
                        </div>


                        <div class="carousel-item active">
                            <div class="prod_image_modify_container">
                                <div id="image_container3" class="prod_modify_image">
                                    <img id="white_image3" src="img/white_background.jpg">
                                </div>

                                <label for="picture_image3">파일추가</label>
                                <input type="file" class="picture_image" name="picture_image3"
                                       id="picture_image3" onchange="setPreview(event,3);"><br>
                            </div>
                        </div>

                        <div class="carousel-item active">
                            <div class="prod_image_modify_container">
                                <div id="image_container4" class="prod_modify_image">
                                    <img id="white_image4" src="img/white_background.jpg">
                                </div>

                                <label for="picture_image4">파일추가</label>
                                <input type="file" class="picture_image" name="picture_image4"
                                       id="picture_image4" onchange="setPreview(event,4);"><br>
                            </div>
                        </div>

                        <div class="carousel-item active">
                            <div class="prod_image_modify_container">
                                <div id="image_container5" class="prod_modify_image">
                                    <img id="white_image5" src="img/white_background.jpg">
                                </div>

                                <label for="picture_image5">파일추가</label>
                                <input type="file" class="picture_image" name="picture_image5"
                                       id="picture_image5" onchange="setPreview(event,5);"><br>
                            </div>
                        </div>

                    </div>

                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample"
                            data-bs-slide="prev">
                        <img src="img/carousel-prev-icon.png">
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExample"
                            data-bs-slide="next">
                        <img src="img/carousel-next-icon.png">
                        <%--<span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
                <div class="col-8">
                    <div class="blue_div">
                        <label for="title">제목</label>
                        <input type="text" id="title" name="title" class="blue_input"><br>

                        <label for="cost">가격</label>
                        <input type="number" id="cost" name="cost" class="blue_input"><br>

                        <label for="category_id">카테고리</label>
                        <select id="category_id" name="category_id" class="blue_input">
                            <option value="1">천재교과서</option>
                            <option value="2">지학사</option>
                            <option value="3">비상교과서</option>
                        </select>

                        <label for="user_region">판매지역</label>
                        <input type="text" id="user_region" value="${requestScope.region}" class="blue_input" readonly/>

                        <span class="create_prod_user">
                            <c:if test="${!empty sessionScope.user_id}">${sessionScope.user_id}</c:if>
                            <c:if test="${empty sessionScope.user_id}">로그인 후 이용해주세요</c:if>
                        </span>

                        <%--<c:out value="잠시 : ${requestScope.user_no}"/>--%>
                    </div>
                </div>
            </div>
            <textarea id="content" name="content" placeholder="상품 설명입니다." cols=40 rows="6"></textarea>

            <button type="submit" class="blue_button">등록</button>
            <button type="reset" class="gray_button">취소</button>
        </form>

    </div>
</div>

<jsp:include page="/footer.jsp"/>

<script>
    /*
    <div class="carousel-item active">
        <div class="prod_image_modify_container">
            <div id="image_container1" class="prod_modify_image">
                <img src="img/white_background.jpg">
            </div>
            <input type="file" class="picture_image" name="picture_image1"
                   onchange="setPreview(event,1);"><br>
        </div>
    </div>
    */
    let setPreview = function (event, num) {
        let reader = new FileReader();
        reader.onload = function (event) {
            let img_container = document.getElementById('image_container' + num);
            let white_img = document.getElementById('white_image' + num);
            img_container.removeChild(white_img);
            let image = document.createElement('img');
            image.src = event.target.result;
            image.id = 'image' + num;
            img_container.appendChild(image);
        }
        reader.readAsDataURL(event.target.files[0]);
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

</body>
</html>