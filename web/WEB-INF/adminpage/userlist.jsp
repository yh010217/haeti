<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-23
  Time: 오후 7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>
  <li>관리자 페이지</li>
  <li>회원관리</li>
  <li>카테고리 관리</li>
  <li>고객센터</li>
</ul>

<%--구분선 추가--%>
<div class="line"></div>

<form method="post" action="userlistresult">
  <select name="search">
    <option value="user_id">아이디</option>
    <option value="name">이름</option>
    <option value="tel">전화번호</option>
  </select>
  <input type="text" name="search_txt">
  <input type="submit" value="검색">
</form>


<div class="line"></div>

<table>
  <thead>
  <tr>
    <th>번호</th><th>아이디</th><th>이름</th><th>전화번호</th><th>가입일</th>
  </tr>
  </thead>
  <tbody>

  </tbody>
</table>

</body>
</html>
