package sponsorship.model.vo;

public class ProductVO {
	
	private String prdCode;
	private String prdName;
	private String prdImg;
	private String prdPrice;
	private String prdCon;
	public ProductVO() {
		super();
		
	}
	public ProductVO(int i) {
		switch (i) {
		case 0:
			this.prdCode = "0";
			this.prdName = "해피투게독 후원 에코백";
			this.prdImg = "76896814691427225_1127979769.jpg";
			this.prdPrice = "20000";
			this.prdCon = "&lt;해피투게독 라이프백&gt; 에는 사람과 동물이 손을 맞잡은 해피투게독의 의미가 담겨있습니다.\r\n" + 
					"따듯한 봄, 해피투게독 라이프백과 함께하세요.";
			break;
			
		case 1:
			this.prdCode = "1";
			this.prdName = "해피투게독 후원 뱃지 무지개 다리";
			this.prdImg = "39066105050978558_-1615663619.jpg";
			this.prdPrice = "10000";
			this.prdCon = "해피투게독은 굿즈를 통해 일상 속에서 사지않고 입양하는 문화를 알리고, 수익금으로 유기동물 입양을 위한 환경을 만들어가고 있습니다. \r\n" + 
					"해피투게독 뱃지와 함께하세요.";
			break;
			
		case 2:
			this.prdCode = "2";
			this.prdName = "해피투게독 캘린더";
			this.prdImg = "prd_img01.jpg";
			this.prdPrice = "15000";
			this.prdCon = "해피투게독은 굿즈를 통해 일상 속에서 사지않고 입양하는 문화를 알리고, 수익금으로 유기동물 입양을 위한 환경을 만들어가고 있습니다. \r\n" + 
					"해피투게독 캘린더와 함께하세요.";
			break;

		default:
			break;
		}
	}
	public ProductVO(String prdCode, String prdName, String prdImg, String prdPrice, String prdCon) {
		super();
		this.prdCode = prdCode;
		this.prdName = prdName;
		this.prdImg = prdImg;
		this.prdPrice = prdPrice;
		this.prdCon = prdCon;
	}
	public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getPrdImg() {
		return prdImg;
	}
	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}
	public String getPrdPrice() {
		return prdPrice;
	}
	public void setPrdPrice(String prdPrice) {
		this.prdPrice = prdPrice;
	}
	public String getPrdCon() {
		return prdCon;
	}
	public void setPrdCon(String prdCon) {
		this.prdCon = prdCon;
	}
	
	
	
	
	

}
