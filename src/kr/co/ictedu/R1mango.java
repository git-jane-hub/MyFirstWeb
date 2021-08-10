package kr.co.ictedu;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class P02ServletBasic02
 */
@WebServlet("/mangguo")
public class R1mango extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R1mango() {
        super();
        System.out.println("망고 생성");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("망고 시작");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("망고 소멸");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 방식으로만 접속할 수 있습니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* doGet, doPost 메서드 내부에서 파라미터로 선언한 request, response 내장객체를 기본으로 사용 가능
		 * 추가로 내부에서 세션과 쿠키도 선언하여 사용 가능 
		 * 폼에서 전달 받은 데이터를 utf-8로 인코딩 
		 */
		//HttpSession session;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// request.getParameter 로 데이터 전달 받기 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		// form 에서 전달받은 데이터 콘솔에 출력 
		System.out.println("ID: " + id);
		System.out.println("PW: " + pw);
	}

}
