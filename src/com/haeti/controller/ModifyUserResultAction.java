package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyUserResultAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("id");
        String pwd=request.getParameter("pwd");
        String nick_name=request.getParameter("nick_name");
        String addr_detail=request.getParameter("addr_detail"); //수정 필요
        String addr_dong=request.getParameter("addr_dong"); //수정 필요
        String fav_region=request.getParameter("fav_region1"); //수정 필요
        String email=request.getParameter("email");

        UserDTO dto=new UserDTO();
        dto.setUser_id(user_id);
        dto.setPwd(pwd);
        dto.setNick_name(nick_name);
        dto.setAddr_detail(addr_detail);
        dto.setAddr_dong(addr_dong);
        dto.setFav_region(fav_region);
        dto.setEmail(email);

        UserService userService=UserService.getUserService();
        int modify_result=userService.modifyUser(dto);
        request.setAttribute("modify_result", modify_result);

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/modify_user_result.jsp");
        return forward;
    }
}
