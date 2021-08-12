package kr.co.ictedu.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class P04UserLogoutService implements P01IUserService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 세션 활성화
		HttpSession session = null;
		session = request.getSession();
		session.invalidate();
	}

}
