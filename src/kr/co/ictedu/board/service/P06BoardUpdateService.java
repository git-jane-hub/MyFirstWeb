package kr.co.ictedu.board.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;

public class P06BoardUpdateService implements P01IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			int bId = Integer.parseInt(request.getParameter("bId"));
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
//			Timestamp bDate = Timestamp.valueOf(request.getParameter("bDate"));
			java.sql.Timestamp bDate = java.sql.Timestamp.valueOf(request.getParameter("bDate"));
			int bHit = Integer.parseInt(request.getParameter("bHit"));
			
			P01BoardVO board = new P01BoardVO();
			board.setbId(bId);
			board.setbName(bName);
			board.setbTitle(bTitle);
			board.setbContent(bContent);
			board.setbDate(bDate);
			board.setbHit(bHit);
			P02BoardDAO dao = P02BoardDAO.getInstance();
			dao.boardUpdate(board);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
