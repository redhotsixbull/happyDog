package finddog.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.FindDogDao;
import adoption.model.vo.DogList;
import adoption.model.vo.SearchDogPageData;
import common.JDBCTemplate;
import finddog.model.dao.SearchDogDao;
import finddog.model.vo.Kind;
import openApi.model.dao.OpenApiDao;
import siBoard.model.boardDao.BoardDao;
import siBoard.model.boardVo.Board;
import siBoard.model.boardVo.BoardPageData;
import siTemplete.JDBCTemplete;

public class SearchDogService {

	public ArrayList<Kind> getKindCode() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		ArrayList<Kind> list = new SearchDogDao().getKindCode(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public SearchDogPageData selectList(int page, String sDay, String eDay, String kind, String cityCode) {
		
		
		
		ArrayList<DogList> list= new SearchDogDao().getList(page,sDay,eDay,kind,cityCode);
		
		//네비바 생성
		String pageNavi="";
		if(page==1) {   //시작페이지 버튼
			pageNavi+="<a href='/printSearchDog?page=1'>1</a>";
			pageNavi+="<a href='/printSearchDog?page=2'>2</a>";
			pageNavi+="<a href='/printSearchDog?page=3'>3</a>";	
			pageNavi+="<a href='/printSearchDog?page=4'>4</a>";
			pageNavi+="<a href='/printSearchDog?page=5'>5</a>";
 			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}else if(page==2) {  //2번페이지 이후
			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/left_arrow.png' style='width:30px;height:30px'></a>";
			pageNavi+="<a href='/printSearchDog?page=1'>1</a>";
			pageNavi+="<a href='/printSearchDog?page=2'>2</a>";
			pageNavi+="<a href='/printSearchDog?page=3'>3</a>";	
			pageNavi+="<a href='/printSearchDog?page=4'>4</a>";
			pageNavi+="<a href='/printSearchDog?page=5'>5</a>";
 			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}else {
			pageNavi+="<a href='/printSearchDog?page="+(page-1)+"' class='paging-arrow next-arrrow'><img src='/img/left_arrow.png' style='width:30px;height:30px'></a>";
			pageNavi+="<a href='/printSearchDog?page="+(page-2)+"'>"+(page-2)+"</a>";
			pageNavi+="<a href='/printSearchDog?page="+(page-1)+"'>"+(page-1)+"</a>";
			pageNavi+="<a href='/printSearchDog?page="+page+"'>"+page+"</a>";	
			pageNavi+="<a href='/printSearchDog?page="+(page+1)+"'>"+(page+1)+"</a>";
			pageNavi+="<a href='/printSearchDog?page="+(page+2)+"'>"+(page+2)+"</a>";
 			pageNavi+="<a href='/printSearchDog?page="+(page+1)+"' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";		
		}
		SearchDogPageData sdpd = new SearchDogPageData(list,pageNavi);
		return sdpd;
	}


	public BoardPageData selectListDB(int page2, int page, String sDay, String eDay, String kind, String cityCode) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getCon();
		
		int reqPage=page;
		String pageNavi="";
		int type=3;
		int numPerPage = 4;
		int totalCount = new SearchDogDao().totalSelCount(conn,type,sDay,eDay,kind,cityCode);
		System.out.println("totalcount"+totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list= new SearchDogDao().getListDB(reqPage,sDay,eDay,kind,cityCode,conn,start,end);
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+page2+"&page="+(pageNo-1)+"'>이전</a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+page2+"&page="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+page2+"&page="+(page+1)+"'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
	}
		
	

	public int change() {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		
		int result = new SearchDogDao().change(conn);
		
		return 0;
	}

	public BoardPageData takeBoard(int reqPage, int type) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 10;
		int totalCount = new SearchDogDao().totalCount(conn,type);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list = new SearchDogDao().takeBoard(conn,start,end,type);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/takeBoard?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
	}

	public SearchDogPageData selectListAPI(int page2, int page, String sDay, String eDay, String kind, String cityCode) {
		// TODO Auto-generated method stub
		System.out.println("sDay:"+sDay+"eDay:"+eDay+"kind:"+kind+"cityCode:"+cityCode);
		ArrayList<DogList> list= new SearchDogDao().getList(page2,sDay,eDay,kind,cityCode);
		
		
		int reqPage=page2;
		String pageNavi="";
		int numPerPage = 4;
		System.out.println(page+sDay+kind+cityCode+eDay);
		int totalCount=0;
		if(list.isEmpty()) {
			totalCount=0;
		}else {
			totalCount=list.get(0).getTotalCount();
		}

		
		
		//네비바 생성
		System.out.println("API총갯수는"+totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+(pageNo-1)+"&page="+page+"'><img src='/img/left_arrow.png' style='width:30px;height:30px'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+pageNo+"&page="+page+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+(pageNo)+"&page="+page+"'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}
		
		
		SearchDogPageData sdpd = new SearchDogPageData(list,pageNavi);
		return sdpd;
	}

	public BoardPageData takeSearchBoard(int reqPage, int type, String word, int sel) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplete.getConnection();
		//sel==1이면 이름 sel==2이면 제목
		int numPerPage = 10;
		int totalCount = new SearchDogDao().totalSearchCount(conn,type,word,sel);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list = new SearchDogDao().takeSearchBoard(conn,start,end,type,word,sel);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/takeBoard?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
	}

	public BoardPageData findSearchBoard(int reqPage, int type, String word, int sel) {
		// TODO Auto-generated method stub
		
		Connection conn = JDBCTemplete.getConnection();
		//sel==1이면 이름 sel==2이면 제목
		int numPerPage = 10;
		int totalCount = new SearchDogDao().totalSearchCount(conn,type,word,sel);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list = new SearchDogDao().takeSearchBoard(conn,start,end,type,word,sel);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/takeBoard?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
	}

	public BoardPageData selectListAllDB(int page, int page2, String sDay, String eDay, String kind, String cityCode) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplete.getConnection();
		//sel==1이면 이름 sel==2이면 제목
		int reqPage=page2;
		int numPerPage = 4;
		int type=3;
		int totalCount = new SearchDogDao().totalCount(conn,type);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list = new SearchDogDao().takeBoard(conn,start,end,type);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+(page)+"&page="+(pageNo-1)+"'>이전</a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+(page)+"&page="+(pageNo)+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/printSearchDog?startDay="+sDay+"&endDay="+eDay+"&kind="+kind+"&happenCity="+cityCode+"&page2="+(page)+"&page="+(pageNo+1)+"'><img src='/img/right_arrow.png' style='width:30px;height:30px'></a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
		
		
		
	}
	


}
