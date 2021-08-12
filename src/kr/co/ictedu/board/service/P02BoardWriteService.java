package kr.co.ictedu.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;

// 구현 클래스이기 때문에 implement를 작성
public class P02BoardWriteService implements P01IBoardService {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String id_session = (String)session.getAttribute("id_session");
		if(id_session == null) {
			try {
				String ui = "/users/P01Users_login.jsp";
				/* service 내부에서는 response.sendRedirect 실행이 안됨
				 * 로그인 아이디 세션이 발급되었는지 확인해서 null 값이 나오면(= 로그인한 이력이 없다면)
				 * 로그인 페이지로 포워딩
				 */
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		// 폼에서 작성한 데이터를 전달 받음
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String bTitle = request.getParameter("title");
			String bContent = request.getParameter("content");
			String bWriter = request.getParameter("writer");
			// DAO 생성
			P02BoardDAO dao = P02BoardDAO.getInstance();
			// VO 생성
			P01BoardVO board = new P01BoardVO();
			board.setbName(bWriter);
			board.setbTitle(bTitle);
			board.setbContent(bContent);
			int result = dao.write(board);
			if(result == 1) {
				System.out.println("글작성 완료");
			}else {
				System.out.println("글작성 에러");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
