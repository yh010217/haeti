<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="/header.jsp"/>

<h3>상품 등록</h3>


<form method="post" action="create_prod_result.do" enctype="multipart/form-data">
    <input type="file" class="picture_image" name="picture_image1" onchange="setPreview(event,1);"><br>
    <div id="image_container1"><img src=""></div>
    <input type="file" class="picture_image" name="picture_image2" onchange="setPreview(event,2);"><br>
    <div id="image_container2"></div>
    <input type="file" class="picture_image" name="picture_image3" onchange="setPreview(event,3);"><br>
    <div id="image_container3"></div>
    <input type="file" class="picture_image" name="picture_image4" onchange="setPreview(event,4);"><br>
    <div id="image_container4"></div>
    <input type="file" class="picture_image" name="picture_image5" onchange="setPreview(event,5);"><br>
    <div id="image_container5"></div>

    <ul>
        <li>
            <label for="title">제목</label>
            <input type="text" id="title" name="title">
        </li>
        <li>
            <label for="cost">가격</label>
            <input type="number" id="cost" name="cost">
        </li>
        <li>
            <label for="category_id">카테고리</label>
            <select id="category_id" name="category_id">
                <option value="1">천재교과서</option>
                <option value="2">지학사</option>
                <option value="3">비상교과서</option>
            </select>
        </li>
        <li>
            <textarea id="content" name="content" placeholder="상품 설명입니다." cols = 40 rows="6"></textarea>
        </li>
    </ul>
    <button type="submit">등록</button>
    <button type="reset">취소</button>
</form>

<jsp:include page="/footer.jsp"/>

</body>
</html>