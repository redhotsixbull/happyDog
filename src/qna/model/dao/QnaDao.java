package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.vo.QnaVO;
import sponsorship.model.vo.SearchVO;

public class QnaDao {
	
	/* 검색 쿼리 */
	public String makeQuery(SearchVO search){
		String query = "";
		if(search.getSearchVal()!=null && search.getSearchVal()!=""){
			query += " and "+search.getSearchType()+" like '%"+search.getSearchVal()+"%'";
		}
		if(search.getPrdCode()!=null && search.getPrdCode()!=""){
			query += " and board_prdcode='"+search.getPrdCode()+"'";
		}
		return query;
	}
	
	public int totalCount(Connection conn, SearchVO search) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select count(*) as cnt from board where board_type=5 "+makeQuery(search);
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}

	public ArrayList<QnaVO> selectQna(Connection conn, int start, int end, SearchVO search) throws SQLException {
		ArrayList<QnaVO> qnaList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from (select rownum rnum,b.* from (select * from board where board_type=5 "+makeQuery(search)+" order by board_date desc) b ) where rnum between ? and ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		rset = pstmt.executeQuery();
		qnaList = new ArrayList<QnaVO>();
		while(rset.next()){
			QnaVO qnaInfo = new QnaVO();
			qnaInfo.setBoardRnum(rset.getInt("rnum"));
			qnaInfo.setBoardNo(rset.getInt("board_no"));
			qnaInfo.setBoardId(rset.getString("board_id"));
			qnaInfo.setBoardName(rset.getString("board_name"));
			qnaInfo.setBoardTitle(rset.getString("board_title"));
			qnaInfo.setBoardContent(rset.getString("board_content"));
			qnaInfo.setBoardFilename(rset.getString("board_filename"));
			qnaInfo.setBoardFilepath(rset.getString("board_filepath"));
			qnaInfo.setBoardDate(rset.getDate("board_date"));
			qnaInfo.setBoardCount(rset.getInt("board_count"));
			qnaInfo.setBoardSecret(rset.getInt("board_secret"));
			qnaInfo.setBoardPrdcode(rset.getString("board_prdcode"));
			
			qnaList.add(qnaInfo);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return qnaList;
	}
	
	
	public QnaVO selectQna(Connection conn, int boardNo) throws SQLException {
		QnaVO qna = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from board where board_no=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			qna = new QnaVO();
			qna.setBoardNo(rset.getInt("board_no"));
			qna.setBoardId(rset.getString("board_id"));
			qna.setBoardName(rset.getString("board_name"));
			qna.setBoardTitle(rset.getString("board_title"));
			qna.setBoardContent(rset.getString("board_content"));
			qna.setBoardFilename(rset.getString("board_filename"));
			qna.setBoardFilepath(rset.getString("board_filepath"));
			qna.setBoardDate(rset.getDate("board_date"));
			qna.setBoardCount(rset.getInt("board_count"));
			qna.setBoardSecret(rset.getInt("board_secret"));
			qna.setBoardPrdcode(rset.getString("board_prdcode"));
			qna.setBoardPw(rset.getString("board_pw"));
			
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return qna;
	}

	public QnaVO checkPw(Connection conn, int boardNo, String boardPw) throws SQLException {
		QnaVO qna = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from board where board_no=? and board_pw=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		pstmt.setString(2, boardPw);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			qna = new QnaVO();
			qna.setBoardNo(rset.getInt("board_no"));
			qna.setBoardId(rset.getString("board_id"));
			qna.setBoardName(rset.getString("board_name"));
			qna.setBoardTitle(rset.getString("board_title"));
			qna.setBoardContent(rset.getString("board_content"));
			qna.setBoardFilename(rset.getString("board_filename"));
			qna.setBoardFilepath(rset.getString("board_filepath"));
			qna.setBoardDate(rset.getDate("board_date"));
			qna.setBoardCount(rset.getInt("board_count"));
			qna.setBoardSecret(rset.getInt("board_secret"));
			qna.setBoardPrdcode(rset.getString("board_prdcode"));
			
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return qna;
	}

	public int insertQna(Connection conn,QnaVO qna) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,sysdate,0,?,?,?,null,null,null) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, qna.getBoardType());
		pstmt.setString(2, qna.getBoardId());
		pstmt.setString(3, qna.getBoardName());
		pstmt.setString(4, qna.getBoardTitle());
		pstmt.setString(5, qna.getBoardContent());
		pstmt.setString(6, qna.getBoardFilename());
		pstmt.setString(7, qna.getBoardFilepath());
		pstmt.setInt(8, qna.getBoardSecret());
		pstmt.setString(9, qna.getBoardPw());
		pstmt.setString(10, qna.getBoardPrdcode());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int updateQna(Connection conn, QnaVO qna) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		if(qna.getBoardPw()==null) {
			sql = "update board set board_title=?,board_name=?,board_content=?,board_filename=?,board_filepath=?,board_secret=? where board_no=? and board_pw is null ";
		}else {
			sql = "update board set board_title=?,board_name=?,board_content=?,board_filename=?,board_filepath=?,board_secret=? where board_no=? and board_pw=?";
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, qna.getBoardTitle());
		pstmt.setString(2, qna.getBoardName());
		pstmt.setString(3, qna.getBoardContent());
		pstmt.setString(4, qna.getBoardFilename());
		pstmt.setString(5, qna.getBoardFilepath());
		pstmt.setInt(6, qna.getBoardSecret());
		pstmt.setInt(7, qna.getBoardNo());
		if(qna.getBoardPw()!=null) {
			pstmt.setString(8, qna.getBoardPw());
		}
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int deleteQna(Connection conn, int boardNo, String boardPw) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		if(boardPw==null) {
			sql = "delete from board where board_no=? and board_pw is null ";
		}else {
			sql = "delete from board where board_no=? and board_pw=?";
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		if(boardPw != null) {
			pstmt.setString(2, boardPw);
		}
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
