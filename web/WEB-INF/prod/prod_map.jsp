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
    <div class="section_left">
        <div class="prod_list_none">
            <c:if test="${empty list || fn.length(list)==0}">
                <ul>
                    <li>현재 관심 지역에 <br>
                        매물이 없습니다.
                    </li>
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
                        <li class="write_date">${item.write_date}</li>
                    </ul>
                </c:forEach>
            </c:if>
        </div>
        <%--페이지번호--%>
        <div class="page_num">
            <c:if test="${start_page>1}">
                <a href="prodmaplist.do?curr=${start_page-1}&lat=${fav_lng}&lng=${fav_lat}&dong=${param.dong}">이전</a>
            </c:if>

            <c:forEach var="i" begin="${start_page}" end="${end_page}" step="1">
                <c:choose>
                    <c:when test="${i==currpage}">
                        <c:out value="${i}"/>
                    </c:when>
                    <c:otherwise>
                        <a href="prodmaplist.do?curr=${i}&lat=${fav_lng}&lng=${fav_lat}&dong=${param.dong}"><c:out value="${i}"/></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${end_page < total_page}">
                <a href="prodmaplist.do?curr=${end_page+1}&lat=${fav_lng}&lng=${fav_lat}&dong=${param.dong}">다음</a>
            </c:if>
        </div>
    </div>

    <%--지도--%>

    <div id="map"></div>
</div>

<div class="space"></div>


<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=39e6966e11fb77579a6ab5fed53f2e28&libraries=clusterer,services"></script>
<script>


    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(${fav_lat}, ${fav_lng}), // 지도의 중심좌표
            // draggable: false, // 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false 옵션을 추가
            level: 4, // 지도의 확대 레벨
        };


    // 지도를 생성한다
    var map = new kakao.maps.Map(mapContainer, mapOption);


    // 버튼 클릭에 따라 지도 확대, 축소 기능을 막거나 풀고 싶은 경우에는 map.setZoomable 함수를 사용합니다
    map.setZoomable(false);


    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map             : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter   : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel        : 1, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });

    // 클러스트 담을 배열
    var markers = [];


    // 상품 목록 json 파일 가져와서 마크추가, 클러스터링
    fetch("prodmapmarker"
        , {
            method   : 'GET'
            , headers: {
                "Content-Type": "application/x-www-form-urlencoded"
                , "Accept"    : "text/json"
            }
        }).then(res => {
        return res.json()
    }).then(data => {
        data.forEach(item => {

            // 지도에 마커를 생성하고 표시한다
            var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(item.lat, item.lng), // 마커의 좌표
                map     : map // 마커를 표시할 지도 객체
            });
            markers.push(marker);

        })
        clusterer.addMarkers(markers);


        // 클러스터링 클릭시 지역 정보 가져옴
        kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {

            var position = map.getCenter(cluster.getCenter());

            // 위경도 가져오기
            var lat = position.La;
            var lng = position.Ma;


            // 지오코딩 하기
            var geocoder = new kakao.maps.services.Geocoder();

            // 동정보 가져오기
            var dong;
            var callback = function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    dong = result[0].region_3depth_name;

                    // 주소에 정보 담아서 이동
                    location.href = "prodmaplist.do?dong=" + dong + "&lat=" + lat + "&lng=" + lng;

                    /* // HTML 요소에 JavaScript 변수 값 설정
                     document.getElementById('map_list').textContent = dong;*/
                }
            };
            geocoder.coord2RegionCode(lat, lng, callback);

        });



    });


</script>
</body>
</html>
