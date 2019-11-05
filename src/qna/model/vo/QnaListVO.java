package qna.model.vo;

import java.util.ArrayList;


public class QnaListVO {
	
	private ArrayList<QnaVO> qnainfoList;
	private String pageNavi;
	public QnaListVO() {
		super();
	}
	public QnaListVO(ArrayList<QnaVO> qnainfoList, String pageNavi) {
		super();
		this.qnainfoList = qnainfoList;
		this.pageNavi = pageNavi;
	}
	public ArrayList<QnaVO> getQnainfoList() {
		return qnainfoList;
	}
	public void setQnainfoList(ArrayList<QnaVO> qnainfoList) {
		this.qnainfoList = qnainfoList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	

}
