package com.kh.jdbc.day03.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.common.JDBCTemplate;
import com.kh.jdbc.day03.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberService {
	
//	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
//		jdbcTemplate = JDBCTemplate.getDriverLoad();
		mDao = new MemberDAO();		// 생략하지 말기
	}
	
	public List<Member> selectAll() {
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = new JDBCTemplate();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		List<Member> mList = mDao.selectAll(conn);
//		jdbcTemplate.close(conn);
		return mList;
	}
	
	public Member selectOneById(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = mDao.selectOneById(conn, memberId);
//		jdbcTemplate.close(conn);
		return member;
	}
	
	public List<Member> selectAllByName(String memberName) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> mList = mDao.selectAllByName(conn, memberName);
		return mList;
	}

	public int insertMember(Member member) {
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
//		jdbcTemplate.close(conn);
		return result;
	}
	
	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.updateMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
//		jdbcTemplate.close(conn);
		return result;
	}
	
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.deleteMember(conn, memberId);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
}
