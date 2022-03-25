package com.ssafy.guestbook.model.service;

import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.dao.GuestBookDao;
import com.ssafy.guestbook.model.dao.GuestBookDaoImpl;

public class GuestBookServiceImpl implements GuestBookService {
	
	private GuestBookDao guestBookDao = GuestBookDaoImpl.getGuestBookDao();
	
	private static GuestBookService guestBookService = new GuestBookServiceImpl();
	
	private GuestBookServiceImpl() {}

	public static GuestBookService getGuestBookService() {
		return guestBookService;
	}

	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws Exception {
		guestBookDao.registerArticle(guestBookDto);
	}

	@Override
	public List<GuestBookDto> listArticle() throws Exception {
		return guestBookDao.listArticle();
	}

}
