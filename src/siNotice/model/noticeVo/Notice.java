package siNotice.model.noticeVo;

import java.sql.Date;

public class Notice {
		private int noticeRnum;
		private int noticeNo;
		private int noticeType;
		private String noticeId;
		private String noticeName;
		private String noticeTitle;
		private String noticeContent;
		private String noticeFilename;
		private String noticeFilepath;
		private Date noticeDate;
		private String noticeDate2;
		private int noticeCount;
		private int noticeSecret;
		private String noticePw;
		private String noticePrdCode;
		private String dogKind;
		private String happenCity;
		private String happenDate;
		public Notice() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Notice(int noticeRnum, int noticeNo, int noticeType, String noticeId, String noticeName,
				String noticeTitle, String noticeContent, String noticeFilename, String noticeFilepath, Date noticeDate,
				String noticeDate2, int noticeCount, int noticeSecret, String noticePw, String noticePrdCode,
				String dogKind, String happenCity, String happenDate) {
			super();
			this.noticeRnum = noticeRnum;
			this.noticeNo = noticeNo;
			this.noticeType = noticeType;
			this.noticeId = noticeId;
			this.noticeName = noticeName;
			this.noticeTitle = noticeTitle;
			this.noticeContent = noticeContent;
			this.noticeFilename = noticeFilename;
			this.noticeFilepath = noticeFilepath;
			this.noticeDate = noticeDate;
			this.noticeDate2 = noticeDate2;
			this.noticeCount = noticeCount;
			this.noticeSecret = noticeSecret;
			this.noticePw = noticePw;
			this.noticePrdCode = noticePrdCode;
			this.dogKind = dogKind;
			this.happenCity = happenCity;
			this.happenDate = happenDate;
		}
		public int getNoticeRnum() {
			return noticeRnum;
		}
		public void setNoticeRnum(int noticeRnum) {
			this.noticeRnum = noticeRnum;
		}
		public int getNoticeNo() {
			return noticeNo;
		}
		public void setNoticeNo(int noticeNo) {
			this.noticeNo = noticeNo;
		}
		public int getNoticeType() {
			return noticeType;
		}
		public void setNoticeType(int noticeType) {
			this.noticeType = noticeType;
		}
		public String getNoticeId() {
			return noticeId;
		}
		public void setNoticeId(String noticeId) {
			this.noticeId = noticeId;
		}
		public String getNoticeName() {
			return noticeName;
		}
		public void setNoticeName(String noticeName) {
			this.noticeName = noticeName;
		}
		public String getNoticeTitle() {
			return noticeTitle;
		}
		public void setNoticeTitle(String noticeTitle) {
			this.noticeTitle = noticeTitle;
		}
		public String getNoticeContent() {
			return noticeContent;
		}
		public void setNoticeContent(String noticeContent) {
			this.noticeContent = noticeContent;
		}
		public String getNoticeFilename() {
			return noticeFilename;
		}
		public void setNoticeFilename(String noticeFilename) {
			this.noticeFilename = noticeFilename;
		}
		public String getNoticeFilepath() {
			return noticeFilepath;
		}
		public void setNoticeFilepath(String noticeFilepath) {
			this.noticeFilepath = noticeFilepath;
		}
		public Date getNoticeDate() {
			return noticeDate;
		}
		public void setNoticeDate(Date noticeDate) {
			this.noticeDate = noticeDate;
		}
		public String getNoticeDate2() {
			return noticeDate2;
		}
		public void setNoticeDate2(String noticeDate2) {
			this.noticeDate2 = noticeDate2;
		}
		public int getNoticeCount() {
			return noticeCount;
		}
		public void setNoticeCount(int noticeCount) {
			this.noticeCount = noticeCount;
		}
		public int getNoticeSecret() {
			return noticeSecret;
		}
		public void setNoticeSecret(int noticeSecret) {
			this.noticeSecret = noticeSecret;
		}
		public String getNoticePw() {
			return noticePw;
		}
		public void setNoticePw(String noticePw) {
			this.noticePw = noticePw;
		}
		public String getNoticePrdCode() {
			return noticePrdCode;
		}
		public void setNoticePrdCode(String noticePrdCode) {
			this.noticePrdCode = noticePrdCode;
		}
		public String getDogKind() {
			return dogKind;
		}
		public void setDogKind(String dogKind) {
			this.dogKind = dogKind;
		}
		public String getHappenCity() {
			return happenCity;
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
	
