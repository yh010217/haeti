<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-22
  Time: 오후 6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div><jsp:include page="header.jsp"/></div>
<div class="middle"><jsp:include page="${param.page}"/></div>
<div class="bottom"><jsp:include page="footer.jsp"/></div>

</body>
</html>
