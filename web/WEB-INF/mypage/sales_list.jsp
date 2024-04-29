<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/mypage/sales_list.css">
    <script defer src="js/sales_list.js"></script>
</head>
<body>
<div id="wrap">
    <h1>판매내역</h1>
    <div id="list">
        <form method="post">
            <select id="status" onchange="selectChange(this.value)">
                <option value="sale">판매중</option>
                <option value="sale_comp">판매완료</option>
            </select>
        </form>
        <div class="table" id="result">

        </div>
    </div>
</div>
</body>
</html>
