package siNotice.model.noticeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import siNotice.model.noticeVo.Notice;
import siNoticeComment.model.noticeCommentVo.NoticeComment;
import siTemplete.JDBCTemplete;

public class NoticeDao {
	public ArrayList<Notice> noticeAll(Connection conn){
		ArrayList<Notice> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD where board_Type=0 ORDER BY BOARD_DATE desc) n)";
		try {
			stmt=conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Notice>();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeRnum(rset.getInt("rnum"));
				n.setNoticeNo(rset.getInt("board_no"));
				n.setNoticeType(rset.getInt("board_Type"));
				n.setNoticeId(rset.getString("board_id"));
				n.setNoticeName(rset.getString("board_Name"));
				n.setNoticeTitle(rset.getString("board_title"));
				n.setNoticeContent(rset.getString("board_content"));
				n.setNoticeFilename(rset.getString("board_filename"));
				n.setNoticeFilepath(rset.getString("board_filepath"));
				n.setNoticeDate(rset.getDate("board_date"));
				n.setNoticeDate2(rset.getString("board_date2"));
				n.setNoticeCount(rset.getInt("board_count"));
				n.setNoticeSecret(rset.getInt("board_secret"));
				n.setNoticePw(rset.getString("board_pw"));
				n.setNoticePrdCode(rset.getString("board_prdCode"));
				n.setDogKind(rset.getString("dog_kind"));
				n.setHappenCity(rset.getString("happen_City"));
				n.setHappenDate(rset.getString("happen_date"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(stmt);
		}
		return list;
	}
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from board where board_type = 0";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt("cnt");
				/*if(result == 0) {
					result += 1;
					// 게시물이 0개일 때 1페이지로 표시되도록 
				}*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(stmt);
		}
		return result;
	}
	public ArrayList<Notice> noticeAll(Connection conn,int start,int end){
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD where board_Type=0 ORDER BY BOARD_DATE desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Notice>();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeRnum(rset.getInt("rnum"));
				n.setNoticeNo(rset.getInt("board_no"));
				n.setNoticeType(rset.getInt("board_Type"));
				n.setNoticeId(rset.getString("board_id"));
				n.setNoticeName(rset.getString("board_Name"));
				n.setNoticeTitle(rset.getString("board_title"));
				n.setNoticeContent(rset.getString("board_content"));
				n.setNoticeFilename(rset.getString("board_filename"));
				n.setNoticeFilepath(rset.getString("board_filepath"));
				n.setNoticeDate(rset.getDate("board_date"));
				n.setNoticeDate2(rset.getString("board_date2"));
				n.setNoticeCount(rset.getInt("board_count"));
				n.setNoticeSecret(rset.getInt("board_secret"));
				n.setNoticePw(rset.getString("board_pw"));
				n.setNoticePrdCode(rset.getString("board_prdCode"));
				n.setDogKind(rset.getString("dog_kind"));
				n.setHappenCity(rset.getString("happen_City"));
				n.setHappenDate(rset.getString("happen_date"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}
	public int noticeInsert(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,sysdate,0,0,null,null,null,null,null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, n.getNoticeType());
			pstmt.setString(2, n.getNoticeId());
			pstmt.setString(3, n.getNoticeName());
			pstmt.setString(4, n.getNoticeTitle());
			pstmt.setString(5, n.getNoticeContent());
			pstmt.setString(6, n.getNoticeFilename());
			pstmt.setString(7, n.getNoticeFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int noticeCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_count = board_count+1 where board_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public Notice noticeView(Connection conn, int noticeNo){
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd HH24:mi') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("board_no"));
				n.setNoticeType(rset.getInt("board_Type"));
				n.setNoticeId(rset.getString("board_id"));
				n.setNoticeName(rset.getString("board_Name"));
				n.setNoticeTitle(rset.getString("board_title"));
				n.setNoticeContent(rset.getString("board_content"));
				n.setNoticeFilename(rset.getString("board_filename"));
				n.setNoticeFilepath(rset.getString("board_filepath"));
				n.setNoticeDate(rset.getDate("board_date"));
				n.setNoticeDate2(rset.getString("board_date2"));
				n.setNoticeCount(rset.getInt("board_count"));
				n.setNoticeSecret(rset.getInt("board_secret"));
				n.setNoticePw(rset.getString("board_pw"));
				n.setNoticePrdCode(rset.getString("board_PrdCode"));
				n.setDogKind(rset.getString("dog_kind"));
				n.setHappenCity(rset.getString("happen_City"));
				n.setHappenDate(rset.getString("happen_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return n;
	}
	public int noticeUpdate(Connection conn, int noticeNo, String noticeTitle, String noticeContent, String noticeFilename, String noticeFilepath) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_title=?, board_content=?, board_filename=?, board_filepath=? where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeTitle);
			pstmt.setString(2, noticeContent);
			pstmt.setString(3, noticeFilename);
			pstmt.setString(4, noticeFilepath);
			pstmt.setInt(5, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public Notice noticeUpdateOriginal(Connection conn, int noticeNo,Notice n) {
		n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("board_no"));
				n.setNoticeType(rset.getInt("board_Type"));
				n.setNoticeId(rset.getString("board_id"));
				n.setNoticeName(rset.getString("board_Name"));
				n.setNoticeTitle(rset.getString("board_title"));
				n.setNoticeContent(rset.getString("board_content"));
				n.setNoticeFilename(rset.getString("board_filename"));
				n.setNoticeFilepath(rset.getString("board_filepath"));
				n.setNoticeDate(rset.getDate("board_date"));
				n.setNoticeCount(rset.getInt("board_count"));
				n.setNoticeSecret(rset.getInt("board_secret"));
				n.setNoticePw(rset.getString("board_pw"));
				n.setNoticePrdCode(rset.getString("board_prdCode"));
				n.setDogKind(rset.getString("dog_kind"));
				n.setHappenCity(rset.getString("happen_City"));
				n.setHappenDate(rset.getString("happen_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return n;
	}
	public int noticeDelete(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public ArrayList<Notice> noticeSearchName(Connection conn,String searchKeyword,int start,int end){
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM board where board_name = ? and board_Type=0 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Notice>();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeRnum(rset.getInt("rnum"));
				n.setNoticeNo(rset.getInt("board_no"));
				n.setNoticeType(rset.getInt("board_Type"));
				n.setNoticeId(rset.getString("board_id"));
				n.setNoticeName(rset.getString("board_Name"));
				n.setNoticeTitle(rset.getString("board_title"));
				n.setNoticeContent(rset.getString("board_content"));
				n.setNoticeFilename(rset.getString("board_filename"));
				n.setNoticeFilepath(rset.getString("board_filepath"));
				n.setNoticeDate(rset.getDate("board_date"));
				n.setNoticeDate2(rset.getString("board_date2"));
				n.setNoticeCount(rset.getInt("board_count"));
				n.setNoticeSecret(rset.getInt("board_secret"));
				n.setNoticePw(rset.getString("board_pw"));
				n.setNoticePrdCode(rset.getString("board_prdCode"));
				n.setDogKind(rset.getString("dog_kind"));
				n.setHappenCity(rset.getString("happen_City"));
				n.setHappenDate(rset.getString("happen_date"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}
	public ArrayList<Notice> noticeSearchTitle(Connection conn,String searchKeyword,int start,int end){
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM board where board_title like ? and board_Type=0 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Notice>();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeRnum(rset.getInt("rnum"));
				n.setNoticeNo(rset.getInt("board_no"));
				n.setNoticeType(rset.getInt("board_Type"));
				n.setNoticeId(rset.getString("board_id"));
				n.setNoticeName(rset.getString("board_Name"));
				n.setNoticeTitle(rset.getString("board_title"));
				n.setNoticeContent(rset.getString("board_content"));
				n.setNoticeFilename(rset.getString("board_filename"));
				n.setNoticeFilepath(rset.getString("board_filepath"));
				n.setNoticeDate(rset.getDate("board_date"));
				n.setNoticeDate2(rset.getString("board_date2"));
				n.setNoticeCount(rset.getInt("board_count"));
				n.setNoticeSecret(rset.getInt("board_secret"));
				n.setNoticePw(rset.getString("board_pw"));
				n.setNoticePrdCode(rset.getString("board_prdCode"));
				n.setDogKind(rset.getString("dog_kind"));
				n.setHappenCity(rset.getString("happen_City"));
				n.setHappenDate(rset.getString("happen_date"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}	
	public int totalSearchTitleCount(Connection conn,String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from board where board_title like ? and board_Type=0";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int totalSearchNameCount(Connection conn,String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from board where board_name=? and board_Type=0";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public ArrayList<NoticeComment> commentAll(Connection conn){
		ArrayList<NoticeComment> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select board_comment_no,board_comment_type,board_comment_id,board_comment_name,board_comment_content,board_ref,board_comment_ref,board_comment_date,to_char(board_comment_date,'yyyy-MM-dd HH24:mi') as board_comment_date2 from board_comment where board_comment_type=0 order by board_comment_no";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<NoticeComment>();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNoticeCommentNo(rset.getInt("board_comment_no"));
				nc.setNoticeCommentType(rset.getInt("board_comment_type"));
				nc.setNoticeCommentId(rset.getString("board_comment_id"));
				nc.setNoticeCommentName(rset.getString("board_comment_name"));
				nc.setNoticeCommentContent(rset.getString("board_comment_content"));
				nc.setNoticeRef(rset.getInt("board_ref"));
				nc.setNoticeCommentRef(rset.getInt("board_comment_ref"));
				nc.setNoticeCommentDate(rset.getDate("board_comment_date"));
				nc.setNoticeCommentDate2(rset.getString("board_comment_date2"));
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(stmt);
		}
		return list;
	}
}
