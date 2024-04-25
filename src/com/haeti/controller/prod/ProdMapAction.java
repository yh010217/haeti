package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.UserDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdMapAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 세션에서 user 정보 받기
        String user_id = "test2";

        // user의 관심지역 받아오기

        UserDTO user_dto = new UserDTO();

        UserService user_service = UserService.getUserService();
        user_dto = user_service.getUserInfo(user_id);

        String fav_region = user_dto.getFav_region();

        // 관심지역에 올라온 매물 검색하기

        ProdService prod_service = ProdService.getInstance();
        List<ProdDTO> list = prod_service.getRegionList(fav_region);


        // 물건들 좌표에 담아서 jsp로 넘기기

        List LatLng = new ArrayList<>();
        LatLng = prod_service.getLatLng(fav_region);


        request.setAttribute("fav_region", fav_region);
        request.setAttribute("list", list);
        request.setAttribute("LatLng", LatLng);


        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/adminpage/prod_map.jsp");

        return forward;
    }
}
