package com.haeti.controller.prod;

import com.haeti.dao.ProdDAO;
import com.haeti.dto.RegionDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProdMapList", value = "/prodmapmarker")
public class ProdMapList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }
    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 맵 표시용 json 만들기

        ProdService service = ProdService.getInstance();
        List<RegionDTO> list = service.getProdCoord();

        JSONArray arr=new JSONArray();
        for(RegionDTO dto:list)
        {
            JSONObject o=new JSONObject();
            o.put("prod_no", dto.getProd_no());
            o.put("lat", dto.getLat());
            o.put("lng", dto.getLng());
            arr.add(o);
        }
        response.setContentType("text/json; charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(arr);

    }
}
