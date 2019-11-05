package sponsorship.model.vo;

public class OrderUpdate {
	
	private String no;
	private String deilveryNum;
	private int status;
	public OrderUpdate() {
		super();
	}
	public OrderUpdate(String no, String deilveryNum, int status) {
		super();
		this.no = no;
		this.deilveryNum = deilveryNum;
		this.status = status;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDeilveryNum() {
		return deilveryNum;
	}
	public void setDeilveryNum(String deilveryNum) {
		this.deilveryNum = deilveryNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

}
