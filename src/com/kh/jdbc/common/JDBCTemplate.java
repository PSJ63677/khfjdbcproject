package com.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	
	private static JDBCTemplate instance;
	private static JDBCTemplate Connection;
	
	private JDBCTemplate() {
		try {
			Class.forName(DRIVER_NAME);		// 반드시 해줘야 하는 것이기 때문에
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 연결을 딱 한번만 생성되게 하는 코드
	public static JDBCTemplate getDriverLoad() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);		// 오토 커밋 해제
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 커밋
	public void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	// 롤백
	public void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 연결해제
	public void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
