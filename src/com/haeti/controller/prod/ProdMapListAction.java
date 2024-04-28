package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProdMapListAction implements Action {

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fav_region = request.getParameter("dong");
        float lat = Float.parseFloat(request.getParameter("lat"));
        float lng = Float.parseFloat(request.getParameter("lng"));


        // 동에 해당하는 매물 목록 가져오기
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

        int pagesize = 6;
        int startrow = (currpage-1)*pagesize;

        int total_data = prod_service.getRegionProdCount(fav_region);
        int block_size = 5;
        int start_page = ((currpage-1)/block_size)*block_size+1;
        int end_page = start_page+block_size-1;
        int total_page = (int)(Math.ceil(total_data/(float)pagesize));

        if(end_page > total_page){
            end_page=total_page;
        }

        // 해당 위치의 매물 목록 가져오기 - List<ProdDTO>

        List<ProdDTO> list = prod_service.getRegionList(startrow, pagesize, fav_region);


        request.setAttribute("list", list);
        request.setAttribute("fav_region", fav_region);
        request.setAttribute("fav_lat", lat);
        request.setAttribute("fav_lng", lng);


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
