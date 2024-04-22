package com.haeti.comm;

import com.haeti.controller.Action;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

@WebServlet(name = "FrontController"
        , value = "*.do"
        , initParams = {@WebInitParam(name = "prop"
        , value = "/WEB-INF/prop.properties")})
public class FrontController extends HttpServlet {
    private Map<String, Action> hm = Collections.synchronizedMap(new HashMap<String, Action>());

    @Override
    public void init(ServletConfig config) throws ServletException {
        String init = config.getInitParameter("prop");
        Properties prop = new Properties();
        FileReader fr = null;

        try {
            String path = config.getServletContext().getRealPath(init);

            fr = new FileReader(path);
            prop.load(fr);


            Enumeration enu = prop.propertyNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                String value = (String) prop.get(key);

                Class libclass =  Class.forName(value);
                Constructor c = libclass.getConstructor();
                Action o =(Action) c.newInstance();
                hm.put(key, o);
            }


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (fr != null) try {
                fr.close();
            } catch (Exception e) {
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    private void doReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath(); // path 정보 받기
        Action act = hm.get(path);
        Forward result = act.execute(req, resp);

        // forward 할 것인지 한번 체크해서 맞으면 forward 아니면 sendredirect
        if (result.isForward()) {
            RequestDispatcher disp = req.getRequestDispatcher(result.getUrl());
            disp.forward(req, resp);
        } else {
            resp.sendRedirect(result.getUrl());
        }
    }
}
