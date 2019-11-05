package volunteer.model.vo;

import java.util.ArrayList;

public class VoluntaryListData {

	private ArrayList<VoluntaryRegister> list;
	private String pageNavi;
	
	public VoluntaryListData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoluntaryListData(ArrayList<VoluntaryRegister> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}

	public ArrayList<VoluntaryRegister> getList() {
		return list;
	}

	public void setList(ArrayList<VoluntaryRegister> list) {
		this.list = list;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
