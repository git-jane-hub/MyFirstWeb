package kr.co.ictedu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class P03PatternServlet
 */
// *.do로 잡힌 패턴은 .do로 끝나는 접속 주소를 모두 불러옴 - /를 작성하지 않음 

@WebServlet("*.do")
public class P03PatternServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public P03PatternServlet() {
        super();
        System.out.println("확장자 패턴 생성 ");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("확장자 패턴 서버 연결");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("확장자 패턴 소멸");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	// doGet, doPost를 호출하면 두 메서드가 doRequest를 호출하게 함 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	
	// 요청 메서드 (get, post) 상관없이 처리하려한다면 메서들 하나를 더 생성 
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// doGet 에 있는 모든 코드를 가져옴
		// 확장자 패턴에서 확장자를 포함한 주소 값을 가져오기 위해 아래 코드를 사용 
		String uri = request.getRequestURI();
		System.out.println("uri 패턴: " + uri);
		// 콘솔이 아닌 사용자가 확인할 수 있도록 .jsp 화면에 변수가 담긴 자료를 출력하는 out.print(); 사용을 위한 코드 준비 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// JSP페이지가 html 형식으로 이루어져 있음을 알려주는 코드 
		response.setContentType("text/html; charset=UTF-8");
		// out 을 통해 .jsp 화면에서 사용자가 볼 수 있도록 출력 
		PrintWriter out = response.getWriter();
		
		// 회원 관련 
		if(uri.equals("/MyFirstWeb/join.do")) {
			System.out.println("회원가입 요청 확인");
		}else if(uri.equals("/MyFirstWeb/login.do")){
			System.out.println("로그인 요청 확인");
		}else if(uri.equals("/MyFirstWeb/update.do")){
			System.out.println("수정 요청 확인");
		}else if(uri.equals("/MyFirstWeb/delete.do")){
			System.out.println("탈퇴 요청 확인");
		// 게시글 관련 
		}else if(uri.equals("/MyFirstWeb/write.do")) {
			System.out.println("글 작성 창으로 이동합니다.");
		}else if(uri.equals("/MyFirstWeb/update.do")) {
			System.out.println("글 수정 창으로 이동합니다.");
		}else if(uri.equals("/MyFirstWeb/wDelete.do")) {	// 탈퇴와 중복되어 변경 
			System.out.println("글 삭제 창으로 이동합니다.");
		}else if(uri.equals("/MyFirstWeb/select.do")) {
			System.out.println("글 조회 창으로 이동합니다.");
		}else {
			// 콘솔창이 아닌 jsp 페이지에 출력 
			out.println("잘못된 패턴입니다.");
		}
	}
}
