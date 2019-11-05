package adoption.model.vo;

import java.util.ArrayList;

public class ShelterPageData {

	ArrayList<Shelter> list;
	private String pageNavi; //페이지네비
	public ShelterPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShelterPageData(ArrayList<Shelter> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Shelter> getList() {
		return list;
	}
	public void setList(ArrayList<Shelter> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
