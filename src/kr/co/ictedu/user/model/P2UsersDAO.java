package kr.co.ictedu.user.model;

import java.sql.*;	
import java.util.*;
// 커넥션 풀 - privte Datasource
import javax.sql.DataSource;

// 커넥션 풀 try ~ catch
import javax.naming.*;

/* DAO 클래스
 * 데이터 베이스의 테이블과 웹 페이지를 연결하는 것으로 하나의 테이블마다 하나의 DAO 클래스를 작성
 * 테이블로부터 데이터를 가져와 자바 객체로 변환 - 테이블 값과 매핑되는 값을 갖는 자바빈 클래스가 필요: P1USersVO.java
 */
public class P2UsersDAO {
	
	// DB 주소 아이디, 패스워드 미리 저장
	// 일반 DAO 활용 시 사용하던 변수
//	private static final String URL = "jdbc:mysql://localhost:3306/ict03";
//	private static final String DBID = "root";
//	private static final String DBPW = "mysql";
	
	// 커넥션 풀 설정 이후 사용하는 변수
	private DataSource ds;
	
	// 메서드 결과에 따른 리턴 값을 상수로 작성
	private static final int SUCCESS = 1;
	private static final int FAIL = 0;
	
	/* DAO 클래스는 하나의 객체만으로도 DB연동을 수행할 수 있기 때문에 메모리 관리 차원에서
	 * 클래스의 객체를 단 1개만 생성하도록 싱글톤 패턴을 적용하여 클래스를 디자인 
	 */
	/* 싱글톤 패턴 클래스 디자인 방법
	 * 1. 외부에서 객체를 new 키워드로 만들어 쓸 수 없도록 생성자에 private을 작성 (생성자에)
	 */
	private P2UsersDAO() {
		// 일반 JDBC에서 활용하던 드라이버 설정 코드
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		// 커넥션 풀에서 사용하는 드라이버 설정 코드
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* 2. 외부에서 객체 생성을 못하기 때문에 자기 클래스 내부에서 자기자신의 객체를 생성 (해당 객체는 하나만 존재 ), 
	 * P02UsersDAO라는 객체에 dao 변수 
	 */
	private static P2UsersDAO dao = new P2UsersDAO();
	
	/* 3. 외부에서 객체 생성이 필요할 때, public으로 선언한 getter를 이용해 2에서 만든 객체를 리턴
	 * P02UsersDAO는 참조형 변수이기 때문에 주소값만 전달 
	 */
	public static P2UsersDAO getInstance() {
		return dao;
	}
// 회원 가입	
	/* 회원가입을 처리하는 메서드 선언
	 * DB에 들어가는 데이터 혹은 DB에서 출력되어 나오는 데이터 모두가 P01UsersVO 내부자료 형식을 기준으로 입출력이 진행됨  
	 */
	public int usersJoin(P1UsersVO user) {
		// 회원가입 창에서 잘라낸 코드를 이곳에 붙여넣기 
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		try{
			// JDBC 기준 DB 접속 코드
//			con = DriverManager.getConnection(URL, DBID, DBPW);
			
			// 커넥션 풀 기준 DB 접속 코드
			con = ds.getConnection();
			
			// 1. 쿼리문 작성
			String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";
			
			// 2. 작성한 쿼리문 ? 자리에 적용할 자바 변수 삽입
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getUpw());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getEmail());
			
			// 3. 만든 쿼리문 실행
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("에러: " + e);
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
		// 위 코드들이 성공적으로 마무리되면 1 리턴 
		return SUCCESS;
	}// end - joinUsers method

// 회원가입 중복 아이디 솔팅
	public int usersJoinDup(P1UsersVO user) {
		// 회원가입 창에서 잘라낸 코드를 이곳에 붙여넣기 
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// JDBC 기준 DB 접속 코드
//			con = DriverManager.getConnection(URL, DBID, DBPW);
			
			// 커넥션 풀 기준 DB 접속 코드
			con = ds.getConnection();
			// 1. 쿼리문 작성
			String sql = "SELECT * FROM users WHERE uid = ?";
			
			// 2. 작성한 쿼리문 ? 자리에 적용할 자바 변수 삽입
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			// 3. 만든 쿼리문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbId = rs.getString("uid");
				if(!dbId.equals(user.getUid())) {
					return SUCCESS;
				}else {
					return FAIL;
				}
			}
			// 3. 만든 쿼리문 실행
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("에러: " + e);
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
		// 위 코드들이 성공적으로 마무리되면 1 리턴 
		return SUCCESS;
	}// end - joinUsers method
	
// 회원 로그인
	public int usersLogin(P1UsersVO user) {
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// JDBC 기준 DB 접속 코드
//			con = DriverManager.getConnection(URL, DBID, DBPW);
			
			// 커넥션 풀 기준 DB 접속 코드
			con = ds.getConnection();	
			// 1. 쿼리문 작성 - uid가 기본키이기 때문에 조건식에 uid로 작성
			String sql = "SELECT * FROM users WHERE uid = ?";
			
			// 2. 작성한 쿼리문 ? 자리에 적용할 자바 변수 삽입
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			// 3. 만든 쿼리문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbId = rs.getString("uid");
				String dbPw = rs.getString("upw");
				if(dbId.equals(user.getUid()) && dbPw.equals(user.getUpw())) {
					return SUCCESS;
				}else {
					return FAIL;
				}
			}
		}catch(SQLException e){
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
		// 상단 try블럭에서 코드가 정상적으로 진행되지 않을 경우 해당 블럭으로 이동해오기 때문에 실행이 제대로 되지 않았다는 0을 리턴
		return FAIL;
	}// end - usersLogin method

	public int usersUpdate(P1UsersVO user) {
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		try{
			// JDBC 기준 DB 접속 코드
//			con = DriverManager.getConnection(URL, DBID, DBPW);
			
			// 커넥션 풀 기준 DB 접속 코드
			con = ds.getConnection();
				// 1. 쿼리문 작성
				String sql = "UPDATE users SET upw = ?, uname = ?, email = ? WHERE uid = ?";
				
				// 2. 작성한 쿼리문 ? 자리에 적용할 자바 변수 삽입
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUpw());
				pstmt.setString(2, user.getUname());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUid());
				
				// 3. 만든 쿼리문 실행
				pstmt.executeUpdate();
				// 위 코드들이 성공적으로 마무리되면 1 리턴 
				return SUCCESS;
			/* else 아래에 비밀번호가 틀린 경우, response를 이용해 로그아웃 페이지로 이동했었지만
			 * DAO 내부에서는 jsp 파일 내장객체를 사용할 수 없으므로 결과 정보만 (계정 삭제 실패 값 0)리턴
			 */
		}catch(SQLException e){
			System.out.println("에러: " + e);
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
		// 상단 try블럭에서 코드가 정상적으로 진행되지 않을 경우 해당 블럭으로 이동해오기 때문에 실행이 제대로 되지 않았다는 0을 리턴
		return FAIL;
	}
// 회원 탈퇴
	/* 원래 대다수의 경우에는 DAO는 P1UsersVO 하나로 모든 처리를 해결할 수 있지만
	 * 삭제 로직은 폼에서 입력받은 비밀번호와 원래 DB에 저장되어있던 비밀번호를 비교해야하기 때문에
	 * '폼에서 입력받은 비밀번호'를 추가로 입력받음
	 */
	public int usersDelete(P1UsersVO user, String dpw) {
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		try{
			if(user.getUpw().equals(dpw)){
				// JDBC 기준 DB 접속 코드
//				con = DriverManager.getConnection(URL, DBID, DBPW);
				
				// 커넥션 풀 기준 DB 접속 코드
				con = ds.getConnection();
				// 1. 쿼리문 작성
				String sql = "DELETE FROM users WHERE uid = ?";
				
				// 2. 작성한 쿼리문 ? 자리에 적용할 자바 변수 삽입
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUid());
				
				// 3. 만든 쿼리문 실행
				pstmt.executeUpdate();
				// 위 코드들이 성공적으로 마무리되면 1 리턴 
				return SUCCESS;
			/* else 아래에 비밀번호가 틀린 경우, response를 이용해 로그아웃 페이지로 이동했었지만
			 * DAO 내부에서는 jsp 파일 내장객체를 사용할 수 없으므로 결과 정보만 (계정 삭제 실패 값 0)리턴
			 */
			}else {
				// 삭제할 계정의 비밀번호가 틀린 경우
				return FAIL;
			}
		}catch(SQLException e){
			System.out.println("에러: " + e);
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
		// 상단 try블럭에서 코드가 정상적으로 진행되지 않을 경우 해당 블럭으로 이동해오기 때문에 실행이 제대로 되지 않았다는 0을 리턴
		return FAIL;
	}// end - usersDelete method
	
// 회원 정보 수정용 조회
	/* 회원 정보를 수정하기 전에 수정할 사용자의 정보를 얻어오기 위해 사용하는 메서드
	 * 아이디, 비밀번호, 이름, 이메일을 P1UsersVO에 삽입하여 리턴
	 */
	
	// 참조형 반환타입은 주소값을 전달함
	public P1UsersVO getUserInfo(P1UsersVO user) {
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 객체 단위로 데이터를 반환하기 위한 객체 생성
		P1UsersVO resultData = new P1UsersVO();
		
		try{
			// JDBC 기준 DB 접속 코드
//			con = DriverManager.getConnection(URL, DBID, DBPW);
			
			// 커넥션 풀 기준 DB 접속 코드
			con = ds.getConnection();
			// 1. 쿼리문 작성 - uid가 기본키이기 때문에 조건식에 uid로 작성
			String sql = "SELECT * FROM users WHERE uid = ?";
			
			// 2. 작성한 쿼리문 ? 자리에 적용할 자바 변수 삽입
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			
			// 3. 만든 쿼리문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultData.setUid(rs.getString("uid"));
				resultData.setUpw(rs.getString("upw"));
				resultData.setUname(rs.getString("uname"));
				resultData.setEmail(rs.getString("email"));
			}
		}catch(SQLException e){
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
		// resultData에 저장한 자료들을 리턴
		return resultData;
	}// end - getUsersInfo() method

// 전체 회원 목록
	/* 조건없이 전체 목록을 가져와야 하기 때문에 파라미터 필요 없음
	 * P1UsersVO 1개는 SELECT 구문의 ROW 하나와 같음
	 * 전체 데이터는 회원 가입 상황에 따라 유동적이므로 길이를 정해놓을 수 없음
	 * 따라서 길이를 가변적으로 사용할 수 있는 ArrayList에 P1UsersVO를 객체타입으로 지정하면 
	 * 조회결과 개수에 상관없이 대응 가능
	 */
	public ArrayList<P1UsersVO> getAllUsers() {
		Connection con = null;
		// 쿼리문 실행을 위한 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 객체 단위로 데이터를 반환하기 위한 객체 생성 - 비어있는 ArrayList<P1UsersVO> 선언
		ArrayList<P1UsersVO> userList = new ArrayList<>();
		
		try{
			// JDBC 기준 DB 접속 코드
//			con = DriverManager.getConnection(URL, DBID, DBPW);
			
			// 커넥션 풀 기준 DB 접속 코드
			con = ds.getConnection();
			String sql = "SELECT * FROM users";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// ArrayList에 데이터를 삽입할 빈 UsersVO를 생성
				P1UsersVO user = new P1UsersVO();
				// ResultSet에 삽입된 컬럼 별로 데이터를 불러냄
				String uid = rs.getString("uid");
				String upw = rs.getString("upw");
				String uname = rs.getString("uname");
				String email = rs.getString("email");
				// P1UsersVO에 setter로 데이터 삽입
				user.setUid(uid);
				user.setUpw(upw);
				user.setUname(uname);
				user.setEmail(email);
				// ArrayList에 위의 P1UsersVO 저장
				userList.add(user);
			}
		}catch(SQLException e){
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
		// select로 조회한 모든 데이터를 가지고 있는 userList를 리턴
		return userList;
	}// end - getAllUsers() method
}
