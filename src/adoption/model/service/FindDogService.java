package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.FindDogDao;
import adoption.model.vo.DogList;
import adoption.model.vo.SearchDogPageData;
import common.JDBCTemplate;
import finddog.model.dao.SearchDogDao;
import finddog.model.vo.Kind;
import openApi.model.vo.cityCode;

public class FindDogService {
	
	public SearchDogPageData selectList(int page) {
		// TODO Auto-generated method stub
		ArrayList<DogList> list= new FindDogDao().getList(page);
		
		//네비바 생성
		String pageNavi="";
		if(page==1) {   //시작페이지 버튼
			pageNavi+="<a href='/searchDog?page=1'>1</a>";
			pageNavi+="<a href='/searchDog?page=2'>2</a>";
			pageNavi+="<a href='/searchDog?page=3'>3</a>";	
			pageNavi+="<a href='/searchDog?page=4'>4</a>";
			pageNavi+="<a href='/searchDog?page=5'>5</a>";
 			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'</a>";
		}else if(page==2) {  //2번페이지 이후
			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/left_arrow.png' style='width:30px;height:30px'</a>";
			pageNavi+="<a href='/searchDog?page=1'>1</a>";
			pageNavi+="<a href='/searchDog?page=2'>2</a>";
			pageNavi+="<a href='/searchDog?page=3'>3</a>";	
			pageNavi+="<a href='/searchDog?page=4'>4</a>";
			pageNavi+="<a href='/searchDog?page=5'>5</a>";
 			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'</a>";
		}else {
			pageNavi+="<a href='/searchDog?page="+(page-1)+"' class='paging-arrow next-arrrow'><img src='/img/left_arrow.png' style='width:30px;height:30px'</a>";
			pageNavi+="<a href='/searchDog?page="+(page-2)+"'>"+(page-2)+"</a>";
			pageNavi+="<a href='/searchDog?page="+(page-1)+"'>"+(page-1)+"</a>";
			pageNavi+="<a href='/searchDog?page="+page+"'>"+page+"</a>";	
			pageNavi+="<a href='/searchDog?page="+(page+1)+"'>"+(page+1)+"</a>";
			pageNavi+="<a href='/searchDog?page="+(page+2)+"'>"+(page+2)+"</a>";
 			pageNavi+="<a href='/searchDog?page="+(page+1)+"' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'</a>";		
		}
		SearchDogPageData sdpd = new SearchDogPageData(list,pageNavi);
		return sdpd;
	}

	public ArrayList<cityCode> getCityCode() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		ArrayList<cityCode> list = new FindDogDao().getCityCode(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	

}
