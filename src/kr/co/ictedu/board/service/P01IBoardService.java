package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 컨트롤러에(P03PatternServlet.java) 직접적으로 기능을 하나씩 작성할 수도 있지만, 코드관리가 어려워지는 문제가 있음
 * 따라서 기능을 하나씩 다시 Service 라는 단위로 나눠 관리
 */
// board의 모든 서비스는 로그인 세션 검사부터 실행해서 발급된 세션이 없다면 로그인 페이지로 이동하도록 각 서비스마다 작성 
public interface P01IBoardService {
	// 서비스 인터페이스 내부에는 실행 메서드 하나만 작성
	void execute(HttpServletRequest request, HttpServletResponse response);
	
}
