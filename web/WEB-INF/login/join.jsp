<%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-22
  Time: 오전 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="css/join.css">
</head>
<body>
<div>회원가입</div>

<form name="joinForm" method="post" action="join_result.do">
    <ul>
        <li>
            <label for="user_id">아이디</label>
            <input type="text" id="user_id" name="user_id" required>

        </li>
        <li>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" required>
        </li>
        <li>
            <label for="name">이름</label>
            <input type="text" name="name" id="name" required>
        </li>
        <li>
            <label for="nick_name">닉네임</label>
            <input type="text" name="nick_name" id="nick_name" >
        </li>
        <li>
            <label for="addr_detail">주소</label>
            <input type="text" name="addr_detail" id="addr_detail" required>
        </li>
        <li>
            <label for="fav_region">관심지역</label>
            <input type="text" name="fav_region" id="fav_region" required>
        </li>
        <li>
            <label for="tel">휴대전화</label>
            <input type="text" name="tel" id="tel" required>
        </li>
        <li>
            <label for="email">이메일</label>
            <input type="email" name="email" id="email" required>
        </li>
        <li>
            <input type="submit" value="회원가입">
        </li>
        <li>
            <button type="submit" id="previous">이전</button>
            <button type="submit" id="next">다음</button>
        </li>
    </ul>

</form>
</body>
</html>
