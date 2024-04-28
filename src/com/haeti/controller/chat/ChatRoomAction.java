package com.haeti.controller.chat;
import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ChatRoomAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 지금은 prod 에서 buyer만 들어가는 듯한 로직인데, 마이페이지에서 들어가는것도 구현해야함
        // 글 작성자가 왔을 때, if 같은거로 하는 로직을 추가해야할 듯

        //어차피 문자열로 들어갈 거 같아서 굳이 int 변환 안했슴
        String prod_no = request.getParameter("prod_no");
        ProdService service = ProdService.getInstance();
        String seller_id = service.getSellerId(prod_no);

        String iam = request.getParameter("iam");
        String buyer_id = request.getParameter("buyer");

        String status = service.getProdStatus(prod_no);
        request.setAttribute("status",status);

        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("user_id");

        //buyer 입장
        PrintWriter writer = response.getWriter();
        if("buyer".equals(iam) && seller_id.equals(user)){
            System.out.println(" Please Access From MyPage ");
            //writer.print("<script> alert('Please Access From your own MyPage'); </script>");

            //마이페이지로 이동시켜주는 리다이렉션도 좋을듯?

            Forward forward = new Forward();
            forward.setForward(false);
            forward.setUrl("index.do");
            return forward;
        }
        //seller 입장
        else if("seller".equals(iam) && !seller_id.equals(user)){
            System.out.println(" CANNOT ACCESS ");
            //writer.print("<script> alert('cannot access'); </script>");
            //그냥 링크 일부로 따고 들어온 나쁜놈임

            Forward forward = new Forward();
            forward.setForward(false);
            forward.setUrl("index.do");
            return forward;
        }else if(user == null || user.equals("")){
            writer.print("<script> alert('Please After Login'); </script>");

            Forward forward = new Forward();
            forward.setForward(false);
            forward.setUrl("index.do");
            return forward;
        }
        else{ // 유일한 정상 작동
            ChatEndPoint.setNowUser(user);
            ChatEndPoint.putUserMap(prod_no,buyer_id);
            ChatEndPoint.setNowRoom(prod_no+"#"+buyer_id);

            Forward forward = new Forward();
            forward.setForward(true);
            forward.setUrl("/WEB-INF/chat/chatting.jsp");
            return forward;
        }

        // prod_no+"#"+buyer

    }
}
