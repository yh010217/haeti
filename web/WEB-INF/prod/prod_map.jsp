<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-25
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<c:set var="list" value="${requestScope.list}"></c:set>
<c:set var="LatLng" value="${requestScope.LatLng}"></c:set>

<c:if test="${empty list || fn.length(list)==0}">
    <ul>
        <li>해당 자료가 없습니다.</li>
    </ul>
</c:if>
<div class="prod_list">
    <c:if test="${!(empty list)}">
        <c:forEach var="item" items="${list}">
            <ul>
                <li><a href="prod_detail.do?prod_no=${item.prod_no}">
                    <img src="upload/${item.prod_no}/${item.img_paths[0]}"></a></li>
                <li class="title">${item.title}</li>
                <li class="cost">${item.cost}원</li>
               <%-- <li class="write_date">${item.write_date}</li>--%>
            </ul>
        </c:forEach>
    </c:if>


</div>


<%--주소 입력값--%>
<c:set var="fav_region" value="${requestScope.fav_region}"/>

<div id="map" style="width:1000px;height:500px;"></div>


<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=03cf1996dda26b1d666f2fc032c10fb4&libraries=clusterer,services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.56682, 126.97865), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
        };

    // 지도를 생성한다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch('<c:out value="${user_fav}"/>', function(result, status) {

        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);


            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });

</script>
</body>
</html>
