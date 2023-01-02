package com.kh.jdbc.day02.member.controller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {
	/**
	 * 회원 전체 조회
	 * @return
	 */
	//매개변수, 에 뭘 넣을지 모르겠으면 쿼리문을 먼저 생각해보기
	public List<Member> printAll() {	// SELECT *FROM MEMBER_TBL 
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	
	public Member printOneById(String memberId) {	// SELECT *FROM MEMBER_TBL WHERE MEMBER_ID = ?
		// 쿼리문의 ResultSet이 몇 개인지 생각해보고 리턴형을 적어준다.
		// ResultSet이 1개면 Member / 1개 이상이면 List<Member>
		MemberDAO mDao = new MemberDAO();
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	
	public List<Member> printMembersByName(String memberName) {	// SELECT *FROM MEMBER_TBL WHERE MEMBER_NAME = ?
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectMembersByName(memberName);
		return mList;
	}
	
	public int checkInfo(Member member) {	// SELECT *FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}
	/**
	 * 
	 * @param member
	 */
	public int registerMember(Member member) {	// INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}
	/**
	 * 
	 * @param memberId
	 */
	public int removeMember(String memberId) {	// DELETE FROM MEMBER_TBL  WHERE MEMBER_ID = ?
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(memberId);
		return result;
	}
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int modifyMemberInfo(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateMember(member);
		return result;	//return 0; 으로 두지 않는다! 
	}
}
