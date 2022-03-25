package com.ssafy.guestbook.model.dao;

import java.sql.*;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	
	private DBUtil dbUtil = DBUtil.getInstance();
	
	private static MemberDao memberDao = new MemberDaoImpl();
	
	private MemberDaoImpl() {}

	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String id) {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(userid) \n");
			loginMember.append("from ssafy_member \n");
			loginMember.append("where userid = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			cnt = 1;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}
	
	@Override
	public void registerMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerMember = new StringBuilder();
			registerMember.append("insert into ssafy_member (userid, username, userpwd, email, joindate) \n");
			registerMember.append("values (?, ?, ?, ?, now())");
			pstmt = conn.prepareStatement(registerMember.toString());
			pstmt.setString(1, memberDto.getUserId());
			pstmt.setString(2, memberDto.getUserName());
			pstmt.setString(3, memberDto.getUserPwd());
			pstmt.setString(4, memberDto.getEmail());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public MemberDto login(String id, String pass) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select userid, username \n");
			loginMember.append("from ssafy_member \n");
			loginMember.append("where userid = ? and userpwd = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("userid"));
				memberDto.setUserName(rs.getString("username"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

}
