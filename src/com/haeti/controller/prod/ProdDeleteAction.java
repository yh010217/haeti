package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProdDeleteAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int prod_no = Integer.parseInt(request.getParameter("prod_no"));

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        ProdService service = ProdService.getInstance();
        int user_no = Integer.parseInt(service.getNoRegion(user_id)[0]);

        int seller_user_no = service.prodDetail(prod_no).getSeller_user_no();
        if (user_no != seller_user_no && !"admin".equals(user_id)) {
            Forward forward = new Forward();
            forward.setForward(false);
            forward.setUrl("index.do");
            return forward;
        }

        String uploadPath = request.getServletContext().getRealPath("upload") + "/" + prod_no;

        service.deleteProd(prod_no,uploadPath);

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("index.do");
        return forward;
    }
}
