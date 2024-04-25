package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.UserDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyPageAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=UserService.getUserService();
        ProdService prodService=ProdService.getInstance();

        HttpSession session=request.getSession();
        String user_id= (String) session.getAttribute("user_id");

        UserDTO userDTO=userService.getUserInfo(user_id);
        int user_no= userDTO.getUser_no();

        ProdDTO prodDTO=prodService.salesProd(user_no);

        request.setAttribute("userInfo", userDTO);
        request.setAttribute("prodDTO", prodDTO);
        request.setAttribute("img_paths",prodDTO.getImg_paths().get(0));
 //       System.out.println(prodDTO.getImg_paths().get(0)+"...");


        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/mypage.jsp");
        return forward;
    }
}
