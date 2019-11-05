package qna.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import qna.model.dao.CommentDao;
import qna.model.vo.CommentVO;

public class CommentService {

	public CommentVO selectComment(int boardNo) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		CommentVO comment = new CommentDao().selectComment(conn,boardNo);
		JDBCTemplate.close(conn);
		return comment;
	}

	public int insertComment(CommentVO comment) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new CommentDao().insertComment(conn,comment);
		if(result>0) {
			/* 답변이 등록되면 답변 상태 업데이트 */
			result = new CommentDao().updateAnswer(conn,comment);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
