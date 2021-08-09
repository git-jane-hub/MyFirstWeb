package kr.co.ictedu;
// Servlet: 스크립틀릿을 JSP 파일에서 최대한 작성하지 않도록 하는 것 
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class P01ServletBasic
 */
// Servlet 생성 시 URL Mapping에 작성한 내용, WebServlet("/주소") 의 주소로 접속 시 서블릿이 작동 - 이렇게 작성하면 프로젝트명/주소명으로 접속 시 실행됨
@WebServlet("/apple")
//	HttpServlet을 상속받은 클래스 내부에서는 session, request, response 등의 객체를 자바 코드내에서 사용 가능 
public class P01ServletBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// 생성자는 생성 시에 한 번 호출되고 다시 호출되지 않음 
    public P01ServletBasic() {
        super();
        System.out.println("서블릿 객체 생성 완료");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    /* HttpServlet 객체를 상속받은 클래스 내에서는 모든 메서드를 생성하여 호출할 수 있는 것이 아님
     * 아래와 같이 상속받은 메서드를 오버라이딩 하는 경우에만 호출이 가능  
     */
	public void init(ServletConfig config) throws ServletException {
		// 요청이 들어오면 처음에 실행할 로직을 작성하는 부분으로 init() 메서드는 서블릿 객체 생성시 한 번 자동 호출됨 (Web과 해당 서블릿을 연결하기 위해 호출)
		System.out.println("init 메서드 호출")
;;	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// 소멸자 메서드, 서블릿 객체가 메모리에서 삭제될 때 호출(소멸시 1회만 호출)
		System.out.println("destroy 메서드 호출");
	}

	// 현재 만든 jsp파일에서는 url 뒤에 get방식을 통해 회원가입이 가능 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Http Get 요청이 들어왔을 때만 작동하는 메서드 
		System.out.println("doGet 메서드 호출");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Http Post 요청이 들어왔을 때만 작동하는 메서드
		 * .jsp 파일에 폼을 만들고 post 요청을 하도록 처리  
		 */
		System.out.println("doPost 메서드 호출");
	}

}
