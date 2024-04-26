package com.haeti.controller.login;

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

import static java.lang.System.out;


@WebServlet(name="UserIdCheckAction",value = "/user_id_check")
public class UserIdCheckAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doReq(request,response);
    }
    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        response.setContentType("text/plain;charset=utf-8");

        String user_id=request.getParameter("user_id");

        //System.out.println("user_id22"+user_id);

        UserService userService=UserService.getUserService();

        //System.out.println("user_id"+user_id);

        boolean result=userService.useridCheck(user_id);
        //System.out.println(result+"hello44444");
        String name1="";

        if (result == true){
          name1="중복된 아이디입니다.";
        }else {
            name1="사용가능한 아이디입니다.";
        }



        PrintWriter out=response.getWriter();
        out.print(name1);



    }
}