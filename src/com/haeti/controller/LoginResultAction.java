package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginResultAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("user_id");
        String pwd=request.getParameter("pwd");

        HttpSession session = request.getSession();

        UserService service=UserService.getInstance();
        int result=service.login(user_id,pwd);
        //request.setAttribute("result",result);
        session.setAttribute("user_id",user_id);
        session.setAttribute("result",result);

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/login/loginresult.jsp");
        return forward;
    }
}
