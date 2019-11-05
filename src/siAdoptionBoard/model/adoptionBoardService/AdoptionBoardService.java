package siAdoptionBoard.model.adoptionBoardService;

import java.sql.Connection;
import java.util.ArrayList;

import siAdoptionBoard.model.adoptionBoardDao.AdoptionBoardDao;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoardPageData;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoardViewData;
import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;
import siTemplete.JDBCTemplete;

public class AdoptionBoardService {
	public ArrayList<AdoptionBoard> adoptionBoardList(){
		Connection conn = JDBCTemplete.getConnection();
		ArrayList<AdoptionBoard> list = new AdoptionBoardDao().adoptionBoardAll(conn);
		JDBCTemplete.close(conn);
		return list;
	}
	public AdoptionBoardPageData adoptionBoardAll(int reqPage){
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 12;
		int totalCount = new AdoptionBoardDao().totalCount(conn);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<AdoptionBoard> list = new AdoptionBoardDao().adoptionBoardAll(conn,start,end);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a class='paging-arrow prev-arrow' href='/siAdoptionBoard?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/siAdoptionBoard?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a class='paging-arrow next-arrow' href='/siAdoptionBoard?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplete.close(conn);
		AdoptionBoardPageData ap = new AdoptionBoardPageData(list,pageNavi);
		return ap;
	}
	
	public int adoptionBoardInsert(AdoptionBoard a) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new AdoptionBoardDao().adoptionBoardInsert(conn, a);
		if(result>0 && a.getAdoptionBoardType()==2) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public AdoptionBoardViewData adoptionBoardView(int adoptionBoardNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new AdoptionBoardDao().adoptionBoardCount(conn, adoptionBoardNo);
		//카운트를 증가 시키기위해 Dao에 update를 하나 추가
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		AdoptionBoard a = new AdoptionBoardDao().adoptionBoardView(conn, adoptionBoardNo);
		ArrayList<AdoptionBoardComment> list = new AdoptionBoardDao().commentAll(conn);
		JDBCTemplete.close(conn);
		AdoptionBoardViewData vd = new AdoptionBoardViewData(list,a);
		return vd;
	}
	public int adoptionBoardUpdate(int adoptionBoardNo, String adoptionBoardTitle, String adoptionBoardContent, String adoptionBoardFilename, String adoptionBoardFilepath) {
		Connection conn = JDBCTemplete.getConnection();
		int result= new AdoptionBoardDao().adoptionBoardUpdate(conn, adoptionBoardNo, adoptionBoardTitle, adoptionBoardContent, adoptionBoardFilename, adoptionBoardFilepath);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public AdoptionBoard adoptionBoardUpdateOriginal(int adoptionBoardNo,AdoptionBoard a) {
		Connection conn = JDBCTemplete.getConnection();
		a = new AdoptionBoardDao().adoptionBoardUpdateOriginal(conn, adoptionBoardNo, a);
		JDBCTemplete.close(conn);
		return a;
	}
	public int adoptionBoardDelete(int adoptionBoardNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result= new AdoptionBoardDao().adoptionBoardDelete(conn, adoptionBoardNo);		
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public AdoptionBoardPageData adoptionBoardSearch(int reqPage, String searchType, String searchKeyword){
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 12;
		int totalCount = 0;
		if(searchType.equals("adoptionBoardName")) {
			totalCount = new AdoptionBoardDao().totalSearchNameCount(conn,searchKeyword);
		}else if(searchType.equals("adoptionBoardTitle")) {
			totalCount = new AdoptionBoardDao().totalSearchTitleCount(conn,searchKeyword);
		}
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<AdoptionBoard> list = new ArrayList<AdoptionBoard>();
		if(searchType.equals("adoptionBoardName")) {
			list = new AdoptionBoardDao().adoptionBoardSearchName(conn,searchKeyword,start,end);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).getAdoptionBoardId());
			}
		}else if(searchType.equals("adoptionBoardTitle")) {
			list = new AdoptionBoardDao().adoptionBoardSearchTitle(conn,searchKeyword,start,end);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).getAdoptionBoardTitle());
			}
		}
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a class='paging-arrow prev-arrow' href='/siAdoptionBoardSearch?reqPage="+(pageNo-1)+"&searchWord="+searchType+"&keyword="+searchKeyword+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/siAdoptionBoardSearch?reqPage="+pageNo+"&searchWord="+searchType+"&keyword="+searchKeyword+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a class='paging-arrow next-arrow' href='/siAdoptionBoardSearch?reqPage="+pageNo+"&searchWord="+searchType+"&keyword="+searchKeyword+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplete.close(conn);
		AdoptionBoardPageData ap = new AdoptionBoardPageData(list,pageNavi);
		return ap;
	}
}
