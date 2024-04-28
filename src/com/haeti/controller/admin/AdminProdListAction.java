package com.haeti.controller.admin;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.UserDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminProdListAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 관리자 세션 확인 꼭 할 것
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");


        String curr = request.getParameter("curr");
        String search = request.getParameter("search");
        String search_txt = request.getParameter("search_txt");

        if(search == null){
            search = "";
        }
        if(search_txt == null){
            search_txt = "";
        }

        int currpage = 1;
        if (curr != null) {
            currpage = Integer.parseInt(curr);
        }

        int pagesize = 30;
        int startrow = (currpage-1)*pagesize;

        ProdService service = ProdService.getInstance();

        int total_data = service.getCount(search, search_txt);
        int block_size = 5;
        int start_page = ((currpage-1)/block_size)*block_size+1;
        int end_page = start_page+block_size-1;
        int total_page = (int)(Math.ceil(total_data/(float)pagesize));

        if(end_page > total_page){
            end_page=total_page;
        }

        List<ProdDTO> list = service.getList(startrow, pagesize, search, search_txt);

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
        forward.setUrl("template.jsp?page=WEB-INF/adminpage/admin_prodlist.jsp");

        return forward;
    }

}
