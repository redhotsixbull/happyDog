package qna.model.vo;

public class CommentVO {
	
	private int boardCommentNo;
	private int boardCommentType;
	private String boardCommentContent;
	private int boardRef;
	private String boardCommentDate;
	public CommentVO() {
		super();
	}
	public CommentVO(int boardCommentNo, int boardCommentType, String boardCommentContent, int boardRef,
			String boardCommentDate) {
		super();
		this.boardCommentNo = boardCommentNo;
		this.boardCommentType = boardCommentType;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentDate = boardCommentDate;
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
	public String getBoardCommentDate() {
		return boardCommentDate;
	}
	public void setBoardCommentDate(String boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}
	
	

}
