package siNoticeComment.model.noticeCommentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import siNoticeComment.model.noticeCommentVo.NoticeComment;
import siTemplete.JDBCTemplete;

public class NoticeCommentDao {
	public int reCommentInsert(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,?,?,?,?,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, nc.getNoticeCommentType());
			pstmt.setString(2, nc.getNoticeCommentId());
			pstmt.setString(3, nc.getNoticeCommentName());
			pstmt.setString(4, nc.getNoticeCommentContent());
			pstmt.setInt(5, nc.getNoticeRef());
			pstmt.setInt(6, nc.getNoticeCommentRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int commentInsert(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,?,?,?,null,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, nc.getNoticeCommentType());
			pstmt.setString(2, nc.getNoticeCommentId());
			pstmt.setString(3, nc.getNoticeCommentName());
			pstmt.setString(4, nc.getNoticeCommentContent());
			pstmt.setInt(5, nc.getNoticeRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int reCommentUpdate(Connection conn,String noticeCommentContent,int noticeCommentNo,int noticeCommentRef) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_comment set board_comment_content = ? where board_comment_Ref = ? and board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, noticeCommentContent);
			pstmt.setInt(2, noticeCommentRef);
			pstmt.setInt(3, noticeCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}		
	public int commentUpdate(Connection conn,String memberId,String noticeCommentContent,int noticeCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_comment set board_comment_content = ? where board_comment_id = ? and board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, noticeCommentContent);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, noticeCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}			
	public int reCommentDelete(Connection conn,int noticeCommentNo,int noticeCommentRef) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete board_comment where board_comment_no = ? and board_comment_ref = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, noticeCommentNo);
			pstmt.setInt(2, noticeCommentRef);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int commentDelete(Connection conn,int noticeCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete board_comment where board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, noticeCommentNo);
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
