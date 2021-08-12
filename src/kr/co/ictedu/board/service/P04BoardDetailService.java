package kr.co.ictedu.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;

public class P04BoardDetailService implements P01IBoardService {

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
		// url에 작성된 글 번호를 getParameter를 이용해 얻음
		String bId = request.getParameter("bId");
		// DAO 생성
		P02BoardDAO dao = P02BoardDAO.getInstance();
		// 조회수를 올린 후에 본문을 가져와야 본문으로 진입시 올라간 조회수를 확인가능
		dao.upHit(bId);
		// bId 하나의 데이터만 받아오는 경우에는 VO를 생성할 필요가 없음
		// DAO에 글 번호를 넘겨 detail 페이지 데이터를 전달 받음
		P01BoardVO board = dao.getBoardDetail(bId);
		// 포워딩을 위해 setAttribute()로 데이터 전달
		request.setAttribute("board", board);
	}
}
