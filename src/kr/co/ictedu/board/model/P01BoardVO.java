package kr.co.ictedu.board.model;

import java.sql.Timestamp;

public class P01BoardVO {
	private int bId;
	private String bName;
	private String bTitle;
	private String bContent;
	// Timestamp는 시,분,초까지 표기, Date는 연,월,일만 표기  
	private Timestamp bDate;
	private int bHit;
	
	public P01BoardVO() {
		
	}
	
	public P01BoardVO(int bId, String bName, String bTitle, String bContent, Timestamp bDate, int bHit) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	@Override
	public String toString() {
		return "P01BoardVO [bId=" + bId + ", bName=" + bName + ", bTitle=" + bTitle + ", bContent=" + bContent
				+ ", bDate=" + bDate + ", bHit=" + bHit + "]";
	}
}