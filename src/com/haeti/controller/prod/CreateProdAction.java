package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateProdAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        ProdService service = ProdService.getInstance();
        //String userRegion = service.getUserRegion(user_id);

        String[] noRegion = service.getNoRegion(user_id);

        //request.setAttribute("region",userRegion);
        request.setAttribute("user_no",noRegion[0]);
        request.setAttribute("region",noRegion[1]);

        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/prod/create_prod.jsp");
        return forward;
    }
}
