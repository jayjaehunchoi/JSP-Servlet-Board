package utils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;

public class JdbcTemplate {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
			conn = ds.getConnection();
//			String url = "jdbc:mysql://127.0.0.1/matrip?autoReconnect=true";
//			String id = "root";
//			String pw = "1234";
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection(url, id, pw);
			conn.setAutoCommit(false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static boolean isConnected(Connection conn) {
		boolean check = false;
		try {
			if(conn.isClosed() || conn == null) {
				check = false;
			}else {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public static void close(Connection conn) {
		try {
			if(isConnected(conn)) {
				conn.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(!pstmt.isClosed() || pstmt != null) {
				pstmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(!stmt.isClosed() || stmt != null) {
				stmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(!rs.isClosed() || rs != null) {
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(isConnected(conn)) {
				conn.commit();
			}
			System.out.println("[커밋 완료]");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollBack(Connection conn) {
		try {
			if(isConnected(conn)) {
				conn.rollback();
			}
			System.out.println("롤백 성공");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
