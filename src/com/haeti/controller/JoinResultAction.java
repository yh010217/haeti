package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class JoinResultAction implements Action{

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("user_id");
        String pwd=request.getParameter("pwd");
        String name=request.getParameter("name");
        String nick_name=request.getParameter("nick_name");
        String tel=request.getParameter("tel");
        String email=request.getParameter("email");
        String addr_dong=request.getParameter("addr_dong");
        String addr_detail=request.getParameter("addr_detail");
        String fav_region=request.getParameter("fav_region");

        UserService service=UserService.getUserService();
        service.insertService(user_id,pwd,name,nick_name,tel,email,addr_dong,addr_detail,fav_region);

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("loginmain.jsp");

        return forward;
    }
}
