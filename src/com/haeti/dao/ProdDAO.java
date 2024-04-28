package com.haeti.dao;

import com.haeti.comm.DBConnection;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.RegionDTO;

import java.security.spec.ECField;
import javax.naming.NamingException;
import javax.swing.*;
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
    public List<ProdDTO> getList(Connection conn, int startrow, int pagesize, String search, String search_txt)
            throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT    p.prod_no           ");
        sql.append("            , p.title           ");
        sql.append("            , p.content         ");
        sql.append("            , u.nick_name            ");
        sql.append("            , c.category        ");
       /* sql.append("            , write_date        ");*/
        sql.append("            , cost              ");
        sql.append("            , i1.img_url        ");
        sql.append("  FROM prod p LEFT OUTER JOIN        ");
        sql.append("                       ( SELECT img_url       ");
        sql.append("                               , prod_no      ");
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no ORDER BY img_no) AS rn   ");
        sql.append("                         FROM image ) i1      ");
        sql.append("               ON p.prod_no = i1.prod_no      ");
        sql.append("               LEFT OUTER JOIN user u               ");
        sql.append("               ON p.seller_user_no = u.user_no      ");
        sql.append("               LEFT OUTER JOIN category c           ");
        sql.append("               ON p.category_id = c.category_id     ");
        sql.append("   WHERE rn=1                                       ");

        if (!"".equals(search) && !"".equals(search_txt)) {
            sql.append("             and                  ");
            if("title".equals(search)){
                sql.append("   (  p.title like   ?    )     ");
            } else if ("content".equals(search)) {
                sql.append("   (  p.content  like  ?   )    ");
            } else if ("nick_name".equals(search)) {
                sql.append("   (  u.nick_name like  ?   )   ");
            } else if ("category".equals(search)) {
                sql.append("   (  c.category like  ?    )   ");
            } 
        }
        sql.append("   ORDER BY  p.prod_no DESC             ");
        sql.append("   limit   ? , ?                        ");

        List<ProdDTO> list = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());

             ) {

            if (!"".equals(search) && !"".equals(search_txt)) {
                pstmt.setString(1, "%" + search_txt + "%");
                pstmt.setInt(2, startrow);
                pstmt.setInt(3, pagesize);
            } else {
                pstmt.setInt(1, startrow);
                pstmt.setInt(2, pagesize);
            }

            rs= pstmt.executeQuery();

            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("i1.img_url"));

                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("p.title"));
               /* dto.setWrite_date(rs.getDate("write_date").toLocalDate());*/
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
    public List<ProdDTO> purchaseList(Connection conn, String period, int user_no) throws SQLException{
        //쿼리문 수정 필요!!
        StringBuilder sql=new StringBuilder();
        sql.append("  select        t.prod_no                 ");
        sql.append("              , title                     ");
        sql.append("              , cost                      ");
        sql.append("              , sell_date                 ");
        sql.append("              , img_url                   ");
        sql.append("              , user_id                   ");
        sql.append("  from  prod p left join trade t          ");
        sql.append("  on p.prod_no = t.prod_no                ");
        sql.append("  left join image i                       ");
        sql.append("  on p.prod_no = i.prod_no                ");
        sql.append("  left join user u                        ");
        sql.append("  on u.user_no = t.buyer_user_no          ");
        if("week".equals(period)){
            sql.append("  where sell_date >= ( DATE_ADD(curdate(), interval -1 week ))  ");
        }else if("month".equals(period)){
            sql.append("  where sell_date >= ( DATE_ADD(curdate(), interval -1 month )) ");
        }else if("3month".equals(period)){
            sql.append("  where sell_date >= ( DATE_ADD(curdate(), interval -3 month )) ");
        }
        sql.append("  and buyer_user_no = ?                   ");
        sql.append("  order by sell_date desc                 ");

        List<ProdDTO> purchase_list=new ArrayList<>();
        ResultSet rs=null;

        try(PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){
            pstmt.setInt(1, user_no);
            rs=pstmt.executeQuery();
            while (rs.next()){
                ProdDTO dto=new ProdDTO();
                dto.setProd_no(rs.getInt("t.prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setCost(rs.getInt("cost"));
                dto.setSell_date((rs.getDate("sell_date").toLocalDate()));
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("img_url"));
                dto.setImg_paths(img_paths);
                dto.setBuyer_id(rs.getString("user_id"));

                purchase_list.add(dto);
            }
        }
        return purchase_list;
    }

    public String getSellerId(Connection conn, String prod_no) {
        StringBuilder sql = new StringBuilder();
        sql.append("    select user_id                                ");
        sql.append("    from user u inner join prod p                 ");
        sql.append("                on u.user_no = p.seller_user_no   ");
        sql.append("    where prod_no =  ? ");
        String seller_id = "";
        ResultSet rs = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ) {
            pstmt.setString(1, prod_no);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                seller_id = rs.getString("user_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return seller_id;
    }

    /**  상태별 판매내역  */
    public List<ProdDTO> salesList(Connection conn, String status, int user_no) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  select t.prod_no                      ");
        sql.append("         , title                      ");
        sql.append("         , cost                       ");
        sql.append("         , write_date                 ");
        sql.append("         , img_url                    ");
        sql.append("  from prod p left join trade t       ");
        sql.append("  on p.prod_no = t.prod_no            ");
        sql.append("  left join image i                   ");
        sql.append("  on p.prod_no=i.prod_no              ");
        sql.append("  where p.seller_user_no = ?          ");
        sql.append("  and t.status = ?                    ");
        sql.append("  group by trade_id                   ");
        sql.append("  order by write_date                 ");

        List<ProdDTO> sales_list=new ArrayList<>();
        ResultSet rs=null;
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setInt(1, user_no);
            pstmt.setString(2, status);
            rs= pstmt.executeQuery();
            while (rs.next()){
                ProdDTO dto=new ProdDTO();
                dto.setProd_no(rs.getInt("t.prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setCost(rs.getInt("cost"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("img_url"));
                dto.setImg_paths(img_paths);

                sales_list.add(dto);
            }
        }
        return sales_list;
    }
    public int getCount(Connection conn, String search, String search_txt) throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("   select   count(*)   ");
        sql.append("   FROM prod p left outer join user u   ");
        sql.append("               ON p.seller_user_no = u.user_no   ");
        sql.append("               left outer join category c   ");
        sql.append("               ON p.category_id = c.category_id   ");

        if(!"".equals(search) && !"".equals(search_txt)){
            if("title".equals(search)){
                sql.append("    where  p.title like  ?      ");
            } else if ("content".equals(search)) {
                sql.append("    where  p.content  like ?       ");
            } else if ("nick_name".equals(search)) {
                sql.append("    where  u.nick_name like ?         ");
            } else if ("category".equals(search)) {
                sql.append("    where  c.category like ?         ");
            }
        }

        int total_data = 0;
        ResultSet rs =null;

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            if(!"".equals(search) && !"".equals(search_txt)){
                pstmt.setString(1, "%"+search_txt+"%");
            }
            rs = pstmt.executeQuery();

            if(rs.next()){
                total_data = rs.getInt(1);
            }
        } finally {
            if(rs!=null) try{rs.close();} catch(Exception e){}
        }
        return total_data;

    }

    /**관심지역의 판매 물품*/
    public List<ProdDTO> getRegionList(Connection conn, int startrow, int pagesize, String fav_region) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT p.prod_no              ");
        sql.append("            , p.title           ");
        /*sql.append("            , write_date        ");*/
        sql.append("            , p.cost              ");
        sql.append("            , i1.img_url        ");
        sql.append("  FROM prod p LEFT OUTER JOIN                   ");
        sql.append("                       ( SELECT img_url         ");
        sql.append("                               , prod_no        ");
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no ORDER BY img_no) AS rn   ");
        sql.append("                         FROM image ) i1        ");
        sql.append("               ON p.prod_no = i1.prod_no        ");
        sql.append("               LEFT OUTER JOIN user u           ");
        sql.append("               on p.seller_user_no = u.user_no  ");
        sql.append("   WHERE  u.fav_region =   ?                    ");
      /*  sql.append("          and    rn = 1                         ");  // 이미지 없으면 오류!!*/
        sql.append("   ORDER BY  p.prod_no DESC                     ");
        sql.append("   limit   ? , ?                                ");

        List<ProdDTO> list = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            pstmt.setString(1, fav_region);
            pstmt.setInt(2, startrow);
            pstmt.setInt(3, pagesize);
            rs= pstmt.executeQuery();

            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("i1.img_url"));

                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("p.title"));
                /*dto.setWrite_date(rs.getDate("write_date").toLocalDate());*/
                dto.setCost(rs.getInt("p.cost"));
                dto.setImg_paths(img_paths);
                list.add(dto);

            }
        }finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }
        return list;
    }

    public List getLatLng(Connection conn, String fav_region) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT lat                          ");
        sql.append("       , lng                          ");
        sql.append("  From coordinate                     ");
        sql.append("  WHERE eup_myeun_dong LIKE ?         ");

        ResultSet rs = null;
        List latlng = new ArrayList();

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ) {
            pstmt.setString(1, "%" + fav_region + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                latlng.add(rs.getFloat("lat"));
                latlng.add(rs.getFloat("lng"));
            }
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
            }
        }
        return latlng;
    }
  
    public void modifyProd(Connection conn, int prod_no, ProdDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("update prod set title = ?  ");
        sql.append("                ,content=? ");
        sql.append("                ,cost=?    ");
        sql.append("         where prod_no = ? ");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setInt(3, dto.getCost());
            //pstmt.setInt(4, dto.getCategory_id());
            pstmt.setInt(4, prod_no);
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
    public ProdDTO salesProd(Connection conn, int user_no) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  select p.prod_no                    ");
        sql.append("         , title                      ");
        sql.append("         , cost                       ");
        sql.append("         , write_date                 ");
        sql.append("         , img_url                    ");
        sql.append("  from prod p left join trade t       ");
        sql.append("  on p.prod_no = t.prod_no            ");
        sql.append("  left join image i                   ");
        sql.append("  on p.prod_no = i.prod_no            ");
        sql.append("  where p.seller_user_no = ?          ");
        sql.append("  and t.status = ?                    ");
        sql.append("  group by trade_id                   ");
        sql.append("  order by write_date                 ");
        sql.append("  limit 1                             ");

        ResultSet rs=null;
        ProdDTO prodDTO=new ProdDTO();
        try(PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
            pstmt.setInt(1, user_no);
            pstmt.setString(2, "판매중");
            rs= pstmt.executeQuery();

            if(rs.next()){
                prodDTO.setProd_no(rs.getInt("p.prod_no"));
                prodDTO.setTitle(rs.getString("title"));
                prodDTO.setCost(rs.getInt("cost"));
                prodDTO.setWrite_date(rs.getDate("write_date").toLocalDate());
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("img_url"));
                prodDTO.setImg_paths(img_paths);

            }
        }
        return prodDTO;
    }

    public List<String> chatBuyer_no(Connection conn, int prod_no) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  select user_id                     ");
        sql.append("  from user u inner join chat c      ");
        sql.append("  on u.user_no = c.buyer_no          ");
        sql.append("  where c.prod_no = ?                ");

        List<String> buyer_userList=new ArrayList<>();
        ResultSet rs=null;
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setInt(1, prod_no);
            rs= pstmt.executeQuery();

            while (rs.next()){
                buyer_userList.add(rs.getString("user_id"));
            }
        }
        return buyer_userList;
  }
    public int getRegionProdCount(Connection conn, String fav_region) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("   select count(*)                               ");
        sql.append("   FROM prod p left outer join user u            ");
        sql.append("               ON p.seller_user_no = u.user_no   ");
        sql.append("   where  u.fav_region = ?                       ");

        int total_data = 0;
        ResultSet rs =null;

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            pstmt.setString(1, fav_region);

            rs = pstmt.executeQuery();

            if(rs.next()){
                total_data = rs.getInt(1);
            }
        } finally {
            if(rs!=null) try{rs.close();} catch(Exception e){}
        }
        return total_data;
    }

    /**거리별 상품 리스트*/
    public List<ProdDTO> getDistanceList(Connection conn, int startrow, int pagesize, float fav_lat, float fav_lng)
            throws SQLException{

        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT p.prod_no              ");
        sql.append("            , p.title           ");
        /*sql.append("            , write_date        ");*/
        sql.append("            , p.cost              ");
        sql.append("            , i1.img_url        ");
        sql.append("            , SQRT  (power( (ifnull(c.lat, 0) - ? ), 2 ) + power( (ifnull(c.lng, 0) - ? ), 2 ))  AS  distance       ");
        sql.append("  FROM prod p LEFT OUTER JOIN                   ");
        sql.append("                       ( SELECT img_url         ");
        sql.append("                               , prod_no        ");
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no ORDER BY img_no) AS rn   ");
        sql.append("                         FROM image ) i1           ");
        sql.append("               ON p.prod_no = i1.prod_no           ");
        sql.append("               LEFT OUTER JOIN user u              ");
        sql.append("               on p.seller_user_no = u.user_no     ");
        sql.append("               LEFT OUTER JOIN coordinate c        ");
        sql.append("               on u.fav_region = c.eup_myeun_dong  ");
        /*  sql.append("   WHERE          rn = 1                         ");  // 이미지 없으면 오류!!*/
        sql.append("   ORDER BY  distance,  p.prod_no DESC             ");
        sql.append("   limit   ? , ?                                   ");

        List<ProdDTO> list = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            pstmt.setFloat(1, fav_lat);
            pstmt.setFloat(2, fav_lng);
            pstmt.setInt(3, startrow);
            pstmt.setInt(4, pagesize);
            rs= pstmt.executeQuery();

            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                List<String> img_paths=new ArrayList<>();
                img_paths.add(rs.getString("i1.img_url"));

                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("p.title"));
                /*dto.setWrite_date(rs.getDate("write_date").toLocalDate());*/
                dto.setCost(rs.getInt("p.cost"));
                dto.setImg_paths(img_paths);
                list.add(dto);

            }
        }finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }
        return list;

    }


    public List<RegionDTO> getProdCoord(Connection conn) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  select prod_no         ");
        sql.append("        , fav_region     ");
        sql.append("        , lat            ");
        sql.append("        , lng            ");
        sql.append("   from prod p left outer join user u           ");
        sql.append("        on p.seller_user_no = u.user_no         ");
        sql.append("        left outer join coordinate c            ");
        sql.append("        on u.fav_region = c.eup_myeun_dong      ");

        ResultSet rs = null;
        List<RegionDTO> list = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            rs = pstmt.executeQuery();

            while (rs.next()){
                RegionDTO dto = new RegionDTO();
                dto.setProd_no(rs.getInt("prod_no"));
                dto.setEup_myeun_dong(rs.getString("fav_region"));
                dto.setLat(rs.getFloat("lat"));
                dto.setLng(rs.getFloat("lng"));
                list.add(dto);
            }

        } finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }

        return list;

    }
}
