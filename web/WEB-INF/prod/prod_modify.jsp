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
</head>
<body>


<c:set var="dto" value="${requestScope.dto}"/>
<c:set var="path" value="${requestScope.path}\\"/>
<c:set var="images" value="${dto.img_paths}"/>


<form method="post" action="prod_modify_result.do?prod_no=${dto.prod_no}" enctype="multipart/form-data">

    <% int i = 1; %>
    <ul>
        <c:forEach var="image" items="${images}">

            <li id="list<%=i%>"><img id="image<%=i%>" src="upload/${dto.prod_no}/${image}"></li>
            <input type="file" class="picture_image" name="picture_image<%=i%>" onchange="setPreview(event,<%=i%>);"><br>
            <input type="checkbox" class="image_remove_checkbox" name="remove_image<%=i%>" value="remove"><br>
            <% i++; %>
        </c:forEach>
        <c:forEach var="fi" begin="<%=i%>" end="5" step="1">
            <li>
                <input type="file" class="picture_image" name="picture_image${fi}" onchange="setPreview(event,${fi});"><br>
            </li>
        </c:forEach>
    </ul>


    <li>
        <input type="text" name="title" value="${dto.title}">
    </li>
    <li>
        <input type="text" name="cost" value="${dto.cost}">
    </li>
    <li>
        <select name="category_id">
            <option value="1">천재교과서</option>
            <option value="2">지학사</option>
            <option value="3">비상교과서</option>
        </select>
    </li>
    <li>
        <textarea name="content"><c:out value="${dto.content}"/></textarea>
    </li>


    <button type="submit">전송</button>
</form>


<script>
    let setPreview = function (event, num) {
        let reader = new FileReader();
        reader.onload = function (event) {
            let li_container = document.getElementById('list' + num);
            let prev_img = document.getElementById('image' + num);
            li_container.removeChild(prev_img);
            let image = document.createElement('img');
            image.src = event.target.result;
            image.id = 'image' + num;
            li_container.appendChild(image);
        };
        reader.readAsDataURL(event.target.files[0]);

        let pic = document.getElementsByName("picture_image"+num)[0]
        pic.attributes += 'checked';

    }
</script>

</body>
</html>
