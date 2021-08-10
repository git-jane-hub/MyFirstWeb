package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 컨트롤러에(P03PatternServlet.java) 직접적으로 기능을 하나씩 작성할 수도 있지만, 코드관리가 어려워지는 문제가 있음
 * 따라서 기능을 하나씩 다시 Service 라는 단위로 나눠 관리
 */
public interface P01IBoardService {
	// 서비스 인터페이스 내부에는 실행 메서드 하나만 작성
	void execute(HttpServletRequest request, HttpServletResponse response);
	
}
