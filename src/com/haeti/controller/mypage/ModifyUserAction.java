package com.haeti.controller.mypage;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ModifyUserAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        UserService userService = UserService.getUserService();
        UserDTO userDTO = userService.getUserInfo(user_id);


        /* 주소 */
        String postcode="";
        String addr="";
        if(userDTO.getAddr_dong()!=null && userDTO.getAddr_dong().contains(",")){
            String[] addr_dong = userDTO.getAddr_dong().split(",");
            postcode = addr_dong[0];
            addr = addr_dong[1];
        }else{
            postcode=userDTO.getAddr_dong();
        }

        String addr_detail = "";
        String addr_extra = "";
        if(userDTO.getAddr_detail()!=null && userDTO.getAddr_detail().contains(",")){
            String[] addr_detail_arr = userDTO.getAddr_detail().split(",");
            addr_detail=addr_detail_arr[0];
            addr_extra=addr_detail_arr[1];
        }else{
            addr_detail=userDTO.getAddr_detail();
        }


        /* 관심지역 */
        String fav_region = "";
        if(userDTO.getFav_region()!=null){
            fav_region = userDTO.getFav_region();
        }

        request.setAttribute("userDTO",userDTO);

        request.setAttribute("postcode",postcode);
        request.setAttribute("addr",addr);
        request.setAttribute("addr_detail",addr_detail);
        request.setAttribute("addr_extra",addr_extra);
        request.setAttribute("fav_region", fav_region);
        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/mypage/modify_user.jsp");
        return forward;
    }
}

