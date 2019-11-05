package siBoardComment.model.boardCommentService;

import java.sql.Connection;
import siBoardComment.model.boardCommentDao.BoardCommentDao;
import siBoardComment.model.boardCommentVo.BoardComment;
import siTemplete.JDBCTemplete;

public class BoardCommentService {
	public int reCommentInsert(BoardComment bc) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().reCommentInsert(conn,bc);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentInsert(BoardComment bc) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().commentInsert(conn,bc);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int reCommentUpdate(String boardCommentContent,int boardCommentNo,int boardCommentRef) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().reCommentUpdate(conn,boardCommentContent,boardCommentNo,boardCommentRef);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentUpdate(String memberId,String boardCommentContent,int boardCommentNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().commentUpdate(conn,memberId,boardCommentContent,boardCommentNo);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int reCommentDelete(int boardCommentNo,int boardCommentRef) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().reCommentDelete(conn,boardCommentNo,boardCommentRef);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentDelete(int boardCommentNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().commentDelete(conn,boardCommentNo);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
}
