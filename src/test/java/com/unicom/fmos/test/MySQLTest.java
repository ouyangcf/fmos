package com.unicom.fmos.test;

import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.utils.PasswordHelper;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Created by zhaojb on 2017/1/21.
 */
public class MySQLTest {
//    @Test
//    public void mysqlTest() {
//        ResultSet rs = null;
//        Statement stmt = null;
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            //new oracle.jdbc.driver.OracleDriver();
//            conn = DriverManager.getConnection("jdbc:mysql://60.205.231.82:3306/fmos?3useUnicode=true&amp;characterEncoding=utf8", "root", "1214379009@qq.com");
//            String sql = "insert into td_user (role_line_id,user_id,user_name,user_password,create_user,create_time,salt) values(?,?,?,?,?,?,?)";
//            PreparedStatement pstmt;
//            User user = new User();
//            user.setRoleLineId(new BigDecimal(1));
//            user.setUserId("superadmin");
//            user.setUserPassword("123");
//            user.setUserName("超级管理员");
//            user.setCreateUser("system");
//            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            PasswordHelper passwordHelper = new PasswordHelper();
//            passwordHelper.encryptPassword(user);
//            java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
//            int i = 0;
//            try {
//                pstmt = (PreparedStatement) conn.prepareStatement(sql);
//                pstmt.setBigDecimal(1, user.getRoleLineId());
//                pstmt.setString(2, user.getUserId());
//                pstmt.setString(3, user.getUserName());
//                pstmt.setString(4, user.getUserPassword());
//                pstmt.setString(5, user.getCreateUser());
//                pstmt.setDate(6,now);
//                pstmt.setString(7,user.getSalt());
//                i = pstmt.executeUpdate();
//                pstmt.close();
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(rs != null) {
//                    rs.close();
//                    rs = null;
//                }
//                if(stmt != null) {
//                    stmt.close();
//                    stmt = null;
//                }
//                if(conn != null) {
//                    conn.close();
//                    conn = null;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    }
