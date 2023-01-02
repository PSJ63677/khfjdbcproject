package com.kh.jdbc.day03.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberDAO {
	/**
	 * 
	 * @param conn
	 * @return
	 */
	public List<Member> selectAll(Connection conn) { // 회원 전체 정보 조회
		List<Member> mList = null;
		String query = "SELECT * FROM MEMBER_TBL";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();
			// 후처리
			 while(rset.next()) {
				 Member member = new Member();
				 member.setMemberId(rset.getString(1));
				 member.setMemberPwd(rset.getString("MEMBER_PWD"));
				 member.setMemberName(rset.getString("MEMBER_NAME"));
				 member.setMemberAge(rset.getInt("MEMBER_AGE"));
				 member.setMemberGender(rset.getString("MEMBER_GENDER"));
				 member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				 member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				 member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				 member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				 member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
				 mList.add(member);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}
	/**
	 * 회원 정보 등록
	 * @param conn
	 * @param member
	 * @return
	 */
	public int insertMember(Connection conn, Member member) {
		// Class.forName()
		// Connection conn = DriverManager~~~
		int result = 0;
		String sql = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(4, member.getMemberAge());
			pstmt.setString(5, member.getMemberGender());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());
			pstmt.setString(9, member.getMemberHobby());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 회원 정보 수정
	 * @param conn
	 * @param member
	 * @return
	 */
	public int updateMember(Connection conn, Member member) {
		// UPDATE MEMBER_TBL SET MEMBER_PWD = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ? WHERE MEMBER_ID = ? 
		//Class.forName(DRIVER_NAME);
		//Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);		
		String sql = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ? WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberAddress());
			pstmt.setString(5, member.getMemberHobby());
			pstmt.setString(6, member.getMemberId());	// 쿼리문 실행 준비 완료
			result = pstmt.executeUpdate();		// PreparedStatement - ()안에 아무것도 안씀!
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Member selectOneById(Connection conn, String memberId) {
		String sql = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		Member member = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return member;
	}
}
