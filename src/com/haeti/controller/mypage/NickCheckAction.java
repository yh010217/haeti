package com.haeti.controller.mypage;

import com.haeti.dto.ProdDTO;
import com.haeti.dto.UserDTO;
import com.haeti.service.ProdService;
import com.haeti.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "NickCheckAction", value = "/nick_name_check")
public class NickCheckAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        String nick_name=request.getParameter("nick_name");

        HttpSession session=request.getSession();
        String user_id= (String) session.getAttribute("user_id");

        UserService userService= UserService.getUserService();

        boolean result=userService.nickCheck(nick_name, user_id);

        JSONArray arr=new JSONArray();
        JSONObject o1=new JSONObject();

        o1.put("result", result);
        arr.add(o1);

        PrintWriter out=response.getWriter();
        out.print(arr);


    }
}