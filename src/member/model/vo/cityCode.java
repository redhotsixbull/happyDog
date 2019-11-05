package member.model.vo;

public class cityCode {
	private String cityCode; //시
	private String cityName; //시이름
	private String district; //지역구
	private String districtName; //지역구 코드
	public cityCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public cityCode(String cityCode, String cityName, String district, String districtName) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.district = district;
		this.districtName = districtName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Override
	public String toString() {
		return "cityCode [cityCode=" + cityCode + ", cityName=" + cityName + ", district=" + district
				+ ", districtName=" + districtName + "]";
	}
	
	

}
