package com.haeti.service;

import com.haeti.comm.DBConnection;
import com.haeti.dao.ImageDAO;
import com.haeti.dao.ProdDAO;
import com.haeti.dao.UserDAO;
import com.haeti.dto.ProdDTO;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.haeti.comm.DBConnection;
import com.haeti.dao.ProdDAO;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.RegionDTO;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdService {
    private static ProdService instance = new ProdService();
    private ProdService() {}
    public static ProdService getInstance() {
        return instance;
    }

    /** 현재 마지막 prod_no 보다 1 큰 값을 반환 */
    public int getNextProdNum() {
        int result = 0;
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO prodDAO = ProdDAO.getProdDAO();
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            result = prodDAO.getNextProdNum(conn);
            //그냥 가장 큰 숫자를 넘기면 삭제하고 바로 삭제한 사람이 있어서 안되니깐,
            // auto_increment를 이 숫자로 맞춰줘야할 듯
            prodDAO.setAutoIncrement(conn,result);

            conn.commit();
        } catch (Exception e) {
            System.out.println(e);
            try {
                conn.rollback();
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } finally {
            db.disconn(conn);
        }
        return result;
    }

    public void createProd(ProdDTO prod) {
        DBConnection db = DBConnection.getInstance();
        ProdDAO prodDAO = ProdDAO.getProdDAO();
        ImageDAO imageDAO = ImageDAO.getInstance();

        int prod_no = prod.getProd_no();
        List<String> img_paths = prod.getImg_paths();

        Connection conn = null;
        try {
            conn = db.getConnection();

            prodDAO.createProd(conn, prod);

            for(String img_path : img_paths) {
                imageDAO.createImage(conn, prod_no, img_path);
            }

            conn.commit();
        } catch (Exception e) {
            System.out.println(e);
            try{
                conn.rollback();
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } finally {
            db.disconn(conn);
        }
    }

    public ProdDTO prodDetail(int prod_no) {
        ProdDTO result = null;

        DBConnection db = DBConnection.getInstance();
        ProdDAO prodDAO = ProdDAO.getProdDAO();
        ImageDAO imageDAO = ImageDAO.getInstance();

        Connection conn = null;
        try{
            conn = db.getConnection();
            result = prodDAO.prodDetail(conn, prod_no);
            List<String> img_paths = imageDAO.getImagePaths(conn, prod_no);
            result.setImg_paths(img_paths);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
        return result;
    }

    public List<ProdDTO> getList(int startrow, int pagesize, String search, String search_txt) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<ProdDTO> list = new ArrayList<>();
        try{
            conn=db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getList(conn, startrow, pagesize, search, search_txt);


        }catch (SQLException | NamingException e){
            System.out.println("ProdService getList exception"+e.getMessage());
        }finally {
            db.disconn(conn);
        }
        return list;
    }

    public List<ProdDTO> categoryList(int startrow, int pagesize, String search, String search_txt) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<ProdDTO> list = new ArrayList<>();
        try{
            conn=db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getCategoryList(conn, startrow, pagesize, search, search_txt);
        }catch (SQLException | NamingException e){
            System.out.println("ProdService categoryList exception"+e.getMessage());
        }finally {
            db.disconn(conn);
        }
        return list;
    }

    public void deleteProd(int prod_no,String uploadPath) {
        DBConnection db = DBConnection.getInstance();
        ProdDAO prodDAO = ProdDAO.getProdDAO();
        ImageDAO imageDAO = ImageDAO.getInstance();

        Connection conn = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);

            List<String> img_paths = imageDAO.getImagePaths(conn, prod_no);
            fileDelete(img_paths,uploadPath);

            imageDAO.deleteImages(conn, prod_no);
            prodDAO.deleteProd(conn, prod_no);
            conn.commit();
        } catch (Exception e) {
            System.out.println(e);
            try {
                conn.rollback();
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } finally {
            db.disconn(conn);
        }
    }

    public void fileDelete(List<String> img_paths,String uploadPath) {
        for(String img_path : img_paths) {
            img_path = uploadPath + "/" + img_path;
            File file = new File(img_path);
            if(file.exists()) {
                file.delete();
            }
        }
        File file = new File(uploadPath);
        if (file.exists()) {
            file.delete();
        }
    }

    public List<ProdDTO> purchaseList(String period, int user_no){
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        ProdDAO dao=ProdDAO.getProdDAO();
        List<ProdDTO> purchase_list=new ArrayList<>();

        try{
            conn= db.getConnection();
            purchase_list=dao.purchaseList(conn, period, user_no);


        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return purchase_list;
    }

    public String getSellerId(String prod_no) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getProdDAO();
        String seller_id = "";
        try {
            conn = db.getConnection();
            seller_id = dao.getSellerId(conn, prod_no);
        } catch (SQLException | NamingException e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
        return seller_id;
    }

    public List<ProdDTO> salesList(String status, int user_no) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        ProdDAO dao=ProdDAO.getProdDAO();
        List<ProdDTO> sales_list=new ArrayList<>();

        try{
            conn= db.getConnection();
            sales_list=dao.salesList(conn, status, user_no);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return sales_list;
  }
    public int getCount(String search, String search_txt) {

        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        int result = 0;
        try{
            conn=db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            result = dao.getCount(conn, search, search_txt);
        } catch (SQLException | NamingException e){
            System.out.println("ProdService getCount Exception!!"+e.getMessage());
        } finally {
            db.disconn(conn);
        }
        return result;

    }

    /**관심지역의 판매 물품 리스트*/
    public List<ProdDTO> getRegionList(int startrow, int pagesize, String fav_region) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<ProdDTO> list = new ArrayList<>();

        try{
            conn = db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getRegionList(conn, startrow, pagesize, fav_region);

        } catch (SQLException | NamingException e){
            System.out.println("ProdService getRegionList exception"+e.getMessage());
        } finally {
            db.disconn(conn);
        }
        return list;
    }

    public List getLatLng(String fav_region) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List LatLng = new ArrayList();

        try{
            conn = db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            LatLng = dao.getLatLng(conn, fav_region);

        } catch (SQLException | NamingException e){
            System.out.println("ProdService getLatLng exception"+e.getMessage());
        } finally {
            db.disconn(conn);
        }
        return LatLng;
    }
    public void modifyProd(int prod_no, ProdDTO dto, List<String> toUpdate) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO prodDAO = ProdDAO.getProdDAO();
        ImageDAO imageDAO = ImageDAO.getInstance();

        List<String> img_paths = dto.getImg_paths();

        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);

            //imageDAO.deleteImages(conn);
            //얘를 delete 인 애만 해야되는데 걍 다지워버림 그래서 dB에서 사라져있음

            prodDAO.modifyProd(conn, prod_no, dto);

            int updateNum = toUpdate.size();
            for(int i = 0 ; i < updateNum ; i++){
                //update
                imageDAO.updateImage(conn, toUpdate.get(i),img_paths.get(i));
            }
            for(int i = updateNum ; i < img_paths.size() ; i++){
                //단순 create
                imageDAO.createImage(conn,prod_no,img_paths.get(i));
            }

/*
            for(String img_path : img_paths) {
                imageDAO.createImage(conn, prod_no, img_path);
            }
*/
            conn.commit();
        } catch (Exception e) {
            System.out.println(e);
            try {
                conn.rollback();
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } finally {
            db.disconn(conn);
        }

    }

    public void deleteImg(List<String> deleteList) {
        ImageDAO dao = ImageDAO.getInstance();
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        try {
            conn = db.getConnection();
            for(String delete : deleteList) {
                dao.deleteOneImage(conn, delete);
            }
        }catch (SQLException|NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
    }
  
    public ProdDTO salesProd(int user_no) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        ProdDTO prodDTO=new ProdDTO();
        ProdDAO dao=ProdDAO.getProdDAO();

        try{
            conn= db.getConnection();
            prodDTO=dao.salesProd(conn, user_no);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return prodDTO;
    }

    public List<String> chatBuyer_no(int prod_no) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        ProdDAO dao=ProdDAO.getProdDAO();
        List<String> buyer_userList=new ArrayList<>();

        try{
            conn= db.getConnection();
            buyer_userList=dao.chatBuyer_no(conn, prod_no);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return buyer_userList;
  }
    /**관심 지역의 상품 갯수*/
    public int getRegionProdCount(String fav_region) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        int result = 0;
        try{
            conn=db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            result = dao.getRegionProdCount(conn, fav_region);
        } catch (SQLException | NamingException e){
            System.out.println("ProdService getRegionProdCount Exception!!"+e.getMessage());
        } finally {
            db.disconn(conn);
        }
        return result;
    }

    /**거리별 상품 리스트*/
    public List<ProdDTO> getDistanceList(int startrow, int pagesize, float fav_lat, float fav_lng) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<ProdDTO> list = new ArrayList<>();

        try{
            conn = db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getDistanceList(conn, startrow, pagesize, fav_lat, fav_lng);

        } catch (SQLException | NamingException e){
            System.out.println("ProdService getDistanceList exception"+e.getMessage());
        } finally {
            db.disconn(conn);
        }
        return list;



    }

    public List<RegionDTO> getProdCoord() {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<RegionDTO> list = new ArrayList<>();
        try{
            conn = db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getProdCoord(conn);
        } catch (SQLException | NamingException e){
            System.out.println("ProdService getProdCoord exception"+e.getMessage());
        } finally {
            db.disconn(conn);
        }
        return list;
    }

    public String getUserRegion(String user_id){
        DBConnection db = DBConnection.getInstance();
        ProdDAO dao = ProdDAO.getProdDAO();
        Connection conn = null;
        String userRegion = "";
        try{
            conn=db.getConnection();
            userRegion = dao.getUserRegion(conn,user_id);

        }catch (SQLException | NamingException e){
            System.out.println("getUserRegion Exception : " + e);
        }finally {
            db.disconn(conn);
        }
        return userRegion;
    }

    public String[] getNoRegion(String user_id) {
        DBConnection db = DBConnection.getInstance();
        ProdDAO dao = ProdDAO.getProdDAO();
        Connection conn = null;
        String[] noRegion = new String[2];
        try{
            conn=db.getConnection();
            noRegion = dao.getNoRegion(conn,user_id);

        }catch (SQLException | NamingException e){
            System.out.println("getUserRegion Exception : " + e);
        }finally {
            db.disconn(conn);
        }
        return noRegion;
    }

    public void repWrite(String user_id, String prod_no, String repcontent) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getProdDAO();
        try {
            conn = db.getConnection();
            dao.repWrite(conn, user_id, prod_no, repcontent);
        } catch (SQLException | NamingException e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
    }

    public void setCreateStatus(ProdDTO prod) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getProdDAO();
        try {
            conn = db.getConnection();
            dao.setCreateStatus(conn, prod);
        } catch (SQLException | NamingException e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
    }

    public void sellEnd(int prod_no, String buyer) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getProdDAO();
        try {
            conn = db.getConnection();
            dao.sellEnd(conn, prod_no, buyer);
        } catch (SQLException | NamingException e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
    }

    public String getProdStatus(String prod_no) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getProdDAO();
        String status = "";
        try {
            conn = db.getConnection();
            status = dao.getProdStatus(conn, prod_no);
        } catch (SQLException | NamingException e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
        return status;
    }

    public void insertChat(String prod_no, String content, int buyer_no, String sender_id) {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getProdDAO();
        try {
            conn = db.getConnection();
            dao.insertChat(conn, prod_no, content, buyer_no, sender_id);
        } catch (SQLException | NamingException e) {
            System.out.println(e);
        } finally {
            db.disconn(conn);
        }
    }
}
