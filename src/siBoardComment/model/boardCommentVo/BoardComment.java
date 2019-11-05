package siBoardComment.model.boardCommentVo;

import java.sql.Date;

public class BoardComment {
	private int boardCommentNo;
	private int boardCommentType;
	private String boardCommentId;
	private String boardCommentName;
	private String boardCommentContent;
	private int boardRef;
	private int boardCommentRef;
	private Date boardCommentDate;
	private String boardCommentDate2;
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardComment(int boardCommentNo, int boardCommentType, String boardCommentId, String boardCommentName,
			String boardCommentContent, int boardRef, int boardCommentRef, Date boardCommentDate,
			String boardCommentDate2) {
		super();
		this.boardCommentNo = boardCommentNo;
		this.boardCommentType = boardCommentType;
		this.boardCommentId = boardCommentId;
		this.boardCommentName = boardCommentName;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
		this.boardCommentDate = boardCommentDate;
		this.boardCommentDate2 = boardCommentDate2;
	}
	public int getBoardCommentNo() {
		return boardCommentNo;
	}
	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}
	public int getBoardCommentType() {
		return boardCommentType;
	}
	public void setBoardCommentType(int boardCommentType) {
		this.boardCommentType = boardCommentType;
	}
	public String getBoardCommentId() {
		return boardCommentId;
	}
	public void setBoardCommentId(String boardCommentId) {
		this.boardCommentId = boardCommentId;
	}
	public String getBoardCommentName() {
		return boardCommentName;
	}
	public void setBoardCommentName(String boardCommentName) {
		this.boardCommentName = boardCommentName;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBoardCommentRef() {
		return boardCommentRef;
	}
	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}
	public Date getBoardCommentDate() {
		return boardCommentDate;
	}
	public void setBoardCommentDate(Date boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}
	public String getBoardCommentDate2() {
		return boardCommentDate2;
	}
	public void setBoardCommentDate2(String boardCommentDate2) {
		this.boardCommentDate2 = boardCommentDate2;
	}
	
}
