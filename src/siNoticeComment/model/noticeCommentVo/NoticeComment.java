package siNoticeComment.model.noticeCommentVo;

import java.sql.Date;

public class NoticeComment {
	private int noticeCommentNo;
	private int noticeCommentType;
	private String noticeCommentId;
	private String noticeCommentName;
	private String noticeCommentContent;
	private int noticeRef;
	private int noticeCommentRef;
	private Date noticeCommentDate;
	private String noticeCommentDate2;
	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeComment(int noticeCommentNo, int noticeCommentType, String noticeCommentId, String noticeCommentName,
			String noticeCommentContent, int noticeRef, int noticeCommentRef, Date noticeCommentDate,
			String noticeCommentDate2) {
		super();
		this.noticeCommentNo = noticeCommentNo;
		this.noticeCommentType = noticeCommentType;
		this.noticeCommentId = noticeCommentId;
		this.noticeCommentName = noticeCommentName;
		this.noticeCommentContent = noticeCommentContent;
		this.noticeRef = noticeRef;
		this.noticeCommentRef = noticeCommentRef;
		this.noticeCommentDate = noticeCommentDate;
		this.noticeCommentDate2 = noticeCommentDate2;
	}
	public int getNoticeCommentNo() {
		return noticeCommentNo;
	}
	public void setNoticeCommentNo(int noticeCommentNo) {
		this.noticeCommentNo = noticeCommentNo;
	}
	public int getNoticeCommentType() {
		return noticeCommentType;
	}
	public void setNoticeCommentType(int noticeCommentType) {
		this.noticeCommentType = noticeCommentType;
	}
	public String getNoticeCommentId() {
		return noticeCommentId;
	}
	public void setNoticeCommentId(String noticeCommentId) {
		this.noticeCommentId = noticeCommentId;
	}
	public String getNoticeCommentName() {
		return noticeCommentName;
	}
	public void setNoticeCommentName(String noticeCommentName) {
		this.noticeCommentName = noticeCommentName;
	}
	public String getNoticeCommentContent() {
		return noticeCommentContent;
	}
	public void setNoticeCommentContent(String noticeCommentContent) {
		this.noticeCommentContent = noticeCommentContent;
	}
	public int getNoticeRef() {
		return noticeRef;
	}
	public void setNoticeRef(int noticeRef) {
		this.noticeRef = noticeRef;
	}
	public int getNoticeCommentRef() {
		return noticeCommentRef;
	}
	public void setNoticeCommentRef(int noticeCommentRef) {
		this.noticeCommentRef = noticeCommentRef;
	}
	public Date getNoticeCommentDate() {
		return noticeCommentDate;
	}
	public void setNoticeCommentDate(Date noticeCommentDate) {
		this.noticeCommentDate = noticeCommentDate;
	}
	public String getNoticeCommentDate2() {
		return noticeCommentDate2;
	}
	public void setNoticeCommentDate2(String noticeCommentDate2) {
		this.noticeCommentDate2 = noticeCommentDate2;
	}
	
}
