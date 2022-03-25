package com.ssafy.guestbook.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.GuestBookService;
import com.ssafy.guestbook.model.service.GuestBookServiceImpl;
import com.ssafy.util.DBUtil;

@WebServlet("/article")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DBUtil dbUtil = DBUtil.getInstance();

	private GuestBookService guestBookService = GuestBookServiceImpl.getGuestBookService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		if ("mvregister".equals(act)) {
			response.sendRedirect(request.getContextPath() + "/guestbook/write.jsp");
		} else if ("register".equals(act)) {
			path = registerArticle(request, response);
			// response.sendRedirect(request.getContextPath() + path);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("list".equals(act)) {
			path = listArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private String registerArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if (memberDto != null) {
			GuestBookDto guestBookDto = new GuestBookDto();
			guestBookDto.setUserId(memberDto.getUserId());
			guestBookDto.setSubject(request.getParameter("subject"));
			guestBookDto.setContent(request.getParameter("content"));
			try {
				guestBookService.registerArticle(guestBookDto);
				return "/guestbook/writesuccess.jsp";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", "글 작성중 에러가 발생했습니다.");
				return "/error/error.jsp";
			}
		} else {

			return "/user?act=mvlogin";
		}

	}

	private String listArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if (memberDto != null) {
			List<GuestBookDto> list;
			try {
				list = guestBookService.listArticle();
				request.setAttribute("articles", list);
				return "/guestbook/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글목록 얻기 중 에러가 발생했습니다.");
				return "/error/error.jsp";
			}
		} else {
			request.setAttribute("msg", "글 작성중 에러가 발생했습니다.");
			return "/error/error.jsp";
		}
	}

}
