package siAdoptionBoard.model.adoptionBoardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;
import siTemplete.JDBCTemplete;

public class AdoptionBoardDao {
	public ArrayList<AdoptionBoard> adoptionBoardAll(Connection conn){
		ArrayList<AdoptionBoard> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD where board_Type=2 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN 1 AND 8";
		try {
			stmt=conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<AdoptionBoard>();
			while(rset.next()) {
				AdoptionBoard a = new AdoptionBoard();
				a.setAdoptionBoardRnum(rset.getInt("rnum"));
				a.setAdoptionBoardNo(rset.getInt("board_no"));
				a.setAdoptionBoardType(rset.getInt("board_Type"));
				a.setAdoptionBoardId(rset.getString("board_id"));
				a.setAdoptionBoardName(rset.getString("board_Name"));
				a.setAdoptionBoardTitle(rset.getString("board_title"));
				a.setAdoptionBoardContent(rset.getString("board_content"));
				a.setAdoptionBoardFilename(rset.getString("board_filename"));
				a.setAdoptionBoardFilepath(rset.getString("board_filepath"));
				a.setAdoptionBoardDate(rset.getDate("board_date"));
				a.setAdoptionBoardDate2(rset.getString("board_date2"));
				a.setAdoptionBoardCount(rset.getInt("board_count"));
				a.setAdoptionBoardSecret(rset.getInt("board_secret"));
				a.setAdoptionBoardPw(rset.getString("board_pw"));
				a.setAdoptionBoardPrdCode(rset.getString("board_prdCode"));
				a.setDogKind(rset.getString("dog_kind"));
				a.setHappenCity(rset.getString("happen_City"));
				a.setHappenDate(rset.getString("happen_date"));
				list.add(a);
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
		String query = "select count(*) as cnt from board where board_type = 2";
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
	public ArrayList<AdoptionBoard> adoptionBoardAll(Connection conn,int start,int end){
		ArrayList<AdoptionBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD where board_Type=2 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<AdoptionBoard>();
			while(rset.next()) {
				AdoptionBoard a = new AdoptionBoard();
				a.setAdoptionBoardRnum(rset.getInt("rnum"));
				a.setAdoptionBoardNo(rset.getInt("board_no"));
				a.setAdoptionBoardType(rset.getInt("board_Type"));
				a.setAdoptionBoardId(rset.getString("board_id"));
				a.setAdoptionBoardName(rset.getString("board_Name"));
				a.setAdoptionBoardTitle(rset.getString("board_title"));
				a.setAdoptionBoardContent(rset.getString("board_content"));
				a.setAdoptionBoardFilename(rset.getString("board_filename"));
				a.setAdoptionBoardFilepath(rset.getString("board_filepath"));
				a.setAdoptionBoardDate(rset.getDate("board_date"));
				a.setAdoptionBoardDate2(rset.getString("board_date2"));
				a.setAdoptionBoardCount(rset.getInt("board_count"));
				a.setAdoptionBoardSecret(rset.getInt("board_secret"));
				a.setAdoptionBoardPw(rset.getString("board_pw"));
				a.setAdoptionBoardPrdCode(rset.getString("board_prdCode"));
				a.setDogKind(rset.getString("dog_kind"));
				a.setHappenCity(rset.getString("happen_City"));
				a.setHappenDate(rset.getString("happen_date"));
				list.add(a);
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
	public int adoptionBoardInsert(Connection conn, AdoptionBoard a) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,sysdate,0,0,null,null,null,null,null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a.getAdoptionBoardType());
			pstmt.setString(2, a.getAdoptionBoardId());
			pstmt.setString(3, a.getAdoptionBoardName());
			pstmt.setString(4, a.getAdoptionBoardTitle());
			pstmt.setString(5, a.getAdoptionBoardContent());
			pstmt.setString(6, a.getAdoptionBoardFilename());
			pstmt.setString(7, a.getAdoptionBoardFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int adoptionBoardCount(Connection conn, int adoptionBoardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_count = board_count+1 where board_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adoptionBoardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public AdoptionBoard adoptionBoardView(Connection conn, int adoptionBoardNo){
		AdoptionBoard a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd HH24:mi') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adoptionBoardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				a = new AdoptionBoard();
				a.setAdoptionBoardNo(rset.getInt("board_no"));
				a.setAdoptionBoardType(rset.getInt("board_Type"));
				a.setAdoptionBoardId(rset.getString("board_id"));
				a.setAdoptionBoardName(rset.getString("board_Name"));
				a.setAdoptionBoardTitle(rset.getString("board_title"));
				a.setAdoptionBoardContent(rset.getString("board_content"));
				a.setAdoptionBoardFilename(rset.getString("board_filename"));
				a.setAdoptionBoardFilepath(rset.getString("board_filepath"));
				a.setAdoptionBoardDate(rset.getDate("board_date"));
				a.setAdoptionBoardDate2(rset.getString("board_date2"));
				a.setAdoptionBoardCount(rset.getInt("board_count"));
				a.setAdoptionBoardSecret(rset.getInt("board_secret"));
				a.setAdoptionBoardPw(rset.getString("board_pw"));
				a.setAdoptionBoardPrdCode(rset.getString("board_PrdCode"));
				a.setDogKind(rset.getString("dog_kind"));
				a.setHappenCity(rset.getString("happen_City"));
				a.setHappenDate(rset.getString("happen_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return a;
	}
	public int adoptionBoardUpdate(Connection conn, int adoptionBoardNo, String adoptionBoardTitle, String adoptionBoardContent, String adoptionBoardFilename, String adoptionBoardFilepath) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_title=?, board_content=?, board_filename=?, board_filepath=? where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adoptionBoardTitle);
			pstmt.setString(2, adoptionBoardContent);
			pstmt.setString(3, adoptionBoardFilename);
			pstmt.setString(4, adoptionBoardFilepath);
			pstmt.setInt(5, adoptionBoardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public AdoptionBoard adoptionBoardUpdateOriginal(Connection conn, int adoptionBoardNo,AdoptionBoard a) {
		a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adoptionBoardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				a = new AdoptionBoard();
				a.setAdoptionBoardNo(rset.getInt("board_no"));
				a.setAdoptionBoardType(rset.getInt("board_Type"));
				a.setAdoptionBoardId(rset.getString("board_id"));
				a.setAdoptionBoardName(rset.getString("board_Name"));
				a.setAdoptionBoardTitle(rset.getString("board_title"));
				a.setAdoptionBoardContent(rset.getString("board_content"));
				a.setAdoptionBoardFilename(rset.getString("board_filename"));
				a.setAdoptionBoardFilepath(rset.getString("board_filepath"));
				a.setAdoptionBoardDate(rset.getDate("board_date"));
				a.setAdoptionBoardCount(rset.getInt("board_count"));
				a.setAdoptionBoardSecret(rset.getInt("board_secret"));
				a.setAdoptionBoardPw(rset.getString("board_pw"));
				a.setAdoptionBoardPrdCode(rset.getString("board_PrdCode"));
				a.setDogKind(rset.getString("dog_kind"));
				a.setHappenCity(rset.getString("happen_City"));
				a.setHappenDate(rset.getString("happen_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return a;
	}
	public int adoptionBoardDelete(Connection conn, int adoptionBoardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adoptionBoardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public ArrayList<AdoptionBoard> adoptionBoardSearchName(Connection conn,String searchKeyword,int start,int end){
		ArrayList<AdoptionBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM board where board_name like ? and board_Type=2 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<AdoptionBoard>();
			while(rset.next()) {
				AdoptionBoard a = new AdoptionBoard();
				a.setAdoptionBoardRnum(rset.getInt("rnum"));
				a.setAdoptionBoardNo(rset.getInt("board_no"));
				a.setAdoptionBoardType(rset.getInt("board_Type"));
				a.setAdoptionBoardId(rset.getString("board_id"));
				a.setAdoptionBoardName(rset.getString("board_Name"));
				a.setAdoptionBoardTitle(rset.getString("board_title"));
				a.setAdoptionBoardContent(rset.getString("board_content"));
				a.setAdoptionBoardFilename(rset.getString("board_filename"));
				a.setAdoptionBoardFilepath(rset.getString("board_filepath"));
				a.setAdoptionBoardDate(rset.getDate("board_date"));
				a.setAdoptionBoardDate2(rset.getString("board_date2"));
				a.setAdoptionBoardCount(rset.getInt("board_count"));
				a.setAdoptionBoardSecret(rset.getInt("board_secret"));
				a.setAdoptionBoardPw(rset.getString("board_pw"));
				a.setAdoptionBoardPrdCode(rset.getString("board_prdCode"));
				a.setDogKind(rset.getString("dog_kind"));
				a.setHappenCity(rset.getString("happen_City"));
				a.setHappenDate(rset.getString("happen_date"));
				list.add(a);
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
	public ArrayList<AdoptionBoard> adoptionBoardSearchTitle(Connection conn,String searchKeyword,int start,int end){
		ArrayList<AdoptionBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM board where board_title like ? and board_Type=2 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<AdoptionBoard>();
			while(rset.next()) {
				AdoptionBoard a = new AdoptionBoard();
				a.setAdoptionBoardRnum(rset.getInt("rnum"));
				a.setAdoptionBoardNo(rset.getInt("board_no"));
				a.setAdoptionBoardType(rset.getInt("board_Type"));
				a.setAdoptionBoardId(rset.getString("board_id"));
				a.setAdoptionBoardName(rset.getString("board_Name"));
				a.setAdoptionBoardTitle(rset.getString("board_title"));
				a.setAdoptionBoardContent(rset.getString("board_content"));
				a.setAdoptionBoardFilename(rset.getString("board_filename"));
				a.setAdoptionBoardFilepath(rset.getString("board_filepath"));
				a.setAdoptionBoardDate(rset.getDate("board_date"));
				a.setAdoptionBoardDate2(rset.getString("board_date2"));
				a.setAdoptionBoardCount(rset.getInt("board_count"));
				a.setAdoptionBoardSecret(rset.getInt("board_secret"));
				a.setAdoptionBoardPw(rset.getString("board_pw"));
				a.setAdoptionBoardPrdCode(rset.getString("board_prdCode"));
				a.setDogKind(rset.getString("dog_kind"));
				a.setHappenCity(rset.getString("happen_City"));
				a.setHappenDate(rset.getString("happen_date"));
				list.add(a);
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
		String query = "select count(*) as cnt from board where board_title like ? and board_Type=2";
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
		String query = "select count(*) as cnt from board where board_name like ? and board_Type=2";
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
	public ArrayList<AdoptionBoardComment> commentAll(Connection conn){
		ArrayList<AdoptionBoardComment> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select board_comment_no,board_comment_type,board_comment_id,board_comment_name,board_comment_content,board_ref,board_comment_ref,board_comment_date,to_char(board_comment_date,'yyyy/MM/dd HH24:mi') as board_comment_date2 from board_comment where board_comment_type=2 order by board_comment_no";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<AdoptionBoardComment>();
			while(rset.next()) {
				AdoptionBoardComment ac = new AdoptionBoardComment();
				ac.setAdoptionBoardCommentNo(rset.getInt("board_comment_no"));
				ac.setAdoptionBoardCommentType(rset.getInt("board_comment_type"));
				ac.setAdoptionBoardCommentId(rset.getString("board_comment_id"));
				ac.setAdoptionBoardCommentName(rset.getString("board_comment_name"));
				ac.setAdoptionBoardCommentContent(rset.getString("board_comment_content"));
				ac.setAdoptionBoardRef(rset.getInt("board_ref"));
				ac.setAdoptionBoardCommentRef(rset.getInt("board_comment_ref"));
				ac.setAdoptionBoardCommentDate(rset.getDate("board_comment_date"));
				ac.setAdoptionBoardCommentDate2(rset.getString("board_comment_date2"));
				list.add(ac);
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
