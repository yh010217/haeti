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
import java.util.List;

public class ProdFavRegionAction implements Action {


    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 세션정보로 수정할 것
        String user_id = "test2";


        // 유저정보 가져오기 - user_dto / fav_region

        UserService user_service = UserService.getUserService();
        UserDTO user_dto = user_service.getUserInfo(user_id);

        String fav_region = user_dto.getFav_region();

        ProdService prod_service = ProdService.getInstance();



        // 페이징 처리
        String curr = request.getParameter("curr");
        String search = "fav_region";
        String search_txt = fav_region;


        if(search_txt == null){
            search_txt = "";
        }

        int currpage = 1;
        if (curr != null) {
            currpage = Integer.parseInt(curr);
        }

        int pagesize = 30;
        int startrow = (currpage-1)*pagesize;

        int total_data = prod_service.getCount(search, search_txt);
        int block_size = 5;
        int start_page = ((currpage-1)/block_size)*block_size+1;
        int end_page = start_page+block_size-1;
        int total_page = (int)(Math.ceil(total_data/(float)pagesize));

        if(end_page > total_page){
            end_page=total_page;
        }


        // 판매자의 fav_region이 같은 매물 목록 가져오기 - List<ProdDTO>

        List<ProdDTO> list = prod_service.getList(startrow, pagesize, search, search_txt);



        request.setAttribute("user_dto", user_dto);
        request.setAttribute("fav_region", fav_region);
        request.setAttribute("list", list);

        request.setAttribute("currpage", currpage);
        request.setAttribute("total_page", total_page);
        request.setAttribute("start_page", start_page);
        request.setAttribute("end_page", end_page);
        request.setAttribute("search", search);
        request.setAttribute("search_txt", search_txt);
        request.setAttribute("total_data", total_data);


        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/prod/index.jsp");


        return forward;
    }
}
