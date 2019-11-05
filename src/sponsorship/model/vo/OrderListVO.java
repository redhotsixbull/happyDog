package sponsorship.model.vo;

import java.util.ArrayList;

public class OrderListVO {
	
	private ArrayList<OrderInfoVO> orderinfoList;
	private String pageNavi;
	public OrderListVO() {
		super();
	}
	public OrderListVO(ArrayList<OrderInfoVO> orderinfoList, String pageNavi) {
		super();
		this.orderinfoList = orderinfoList;
		this.pageNavi = pageNavi;
	}
	public ArrayList<OrderInfoVO> getOrderinfoList() {
		return orderinfoList;
	}
	public void setOrderinfoList(ArrayList<OrderInfoVO> orderinfoList) {
		this.orderinfoList = orderinfoList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	

}
