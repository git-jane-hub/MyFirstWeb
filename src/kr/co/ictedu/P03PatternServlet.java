package kr.co.ictedu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.service.P01IBoardService;
import kr.co.ictedu.board.service.P02BoardWriteService;
import kr.co.ictedu.board.service.P03BoardListService;
import kr.co.ictedu.board.service.P04BoardDetailService;
import kr.co.ictedu.board.service.P05BoardDeleteService;
import kr.co.ictedu.board.service.P06BoardUpdateService;
import kr.co.ictedu.board.service.P07BoardPagingService;
import kr.co.ictedu.user.service.P01IUserService;
import kr.co.ictedu.user.service.P02UserLoginService;
import kr.co.ictedu.user.service.P03UserJoinService;
import kr.co.ictedu.user.service.P04UserLogoutService;

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
		// 서비스 호출을 위해 모든 서비스 데이터 타입을 받을 수 있는 인터페이스 생성
		P01IBoardService sv = null;
		P01IUserService uv = null;
		// 로그인 확인 session을 위한 변수 생성
		HttpSession session = null;
		session = request.getSession();
		// 해당 로직을 실행한 뒤에 이동할 .jsp 파일 지정
		String ui = null;
		// doGet 에 있는 모든 코드를 가져옴
		// 확장자 패턴에서 확장자를 포함한 주소 값을 가져오기 위해 아래 코드를 사용 (getRequestURI는 포트번호 뒤의 내용)
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
		if(uri.equals("/MyFirstWeb/reqjoin.do")) {				// 회원가입 요청
			System.out.println("회원가입 요청창으로");
			// ui에서 .jsp 파일 경로로 작성해도 jsp 파일 내부에만 .jsp파일로 작성하지 않으면 uri가 주소창에 출력됨
			ui = "/users/P02Users_join_form.jsp";
		}else if(uri.equals("/MyFirstWeb/join.do")) {			// 회원가입
			System.out.println("회원가입 요청 확인");
			uv = new P03UserJoinService();
			uv.execute(request, response);
			ui = "/users/P01Users_login.jsp";
		}else if(uri.equals("/MyFirstWeb/login.do")){			// 로그인
			System.out.println("로그인 요청 확인");
			uv = new P02UserLoginService();
			uv.execute(request, response);
			String loginCheck = (String)session.getAttribute("login");
			if(loginCheck.equals("failed")) {
				session.invalidate();
				ui = "/users/P01Users_login.jsp";
			}else if(loginCheck.equals("success")){	// success로 세션을 발급하지 않으면, null인지 아닌지 검사해줘야함
				ui = "/boardselect.do";
			}
		}else if(uri.equals("/MyFirstWeb/logout.do")){			// 로그아웃
			System.out.println("로그아웃 요청 확인");
			uv = new P04UserLogoutService();
			uv.execute(request, response);
			ui = "/users/P01Users_login.jsp";
		}else if(uri.equals("/MyFirstWeb/userupdate.do")){		// 회원 정보 수정
			System.out.println("수정 요청 확인");
		}else if(uri.equals("/MyFirstWeb/userdelete.do")){		// 회원 탈퇴
			System.out.println("탈퇴 요청 확인");
		// 게시글 관련 
		}else if(uri.equals("/MyFirstWeb/boardreqwrite.do")) {	// 글 작성 요청
			ui = "/board/P01Board_write_form.jsp";
		}else if(uri.equals("/MyFirstWeb/boardwrite.do")) {		// 글 작성
			// 글쓰기에 필요한 로직을 호출하도록 서비스를 생성
			sv = new P02BoardWriteService();
			// 생성한 객체의 execute을 호출하면 복잡한 서비스 로직을 처리 가능
			sv.execute(request, response);
			// 경로 저장 시 /는 WebContent 폴더가 기본으로 작성되어 있음
			ui = "/boardselect.do";
			// 경로를 저장한 후에는 페이지 강제이동(forward) 수행
		}else if(uri.equals("/MyFirstWeb/boarddetail.do")) {	// 글 본문 조회
			sv = new P04BoardDetailService();
			sv.execute(request, response);
			ui = "/board/P03Board_detail.jsp";
		}else if(uri.equals("/MyFirstWeb/boardupdate.do")) {	// 글 수정을 위한 조회 
			// BoardDetailService 객체가 글 정보를 가져오기 때문에 해당 객체를 사용 
			sv = new P04BoardDetailService();
			sv.execute(request, response);
			ui = "/board/P04Board_update_form.jsp";
		}else if(uri.equals("/MyFirstWeb/boardupdateok.do")) {	// 글 수정 완료
			sv = new P06BoardUpdateService();
			sv.execute(request, response);
			ui = "/boarddetail.do";
		}else if(uri.equals("/MyFirstWeb/boarddelete.do")) {	// 글 삭제
			sv = new P05BoardDeleteService();
			sv.execute(request, response);
			ui = "/boardselect.do";
		}else if(uri.equals("/MyFirstWeb/boardselect.do")) {	// 글 조회
			//sv = new P03BoardListService();
			//sv.execute(request, response);
			//ui = "/board/P02Board_list.jsp";
			
			// 위의 전체 게시물 가져오기를 페이징 가져오기로 대체
			sv = new P07BoardPagingService();
			sv.execute(request, response);
			ui = "/board/P02Board_list.jsp";
		}else {
			// 콘솔창이 아닌 jsp 페이지에 출력 
			out.println("잘못된 패턴입니다.");
		}
		/* 포워드 로직은 조건문이 모두 작동하고 나서 실행
		 * RequestDispatcher를 사용해 포워딩하면 request, response 및 자료를 jsp페이지에 전달 가능
		 * Model2 방식은 스크립트릿을 작성하지 않기 때문에 controller에 출력이 필요한 데이터를 받고
		 * 포워드로 .jsp에 전달
		 */
		RequestDispatcher dp = request.getRequestDispatcher(ui);
		dp.forward(request, response);
	}
}
