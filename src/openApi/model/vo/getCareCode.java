package openApi.model.vo;

import java.util.ArrayList;

public class getCareCode {
	ArrayList<cityCode> list;
	String areaCode;
	public getCareCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "getCareCode [list=" + list + ", areaCode=" + areaCode + "]";
	}
	public ArrayList<cityCode> getList() {
		return list;
	}
	public void setList(ArrayList<cityCode> list) {
		this.list = list;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public getCareCode(ArrayList<cityCode> list, String areaCode) {
		super();
		this.list = list;
		this.areaCode = areaCode;
	}
	
	
}
