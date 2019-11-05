package siBoard.model.boardVo;

import java.sql.Date;

public class Board {
	private int boardRnum;
	private int boardNo;
	private int boardType;
	private String boardId;
	private String boardName;
	private String boardTitle;
	private String boardContent;
	private String boardFilename;
	private String boardFilepath;
	private Date boardDate;
	private String boardDate2;
	private int boardCount;
	private int boardSecret;
	private String boardPw;
	private String boardPrdCode;
	private String dogKind;
	private String happenCity;
	private String happenDate;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int boardRnum, int boardNo, int boardType, String boardId, String boardName, String boardTitle,
			String boardContent, String boardFilename, String boardFilepath, Date boardDate, String boardDate2,
			int boardCount, int boardSecret, String boardPw, String boardPrdCode, String dogKind, String happenCity,
			String happenDate) {
		super();
		this.boardRnum = boardRnum;
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.boardId = boardId;
		this.boardName = boardName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardFilename = boardFilename;
		this.boardFilepath = boardFilepath;
		this.boardDate = boardDate;
		this.boardDate2 = boardDate2;
		this.boardCount = boardCount;
		this.boardSecret = boardSecret;
		this.boardPw = boardPw;
		this.boardPrdCode = boardPrdCode;
		this.dogKind = dogKind;
		this.happenCity = happenCity;
		this.happenDate = happenDate;
	}
	public int getBoardRnum() {
		return boardRnum;
	}
	public void setBoardRnum(int boardRnum) {
		this.boardRnum = boardRnum;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardDate2() {
		return boardDate2;
	}
	public void setBoardDate2(String boardDate2) {
		this.boardDate2 = boardDate2;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getBoardSecret() {
		return boardSecret;
	}
	public void setBoardSecret(int boardSecret) {
		this.boardSecret = boardSecret;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	public String getBoardPrdCode() {
		return boardPrdCode;
	}
	public void setBoardPrdCode(String boardPrdCode) {
		this.boardPrdCode = boardPrdCode;
	}
	public String getDogKind() {
		return dogKind;
	}
	public void setDogKind(String dogKind) {
		this.dogKind = dogKind;
	}
	public String getHappenCity() {
		String city="";
		switch(happenCity) {
		case "6110000":
			city="서울";
			break;
		case "6260000":
			city="부산";
			break;
		case "6270000":
			city="대구";
			break;
		case "6280000":
			city="인천";
			break;
		case "6290000":
			city="광주";
			break;
		case "5690000":
			city="세종시";
			break;
		case "6300000":
			city="대전";
			break;
		case "6310000":
			city="울산";
			break;
		case "6410000":
			city="경기도";
			break;
		case "6420000":
			city="강원도";
			break;
		case "6430000":
			city="충청북도";
			break;
		case "6440000":
			city="충청남도";
			break;
		case "6450000":
			city="전라북도";
			break;
		case "6460000":
			city="전라남도";
			break;
		case "6470000":
			city="경상북도";
			break;
		case "6500000":
			city="제주도";
			break;
			
	}
	
	return city;
	}
	public void setHappenCity(String happenCity) {
		this.happenCity = happenCity;
	}
	public String getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	
}
