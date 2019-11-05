package siNotice.model.noticeService;

import java.sql.Connection;
import java.util.ArrayList;

import siAdoptionBoard.model.adoptionBoardDao.AdoptionBoardDao;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siNotice.model.noticeDao.NoticeDao;
import siNotice.model.noticeVo.Notice;
import siNotice.model.noticeVo.NoticePageData;
import siNotice.model.noticeVo.NoticeViewData;
import siNoticeComment.model.noticeCommentVo.NoticeComment;
import siTemplete.JDBCTemplete;

public class NoticeService {
	public ArrayList<Notice> noticeList(){
		Connection conn = JDBCTemplete.getConnection();
		ArrayList<Notice> list = new NoticeDao().noticeAll(conn);
		JDBCTemplete.close(conn);
		return list;
	}
	public NoticePageData noticeAll(int reqPage){
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 10;
		int totalCount = new NoticeDao().totalCount(conn);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Notice> list = new NoticeDao().noticeAll(conn,start,end);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a class='paging-arrow prev-arrow' href='/siNotice?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/siNotice?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a class='paging-arrow next-arrow' href='/siNotice?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplete.close(conn);
		NoticePageData np = new NoticePageData(list,pageNavi);
		return np;
	}
	
	public int noticeInsert(Notice n) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new NoticeDao().noticeInsert(conn, n);
		if(result>0 && n.getNoticeType()==0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public NoticeViewData noticeView(int noticeNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new NoticeDao().noticeCount(conn, noticeNo);
		//카운트를 증가 시키기위해 Dao에 update를 하나 추가
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		Notice n = new NoticeDao().noticeView(conn, noticeNo);
		ArrayList<NoticeComment> list = new NoticeDao().commentAll(conn);
		JDBCTemplete.close(conn);
		NoticeViewData vd = new NoticeViewData(list,n);
		return vd;
	}
	public int noticeUpdate(int noticeNo, String noticeTitle, String noticeContent, String noticeFilename, String noticeFilepath) {
		Connection conn = JDBCTemplete.getConnection();
		int result= new NoticeDao().noticeUpdate(conn, noticeNo, noticeTitle, noticeContent, noticeFilename, noticeFilepath);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public Notice noticeUpdateOriginal(int noticeNo,Notice n) {
		Connection conn = JDBCTemplete.getConnection();
		n = new NoticeDao().noticeUpdateOriginal(conn, noticeNo, n);
		JDBCTemplete.close(conn);
		return n;
	}
	public int noticeDelete(int noticeNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result= new NoticeDao().noticeDelete(conn, noticeNo);		
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public NoticePageData noticeSearch(int reqPage, String searchType, String searchKeyword){
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 10;
		int totalCount = 0;
		if(searchType.equals("noticeName")) {
			totalCount = new NoticeDao().totalSearchNameCount(conn,searchKeyword);
		}else if(searchType.equals("noticeTitle")) {
			totalCount = new NoticeDao().totalSearchTitleCount(conn,searchKeyword);
		}
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Notice> list = new ArrayList<Notice>();
		if(searchType.equals("noticeName")) {
			list = new NoticeDao().noticeSearchName(conn,searchKeyword,start,end);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).getNoticeId());
			}
		}else if(searchType.equals("noticeTitle")) {
			list = new NoticeDao().noticeSearchTitle(conn,searchKeyword,start,end);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).getNoticeTitle());
			}
		}
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a class='paging-arrow prev-arrow' href='/siNoticeSearch?reqPage="+(pageNo-1)+"&searchWord="+searchType+"&keyword="+searchKeyword+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/siNoticeSearch?reqPage="+pageNo+"&searchWord="+searchType+"&keyword="+searchKeyword+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a class='paging-arrow next-arrow' href='/siNoticeSearch?reqPage="+pageNo+"&searchWord="+searchType+"&keyword="+searchKeyword+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		JDBCTemplete.close(conn);
		NoticePageData np = new NoticePageData(list,pageNavi);
		return np;
	}
}
