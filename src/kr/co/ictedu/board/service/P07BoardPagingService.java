package kr.co.ictedu.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;
import kr.co.ictedu.board.model.P03BoardPageDTO;

public class P07BoardPagingService implements P01IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// page 파라미터에 있던 값을 가져옴
		String strPage = request.getParameter("page");
		// 패턴 서비스에서 page 파라미터 값을 따로 설정하지 않고 디폴트로 1페이지가 나오도록 설정 
		int currentPage = 1;
		if(strPage != null) {
			currentPage = Integer.parseInt(strPage);
		}
		P02BoardDAO dao = P02BoardDAO.getInstance();
		// 각 페이지 별 시작 번호: (페이지번호 - 1) * 10 - dao에서 처리 
		List<P01BoardVO> boardList = dao.getPageList(currentPage);
		// 전달 받은 글 전체 개수와 현재 조회중인 페이지 정보를 DTO에 전달 
		int total = dao.getBoardCount();
		// DTO - 페이지 하단에 만들 링크의 정보를 계산 
		P03BoardPageDTO pageDTO = new P03BoardPageDTO(total, currentPage, boardList);
		System.out.println("링크 정보 버튼: " + pageDTO);
		// 포워딩하기 위한 적재 
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageDTO", pageDTO);
	}
}
