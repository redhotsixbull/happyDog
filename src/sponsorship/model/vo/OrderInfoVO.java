package sponsorship.model.vo;

public class OrderInfoVO {
	
	private int rnum;
	private String no;
	private String id;
	private String name;
	private String phone;
	private String payMethod;
	private int pay;
	private int amount;
	private int status;
	private String deilveryNum;
	private String productName;
	private String sponDate;
	private String memo;
	private String post;
	private String address;
	private String email;
	private String receiveName;
	private String receivePhone;
	private String vbankName;
	private String vbankNum;
	private String vbankHolder;
	private String vbankDate;
	
	public OrderInfoVO() {
		super();
	}
	
	public OrderInfoVO(int rnum, String no, String id, String name, String phone, String payMethod, int pay, int amount,
			int status, String deilveryNum, String productName, String sponDate, String memo, String post,
			String address, String email, String receiveName, String receivePhone, String vbankName, String vbankNum,
			String vbankHolder, String vbankDate) {
		super();
		this.rnum = rnum;
		this.no = no;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.payMethod = payMethod;
		this.pay = pay;
		this.amount = amount;
		this.status = status;
		this.deilveryNum = deilveryNum;
		this.productName = productName;
		this.sponDate = sponDate;
		this.memo = memo;
		this.post = post;
		this.address = address;
		this.email = email;
		this.receiveName = receiveName;
		this.receivePhone = receivePhone;
		this.vbankName = vbankName;
		this.vbankNum = vbankNum;
		this.vbankHolder = vbankHolder;
		this.vbankDate = vbankDate;
	}

	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDeilveryNum() {
		return deilveryNum;
	}
	public void setDeilveryNum(String deilveryNum) {
		this.deilveryNum = deilveryNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSponDate() {
		return sponDate;
	}
	public void setSponDate(String sponDate) {
		this.sponDate = sponDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getVbankName() {
		return vbankName;
	}

	public void setVbankName(String vbankName) {
		this.vbankName = vbankName;
	}

	public String getVbankNum() {
		return vbankNum;
	}

	public void setVbankNum(String vbankNum) {
		this.vbankNum = vbankNum;
	}

	public String getVbankHolder() {
		return vbankHolder;
	}

	public void setVbankHolder(String vbankHolder) {
		this.vbankHolder = vbankHolder;
	}

	public String getVbankDate() {
		return vbankDate;
	}

	public void setVbankDate(String vbankDate) {
		this.vbankDate = vbankDate;
	}
	
	
	
	

}
