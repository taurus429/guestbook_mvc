package com.ssafy.guestbook.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.util.DBUtil;

public class GuestBookDaoImpl implements GuestBookDao {
	
	private DBUtil dbUtil = DBUtil.getInstance();
	
	private static GuestBookDao guestBookDao = new GuestBookDaoImpl();
	
	private GuestBookDaoImpl() {}

	public static GuestBookDao getGuestBookDao() {
		return guestBookDao;
	}

	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("insert into guestbook (userid, subject, content, regtime) \n");
			registerArticle.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setString(1, guestBookDto.getUserId());
			pstmt.setString(2, guestBookDto.getSubject());
			pstmt.setString(3, guestBookDto.getContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<GuestBookDto> listArticle() throws SQLException {
		List<GuestBookDto> list = new ArrayList<GuestBookDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select g.articleno, g.userid, g.subject, g.content, g.regtime, m.username \n");
			listArticle.append("from guestbook g, ssafy_member m \n");
			listArticle.append("where g.userid = m.userid \n");
			listArticle.append("order by g.articleno desc \n");
			pstmt = conn.prepareStatement(listArticle.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookDto guestBookDto = new GuestBookDto();
				guestBookDto.setArticleNo(rs.getInt("articleno"));
				guestBookDto.setUserId(rs.getString("userid"));
				guestBookDto.setUserName(rs.getString("username"));
				guestBookDto.setSubject(rs.getString("subject"));
				guestBookDto.setContent(rs.getString("content"));
				guestBookDto.setRegTime(rs.getString("regtime"));
				
				list.add(guestBookDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

}
