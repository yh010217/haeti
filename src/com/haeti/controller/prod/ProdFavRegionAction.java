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
import java.io.IOException;
import java.util.List;

public class ProdFavRegionAction implements Action {


    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 세션정보로 수정할 것
        String user_id = "test18";


        // 유저의 관심지역 정보 가져오기 - regionDTO

        UserService user_service = UserService.getUserService();
        RegionDTO fav_dto = user_service.getFavRegion(user_id);


        // 관심지역 판매자의 물품 가져오기

        String fav_region = fav_dto.getEup_myeun_dong();
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

        int pagesize = 30;
        int startrow = (currpage-1)*pagesize;

        int total_data = prod_service.getRegionProdCount(fav_region);
        int block_size = 5;
        int start_page = ((currpage-1)/block_size)*block_size+1;
        int end_page = start_page+block_size-1;
        int total_page = (int)(Math.ceil(total_data/(float)pagesize));

        if(end_page > total_page){
            end_page=total_page;
        }


        // 판매자의 fav_region이 같은 매물 목록 가져오기 - List<ProdDTO>

        List<ProdDTO> list = prod_service.getRegionList(startrow, pagesize, fav_region);



        request.setAttribute("fav_dto", fav_dto);  // 관심지역정보
        request.setAttribute("list", list);  // 매물 목록

        request.setAttribute("currpage", currpage);
        request.setAttribute("total_page", total_page);
        request.setAttribute("start_page", start_page);
        request.setAttribute("end_page", end_page);
        request.setAttribute("total_data", total_data);


        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/prod/index.jsp");


        return forward;
    }
}
