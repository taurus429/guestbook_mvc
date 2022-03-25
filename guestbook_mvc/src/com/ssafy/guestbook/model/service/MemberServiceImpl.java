package com.ssafy.guestbook.model.service;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.dao.MemberDao;
import com.ssafy.guestbook.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao = MemberDaoImpl.getMemberDao();
	
	private static MemberService memberService = new MemberServiceImpl();
	
	private MemberServiceImpl() {}

	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String id) {
		return memberDao.idCheck(id);
	}
	
	@Override
	public void registerMember(MemberDto memberDto) throws Exception {
		memberDao.registerMember(memberDto);
	}

	@Override
	public MemberDto login(String id, String pass) throws Exception {
		return memberDao.login(id, pass);
	}

}
