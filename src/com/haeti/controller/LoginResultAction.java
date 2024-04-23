package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginResultAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("user_id");
        String pwd=request.getParameter("pwd");
        //String email=request.getParameter("email");

        HttpSession session = request.getSession();


        UserService service=UserService.getInstance();
        int result=service.login(user_id,pwd);
        String email=service.loginemail(user_id);
        UserDTO dto=service.loginlist(user_id);



        //request.setAttribute("result",result);
        session.setAttribute("user_id",user_id);
        session.setAttribute("email",email);
        session.setAttribute("dto",dto);
        session.setAttribute("result",result);


        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/login/loginresult.jsp");
        return forward;
    }
}
