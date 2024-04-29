package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.RegionDTO;
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

public class ProdMapAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //유저 세션 받아오기
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        if (user_id == null) {
            user_id = "test";
        }


        // 유저의 관심지역 정보 가져오기 - regionDTO

        UserService user_service = UserService.getUserService();
        RegionDTO fav_dto = user_service.getFavRegion(user_id);

        String fav_region = fav_dto.getEup_myeun_dong();
        float fav_lat = fav_dto.getLat();
        float fav_lng = fav_dto.getLng();


        // 관심지역과 거리가 가까운 곳에 있는 상품 가져오기

        ProdService prod_service = ProdService.getInstance();


        // 페이징 처리
        String curr = request.getParameter("curr");

        if(fav_region == null){
            fav_region = "";
        }

        int currpage = 1;
        if (curr != null) {
            currpage = Integer.parseInt(curr);
        }

        int pagesize = 4;
        int startrow = (currpage-1)*pagesize;


        int total_data = prod_service.getRegionProdCount(fav_region);
        int block_size = 5;
        int start_page = ((currpage-1)/block_size)*block_size+1;
        int end_page = start_page+block_size-1;
        int total_page = (int)(Math.ceil(total_data/(float)pagesize));

        if(end_page > total_page){
            end_page=total_page;
        }


        // 판매자의 fav_regionr와 같은 매물 목록 가져오기 - List<ProdDTO>

        List<ProdDTO> list = prod_service.getRegionList(startrow, pagesize, fav_region);


        request.setAttribute("list", list);
        request.setAttribute("fav_region", fav_region);
        request.setAttribute("fav_lat", fav_lat);
        request.setAttribute("fav_lng", fav_lng);


        request.setAttribute("currpage", currpage);
        request.setAttribute("total_page", total_page);
        request.setAttribute("start_page", start_page);
        request.setAttribute("end_page", end_page);
        request.setAttribute("total_data", total_data);




        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/prod/prod_map.jsp");

        return forward;
    }
}
