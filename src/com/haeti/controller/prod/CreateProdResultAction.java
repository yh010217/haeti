package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateProdResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        ProdService service = ProdService.getInstance();

        int user_no = Integer.parseInt(service.getNoRegion(user_id)[0]);

        int nextProdNum = service.getNextProdNum();

        String uploadPath = request.getServletContext().getRealPath("upload") + "/" + nextProdNum;

        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        int filesize = 1024*1024*100;
        MultipartRequest multi = new MultipartRequest(request,uploadPath,filesize,"utf-8"
                ,new DefaultFileRenamePolicy());

        ArrayList<String> images = new ArrayList<>();
        String image = "";
        try {
            for (int i = 1; i <= 5; i++) {
                if((image = multi.getFilesystemName("picture_image"+i)) == null) break;
                images.add(image);
                System.out.println(image);
            }
        } catch (Exception e) {
            System.out.println("maybe all");
        }
        String title = multi.getParameter("title");
        String content = multi.getParameter("content");
        int cost = Integer.parseInt(multi.getParameter("cost"));
        int category_id = Integer.parseInt(multi.getParameter("category_id"));

        ProdDTO prod = new ProdDTO();
        prod.setSeller_user_no(user_no);
        prod.setProd_no(nextProdNum);
        prod.setTitle(title);
        prod.setContent(content);
        prod.setCost(cost);
        prod.setCategory_id(category_id);
        prod.setImg_paths(images);

        service.createProd(prod);


        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("prod_detail.do?prod_no="+nextProdNum);
        return forward;
    }
}
