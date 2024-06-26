package com.haeti.dao;

import com.haeti.dto.RegionDTO;
import com.haeti.dto.UserDTO;

import javax.swing.plaf.synth.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static UserDAO userDAO=new UserDAO();
    public static UserDAO getUserDAO(){
        return userDAO;
    }
    private UserDAO(){}


    public UserDTO getUserInfo(Connection conn, String user_id) throws SQLException {
        StringBuilder sql=new StringBuilder();
        sql.append("  select  user_no                    ");
        sql.append("         , user_id                   ");
        sql.append("         , pwd                       ");
        sql.append("         , name                      ");
        sql.append("         , nick_name                 ");
        sql.append("         , tel                       ");
        sql.append("         , email                     ");
        sql.append("         , join_date                 ");
        sql.append("         , teacher_school            ");
        sql.append("         , addr_dong                 ");
        sql.append("         , addr_detail               ");
        sql.append("         , fav_region                ");
        sql.append("  from user                          ");
        sql.append("  where user_id  =  ?                ");

        ResultSet rs=null;
        UserDTO userDTO=new UserDTO();
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setString(1, user_id);
            rs= pstmt.executeQuery();
            if(rs.next()){
                userDTO.setUser_no(rs.getInt("user_no"));
                userDTO.setUser_id(rs.getString("user_id"));
                userDTO.setPwd(rs.getString("pwd"));
                userDTO.setName(rs.getString("name"));
                userDTO.setNick_name(rs.getString("nick_name"));
                userDTO.setTel(rs.getString("tel"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setJoin_date(rs.getDate("join_date").toLocalDate());
                userDTO.setTeacher_school(rs.getString("teacher_school"));
                userDTO.setAddr_dong(rs.getString("addr_dong"));
                userDTO.setAddr_detail(rs.getString("addr_detail"));
                userDTO.setFav_region(rs.getString("fav_region"));
            }
        }finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }
        return userDTO;
    }

    public int modifyUser(Connection conn, UserDTO dto) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  update   user            ");
        sql.append("  set  pwd = ?             ");
        sql.append("      , nick_name = ?      ");
        sql.append("      , addr_dong = ?      ");
        sql.append("      , addr_detail = ?    ");
        sql.append("      , email = ?          ");
        sql.append("      , fav_region = ?     ");
        sql.append("  where user_id = ?        ");

        int modify_result=0;
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setString(1, dto.getPwd());
            pstmt.setString(2, dto.getNick_name());
            pstmt.setString(3, dto.getAddr_dong());
            pstmt.setString(4, dto.getAddr_detail());
            pstmt.setString(5, dto.getEmail());
            pstmt.setString(6, dto.getFav_region());
            pstmt.setString(7, dto.getUser_id());

            pstmt.executeUpdate();
            modify_result=1;
        }
        return modify_result;
    }


    /** 회원가입 */
    public int JoinUser(Connection conn, UserDTO dto) throws SQLException {
        StringBuilder sql=new StringBuilder();
        sql.append(" insert into user(             ");
        sql.append("                    user_id    ");
        sql.append("                  , pwd       ");
        sql.append("                  , name       ");
        sql.append("                  , nick_name  ");
        sql.append("                  , teacher_school ");
        sql.append("                  , tel        ");
        sql.append("                  , email      ");
        sql.append("                 , join_date   ");
        sql.append("                  , addr_dong  ");
        sql.append("                  , addr_detail  ");
        sql.append("                  , fav_region ) ");
        sql.append("     values(  ?,  ?,   ?,  ?, ? , ? ,?, curdate(), ?, ? ,? )           ");
        int join_result=0;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){
            pstmt.setString(1,dto.getUser_id());
            pstmt.setString(2,dto.getPwd());
            pstmt.setString(3,dto.getName());
            pstmt.setString(4,dto.getNick_name());
            pstmt.setString(5,dto.getTeacher_school());
            pstmt.setString(6,dto.getTel());
            pstmt.setString(7,dto.getEmail());
            pstmt.setString(8,dto.getAddr_dong());
            pstmt.setString(9,dto.getAddr_detail());
            pstmt.setString(10,dto.getFav_region());

            pstmt.executeUpdate();
            join_result=1; //성공
        }
        return join_result;
    }
    /**로그인 */
    public int login(Connection conn,String user_id, String pwd) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select     pwd   ");
        sql.append(" from     user      ");
        sql.append(" where    user_id = ?  ");
        int login_result=0;
        ResultSet rs=null;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){

            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if(rs.getString("pwd").equals(pwd)
                ) {
                    login_result=1;
                } else{
                   login_result=0;
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn!=null) try {conn.close();}catch (Exception e){}
            if (rs!=null) try {rs.close();}catch (Exception e){}

        }
        return login_result;
    }

    public String loginemail(Connection conn, String user_id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select     pwd   ");
        sql.append("           , email ");
        sql.append(" from     user      ");
        sql.append(" where    user_id = ?  ");
        String result="";
        ResultSet rs=null;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){

            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                result=rs.getString("email");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn!=null) try {conn.close();}catch (Exception e){}
            if (rs!=null) try {rs.close();}catch (Exception e){}

        }
        return result;

    }


    public UserDTO loginlist(Connection conn, String user_id) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append(" select     pwd          ");
        sql.append("           , name         ");
        sql.append("           ,nick_name      ");
        sql.append("           ,tel            ");
        sql.append("           , email          ");
        sql.append("           , addr_dong      ");
        sql.append("           , addr_detail     ");
        sql.append("           , fav_region     ");
        sql.append("       from  user             ");
        sql.append("      where  user_id = ?      ");
        ResultSet rs=null;
        UserDTO dto=new UserDTO();
        PreparedStatement pstmt=null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, user_id);
            rs=pstmt.executeQuery();
            if (rs.next()){
                dto.setPwd(rs.getString("pwd"));
                dto.setName(rs.getString("name"));
                dto.setNick_name(rs.getString("nick_name"));
                dto.setTel(rs.getString("tel"));
                dto.setEmail(rs.getString("email"));
                dto.setAddr_dong(rs.getString("addr_dong"));
                dto.setAddr_detail(rs.getString("addr_detail"));
                dto.setFav_region(rs.getString("fav_region"));
            }

        }finally {
            if (pstmt!=null) try {pstmt.close();}catch (Exception e){}
        }
        return dto;
    }


    /** 회원 수*/
    public int getCount(Connection conn, String search, String search_txt) throws SQLException{

        StringBuilder sql = new StringBuilder();

        sql.append("   select   count(*)   ");
        sql.append("   from     user   ");

        if(!"".equals(search) && !"".equals(search_txt)){
            if("user_id".equals(search)){
                sql.append("    where  user_id like  ?      ");
            } else if ("name".equals(search)) {
                sql.append("   where  name  like ?       ");
            } else if ("tel".equals(search)) {
                sql.append("   where  tel like ?         ");
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
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }
        return total_data;
    }

    /**회원 목록 가져오기*/
    public List<UserDTO> getList(Connection conn, int startrow, int pagesize, String search, String search_txt)
            throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("    select        user_no        ");
        sql.append("                  , user_id      ");
        sql.append("                  , name         ");
        sql.append("                  , tel          ");
        sql.append("                  , join_date    ");
        sql.append("    from    user                 ");
        sql.append("    where  user_no <> 19         ");  // 관리자 계정 제외


        if (!"".equals(search) && !"".equals(search_txt)) {
            if ("user_id".equals(search)) {
                sql.append("    and  user_id like   ?    ");
            } else if ("name".equals(search)) {
                sql.append("   and  name  like  ?        ");
            } else if ("tel".equals(search)) {
                sql.append("   and  tel like ?           ");
            }
        }
        sql.append("  order by user_no DESC                ");
        sql.append("  limit   ?,  ?                        ");

        ResultSet rs = null;
        ArrayList<UserDTO> list = new ArrayList<>();

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

            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setUser_no(rs.getInt("user_no"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setName(rs.getString("name"));
                dto.setTel(rs.getString("tel"));
                dto.setJoin_date(rs.getDate("join_date").toLocalDate());
                list.add(dto);
            }

        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    /**내 정보 수정: 닉네임 중복체크*/
    public boolean nickCheck(Connection conn, String nick_name, String user_id) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  select nick_name      ");
        sql.append("  from user             ");
        sql.append("  where nick_name = ?   ");
        sql.append("  and user_id <> ?      ");

        ResultSet rs=null;
        boolean result=false; // 아이디 중복X
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setString(1, nick_name);
            pstmt.setString(2, user_id);
            rs=pstmt.executeQuery();
            result=rs.next();
        }
        return result;
    }

    /**내 정보 수정: 이메일 중복체크*/
    public boolean emailCheck(Connection conn, String email, String user_id) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  select email          ");
        sql.append("  from user             ");
        sql.append("  where email = ?       ");
        sql.append("  and user_id <> ?      ");

        ResultSet rs=null;
        boolean result=false; // 아이디 중복X
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setString(1, email);
            pstmt.setString(2, user_id);
            rs=pstmt.executeQuery();
            result=rs.next();
        }
        return result;
    }
    /**  회원가입용 아이디 체크 */
    public boolean useridCheck(Connection conn, String user_id) throws  SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("    select   user_id    ");
        sql.append("   from user            ");
        sql.append("    where  user_id = ?   ");
        ResultSet rs=null;
        boolean result=false;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
//            System.out.println(user_id+"id1");
            pstmt.setString(1, user_id);
            rs=pstmt.executeQuery();
            result=rs.next();
         }
        return result;
    }

  /** 회원가입용 닉네임 체크*/
    public boolean usernickCheck(Connection conn, String nick_name) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("   select nick_name    ");
        sql.append("    from user         ");
        sql.append("  where nick_name  =  ? ");
        ResultSet rs=null;
        boolean result=false;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
            pstmt.setString(1,nick_name);
            rs=pstmt.executeQuery();
            result=rs.next();
        }
        return result;
    }
    /** 회원가입용 이메일체크*/
    public boolean useremailCheck(Connection conn, String email) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  select email   ");
        sql.append("   from user     ");
        sql.append(" where  email = ? ");
        ResultSet rs=null;
        boolean result=false;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
            pstmt.setString(1,email);
            rs=pstmt.executeQuery();
            result=rs.next();
        }
        return result ;
    }


//    public boolean joinIdCheck(Connection conn, String user_id) throws  SQLException{
//        StringBuilder sql=new StringBuilder();
//        sql.append(" select   user_id    ");
//        sql.append("          ,nick_ name  ");
//        sql.append("          , email       ");
//        sql.append("       from user         ");
//        sql.append("    where  user_id = ?   ");
//        boolean join_result=false;
//        ResultSet rs=null;
//        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
//            pstmt.setString(1, user_id);
//            rs=pstmt.executeQuery();
//            while (rs.next()) join_result=true; // 해당 아이디 존재
//        }
//        return  join_result;
//    }

    /**유저 관심지역과 좌표 가져오기*/
    public RegionDTO getFavRegion(Connection conn, String user_id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("  select u.fav_region                      ");
        sql.append("         , c.lat                           ");
        sql.append("         , c.lng                           ");
        sql.append("  from user u                              ");
        sql.append("       left outer join coordinate c        ");
        sql.append("       on u.fav_region = c.eup_myeun_dong  ");
        sql.append("  where u.user_id = ?                      ");

        ResultSet rs = null;
        RegionDTO dto = new RegionDTO();

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            pstmt.setString(1, user_id);
            rs=pstmt.executeQuery();

            while (rs.next()) {
                dto.setEup_myeun_dong(rs.getString("u.fav_region"));
                dto.setLat(rs.getFloat("c.lat"));
                dto.setLng(rs.getFloat("c.lng"));
            }
        } finally {
            if(rs!=null) try{rs.close();} catch (Exception e){}
        }
        return dto;
    }

}

