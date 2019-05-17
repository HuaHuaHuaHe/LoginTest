package com.np.hth;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.sql.Connection;

public class JDBCUtil {
	
	public static Connection getMysqlConn(String url,String user, String pwd){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(ResultSet rs, Statement ps ,Connection conn) {
		    if(rs != null){
		        try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        }
		    if(ps != null) {
		    	try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		    if(conn != null) {
		    	try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
	}
	
	public static void close(Statement ps ,Connection conn) {
	    if(ps != null) {
	    	try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    if(conn != null) {
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
}
	
	public static void close(Connection conn) {
	    if(conn != null) {
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
}
	
}
