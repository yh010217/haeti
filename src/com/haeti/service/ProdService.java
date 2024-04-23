package com.haeti.service;

import com.haeti.comm.DBConnection;
import com.haeti.dao.ImageDAO;
import com.haeti.dao.ProdDAO;
import com.haeti.dto.ProdDTO;

import java.sql.Connection;
import java.util.List;

import com.haeti.comm.DBConnection;
import com.haeti.dao.ProdDAO;
import com.haeti.dto.ProdDTO;

import javax.naming.NamingException;
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
        ProdDAO dao = ProdDAO.getProdDAO();
        try {
            conn = db.getConnection();
            result = dao.getNextProdNum(conn);
        } catch (Exception e) {
            System.out.println(e);
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

    public List<ProdDTO> getList() {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<ProdDTO> list = new ArrayList<>();
        try{
            conn=db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getList(conn);

        }catch (SQLException | NamingException e){
            System.out.println("ProdService getList exception");
        }finally {
            db.disconn(conn);
        }
        return list;
    }
}
