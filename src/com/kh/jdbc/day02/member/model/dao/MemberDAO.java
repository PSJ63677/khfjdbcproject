package com.kh.jdbc.day02.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberDAO {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	
	public List<Member> selectAll() {
		String sql = "SELECT * FROM MEMBER_TBL";
		Member member = null;
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			mList = new ArrayList<Member>();
			while(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
				mList.add(member);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	public int checkLogin(Member member) {
		String query = "SELECT COUNT(*) AS M_COUNT FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());	// 쿼리문 실행 준비
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("M_COUNT");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		String sql = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
//		String sql = "INSERT INTO MEMBER_TBL VALUES("
//				+ "'"+member.getMemberId()+"', "
//						+ "'"+member.getMemberPwd()+"', "
//								+ "'"+member.getMemberName()+"', "
//										+ "'"+member.getMemberGender()+"', "
//												+member.getMemberAge()+", "
//														+ "'"+member.getMemberEmail()+"', "
//																+ "'"+member.getMemberPhone()+"', "
//																		+ "'"+member.getMemberAddress()+"', "
//																				+ "'"+member.getMemberHobby()+"', DEFAULT)";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// PreparedStatement는 셋 스트링은 순서대로 쓰지 않아도 됨☆
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(9, member.getMemberHobby());	// 순서 뒤죽박죽ok
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setInt(5, member.getMemberAge());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());
			result = pstmt.executeUpdate();		// ()안에 sql안들어감!☆
//			Statement stmt = conn.createStatement(); // 쿼리문 실행준비
			// DML(INSERT, UPDATE, DELETE)은 executeUpdate() -> int 반환
//			result = stmt.executeUpdate(sql);				// 쿼리문 실행
//			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteMember(String memberId) {
		String sql = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);	// 여기 아니고
			pstmt.setString(1, memberId);	// 여기가 쿼리문 실행 준비
			result = pstmt.executeUpdate();	// 쿼리문 실행
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
