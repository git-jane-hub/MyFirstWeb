package kr.co.ictedu.board.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
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
		return dao;
	}
}
