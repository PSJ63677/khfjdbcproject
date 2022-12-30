package com.kh.jdbc.day02.member.controller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {
	/**
	 * 
	 * @return
	 */
	public List<Member> printAll() {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	public int checkInfo(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}
	/**
	 * 
	 * @param member
	 */
	public int registerMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}
	/**
	 * 
	 * @param memberId
	 */
	public int removeMember(String memberId) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(memberId);
		return result;
	}
}
