package kr.co.ictedu.user.service;

import javax.servlet.http.*;

import kr.co.ictedu.user.model.P1UsersVO;
import kr.co.ictedu.user.model.P2UsersDAO;

public class P02UserLoginService implements P01IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			String uId = request.getParameter("uId");
			String uPw = request.getParameter("uPw");
			
			// session 사용
			HttpSession session = null;
			session = request.getSession();

			P1UsersVO user = new P1UsersVO();
			user.setUid(uId);
			user.setUpw(uPw);
			P2UsersDAO dao = P2UsersDAO.getInstance();
			// dao.usersLogin(user); 가 두 번 실행되는 것을 막기위해 변수에 작성하고 변수를 if에서 체크
			int result = dao.usersLogin(user);
			if(result == 1) {		// 로그인 성공
				// 로그인 완료시 세션 발급 
				session.setAttribute("login", "success");
				session.setAttribute("id_session", uId);
				session.setAttribute("pw_session", uPw);
			}else if(result == 0) {	// 로그인 실패
				session.setAttribute("login", "failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
