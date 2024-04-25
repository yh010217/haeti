package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ModifyUserAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        UserService userService = UserService.getUserService();
        UserDTO userDTO = userService.getUserInfo(user_id);

        /* 주소 */
        String[] addr_dong = userDTO.getAddr_dong().split(",");
        String[] addr_detail = userDTO.getAddr_detail().split(",");
        String postcode = addr_dong[0];
        String addr = addr_dong[1];
        String addrdetail = addr_detail[0];
        String addrextra = addr_detail[1];

        /* 관심지역 */
        String[] fav_region = userDTO.getFav_region().split(",");

        request.setAttribute("userDTO",userDTO);

        request.setAttribute("postcode",postcode);
        request.setAttribute("addr",addr);
        request.setAttribute("addr_detail",addrdetail);
        request.setAttribute("addr_extra",addrextra);
        request.setAttribute("fav_region", fav_region);
        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/modify_user.jsp");
        return forward;
    }
}

