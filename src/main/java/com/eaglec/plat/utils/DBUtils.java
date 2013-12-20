package com.eaglec.plat.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.XAConnection;
import javax.sql.XADataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class DBUtils {
	
	private static String dbUrl = Common.chatDataBaseURL; // 数据 URL

	private static String dbUsername = Common.chatDataBaseUser; // 数据库用户名

	private static String dbPassword = Common.chatDataBasePassword; // 数据库用户密码

	public static XADataSource getXADataSource() {
        MysqlXADataSource dataSource = new MysqlXADataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUser(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
	
    public static XAConnection getXAConnetion(XADataSource dataSource) {
        XAConnection XAConn = null;
        try {
            XAConn = dataSource.getXAConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return XAConn;
    }
    public static Connection getConnection() {
        Connection conn = null;
        XADataSource dataSource = DBUtils.getXADataSource();
        XAConnection xaConn = DBUtils.getXAConnetion(dataSource);
        xaConn.addConnectionEventListener(new ConnectionEventListener() {
            public void connectionClosed(ConnectionEvent event) {
                System.out.println("连接被关闭！");
            }
            public void connectionErrorOccurred(ConnectionEvent event) {
                System.out.println("连接发生错误！");
            }
        });
        try {
            conn = xaConn.getConnection();
            System.out.println("数据库连接创建成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("连接关闭失败");
        }
    }
    
    public static void main(String[] args) {
        Connection conn = DBUtils.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select `uid`, `u_code`, `u_name`, `u_enterprid`, `u_role`, `u_email` from `chat_users` where `u_enterprid`=507 and `u_role`='admin'");
            DBUtils.closeConnection(conn);
            List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("uid"));
				resMap.put("username", rs.getString("u_code"));
				resMap.put("staffname", rs.getString("u_name"));
				resMap.put("eid", rs.getString("u_enterprid"));
				resMap.put("role", rs.getString("u_role"));
				resMap.put("email", rs.getString("u_email"));
				resList.add(resMap);
			}
			System.out.println(resList.toString());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
//        try {   
//            //关闭连接，但物理连接没有关闭，
//            conn.close();
//            //再次获得连接；
//            conn = xaConn.getConnection();
//            Statement statement2=conn.createStatement();
//            statement2.executeUpdate("insert into customer(name) values('syna')");
//        } catch (SQLException e) {
//            System.out.println("关闭连接失败");
//        }
    }
}

