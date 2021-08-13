package kr.co.ictedu.board.model;

import java.util.List;

/* VO와 DTO는 구분의 경계가 명확하지 않음
 * 차이점은
 * VO는 DB에서 받아온 데이터를 그대로 전달
 * DTO는  DB에서 받아온 데이터를 가공해서 전달  
 */
public class P03BoardPageDTO {
	private int total;					// 전체 글 개수
	private int currentPage;			// 현재 위치한 페이지
	private List<P01BoardVO> boardList;	// 페이징 된 글 목록
	private int totalPages;				// 전체 페이지 개수 
	private int startPage;				// 시작 페이지 번호 
	private int endPage;				// 끝 페이지 번호 
	
	// setter 역할을 해주는 생성자 
	// 생성자에서는 전체 글 개수, 현재 페이지, 페이징된 글목록만 - 1차 데이터 
	public P03BoardPageDTO(int total, int currentPage, List<P01BoardVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;
		
		// 위 3개의 변수를 가공해서 나머지 변수를 초기화 - 2차 데이터 
		if(total == 0) {				// 글이 없는 경우에는 버튼이 생성될 필요가 없음 
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		}else {							// 글이 있는 경우 
			this.totalPages = this.total / 10;
			if(this.total % 10 > 0) {	// 10으로 나눠 나머지가 있으면  
				this.totalPages += 1;	// 전체 페이지를 하나 더 추가 	
			}
			// 해당 페이지의 시작 페이지 
			int modVal = this.currentPage % 10;		// int 값으로 산출됨 
			this.startPage = this.currentPage / 10 * 10 + 1;
			if(modVal == 0) {
				this.startPage -= 10;
			}
			// 해당 페이지의 끝 페이지 
			this.endPage = this.startPage + (10 - 1);
			if(this.endPage > this.totalPages) {	// 마지막 페이지에서 위에서 구한 endPage가 totalPage보다 크다면 
				this.endPage = this.totalPages;
			}
		}
	}// end constructor
	
	// getter를 직접 만들어야함 - 가공해야하므로 
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoBoard() {
		return total == 0;		// 게시물이 없는 경우 true를 리턴 
	}
	
	public boolean hasBoard() {
		return total > 0;		// 게시물이 있는 경우 true를 리턴 
	}
	
	public int getTotalPages() {
		return totalPages;		// 페이지의 총 개수 리턴 
	}
	
	public List<P01BoardVO> getBoardList(){
		return boardList;		// 해당 페이지에 표기한 글 목록 리턴 
	}
	
	public int getStartPage() {
		return startPage;		// 해당 페이지 그룹의 시작 번호 리턴 
	}
	
	public int getEndPage() {
		return endPage;			// 해당 페이지 그룹의 마지막 번호 리턴 
	}

	@Override
	public String toString() {
		return "P03BoardPageDTO [total=" + total + ", currentPage=" + currentPage + ", boardList=" + boardList
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
