package siAdoptionBoard.model.adoptionBoardVo;

import java.util.ArrayList;

public class AdoptionBoardPageData {
	private ArrayList<AdoptionBoard> list;
	private String pageNavi;
	public AdoptionBoardPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdoptionBoardPageData(ArrayList<AdoptionBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<AdoptionBoard> getList() {
		return list;
	}
	public void setList(ArrayList<AdoptionBoard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
