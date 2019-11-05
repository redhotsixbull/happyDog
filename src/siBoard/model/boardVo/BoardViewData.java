package siBoard.model.boardVo;

import java.util.ArrayList;

import siBoardComment.model.boardCommentVo.BoardComment;

public class BoardViewData {
	private ArrayList<BoardComment> list;
	private Board b;
	public BoardViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardViewData(ArrayList<BoardComment> list, Board b) {
		super();
		this.list = list;
		this.b = b;
	}
	public ArrayList<BoardComment> getList() {
		return list;
	}
	public void setList(ArrayList<BoardComment> list) {
		this.list = list;
	}
	public Board getB() {
		return b;
	}
	public void setB(Board b) {
		this.b = b;
	}
	
}
