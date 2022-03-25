package com.ssafy.guestbook.model.service;

import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;

public interface GuestBookService {

	void registerArticle(GuestBookDto guestBookDto) throws Exception;
	List<GuestBookDto> listArticle() throws Exception;
	
//	구현해 보세요!!!
//	void updateArticle(GuestBookDto guestBookDto) throws Exception;
//	void deleteArticle(int articleNo) throws Exception;
	
}
