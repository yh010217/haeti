package com.haeti.dao;

import com.haeti.dto.ProdDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdDAO {
    private static ProdDAO prodDAO=new ProdDAO();
    public static ProdDAO getProdDAO(){
        return prodDAO;
    }
    private ProdDAO(){}

    public List<ProdDTO> getList(Connection conn) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT p.prod_no              ");
        sql.append("            , title             ");
        sql.append("            , write_date        ");
        sql.append("            , cost              ");
        sql.append("            , i1.img_url        ");
        sql.append("  FROM prod p Inner JOIN        ");
        sql.append("                       ( SELECT img_url      ");
        sql.append("                               , prod_no     ");
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no) AS rn   ");
        sql.append("                         FROM image ) i1     ");
        sql.append("               ON p.prod_no = i1.prod_no     ");
        sql.append("   WHERE rn=1                                ");
        sql.append("   ORDER BY p.prod_no DESC;                  ");

        List<ProdDTO> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
             ResultSet rs = pstmt.executeQuery();
        ) {
            if(rs.next()){
                ProdDTO dto = new ProdDTO();
                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                dto.setCost(rs.getInt("cost"));
                dto.set
            }
        }


        return list;
    }

    /** 현재 마지막 prod_no 보다 1 큰 값을 반환 */
    public int getNextProdNum(Connection conn) throws SQLException {
        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("select nvl(max(prod_no),0) as greatest from prod");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("greatest") + 1;
            }
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

    public void createProd(Connection conn, ProdDTO prod) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into prod (          ");
        sql.append("   title                    ");
        sql.append("   ,content                 ");
        sql.append("   ,write_date              ");
        sql.append("   ,cost                    ");
        sql.append("   ,category_id             ");
        sql.append(" ) values (?,?,CURDATE(),?,?) ");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, prod.getTitle());
            pstmt.setString(2, prod.getContent());
            pstmt.setInt(3, prod.getCost());
            pstmt.setInt(4, prod.getCategory_id());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (pstmt != null) try { pstmt.close(); }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ProdDTO prodDetail(Connection conn, int prod_no) {

        ProdDTO result = null;
        StringBuilder sql = new StringBuilder();
        sql.append("    select title                                ");
        sql.append("        ,content                                ");
        sql.append("        ,write_date                             ");
        sql.append("        ,cost                                   ");
        sql.append("        ,c.category as category                 ");
        sql.append("    from prod p  inner join category c          ");
        sql.append("        on p.category_id = c.category_id        ");
        sql.append("    where prod_no = ?       ");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, prod_no);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                result = new ProdDTO();
                result.setProd_no(prod_no);
                result.setTitle(rs.getString("title"));
                result.setContent(rs.getString("content"));
                result.setWrite_date(rs.getDate("write_date").toLocalDate());
                result.setCost(rs.getInt("cost"));
                result.setCategory(rs.getString("category"));

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
