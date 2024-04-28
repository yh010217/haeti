package com.haeti.controller.mypage;

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

@WebServlet(name = "EmailCheckAction", value = "/email_check")
public class EmailCheckAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");

        String email=request.getParameter("email");

        HttpSession session=request.getSession();
        String user_id= (String) session.getAttribute("user_id");

        UserService userService= UserService.getUserService();

        boolean result=userService.emailCheck(email, user_id);

        String email_result="";
        if(result){
            email_result="true";
        }else if(email==null || "".equals(email)){
            email_result="null";
        }else{
            email_result="false";
        }

        PrintWriter out=response.getWriter();
        out.print(email_result);

    }
}