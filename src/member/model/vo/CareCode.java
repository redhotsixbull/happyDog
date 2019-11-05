package member.model.vo;

public class CareCode {
	String careNm;
	String careRegNo;
	public CareCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CareCode(String careNm, String careRegNo) {
		super();
		this.careNm = careNm;
		this.careRegNo = careRegNo;
	}
	public String getCareNm() {
		return careNm;
	}
	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}
	public String getCareRegNo() {
		return careRegNo;
	}
	public void setCareRegNo(String careRegNo) {
		this.careRegNo = careRegNo;
	}
	
	
}
