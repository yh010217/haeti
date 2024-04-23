package com.haeti.controller.chat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chatting")
public class ChatRoomAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req,resp);
    }

    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String room = request.getParameter("room");
        ChatEndPoint.setNowRoom(room);

        //이러고 ChatEndPoint로 포워드가 아니라, endpoint 받는건 자바스크립트에서 할거임
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/chat/chattingRoom.jsp");
        disp.forward(request,response);
    }
}
