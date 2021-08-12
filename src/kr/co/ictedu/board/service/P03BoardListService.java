package kr.co.ictedu.board.service;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;

public class P03BoardListService implements P01IBoardService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 로그인이 안되어있다면 서비스를 사용하지 못하고 로그인페이지로 이동하도록 작성 
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
		// 로그인이 되어있다면 그대로 실행
		// 폼에서 받아오는 내용이 없고 접속하자마자 전체 데이터 조회가능하도록 작성
		P02BoardDAO dao = P02BoardDAO.getInstance();
		List<P01BoardVO> boardList = dao.getBoardList();
		// request에 데이터를 담아놓기위해 request.setAttribute("명칭", 데이터);
		request.setAttribute("boardList", boardList);
	}
}
