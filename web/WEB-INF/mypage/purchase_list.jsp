<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/mypage/purchase_list.css">
    <script defer src="js/purchase_list.js"></script>
</head>
<body>
<c:set var="list" value="${requestScope.purchase_list}"/>
<div id="wrap">
    <h1>구매내역</h1>
    <div id="list">
        <form method="post">
            <select id="period" onchange="selectChange(this.value)">
                <option value="week">최근 1주일</option>
                <option value="month">최근 1개월</option>
                <option value="3month">최근 3개월</option>
            </select>
<%--            <button type="submit" id="btn">확인</button>--%>
        </form>
        <div class="table" id="result">

        </div>
    </div>
</div>
</body>
</html>
