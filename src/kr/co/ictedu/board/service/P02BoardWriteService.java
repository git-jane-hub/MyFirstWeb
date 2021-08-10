package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.P01BoardVO;
import kr.co.ictedu.board.model.P02BoardDAO;

// 구현 클래스이기 때문에 implement를 작성
public class P02BoardWriteService implements P01IBoardService {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 폼에서 작성한 데이터를 전달 받음
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String bTitle = request.getParameter("title");
			String bContent = request.getParameter("content");
			String bWriter = request.getParameter("writer");
			// DAO 생성
			P02BoardDAO dao = P02BoardDAO.getInstance();
			// VO 생성
			P01BoardVO board = new P01BoardVO();
			board.setbName(bWriter);
			board.setbTitle(bTitle);
			board.setbContent(bContent);
			int result = dao.write(board);
			if(result == 1) {
				System.out.println("글작성 완료");
			}else {
				System.out.println("글작성 에러");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
