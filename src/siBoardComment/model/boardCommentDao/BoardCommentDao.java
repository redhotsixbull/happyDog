package siBoardComment.model.boardCommentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import siBoardComment.model.boardCommentVo.BoardComment;
import siTemplete.JDBCTemplete;

public class BoardCommentDao {
	public int reCommentInsert(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,?,?,?,?,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, bc.getBoardCommentType());
			pstmt.setString(2, bc.getBoardCommentId());
			pstmt.setString(3, bc.getBoardCommentName());
			pstmt.setString(4, bc.getBoardCommentContent());
			pstmt.setInt(5, bc.getBoardRef());
			pstmt.setInt(6, bc.getBoardCommentRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int commentInsert(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,?,?,?,null,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, bc.getBoardCommentType());
			pstmt.setString(2, bc.getBoardCommentId());
			pstmt.setString(3, bc.getBoardCommentName());
			pstmt.setString(4, bc.getBoardCommentContent());
			pstmt.setInt(5, bc.getBoardRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int reCommentUpdate(Connection conn,String boardCommentContent,int boardCommentNo,int boardCommentRef) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_comment set board_comment_content = ? where board_comment_Ref = ? and board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, boardCommentContent);
			pstmt.setInt(2, boardCommentRef);
			pstmt.setInt(3, boardCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}		
	public int commentUpdate(Connection conn,String memberId,String boardCommentContent,int boardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_comment set board_comment_content = ? where board_comment_id = ? and board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, boardCommentContent);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, boardCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}		
	public int reCommentDelete(Connection conn,int boardCommentNo,int boardCommentRef) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete board_comment where board_comment_no = ? and board_comment_ref = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardCommentNo);
			pstmt.setInt(2, boardCommentRef);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int commentDelete(Connection conn,int boardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete board_comment where board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
}
