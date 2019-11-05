package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.PrintShelterDao;
import adoption.model.vo.Shelter;
import adoption.model.vo.ShelterPageData;
import common.JDBCTemplate;
import member.model.vo.Member;
import member.model.vo.MemberPageData;

public class PrintShelterService {

	public MemberPageData printShelter(int city,int reqPage) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		String pageNavi ="";
		int numPerPage =10;
		int totalCount = new PrintShelterDao().totalCount(conn,city);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		int start =(reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		
		MemberPageData spd = new PrintShelterDao().selectList(city,conn,start,end);
		
		int pageNaviSize =5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi +="<a class='paging-arrow prev-arrow' href='/printShelter?page="+(pageNo-1)+"&city="+city+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";	
		}
		
		int i =1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/printShelter?page="+pageNo+"&city="+city+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/printShelter?page="+(pageNo)+"&city="+city+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		spd.setPageNavi(pageNavi);
		
		JDBCTemplate.close(conn);

		
		return spd;
	}

	public MemberPageData getSearchName(String key, int reqPage) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getCon();
		String pageNavi ="";
		int numPerPage =10;
		int totalCount = new PrintShelterDao().searchNameCount(conn,key);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start =(reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Member> list = new PrintShelterDao().getSearchName(conn,key,start,end);
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/nameSearchShelter?page="+(pageNo-1)+"&keyword="+key+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/nameSearchShelter?page="+pageNo+"&keyword="+key+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/nameSearchShelter?page="+pageNo+"&keyword="+key+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplate.close(conn);
		JDBCTemplate.close(conn);
		MemberPageData mpd = new MemberPageData(list,pageNavi);
		return mpd;
		
	}

	public MemberPageData printShelterFirst(int reqPage) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		String pageNavi ="";
		int numPerPage =10;
		int totalCount = new PrintShelterDao().totalCountFirst(conn);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		int start =(reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		
		MemberPageData spd = new PrintShelterDao().selectListFirst(conn,start,end);
		
		int pageNaviSize =5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi +="<a class='paging-arrow prev-arrow' href='/findShelter?page="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";	
		}
		
		int i =1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/findShelter?page="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/findShelter?page="+(pageNo)+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		spd.setPageNavi(pageNavi);
		
		JDBCTemplate.close(conn);

		
		return spd;
	}

}
