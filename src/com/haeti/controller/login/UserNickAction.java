package com.haeti.controller.login;

import com.haeti.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "UserNickAction",value = "/nick_name_check2")
public class UserNickAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request,response);
    }

    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/plain;charset=utf-8");

        //String user_id=request.getParameter("user_id");

        String nick_name=request.getParameter("nick_name");

//        System.out.println(nick_name + "ddd");

        UserService userService=UserService.getUserService();

        boolean result=userService.usernickCheck(nick_name);

        String nick_name1="";

        if (result){
            nick_name1="중복된 닉네임입니다.";
        }else if(nick_name==null || "".equals(nick_name)){
            nick_name1="닉네임을 작성해주세요!";
        } else {
            nick_name1="사용가능한 닉네임입니다.";
        }

        PrintWriter out=response.getWriter();
        out.print(nick_name1);

    }
}