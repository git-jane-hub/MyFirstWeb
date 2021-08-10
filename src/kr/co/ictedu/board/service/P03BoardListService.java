package kr.co.ictedu.board.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;

public class P03BoardListService implements P01IBoardService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 폼에서 받아오는 내용이 없고 접속하자마자 전체 데이터 조회가능하도록 작성
		P02BoardDAO dao = P02BoardDAO.getInstance();
		// 여기 헷갈림
		List<P01BoardVO> boardList = dao.getBoardList();
		// request에 데이터를 담아놓기위해 request.setAttribute("명칭", 데이터);
		request.setAttribute("boardList", boardList);
	}
}
