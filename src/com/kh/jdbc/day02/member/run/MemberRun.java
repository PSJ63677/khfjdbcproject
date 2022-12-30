package com.kh.jdbc.day02.member.run;

import java.util.List;

import com.kh.jdbc.day02.member.controller.MemberController;
import com.kh.jdbc.day02.member.model.vo.Member;
import com.kh.jdbc.day02.member.view.MemberView;

public class MemberRun {
	public static void main(String [] args) {
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = null;
		int result = 0;
		String memberId = "";
		goodbye :
		while(true) {
			int choice = mView.mainMenu();
			switch(choice) {
			case 0 : break goodbye;
			case 1 : 
				List<Member> mList = mCon.printAll();
				mView.showAll(mList);
				if(result > 0) {
					mView.displaySuccess("회원 가입 성공");
				} else {
					mView.displayError("회원 가입 실패");
				}
				break;
			case 2 : break;
			case 3 : break;
			case 4 : 
				member = mView.inputMember();
				result = mCon.registerMember(member);
				break;
			case 5 : break;
			case 6 : 
				memberId = mView.inputMemberId("삭제");
				result = mCon.removeMember(memberId);
				if(result > 0) {
					mView.displaySuccess("회원 탈퇴 성공");
				} else {
					mView.displayError("회원 탈퇴 실패");
				}
				break;
			case 7 :
				member = mView.inputLoginInfo();
				result = mCon.checkInfo(member);
				if(result > 0) {
					mView.displaySuccess("로그인 성공");
				} else {
					mView.displayError("일치하는 정보가 존재하지 않습니다.");
				}
				break;
			default : break;
			}
		}
	}
}	
