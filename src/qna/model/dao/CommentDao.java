package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import qna.model.vo.CommentVO;

public class CommentDao {

	public CommentVO selectComment(Connection conn, int boardNo) throws SQLException {
		CommentVO comment = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select board_comment_content,TO_CHAR(board_comment_date,'YYYY-MM-DD HH24:MI:SS') as time from board_comment where board_ref=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			comment = new CommentVO();
			comment.setBoardCommentContent(rset.getString("board_comment_content"));
			comment.setBoardCommentDate(rset.getString("time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return comment;
	}

	public int insertComment(Connection conn, CommentVO comment) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into board_comment values(board_comment_seq.nextval,5,null,null,?,?,null,sysdate )";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment.getBoardCommentContent());
		pstmt.setInt(2, comment.getBoardRef());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int updateAnswer(Connection conn, CommentVO comment) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update board set board_count=1 where board_no=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getBoardRef());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
