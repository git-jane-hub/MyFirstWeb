package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.P02BoardDAO;

public class P05BoardDeleteService implements P01IBoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");
		P02BoardDAO dao = P02BoardDAO.getInstance();
		dao.boardDelete(bId);
	}

}
