package com.haeti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {
    private static ImageDAO instance = new ImageDAO();
    private ImageDAO() {}
    public static ImageDAO getInstance() {
        return instance;
    }

    public void createImage(Connection conn, int prod_no, String img_path) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into image (         ");
        sql.append(" prod_no , img_url       )  ");
        sql.append(" values (?, ?);            ");

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, prod_no);
            pstmt.setString(2, img_path);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) try { pstmt.close(); }
            catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public List<String> getImagePaths(Connection conn, int prod_no) {
        List<String> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("   select  img_url      ");
        sql.append("   from image      ");
        sql.append("   where prod_no = ?      ");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, prod_no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("img_url"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) try { rs.close(); }
            catch (Exception e) {
                System.out.println(e);
            }
            if (pstmt != null) try { pstmt.close(); }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        return result;
    }
}
