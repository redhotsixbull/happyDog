package member.model.vo;

public class Member {
	private String id;
	private String pw;
	private String code;
	private String name;
	private String phone;
	private String post;
	private String address;
	private String possibleTime;
	private String email;
	private int memberLevel;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String id, String pw, String code, String name, String phone, String post, String address,
			String possibleTime, String email, int memberLevel) {
		super();
		this.id = id;
		this.pw = pw;
		this.code = code;
		this.name = name;
		this.phone = phone;
		this.post = post;
		this.address = address;
		this.possibleTime = possibleTime;
		this.email = email;
		this.memberLevel = memberLevel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPossibleTime() {
		return possibleTime;
	}
	public void setPossibleTime(String possibleTime) {
		this.possibleTime = possibleTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getStartTime() {
		String startTime = possibleTime.substring(0,2);
		return startTime;
	}
	public String getEndTime() {
		String endTime = possibleTime.substring(4, 6);
		return endTime;
	}
	public String getUser() {
		int num = this.memberLevel;
		String user = "";
		if(num == 0){
			user = "일반회원";
		}else if(num ==1){
			user = "보호소 회원";
		}
		return user;
	}
	public String getMainAddress() {
		String[] address = this.address.split("//");
		return address[0];
	}
	public String getDetailAddress() {
		String[] address = this.address.split("//");
		return address[1];
	}
	public String getPhone1() {
		String[] phone = this.phone.split("-");
		return phone[0];
	}
	public String getPhone2() {
		String[] phone = this.phone.split("-");
		return phone[1];
	}
	public String getPhone3() {
		String[] phone = this.phone.split("-");
		return phone[2];
	}
	
}
