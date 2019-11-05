package adoption.model.vo;

import java.sql.Date;

public class BookApply {
	private int rnum;
	private int no;				//방문예약신청 넘버
	private String code;		//보호소 코드
	private String id;			//신청자 아이디
	private String name;		//신청자 이름
	private String phone;		//신청자 전화번호
	private Date visitDate;		//방문 날짜
	private String visitTime;	//방문 시간(08시~18시 -> 0818로 저장)
	private Date applyDate;		//신청 날짜(년월일까지)
	private int status;			//방문신청 상태(0:대기,1:승인,2:거절)
	private String yard;		//마당여부
	private String animal;		//애완동물 키우는지
	private String family;		//가족 구성원
	private String experience;	//애완동물 키워본 경험
	private String avgTime;		//애완동물과 함께 있어줄 수 있는 평균 시간
	public BookApply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookApply(int rnum, int no, String code, String id, String name, String phone, Date visitDate, String visitTime,
			Date applyDate, int status, String yard, String animal, String family, String experience, String avgTime) {
		super();
		this.rnum = rnum;
		this.no = no;
		this.code = code;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.visitDate = visitDate;
		this.visitTime = visitTime;
		this.applyDate = applyDate;
		this.status = status;
		this.yard = yard;
		this.animal = animal;
		this.family = family;
		this.experience = experience;
		this.avgTime = avgTime;
	}
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	public String getResult() {
		if(status==0) {
			return "대기중";
		}else if(status==1) {
			return "승인";
		}else if(status==2){
			return "거절";
		}else {
			return "신청취소";
		}
	}
}
