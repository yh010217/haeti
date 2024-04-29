package com.haeti.service;

import com.haeti.comm.DBConnection;
import com.haeti.dao.UserDAO;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.RegionDTO;
import com.haeti.dto.UserDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService userService=new UserService();
    public static UserService getUserService(){
        return userService;
    }
    private UserService(){}

    public UserDTO getUserInfo(String user_id){
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO userDAO=UserDAO.getUserDAO();
        UserDTO userDTO=new UserDTO();

        try{
            conn= db.getConnection();
            userDTO=userDAO.getUserInfo(conn, user_id);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return userDTO;
    }

    public int modifyUser(UserDTO dto) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO userDAO=UserDAO.getUserDAO();

        int modify_result=0;
        try{
            conn=db.getConnection();
            modify_result=userDAO.modifyUser(conn, dto);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return modify_result;
    }
    /** 회원가입 */
    public int JoinUser(UserDTO dto) {
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        Connection conn=null;

        int join_result=0;
        try {
            conn=db.getConnection();
            join_result=dao.JoinUser(conn,dto);
        }catch (SQLException| NamingException e){
            System.out.println(e);
        }finally {
           db.disconn(conn);
        }
        return join_result;
    }


    public int login(String user_id, String pwd) {
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        Connection conn=null;
        int login_result=0;
        try {
            conn=db.getConnection();
            login_result=dao.login(conn,user_id,pwd);
        }catch (SQLException| NamingException e){
            System.out.println(e);
        }finally {
            if (conn!=null) try {conn.close();}catch (Exception e){System.out.println(e);}
        }
        return login_result;
    }

    public int getCount(String search, String search_txt) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        int result = 0;
        try{
            conn=db.getConnection();
            UserDAO dao = UserDAO.getUserDAO();
            result = dao.getCount(conn, search, search_txt);
        } catch (SQLException | NamingException e){
            System.out.println("userService getCount Exception");
        } finally {
            db.disconn(conn);
        }
        return result;
    }

    public List<UserDTO> getList(int startrow, int pagesize, String search, String search_txt) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        List<UserDTO> list = new ArrayList<>();
        try{
            conn=db.getConnection();
            UserDAO dao = UserDAO.getUserDAO();
            list = dao.getList(conn, startrow, pagesize, search, search_txt);
        }  catch (SQLException | NamingException e){
            System.out.println("userService getList Exception");
        } finally {
            db.disconn(conn);
        }
        return list;
    }

    public UserDTO loginlist(String user_id) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        UserDAO dao=UserDAO.getUserDAO();
        UserDTO dto=new UserDTO();
        try{
            conn=db.getConnection();
            dto=dao.loginlist(conn,user_id);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            if (conn!=null)try {
                conn.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return dto;
    }

    public boolean nickCheck(String nick_name, String user_id) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=true;

        try{
            conn= db.getConnection();
            result=dao.nickCheck(conn, nick_name, user_id);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return result;
    }

    public boolean emailCheck(String email, String user_id) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=true;

        try{
            conn= db.getConnection();
            result=dao.emailCheck(conn, email, user_id);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return result;
    }

    public boolean useridCheck(String user_id) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=false;
        try {
            conn=db.getConnection();
            result=dao.useridCheck(conn,user_id);
            //System.out.println(result+"iddddd");
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return result;
    }


    public boolean usernickCheck(String nick_name) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=false;
        try {
            conn=db.getConnection();
            result=dao.usernickCheck(conn,nick_name);
//            System.out.println(result+"nick_name!!!!!!");
//            System.out.println(result+"nick_name");

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return  result;
    }

    public boolean useremailCheck(String email) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=false;

        try {
            conn=db.getConnection();
            result=dao.useremailCheck(conn,email);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return result;

    }
    public RegionDTO getFavRegion(String user_id) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        RegionDTO dto = null;
        try{
            conn = db.getConnection();
            UserDAO dao = UserDAO.getUserDAO();
            dto = dao.getFavRegion(conn, user_id);

        } catch (SQLException | NamingException e){
            System.out.println("UserService getFavRegion Exception"+e.getMessage());
        }finally {
            db.disconn(conn);
        }
        return dto;
    }

}
