package kr.co.ictedu.board.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public class P02BoardDAO {
	private DataSource ds;
	
	private P02BoardDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static P02BoardDAO dao = new P02BoardDAO();
	
	public static P02BoardDAO getInstance() {
		if(dao == null) {
			dao = new P02BoardDAO();
		}
		return dao;
	}
	
	public int write(P01BoardVO board) {
		// Connection, PreparedStatement 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int result;
		/* 구문 작성 시 bId는 auto_increment이므로 작성하지 않아도 됨
		 * bName, bTitle, bContent는 폼에서 작성된 내용을 입력
		 * bDate는 자동으로 현재 서버시간입력 - now()(오라클에서는 sysdate)
		 * bHit은 자동으로 0입력
		 */
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO jspboard (bname, btitle, bcontent, bdate, bhit) VALUES (?, ?, ?, now(), 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getbName());
			pstmt.setString(2, board.getbTitle());
			pstmt.setString(3, board.getbContent());
			pstmt.executeUpdate();
			result = 1;
		}catch(Exception e){
			System.out.println("에러: " + e);
			result = 0;
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}// end write()
	
	// 전체 게시글의 정보를 DB로부터 받아오는 메서드 
	public List<P01BoardVO> getBoardList() {
		// Connection, PreparedStatement 객체 선언
		List<P01BoardVO> boardList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/* 구문 작성 시 bId는 auto_increment이므로 작성하지 않아도 됨
		 * bName, bTitle, bContent는 폼에서 작성된 내용을 입력
		 * bDate는 자동으로 현재 서버시간입력 - now()(오라클에서는 sysdate)
		 * bHit은 자동으로 0입력
		 */
		try {
			con = ds.getConnection();
			// 새로 작성된 순서대로 위에 위치하려면 글번호 내림차순으로 정렬
			String sql = "SELECT * FROM jspboard ORDER BY bId DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P01BoardVO board = new P01BoardVO();
				board.setbId(rs.getInt("bId"));
				board.setbName(rs.getString("bName"));
				board.setbTitle(rs.getString("bTitle"));
				board.setbContent(rs.getString("bContent"));
				board.setbDate(rs.getTimestamp("bDate"));
				board.setbHit(rs.getInt("bHit"));
				boardList.add(board);
			}
		}catch(Exception e){
			System.out.println("에러: " + e);
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(rs != null && !rs.isClosed()){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return boardList;
	}// end getBoardList
	
	public P01BoardVO getBoardDetail(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		P01BoardVO board = new P01BoardVO();
		try {
			con = ds.getConnection();
			// 새로 작성된 순서대로 위에 위치하려면 글번호 내림차순으로 정렬
			String sql = "SELECT * FROM jspboard WHERE bid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setbId(rs.getInt("bid"));
				board.setbName(rs.getString("bname"));
				board.setbTitle(rs.getString("btitle"));
				board.setbContent(rs.getString("bcontent"));
				board.setbDate(rs.getTimestamp("bdate"));
				board.setbHit(rs.getInt("bhit"));
			}
		}catch(Exception e){
			System.out.println("에러: " + e);
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(rs != null && !rs.isClosed()){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return board;
	}// end getBoardDetail
	
	public int boardDelete(String bId) {
		// Connection, PreparedStatement 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int result;
		/* 구문 작성 시 bId는 auto_increment이므로 작성하지 않아도 됨
		 * bName, bTitle, bContent는 폼에서 작성된 내용을 입력
		 * bDate는 자동으로 현재 서버시간입력 - now()(오라클에서는 sysdate)
		 * bHit은 자동으로 0입력
		 */
		try {
			con = ds.getConnection();
			String sql = "DELETE FROM jspboard WHERE bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			pstmt.executeUpdate();
			result = 1;
		}catch(Exception e){
			System.out.println("에러: " + e);
			result = 0;
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}// end boardDelete()
	
	public int boardUpdate(P01BoardVO board) {
		// Connection, PreparedStatement 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		int result;
		/* 구문 작성 시 bId는 auto_increment이므로 작성하지 않아도 됨
		 * bName, bTitle, bContent는 폼에서 작성된 내용을 입력
		 * bDate는 자동으로 현재 서버시간입력 - now()(오라클에서는 sysdate)
		 * bHit은 자동으로 0입력
		 */
		try {
			con = ds.getConnection();
			String sql = "UPDATE jspboard SET bTitle = ?, bContent = ? WHERE bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getbTitle());
			pstmt.setString(2, board.getbContent());
			pstmt.setInt(3, board.getbId());
			pstmt.executeUpdate();
			result = 1;
		}catch(Exception e){
			System.out.println("에러: " + e);
			result = 0;
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}// end boardUpdate()
}
