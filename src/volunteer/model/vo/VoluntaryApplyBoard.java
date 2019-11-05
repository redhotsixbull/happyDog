package volunteer.model.vo;

import java.sql.Date;

public class VoluntaryApplyBoard {

	private int applyNo;		//신청 글 번호	
	private int no;				//공고번호
	private String code;		//보호소 코드
	private String name;		//보호소 이름
	private String title;		//공고 제목
	private String id;			//신청자 아이디
	private String phone;		//신청자 전화번호
	private int person;			//신청 인원 수
	private String volunDate;	//봉사 날짜
	private String volunTime;	//봉사 시간
	private Date enrollDate;	//신청 날짜
	
	public VoluntaryApplyBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public VoluntaryApplyBoard(int applyNo, int no, String code, String title, String name, String id, String phone, int person,
			String volunDate, String volunTime, Date enrollDate) {
		super();
		this.applyNo = applyNo;
		this.no = no;
		this.code = code;
		this.title = title;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.person = person;
		this.volunDate = volunDate;
		this.volunTime = volunTime;
		this.enrollDate = enrollDate;
	}


	public int getApplyNo() {
		return applyNo;
	}


	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getPerson() {
		return person;
	}


	public void setPerson(int person) {
		this.person = person;
	}


	public String getVolunDate() {
		return volunDate;
	}


	public void setVolunDate(String volunDate) {
		this.volunDate = volunDate;
	}


	public String getVolunTime() {
		return volunTime;
	}


	public void setVolunTime(String volunTime) {
		this.volunTime = volunTime;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	// 시간 :: 시작 시간
	public String getStartTime() {
		return volunTime.split(",")[0];
	}
	
	// 시간 :: 끝나는 시간
	public String getEndTime() {
		return volunTime.split(",")[1];
	}
	
}
