<%--
  Created by IntelliJ IDEA.
  User: FULL8-008
  Date: 2024-04-22
  Time: 오전 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>약관동의</title>
    <link rel="stylesheet" href="../../css/agreeterms.css">
</head>
<body>
<div>회원가입</div>

<%--서비스 이용 약관 --%>
<%--개인정보 수집 및 이용 동의--%>
<%--마케팅 활용 및 정보수신 동의 --%>
<h1>약관 동의</h1>
<textarea name="agree" id="agree" cols="30" rows="14"></textarea>
<div>
<input type="checkbox" id="agreeterms"> 서비스 이용약관 동의 (필수)
</div>
<div>
    <input type="checkbox" id="agreeterms-2"> 개인정보 수집 및 이용 동의(필수)
</div>
<div>
    <input type="checkbox" id="agreeterms-3"> 개인정보 제 3자 제공 동의 (선택)
</div>
<div>
    <input type="checkbox" id="agreeterms-4"> 모두 동의 합니다
</div>
<div>
    <button type="submit" id="previous">이전</button>
    <button type="submit" id="next">다음</button>
</div>

</body>
</html>
