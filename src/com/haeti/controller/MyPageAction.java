package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.UserDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyPageAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=UserService.getUserService();
        ProdService prodService=ProdService.getInstance();

        String user_id="test";
        UserDTO userDTO=userService.getUserInfo(user_id);

        request.setAttribute("userInfo", userDTO);

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/mypage.jsp");
        return forward;
    }
}
