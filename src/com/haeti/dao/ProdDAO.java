package com.haeti.dao;

import com.haeti.comm.DBConnection;
import com.haeti.dto.ProdDTO;

import java.security.spec.ECField;
import javax.naming.NamingException;
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

    /** 메인 페이지 전체 상품 목록 가져오기 */
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
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no ORDER BY img_no) AS rn   ");
        sql.append("                         FROM image ) i1     ");
        sql.append("               ON p.prod_no = i1.prod_no     ");
        sql.append("   WHERE rn=1                                ");
        sql.append("   ORDER BY  p.prod_no DESC;         ");

        List<ProdDTO> list = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
             ) {
            rs=pstmt.executeQuery();

            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("i1.img_url"));

                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                dto.setCost(rs.getInt("cost"));
                dto.setImg_paths(img_paths);
                list.add(dto);

            }
        } finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
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

    public List<ProdDTO> getCategoryList(Connection conn, String category) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT p.prod_no              ");
        sql.append("            , title             ");
        sql.append("            , write_date        ");
        sql.append("            , cost              ");
        sql.append("            , i1.img_url        ");
        sql.append("  FROM prod p Inner JOIN        ");
        sql.append("                       ( SELECT img_url      ");
        sql.append("                               , prod_no     ");
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no ORDER BY img_no) AS rn   ");
        sql.append("                         FROM image ) i1     ");
        sql.append("               ON p.prod_no = i1.prod_no     ");
        sql.append("   WHERE rn=1    and    category_id = ?      ");
        sql.append("   ORDER BY  p.prod_no DESC;                 ");

        List<ProdDTO> list = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ) {
            if("chunjae".equals(category)){
                pstmt.setInt(1, 1);
            } else if("jihak".equals(category)){
                pstmt.setInt(1, 2);
            } else if("bisang".equals(category)){
                pstmt.setInt(1, 3);
            } else {
                pstmt.setInt(1, 4);
            }

            rs = pstmt.executeQuery();

            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("i1.img_url"));

                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                dto.setCost(rs.getInt("cost"));
                dto.setImg_paths(img_paths);
                list.add(dto);

            }
        } finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }

        return list;
    }
    public void deleteProd(Connection conn, int prod_no) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from prod where prod_no = ?");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, prod_no);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    /**  기간별 구매내역  */
    public List<ProdDTO> purchaseList(Connection conn, int period) throws SQLException{
        //쿼리문 수정 필요!!
        StringBuilder sql=new StringBuilder();
        sql.append("  select        prod_no                  ");
        sql.append("              , title                    ");
        sql.append("              , content                  ");
        sql.append("              , write_date               ");
        sql.append("              , cost                     ");
        sql.append("              , category_id              ");
        sql.append("  from  prod                             ");
        sql.append("  WHERE DATEDIFF(NOW(),write_date) <= ?   ");

        List<ProdDTO> purchase_list=new ArrayList<>();
        ResultSet rs=null;

        try(PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){
            pstmt.setInt(1, period);
            rs=pstmt.executeQuery();
            while (rs.next()){
                ProdDTO dto=new ProdDTO();
                dto.setProd_no(rs.getInt("prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                dto.setCost(rs.getInt("cost"));
                dto.setCategory_id(rs.getInt("category_id"));
                purchase_list.add(dto);

            }
        }
        return purchase_list;
    }
}
