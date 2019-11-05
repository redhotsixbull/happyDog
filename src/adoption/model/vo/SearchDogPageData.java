package adoption.model.vo;

import java.util.ArrayList;

public class SearchDogPageData {
	private ArrayList<DogList> list;  //강아지 데이터 리스트<12>개만담기
	private String pageNavi; //페이지네비
	public SearchDogPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchDogPageData(ArrayList<DogList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<DogList> getList() {
		return list;
	}
	public void setList(ArrayList<DogList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
