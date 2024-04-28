<%--
  Created by IntelliJ IDEA.
  User: seeyh
  Date: 2024-04-20
  Time: 오후 6:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<%@ page import="com.haeti.service.ProdService" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/prod/chatting.css">
</head>
<body>


<jsp:include page="/header.jsp"/>


<div id="container">

    <c:if test="${param.iam == 'seller'}">
        <button id="sell_end">판매 완료</button>
    </c:if>
    <c:if test="${requestScope.status == '판매완료'}">
        <div class="chat_content chat_end">판매가 종료되었습니다.</div>
    </c:if>
    <!--     채팅창 -->
    <div id="_chatbox">
        <div id="messageWindow">
        </div>

        <div class="row input_window">
            <div class="col-11">
                <textarea id="inputMessage" onkeyup="enterkey()"></textarea>
            </div>
            <div class="col-1 message_send">
                <input id="message_submit" type="submit" value="SEND" onclick="send()"/>
            </div>

        </div>
    </div>

</div>
<%-- 세션으로 buyer의 user_no를 얻을 수 있음--%>
<%-- request로 prod를 받을 수 있음 -> 이거로 seller의 user_no를 받을 수 있음--%>
<%-- 그 user 들을 채팅 안에다 심어서 보낼 거임. ChatRoomDTO 에서 얻어온 user들이 있을텐데, --%>
<%-- 그 user 들중에 해당하지 않는다면 (채팅마다 자신의 user_no를 심어서 보내는데, 채팅방에도 DTO로 유저정보가 있을테니) --%>
<%--  --%>

<%
    //request 에 넣어
    request.setAttribute("seller", ProdService.getInstance().getSellerId(request.getParameter("prod_no")));
%>

<script>
    document.getElementById("sell_end").addEventListener("click", function () {
        location.href = "sell_end.do?prod_no=${param.prod_no}&buyer=${param.buyer}";
    });
</script>

</body>

<script type="text/javascript">

    var webSocket = new WebSocket('ws://172.30.1.88:8080/haeti/ChattingRoom');
    var inputMessage = document.getElementById('inputMessage');

    let prod_no = "${param.prod_no}";
    let seller = "${requestScope.seller}";
    let buyer = "${param.buyer}";
    let user1 = "${sessionScope.user_id}";

    let scroll_time = 0;//send 될때에는 0.1로 둬서 내려가게 하고, 아니면 0으로 그냥 두기

    let roomUser = prod_no + "#" + buyer + "#" + user1;
    console.log(roomUser);


    webSocket.onerror = function (event) {
        onError(event)
    };
    webSocket.onopen = function (event) {
        onOpen(event)
    };
    webSocket.onmessage = function (event) {
        onMessage(event)
    };

    function onMessage(event) {
        let rawMessage = event.data;

        let splitMessage = rawMessage.split("|");
        let user = splitMessage[0];
        let content = splitMessage[1];
        console.log("user : ");
        console.log(user);
        console.log("content : ");
        console.log(content);


        let mw = document.getElementById("messageWindow");
        mw.innerHTML = "<div class='chat_content chat_div'><div class='other_chat_region'><span class='just_other'>" + user + "</span>" + content + "</div></div>" + mw.innerHTML;

    }

    function onOpen(event) {
        let mw = document.getElementById("messageWindow");
        mw.innerHTML += "<div class='chat_content chat_open'>open</div>";
    }

    function onError(event) {
        //alert(event.data);
    }


    function send() {


        if (inputMessage.value == "") {
        } else {
            let mw = document.getElementById("messageWindow")
            mw.innerHTML = "<div class='chat_content chat_div'><div class='my_chat_region'> <span class='just_me'> 나 </span>" + inputMessage.value + "</div></div>" + mw.innerHTML;
        }
        webSocket.send(roomUser + "#" + inputMessage.value);
        inputMessage.value = "";

    }

    //     엔터키를 통해 send함
    function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }

    //지금 '채팅방 나가기' 키를 따로 만들면 room session 에서 빼낼 수 있겠다만,
    //그냥 브라우저를 닫는 경우에는 room 정보를 못주니깐 onClose 에서 제대로 못 빼낼거임
    //그러니깐 자바스크립트에서 브라우저를 닫는 순간 일어나는 이벤트리스너가 있는지 알아내고
    //그 이벤트 리스너에 이제 Action이나 Forward 같은 곳에서 받아낼 수 있게 해놓고
    //webSocket.send(message) 를 보내는 거임, room 번호를 같이 주기 위해
    //앞에 room + 도 꼭 넣어주고

    //     채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함

</script>
</html>