package adoption.model.vo;

public class DogList {
	private String age;			//강아지 나이
	private String careAddr;	//보호소 주소
	private String careNm;		//보호소 이름
	private String careTel;		//보호소 전화번호
	private String colorCd;		//강아지 색깔
	private String filename;	//popfile(강아지사진)
	private String happenDt;	//발견날짜
	private String happenPlace;	//발결장소
	private String kindCd;		//품종
	private String noticeNo;	//공고번호
	private String noticeEdt;	//공고종료일
	private String orgNm;		//관할기관
	private String sexCd;		//성별
	private String specialMark;	//특징
	private String weight;		//몸무게
	private String noticeSdt;	//공고시작일
	private String processState;//입양상태
	private String neuterYn;	//중성화여부
	private int totalCount;
	
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public DogList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DogList(String age, String careAddr, String careNm, String careTel, String colorCd, String filename,
			String happenDt, String happenPlace, String kindCd, String noticeNo, String noticeEdt, String orgNm,
			String sexCd, String specialMark, String weight, String noticeSdt, String processState, String neuterYn) {
		super();
		this.age = age;
		this.careAddr = careAddr;
		this.careNm = careNm;
		this.careTel = careTel;
		this.colorCd = colorCd;
		this.filename = filename;
		this.happenDt = happenDt;
		this.happenPlace = happenPlace;
		this.kindCd = kindCd;
		this.noticeNo = noticeNo;
		this.noticeEdt = noticeEdt;
		this.orgNm = orgNm;
		this.sexCd = sexCd;
		this.specialMark = specialMark;
		this.weight = weight;
		this.noticeSdt = noticeSdt;
		this.processState = processState;
		this.neuterYn = neuterYn;
	}
	//보호소 방문예약 신청페이지로 데이터 넘기기 위해
	

	public String getNoticeSdt() {
		return noticeSdt;
	}
	public DogList(String age, String careAddr, String careNm, String careTel, String filename, String kindCd,
			String sexCd, String specialMark, String neuterYn) {
		super();
		this.age = age;
		this.careAddr = careAddr;
		this.careNm = careNm;
		this.careTel = careTel;
		this.filename = filename;
		this.kindCd = kindCd;
		this.sexCd = sexCd;
		this.specialMark = specialMark;
		this.neuterYn = neuterYn;
	}

	public void setNoticeSdt(String noticeSdt) {
		this.noticeSdt = noticeSdt;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCareAddr() {
		return careAddr;
	}
	public void setCareAddr(String careAddr) {
		this.careAddr = careAddr;
	}
	public String getCareNm() {
		return careNm;
	}
	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}
	public String getCareTel() {
		return careTel;
	}
	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}
	public String getColorCd() {
		return colorCd;
	}
	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getHappenDt() {
		return happenDt;
	}
	public void setHappenDt(String happenDt) {
		this.happenDt = happenDt;
	}
	public String getHappenPlace() {
		return happenPlace;
	}
	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
	public String getKindCd() {
		return kindCd;
	}
	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeEdt() {
		return noticeEdt;
	}
	public void setNoticeEdt(String noticeEdt) {
		this.noticeEdt = noticeEdt;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getSpecialMark() {
		return specialMark;
	}
	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getNeuterYn() {
		return neuterYn;
	}
	public void setNeuterYn(String neuterYn) {
		this.neuterYn = neuterYn;
	}
	// 성별 문자열로 가져오기
	public String getSex() {
		if(sexCd.equals("M")) {
			return "수컷";
		}else if(sexCd.equals("F")){
			return "암컷";
		}else {
			return "미상";
		}
	}
	//중성화 여부 문자열로 가져오기
	public String getNeuter() {
		if(neuterYn.equals("Y")) {
			return "O";
		}else if(neuterYn.equals("N")) {
			return "X";
		}else {
			return "미상";
		}
	}
}
