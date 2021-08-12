package kr.co.ictedu.user.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.user.model.P1UsersVO;
import kr.co.ictedu.user.model.P2UsersDAO;

public class P03UserJoinService implements P01IUserService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			String uId = request.getParameter("uId");
			String uPw = request.getParameter("uPw");
			String uName = request.getParameter("uName");
			String uEmail = request.getParameter("uEmail");
			P1UsersVO user = new P1UsersVO(uId, uPw, uName, uEmail);
			P2UsersDAO dao = P2UsersDAO.getInstance();
			// 중복 아이디 관리
			int result = dao.usersJoinDup(user);
			if(result ==1) {
				dao.usersJoin(user);
			}else {
				String ui = "/users/P01Users_login.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
