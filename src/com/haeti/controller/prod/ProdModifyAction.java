package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProdModifyAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_id = (String) request.getSession().getAttribute("user_id");

        ProdService service = ProdService.getInstance();

        int user_no = Integer.parseInt(service.getNoRegion(user_id)[0]);

        int prod_no = Integer.parseInt(request.getParameter("prod_no"));
        ProdDTO dto = service.prodDetail(prod_no);

        if (user_no != dto.getSeller_user_no() && !"admin".equals(user_id)) {
            Forward forward = new Forward();
            forward.setForward(false);
            forward.setUrl("index.do");
            return forward;
        }

        request.setAttribute("dto", dto);

        String uploadPath = request.getServletContext().getRealPath("upload") + "\\" + prod_no;


        String region = service.getNoRegion(user_id)[1];
        request.setAttribute("region", region);

        request.setAttribute("path",uploadPath);


        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/prod/prod_modify.jsp");
        return forward;
    }
}
