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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdModifyResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int filesize = 1024*1024*100;
        int prod_no = Integer.parseInt(request.getParameter("prod_no"));

        String uploadPath = request.getServletContext().getRealPath("upload") + "\\" + prod_no;


        ProdService service = ProdService.getInstance();
        ProdDTO dto = service.prodDetail(prod_no);

        List<String> beforeImages = dto.getImg_paths();
        //service.fileDelete(beforeImages,uploadPath);




        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) uploadDir.mkdirs();

        MultipartRequest multi = new MultipartRequest(request,uploadPath,filesize,"utf-8"
                ,new DefaultFileRenamePolicy());


        boolean[] remove = new boolean[beforeImages.size()];
        for(int i = 0; i < remove.length ; i++){
            if("remove".equals(multi.getParameter("remove_image"+(i+1)))){
                remove[i] = true;
            }else{
                remove[i] = false;
            }
        }


        List<String> deleteList = new ArrayList<>(); // 단순 삭제
        List<String> toUpdate = new ArrayList<>(); // 지울 친구긴 한데, 나중에 수정될 친구
        List<String> afterImages = new ArrayList<>(); // 수정된 결과인 친구
        String image = "";


        try {
            for (int i = 1; i <= 5; i++) {
                image = multi.getFilesystemName("picture_image"+i);
                //여기서 get 하면서 예외 나오긴 함. 수정 필요
                String bi="";
                if(i <= beforeImages.size()) {
                    bi = beforeImages.get(i - 1);
                }
                String delFile = uploadPath + "/"+bi;
                if( i <= remove.length &&remove[i-1] == true){
                    //있는데 지울 친구들 , 다시 뭔 파일을 주지는 않음
                    deleteList.add(bi); // DB 정보 삭제 보관함
                    deleteImage(delFile); // 파일 삭제

                    //원래는 그냥 지우고 받아온다는 느낌의 그게 있으면 좋을듯
                }else if(!"".equals(bi)
                        && image != null) {
                    //있는데, 수정할 친구들
                    toUpdate.add(bi);
                    afterImages.add(image);
                }
                else if("".equals(bi)
                        && image != null) {
                    //원래 아무것도 없던 친구들인데 생긴 경우
                    afterImages.add(image);
                    //toUpdate 는 굳이 할 필요 없을 듯, 아마 afterImage가 toUpdate보다 사이즈가 크면 거기부터
                    // i 시작해서 돌아다니면 괜춘할듯
                }
                //있는데, 수정 안한것도 필요한가?

                //없는데 또 없는건 아무것도 안해도 괜찮을듯
            }
        } catch (Exception e) {
            System.out.println("file list add exception");
        }


        String title = multi.getParameter("title");
        String content = multi.getParameter("content");
        int cost = Integer.parseInt(multi.getParameter("cost"));
        int category_id = Integer.parseInt(multi.getParameter("category_id"));

        ProdDTO prod = new ProdDTO();
        prod.setTitle(title);
        prod.setContent(content);
        prod.setCost(cost);
        prod.setCategory_id(category_id);
        prod.setImg_paths(afterImages);


        service.deleteImg(deleteList);
        service.modifyProd(prod_no, prod,toUpdate);







        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("prod_detail.do?prod_no="+prod_no);
        return forward;
    }

    private void deleteImage(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }
}
