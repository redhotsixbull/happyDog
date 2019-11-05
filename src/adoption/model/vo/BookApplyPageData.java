package adoption.model.vo;

import java.util.ArrayList;

public class BookApplyPageData {
	private ArrayList<BookApply> list;
	private String pageNavi;
	public BookApplyPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookApplyPageData(ArrayList<BookApply> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<BookApply> getList() {
		return list;
	}
	public void setList(ArrayList<BookApply> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
