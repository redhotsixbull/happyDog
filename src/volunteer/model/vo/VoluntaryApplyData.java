package volunteer.model.vo;

import java.util.ArrayList;

public class VoluntaryApplyData {

	private ArrayList<VoluntaryApplyBoard> list;
	private String pageNavi;
	
	public VoluntaryApplyData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoluntaryApplyData(ArrayList<VoluntaryApplyBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}

	public ArrayList<VoluntaryApplyBoard> getList() {
		return list;
	}

	public void setList(ArrayList<VoluntaryApplyBoard> list) {
		this.list = list;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
