package siAdoptionBoard.model.adoptionBoardVo;

import java.util.ArrayList;

import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;

public class AdoptionBoardViewData {
	private ArrayList<AdoptionBoardComment> list;
	private AdoptionBoard a;
	public AdoptionBoardViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdoptionBoardViewData(ArrayList<AdoptionBoardComment> list, AdoptionBoard a) {
		super();
		this.list = list;
		this.a = a;
	}
	public ArrayList<AdoptionBoardComment> getList() {
		return list;
	}
	public void setList(ArrayList<AdoptionBoardComment> list) {
		this.list = list;
	}
	public AdoptionBoard getA() {
		return a;
	}
	public void setA(AdoptionBoard a) {
		this.a = a;
	}
	
}
