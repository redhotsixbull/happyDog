package sponsorship.model.vo;

public class SearchVO {
	
	private int reqPage;
	private String startDay;
	private String endDay;
	private String status;
	private String payMethod;
	private String searchType;
	private String searchVal;
	private String prdCode;
	public SearchVO() {
		super();
	}
	public SearchVO(int reqPage, String startDay, String endDay, String status, String payMethod, String searchType,
			String searchVal,String prdCode) {
		super();
		this.reqPage = reqPage;
		this.startDay = startDay;
		this.endDay = endDay;
		this.status = status;
		this.payMethod = payMethod;
		this.searchType = searchType;
		this.searchVal = searchVal;
		this.prdCode = prdCode;
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchVal() {
		return searchVal;
	}
	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}
	public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	
	
	

}
