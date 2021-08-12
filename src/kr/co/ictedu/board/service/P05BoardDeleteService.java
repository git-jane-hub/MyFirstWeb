package kr.co.ictedu.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.P02BoardDAO;

public class P05BoardDeleteService implements P01IBoardService{

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
		String bId = request.getParameter("bId");
		P02BoardDAO dao = P02BoardDAO.getInstance();
		dao.boardDelete(bId);
	}

}
