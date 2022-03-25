package com.ssafy.guestbook.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;

public interface GuestBookDao {

	void registerArticle(GuestBookDto guestBookDto) throws SQLException;
	List<GuestBookDto> listArticle() throws SQLException;
	
}