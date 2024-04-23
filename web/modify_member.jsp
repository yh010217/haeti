<%--
  Created by IntelliJ IDEA.
  User: FULL8-009
  Date: 2024-04-22
  Time: 오전 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- jquery -->
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script src="js/addr_test.js"></script>
    <link rel="stylesheet" href="css/modify_member.css">
</head>
<body>
<div id="wrap">
    <h2>내 정보 수정</h2>
    <form method="post" action="modify_member_result.do">
        <ul>
            <li>
                <label for="pwd">아이디</label>
                <input type="text" name="id" id="id" readonly>
            </li>
            <li>
                <label for="pwd">비밀번호</label>
                <input type="password" name="pwd" id="pwd">
                <button type="submit" name="check">비밀번호 변경</button>
            </li>
            <li>
                <label for="name">이름</label>
                <input type="text" name="name" id="name" readonly>
            </li>
            <li>
                <label for="nickname">닉네임</label>
                <input type="text" name="nickname" id="nickname">
                <button type="submit" name="check">중복확인</button>
            </li>
            <li>
                <label>구분선택</label>
                <select name="select_teacher">
                    <option value="ele">초등교사</option>
                    <option value="middlehigh">중·고등교사</option>
                </select>
            </li>
            <li>
                <label>주소</label>
                <input type="text" id="sample6_postcode" placeholder="우편번호">
                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" id="sample6_address" placeholder="주소"><br>
                <input type="text" id="sample6_detailAddress" placeholder="상세주소">
                <input type="text" id="sample6_extraAddress" placeholder="참고항목">
            </li>
            <li>
                <label>관심지역</label>
                <input type="text" name="fav_region1">
                <input type="text" name="fav_region2">
                <input type="text" name="fav_region3">
            </li>
            <li>
                <label>휴대전화</label>
                <input type="text" name="tel" id="tel" readonly>
            </li>
            <li>
                <label>이메일</label>
                <input type="email" name="email" id="email">
                <button type="submit" name="check">중복확인</button>
            </li>
            <li>
                <label>가입일</label>
                <input type="text" name="join_date" id="join_date" readonly>
            </li>
            <li>
                <button type="submit">변경하기</button>
            </li>
        </ul>
    </form>
</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>
