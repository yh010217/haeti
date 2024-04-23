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
<div id="join">
    <h2>회원가입</h2>
    <form name="joinForm" method="post" action="join_result.do">
        <ul>
            <li>
                <label for="user_id">아이디</label>
                <input type="text" name="user_id" id="user_id" required>
                <button type="submit" name="check">중복검사</button>
            </li>
            <li>
                <label for="pwd">비밀번호</label>
                <input type="password" name="pwd" id="pwd" required>
            </li>
            <li>
                <label for="name">이름</label>
                <input type="text" name="name" id="name" required>
            </li>
            <li>
                <label for="nick_name">닉네임</label>
                <input type="text" name="nick_name" id="nick_name">
                <button type="submit" name="check">중복확인</button>
            </li>
            <li>
                <label>구분선택</label>
                <select class="teacher_school" id="teacher_school">
                    <option value="ele_school" >초등교사</option>
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
                <label for= "fav_region">관심지역</label>
                <input type="text" id="fav_region" name="fav_region">
                <input type="text"  id="fav_region2" name="fav_region">
                <input type="text"  id="fav_region3" name="fav_region">
            </li>
            <li>
                <label for="tel">휴대전화</label>
                <input type="text" name="tel" id="tel" required>
            </li>
            <li>
                <label for="email">이메일</label>
                <input type="email" name="email" id="email" required>
                <button type="submit" name="check">중복확인</button>
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
</div>



</form>
</body>
</html>
