package com.ssafy.guestbook.model.dao;

import java.sql.SQLException;

import com.ssafy.guestbook.model.MemberDto;

public interface MemberDao {

	int idCheck(String id);
	void registerMember(MemberDto memberDto) throws SQLException;
	MemberDto login(String id, String pass) throws SQLException;
	
}
