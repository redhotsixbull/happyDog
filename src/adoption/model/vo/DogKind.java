package adoption.model.vo;

public class DogKind {
	private String code;		//품종코드
	private String kind;		//품종
	private String dogsize;		//강아지크기(소,중,대,기타)
	public DogKind() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DogKind(String code, String kind, String dogsize) {
		super();
		this.code = code;
		this.kind = kind;
		this.dogsize = dogsize;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDogsize() {
		return dogsize;
	}
	public void setDogsize(String dogsize) {
		this.dogsize = dogsize;
	}
	
}
