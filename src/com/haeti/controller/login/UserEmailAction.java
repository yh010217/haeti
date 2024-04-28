package com.haeti.controller.login;

import com.haeti.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserEmailAction",value = "/email_check2")
public class UserEmailAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request,response);
    }

    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/plain;charset=utf-8");


        String email=request.getParameter("email");

        UserService userService=UserService.getUserService();

        boolean result=userService.useremailCheck(email);

        String email1="";

        if (result){
            email1="중복된 이메일입니다.";
        } else if (email==null || "".equals(email)) {
            email1="이메일을 작성해주세요!";
        } else {
            email1="사용 가능한 이메일입니다.";
        }

        PrintWriter out=response.getWriter();
        out.print(email1);


    }
}