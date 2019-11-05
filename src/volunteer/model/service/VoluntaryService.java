package volunteer.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import volunteer.model.dao.VoluntaryDao;
import volunteer.model.vo.VoluntaryApplyBoard;
import volunteer.model.vo.VoluntaryApplyData;
import volunteer.model.vo.VoluntaryListData;
import volunteer.model.vo.VoluntaryRegister;

public class VoluntaryService {

	// 봉사활동 공고 등록
	public int insertVoluntaryRegister(VoluntaryRegister vr) {
		Connection conn = JDBCTemplate.getCon();
		vr.setDetail(vr.getDetail().replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\r\n", "<br>"));
		int result = new VoluntaryDao().insertVoluntaryRegister(conn, vr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//봉사활동 리스트+페이징
	public VoluntaryListData voluntaryList(int reqPage) {
		Connection conn = JDBCTemplate.getCon();
		
		int numPerPage = 10;
		int totalCount = new VoluntaryDao().totalCount(conn);
		int totalPage = (totalCount%numPerPage == 0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<VoluntaryRegister> list = new VoluntaryDao().voluntaryList(conn,start,end);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/volunteerList?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		int i = 1;
		while( !(i++>pageNaviSize || pageNo>totalPage) ) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='' href='/volunteerList?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='/volunteerList?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		VoluntaryListData vld = new VoluntaryListData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return vld;
	}

	// 봉사활동 신청 게시판 :: 검색 + 페이징
	public VoluntaryListData searchVoluntary(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getCon();
		
		int numPerPage = 10;
		int totalCount = 0;
		switch(type) {
			case "title": totalCount = new VoluntaryDao().totalCountTitle(conn, keyword); break;
			case "name": totalCount = new VoluntaryDao().totalCountName(conn, keyword); break;
		}
		int totalPage = (totalCount%numPerPage == 0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		
		ArrayList<VoluntaryRegister> list = null;
		switch(type) {
			case "title": list = new VoluntaryDao().searchKeywordTitle(conn, keyword, start, end); break;
			case "name": list = new VoluntaryDao().searchKeywordName(conn, keyword, start, end); break;
		}
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/searchVoluntary?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		int i = 1;
		while( !(i++>pageNaviSize || pageNo>totalPage) ) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='' href='/searchVoluntary?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='/searchVoluntary?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		VoluntaryListData vld = new VoluntaryListData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return vld;
	}

	// 봉사활동 뷰
	public VoluntaryRegister voluntaryView(int no) {
		Connection conn = JDBCTemplate.getCon();
		VoluntaryRegister vr = new VoluntaryDao().voluntaryView(conn, no);
		//vr.setDetail(vr.getDetail().replaceAll("<br>", "\r\n"));
		JDBCTemplate.close(conn);
		return vr;
	}

	// 봉사활동 공고 수정
	public int updateVoluntary(VoluntaryRegister vr) {
		Connection conn = JDBCTemplate.getCon();
		//vr.setDetail(vr.getDetail().replaceAll("<br>", "\r\n"));
		vr.setDetail(vr.getDetail().replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\r\n", "<br>"));
		int result = new VoluntaryDao().updateVoluntary(conn, vr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 봉사활동 공고 삭제
	public int deleteVoluntary(int no) {
		Connection conn = JDBCTemplate.getCon();
		int result = new VoluntaryDao().deleteVoluntary(conn, no);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 봉사활동 신청
	public int voluntaryApply(VoluntaryApplyBoard vab, int possiblePerson, int currentApplyNum) { // possiblePerson 봉사 가능 인원수, currentApplyNum 현재까지 신청한  인원수
		Connection conn = JDBCTemplate.getCon();
		//int possible = new VoluntaryDao().totalApply(conn, vab); //이미 신청된 인원수 파악
		//int possible = vab.getApplyNo();
		System.out.println("현재까지 신청한  인원수2 : "+currentApplyNum);
		
		int result = 0; //신청
		int currentPerson = 0;//신청 누적 인원수
		
		if(possiblePerson > currentApplyNum && (possiblePerson - currentApplyNum) >= vab.getPerson()) { 
			//봉사 가능 인원수 > 해당공고에 현재까지 신청된 인원수 && (봉사 가능 인원수 - 해당공고에 현재까지 신청된 인원수) >= 신청한 인원 수
			System.out.println("(봉사 가능 인원수 - 해당공고에 현재까지 신청된 인원수) >= 신청한 인원 수 : "+((possiblePerson - currentApplyNum) > vab.getPerson()));
			System.out.println("봉사 가능 인원수 : "+possiblePerson);
			System.out.println("해당공고에 현재까지 신청된 인원수1(currentApplyNum) : "+currentApplyNum);
			System.out.println("내가 신청한 인원 수 : "+vab.getPerson());
			
			result = new VoluntaryDao().voluntaryApply(conn, vab); //성공 1
			if(result > 0) {				
				currentApplyNum = currentApplyNum+vab.getPerson();
				System.out.println("신청 완료 후 총 신청 인원수1(currentApplyNum) : "+currentApplyNum);
				
				currentPerson = new VoluntaryDao().voluntaryCurrentPerson(conn, vab, currentApplyNum);
				
				if(currentPerson > 0) { //신청 성공 시
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}			
			}else {
				currentPerson = 0;
			}	
		}else {
			currentPerson = -1;
		}
		JDBCTemplate.close(conn);
		
		return currentPerson;
	}

	// 마이페이지 :: 일반회원 봉사활동 신청내역
	public VoluntaryApplyData myVoluntaryList(int reqPage, String id) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int numPerPage = 10;
		int totalCount = new VoluntaryDao().totalMyApplyCount(conn, id);
		int totalPage = (totalCount%numPerPage == 0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage+numPerPage;
		ArrayList<VoluntaryApplyBoard> list = new VoluntaryDao().myVoluntaryList(conn,id,start,end);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/voluntaryApplyList?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		int i = 1;
		while( !(i++>pageNaviSize || pageNo>totalPage) ) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='' href='/voluntaryApplyList?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='/voluntaryApplyList?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		VoluntaryApplyData vad = new VoluntaryApplyData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return vad;
	}

	// 마이페이지 :: 보호소회원 봉사활동 공고등록내역
	public VoluntaryListData voluntaryList(int reqPage, String id) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		
		int numPerPage = 10;
		int totalCount = new VoluntaryDao().totalCount(conn, id);
		int totalPage = (totalCount%numPerPage == 0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<VoluntaryRegister> list = new VoluntaryDao().voluntaryList(conn,id,start,end);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/volunteerMyList?id="+id+"&reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		int i = 1;
		while( !(i++>pageNaviSize || pageNo>totalPage) ) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='' href='/volunteerMyList?id="+id+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='/volunteerMyList?id="+id+"&reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		VoluntaryListData vld = new VoluntaryListData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return vld;
	}

	//해당공고에 신청한 사람들 목록
	public ArrayList<VoluntaryApplyBoard> VoluntaryApplyPerson(int no) {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<VoluntaryApplyBoard> list = new VoluntaryDao().VoluntaryApplyPerson(conn, no);
		JDBCTemplate.close(conn);
		return list;
	}

	/* 메인 :: 봉사활동 게시판 공고 노출 */
	public ArrayList<VoluntaryRegister> mainVoluntaryList() {
		Connection conn = JDBCTemplate.getCon();
		int start = 1;
		int end = 5;
		ArrayList<VoluntaryRegister> volunList = new VoluntaryDao().mainVoluntaryList(conn, start, end);
		JDBCTemplate.close(conn);
		return volunList;
	}

	
	
	

	
	

	

	
	
	
	

}
