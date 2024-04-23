package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProdDeleteAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int prod_no = Integer.parseInt(request.getParameter("prod_no"));

        String uploadPath = request.getServletContext().getRealPath("upload") + "/" + prod_no;
        ProdService service = ProdService.getInstance();
        service.deleteProd(prod_no,uploadPath);

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("index.do");
        return forward;
    }
}
