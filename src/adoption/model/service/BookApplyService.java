package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.BookApplyDao;
import adoption.model.vo.BookApply;
import adoption.model.vo.BookApplyPageData;
import adoption.model.vo.DogKind;
import adoption.model.vo.DogList;
import adoption.model.vo.SearchDogPageData;
import common.JDBCTemplate;
import openApi.model.vo.cityCode;

public class BookApplyService {
	//유기견 리스트 받아오기
	public SearchDogPageData dogList(int reqPage,String cityCode,String gunCode,String dogsize, String kindCd, String neuterYn) {
		if(cityCode==null) {
			cityCode="";
		}
		if(gunCode==null) {
			gunCode="";
		}
		if(kindCd==null) {
			kindCd="";
		}
		if(neuterYn==null) {
			neuterYn="";
		}
		ArrayList<DogList> list= new BookApplyDao().dogList(reqPage,cityCode,gunCode,kindCd,neuterYn);
		int numPerPage = 12;
		int totalCount = 0;
		//list.get(0).getOrgNm() 비어있을 경우 int로 파싱할때 에러가 생김
		if(list.isEmpty()) {
			totalCount = 0;
		}else {
			totalCount = Integer.parseInt(list.get(0).getOrgNm());		//totalCount
		}
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
//		System.out.println(list.get(0).getSexCd());
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/dogAdopList?city="+cityCode+"&gun="+gunCode+"&dogsize="+dogsize+"&kindCd="+kindCd+"&neuterYn="+neuterYn+"&reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {	
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/dogAdopList?city="+cityCode+"&gun="+gunCode+"&dogsize="+dogsize+"&kindCd="+kindCd+"&neuterYn="+neuterYn+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/dogAdopList?city="+cityCode+"&gun="+gunCode+"&dogsize="+dogsize+"&kindCd="+kindCd+"&neuterYn="+neuterYn+"&reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
			
		}
		SearchDogPageData sdpd = new SearchDogPageData(list,pageNavi);
		return sdpd;
	}
	
	//도시 코드, 코드 가져오기
	public ArrayList<cityCode> getCityCode() throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<cityCode> list = new BookApplyDao().getCityCode(conn);
		JDBCTemplate.close(conn);
		return list;
	}
		
	//도시코드 받아서 지역구리스트 가져오기
	public ArrayList<cityCode> getAreaCode(String cityCode) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<cityCode> list = new BookApplyDao().getCityCode(conn,cityCode);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//크기선택에 따른 품종 가져오기
	public ArrayList<DogKind> getKind(String dogsize) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<DogKind> list = new BookApplyDao().getKind(conn,dogsize);
		JDBCTemplate.close(conn);
		return list;
	}
		
	/////////////////////////////////////////////////일반회원 유기견 입양 방문 신청///////////////////////////////////////////////////////////

	//보호소 방문가능시간,보호소코드 가져오기
	public ArrayList<String> careTime(String careNm) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<String> list = new BookApplyDao().careTime(conn, careNm);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//방문예약 신청
	public int reservation(BookApply ba) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new BookApplyDao().reservation(conn, ba);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
		
	//예약된 방문 시간 구해오기
	public ArrayList<String> possibleTime(String visitDate, String careNm) throws SQLException{
		Connection conn = JDBCTemplate.getCon();
		ArrayList<String> list = new BookApplyDao().possibleTime(conn,visitDate, careNm);
		JDBCTemplate.close(conn);
		return list;
	}
	
	
	/////////////////////////////////////////////////일반회원 마이페이지///////////////////////////////////////////////////////////
	
	//일반회원 마이페이지에 방문예약 신청리스트 보여주기
	public BookApplyPageData selectList(int reqPage,String id) throws SQLException{
		Connection conn = JDBCTemplate.getCon();
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		int numPerPage = 10;
		int totalCount = new BookApplyDao().reservationCount(conn,id);
		System.out.println(totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		list = new BookApplyDao().selectList(conn, start, end, id);
		
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationMypage?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/reservationMypage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/reservationMypage?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplate.close(conn);
		BookApplyPageData bp = new BookApplyPageData(list, pageNavi);
		return bp;
	}

	//일반회원이 방문예약 신청내역 내용 확인
	public BookApply myViewOne(int no, String id) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		BookApply b = new BookApplyDao().myViewOne(conn ,no, id);
		JDBCTemplate.close(conn);
		return b;
	}
	
	//일반회원이 방문예약 신청 취소
	public int cancelReservation(int no) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new BookApplyDao().cancelReservation(conn, no);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	/////////////////////////////////////////////////보호소 회원 마이페이지///////////////////////////////////////////////////////////
	
	//보호소회원 방문예약 신청내역 리스트 확인
	public BookApplyPageData reservationCareMypage(int reqPage, String code, String startDay, String endDay) throws SQLException {
		if(startDay==null) {
			startDay="";
		}
		if(endDay==null) {
			endDay="";
		}
		Connection conn = JDBCTemplate.getCon();
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		int numPerPage = 10;
		int totalCount = new BookApplyDao().reservationCareCount(conn,code, startDay, endDay);
		System.out.println("service().reservationCareMypage() totalCount : "+totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		list = new BookApplyDao().reservationCareList(conn, start, end, code, startDay, endDay);
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationCareMypage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/reservationCareMypage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/reservationCareMypage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplate.close(conn);
		BookApplyPageData bp = new BookApplyPageData(list, pageNavi);
		return bp;
	}

	//보호소회원이 방문예약 신청내역 내용 확인
	public BookApply viewOne(int no) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		System.out.println("no: "+no);
		BookApply b = new BookApplyDao().viewOne(conn ,no);
		JDBCTemplate.close(conn);
		return b;
	}
	
	//보호소회원이 예약상태 업데이트
	public int updateStatus(int status,int no) throws SQLException {
		System.out.println("방문예약상태(service) : "+status);
		Connection conn = JDBCTemplate.getCon();
		int result = new BookApplyDao().updateStatus(conn, status, no);
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;	
	}
	
	/////////////////////////////////////////////////관리자 마이페이지///////////////////////////////////////////////////////////
	
	//관리자가 방문예약 신청 리스트 확인
	public BookApplyPageData adminReservationMypage(int reqPage, String startDay, String endDay) throws SQLException {
		if(startDay==null) {
			startDay="";
		}
		if(endDay==null) {
			endDay="";
		}
		Connection conn = JDBCTemplate.getCon();
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		int numPerPage = 10;
		int totalCount = new BookApplyDao().adminReservationCount(conn, startDay, endDay);
		System.out.println("service().adminReservationMypage() totalCount : "+totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		list = new BookApplyDao().adminReservationList(conn, start, end, startDay, endDay);
		System.out.println("Dao에서 리스트 구해옴");
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/adminReservPage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/adminReservPage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/adminReservPage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplate.close(conn);
		BookApplyPageData bp = new BookApplyPageData(list, pageNavi);
		return bp;
	}
	
	//관리자가 방문예약 신청내역 내용 확인
	public BookApply adminViewOne(int no) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		System.out.println("no: "+no);
		BookApply b = new BookApplyDao().adminViewOne(conn ,no);
		JDBCTemplate.close(conn);
		return b;
	}


}
