package qna.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.dao.QnaDao;
import qna.model.vo.QnaListVO;
import qna.model.vo.QnaVO;
import sponsorship.model.vo.SearchVO;

public class QnaService {

	public QnaListVO selectQna(SearchVO search) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int reqPage = search.getReqPage();
		int total = 0;
		total = new QnaDao().totalCount(conn,search);
		int pageNum = 10;
		int totalPage = (total%pageNum==0)?(total/pageNum):(total/pageNum)+1;
		int start = (reqPage*pageNum-pageNum)+1;
		int end  = reqPage*pageNum;
		/* 리스트 */
		ArrayList<QnaVO> qnainfoList = new QnaDao().selectQna(conn,start,end,search);
		
		/* 페이지 네비 */
		int totalNavi = 5;
		String pageNavi = "";
		int pageNo = ((reqPage-1)/totalNavi)*totalNavi+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='javascript:list("+(pageNo-1)+");'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a> ";	
		}
		int i = 1;
		while(!(i++>totalNavi || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<a class='cur'>"+pageNo+"</a> ";
			}else {
				pageNavi += "<a href='javascript:list("+pageNo+");'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='javascript:list("+pageNo+");'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}

		QnaListVO qnaList = new QnaListVO(qnainfoList, pageNavi);
		JDBCTemplate.close(conn);
		return qnaList;
	}
	
	/* 상품 상세페이지에 노출될 Q&A리스트 */
	public ArrayList<QnaVO> selectQna(int start, int end, SearchVO search) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<QnaVO> qnainfoList = new QnaDao().selectQna(conn,start,end,search);
		JDBCTemplate.close(conn);
		return qnainfoList;
	}
	
	

	public QnaVO selectQna(int boardNo) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		QnaVO qna = new QnaDao().selectQna(conn, boardNo);
		JDBCTemplate.close(conn);
		return qna;
	}

	public QnaVO checkPw(int boardNo, String boardPw) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		QnaVO qna = new QnaDao().checkPw(conn, boardNo, boardPw);
		JDBCTemplate.close(conn);
		return qna;
	}

	public int insertQna(QnaVO qna) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new QnaDao().insertQna(conn,qna);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateQna(QnaVO qna) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new QnaDao().updateQna(conn,qna);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteQna(int boardNo, String boardPw) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new QnaDao().deleteQna(conn,boardNo,boardPw);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



	

}
