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
    <link rel="stylesheet" href="css/prod/prod_map.css">

</head>
<body>
<c:set var="list" value="${requestScope.list}"></c:set>
<c:set var="fav_region" value="${requestScope.fav_region}"></c:set>
<c:set var="fav_lat" value="${requestScope.fav_lat}"></c:set>
<c:set var="fav_lng" value="${requestScope.fav_lng}"></c:set>

<div class="prod_wrap">
<div class="prod_list">
<c:if test="${empty list || fn.length(list)==0}">
    <ul>
        <li>해당 자료가 없습니다.</li>
    </ul>
</c:if>
</div>
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
<%--페이지번호--%>
<div class="page_num">
    <c:if test="${start_page>1}">
        <a href="index.do?curr=${start_page-1}&search=${search}&search_txt=${search_txt}">이전</a>
    </c:if>

    <c:forEach var="i" begin="${start_page}" end="${end_page}" step="1">
        <c:choose>
            <c:when test="${i==currpage}">
                <c:out value="${i}"/>
            </c:when>
            <c:otherwise>
                <a href="index.do?curr=${i}&search=${search}&search_txt=${search_txt}"><c:out value="${i}"/></a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${end_page < total_page}">
        <a href="index.do?curr=${end_page+1}&search=${search}&search_txt=${search_txt}">다음</a>
    </c:if>
</div>




<%-- 선호 지역 --%>
<c:set var="fav_region" value="${requestScope.fav_region}"/>

<div id="map"></div>


</div>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=[MY KEY]&libraries=clusterer,services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(${fav_lat}, ${fav_lng}), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
        };

  // 지도를 생성한다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 1 // 클러스터 할 최소 지도 레벨
    });

var markers = [];

/*
      var data = accidentDeath.searchResult.accidentDeath;

      for(let i=0; i<data.length; i++) {
          // 지도에 마커를 생성하고 표시한다
          var marker = new kakao.maps.Marker({
              position: new kakao.maps.LatLng(data[i].grd_la, data[i].grd_lo), // 마커의 좌표
              map     : map // 마커를 표시할 지도 객체
          });
          markers.push(marker);
      }
      clusterer.addMarkers(markers);*/

/*    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/download/web/data/chicken.json", function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = $(data.positions).map(function(i, position) {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(position.lat, position.lng)
            });
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });*/
    /*===========================================================================*/

    fetch("prodmaplist"
    , {
        method : 'GET'
            , headers: {
                "Content-Type": "application/x-www-form-urlencoded"
                , "Accept": "text/json"
            }
        }).then(res => {
            return res.json()
    }).then(data=> {
        data.forEach(item => {

            // 지도에 마커를 생성하고 표시한다
            var marker = new kakao.maps.Marker({
                position : new kakao.maps.LatLng(item.lat, item.lng), // 마커의 좌표
                map      : map // 마커를 표시할 지도 객체
            });
            markers.push(marker);

        })
        clusterer.addMarkers(markers);
    })



</script>
</body>
</html>
