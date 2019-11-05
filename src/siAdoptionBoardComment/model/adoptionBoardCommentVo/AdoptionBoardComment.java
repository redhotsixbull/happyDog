package siAdoptionBoardComment.model.adoptionBoardCommentVo;

import java.sql.Date;

public class AdoptionBoardComment {
	private int adoptionBoardCommentNo;
	private int adoptionBoardCommentType;
	private String adoptionBoardCommentId;
	private String adoptionBoardCommentName;
	private String adoptionBoardCommentContent;
	private int adoptionBoardRef;
	private int adoptionBoardCommentRef;
	private Date adoptionBoardCommentDate;
	private String adoptionBoardCommentDate2;
	public AdoptionBoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdoptionBoardComment(int adoptionBoardCommentNo, int adoptionBoardCommentType, String adoptionBoardCommentId,
			String adoptionBoardCommentName, String adoptionBoardCommentContent, int adoptionBoardRef,
			int adoptionBoardCommentRef, Date adoptionBoardCommentDate, String adoptionBoardCommentDate2) {
		super();
		this.adoptionBoardCommentNo = adoptionBoardCommentNo;
		this.adoptionBoardCommentType = adoptionBoardCommentType;
		this.adoptionBoardCommentId = adoptionBoardCommentId;
		this.adoptionBoardCommentName = adoptionBoardCommentName;
		this.adoptionBoardCommentContent = adoptionBoardCommentContent;
		this.adoptionBoardRef = adoptionBoardRef;
		this.adoptionBoardCommentRef = adoptionBoardCommentRef;
		this.adoptionBoardCommentDate = adoptionBoardCommentDate;
		this.adoptionBoardCommentDate2 = adoptionBoardCommentDate2;
	}
	public int getAdoptionBoardCommentNo() {
		return adoptionBoardCommentNo;
	}
	public void setAdoptionBoardCommentNo(int adoptionBoardCommentNo) {
		this.adoptionBoardCommentNo = adoptionBoardCommentNo;
	}
	public int getAdoptionBoardCommentType() {
		return adoptionBoardCommentType;
	}
	public void setAdoptionBoardCommentType(int adoptionBoardCommentType) {
		this.adoptionBoardCommentType = adoptionBoardCommentType;
	}
	public String getAdoptionBoardCommentId() {
		return adoptionBoardCommentId;
	}
	public void setAdoptionBoardCommentId(String adoptionBoardCommentId) {
		this.adoptionBoardCommentId = adoptionBoardCommentId;
	}
	public String getAdoptionBoardCommentName() {
		return adoptionBoardCommentName;
	}
	public void setAdoptionBoardCommentName(String adoptionBoardCommentName) {
		this.adoptionBoardCommentName = adoptionBoardCommentName;
	}
	public String getAdoptionBoardCommentContent() {
		return adoptionBoardCommentContent;
	}
	public void setAdoptionBoardCommentContent(String adoptionBoardCommentContent) {
		this.adoptionBoardCommentContent = adoptionBoardCommentContent;
	}
	public int getAdoptionBoardRef() {
		return adoptionBoardRef;
	}
	public void setAdoptionBoardRef(int adoptionBoardRef) {
		this.adoptionBoardRef = adoptionBoardRef;
	}
	public int getAdoptionBoardCommentRef() {
		return adoptionBoardCommentRef;
	}
	public void setAdoptionBoardCommentRef(int adoptionBoardCommentRef) {
		this.adoptionBoardCommentRef = adoptionBoardCommentRef;
	}
	public Date getAdoptionBoardCommentDate() {
		return adoptionBoardCommentDate;
	}
	public void setAdoptionBoardCommentDate(Date adoptionBoardCommentDate) {
		this.adoptionBoardCommentDate = adoptionBoardCommentDate;
	}
	public String getAdoptionBoardCommentDate2() {
		return adoptionBoardCommentDate2;
	}
	public void setAdoptionBoardCommentDate2(String adoptionBoardCommentDate2) {
		this.adoptionBoardCommentDate2 = adoptionBoardCommentDate2;
	}
	
}
