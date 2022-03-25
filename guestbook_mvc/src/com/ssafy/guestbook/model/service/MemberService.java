package com.ssafy.guestbook.model.service;

import com.ssafy.guestbook.model.MemberDto;

public interface MemberService {

	int idCheck(String id);
	void registerMember(MemberDto memberDto) throws Exception;
	MemberDto login(String id, String pass) throws Exception;
	
//	구현해 보세요!!!
//	void updateMember(MemberDto memberDto) throws Exception;
//	void deleteMember(String id) throws Exception;
//	MemberDto infoMember(String id) throws Exception;
	
}
