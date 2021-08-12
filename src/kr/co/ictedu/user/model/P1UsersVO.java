package kr.co.ictedu.user.model;

public class P1UsersVO {
	/* VO(Value Object) / DTO(Data Transfer Object) - ResultSet을 대신함 
	 * - VO클래스는 웹서버와 DB간의 데이터 송수신을 돕는 자바 클래스 파일
	 * 
	 * - VO클래스를 설계할 때는 DB 테이블 컬럼 개수만큼 1:1 로 자료형, 이름 등이 매칭되는 멤버변수들을 선언
	 * 
	 * - VO클래스 내부 변수들은 private 접근 제한자를 걸어 데이터 접근을 제어
	 */
	private String uid;
	private String upw;
	private String uname;
	private String email;
	
	/* Alt + Shift + S 혹은 우클릭 - Source 에 있는 메뉴 사용 
	 * VO클래스는 기본 생성자와 모든 멤버변수를 초기화하는 생성자를 선언
	 */
	// 기본 생성자 직접 작성 - 4개의 변수 중에 몇 개의 값만 받는 경우, 기본 생성자로 객체를 생성하고 setter로 몇 개의 값만 호출
	public P1UsersVO(){
	}
	// Generate Constructor Using Fields...
	public P1UsersVO(String uid, String upw, String uname, String email) {
		super();
		this.uid = uid;
		this.upw = upw;
		this.uname = uname;
		this.email = email;
	}
	
	// Generate getter and setter...
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpw() {
		return upw;
	}

	public void setUpw(String upw) {
		this.upw = upw;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// Generate toString()
	@Override
	public String toString() {
		return "P01UsersVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", email=" + email + "]";
	}
}