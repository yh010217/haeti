package com.haeti.controller.mypage;

import com.haeti.dto.ProdDTO;
import com.haeti.dto.UserDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PurchaseListResultAction", value = "/purchase_list_result")
public class PurchaseListResultAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    protected void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        HttpSession session=request.getSession();
        String user_id= (String) session.getAttribute("user_id");
        String period=request.getParameter("period");

        UserService userService= UserService.getUserService();
        UserDTO userDTO=userService.getUserInfo(user_id);
        int user_no=userDTO.getUser_no();

        ProdService prodService=ProdService.getInstance();
        List<ProdDTO> purchase_list=prodService.purchaseList(period,user_no);

        JSONArray arr=new JSONArray();

        for(ProdDTO dto:purchase_list){
            JSONObject o1=new JSONObject();
            String img=dto.getImg_paths().get(0);

            o1.put("prod_no", dto.getProd_no());
            o1.put("title",dto.getTitle());
            o1.put("cost",dto.getCost());
            o1.put("sell_date",dto.getSell_date().toString());
            o1.put("img", img);
            o1.put("buyer_id",dto.getBuyer_id());

            arr.add(o1);
        }

        PrintWriter out=response.getWriter();
        out.print(arr);
    }

}