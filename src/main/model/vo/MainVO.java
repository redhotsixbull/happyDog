package main.model.vo;

import java.util.ArrayList;

import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siNotice.model.noticeVo.Notice;
import volunteer.model.vo.VoluntaryRegister;

public class MainVO {

	private ArrayList<AdoptionBoard> adoptionBoardList;
	private ArrayList<Notice> noticeList;
	private ArrayList<VoluntaryRegister> volunList;
	public MainVO() {
		super();
	}
	public MainVO(ArrayList<AdoptionBoard> adoptionBoardList, ArrayList<Notice> noticeList,
			ArrayList<VoluntaryRegister> volunList) {
		super();
		this.adoptionBoardList = adoptionBoardList;
		this.noticeList = noticeList;
		this.volunList = volunList;
	}
	public ArrayList<AdoptionBoard> getAdoptionBoardList() {
		return adoptionBoardList;
	}
	public void setAdoptionBoardList(ArrayList<AdoptionBoard> adoptionBoardList) {
		this.adoptionBoardList = adoptionBoardList;
	}
	public ArrayList<Notice> getNoticeList() {
		return noticeList;
	}
	public void setNoticeList(ArrayList<Notice> noticeList) {
		this.noticeList = noticeList;
	}
	public ArrayList<VoluntaryRegister> getVolunList() {
		return volunList;
	}
	public void setVolunList(ArrayList<VoluntaryRegister> volunList) {
		this.volunList = volunList;
	}
	
	
}
