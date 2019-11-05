package siBoard.model.boardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import siBoard.model.boardVo.Board;
import siBoardComment.model.boardCommentVo.BoardComment;
import siTemplete.JDBCTemplete;

public class BoardDao {
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from board where board_type = 1";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt("cnt");
				/*if(result == 0) {
					result += 1;
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
	public ArrayList<Board> myBoardList(Connection conn, int start, int end, String boardId){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy/MM/dd HH:mi') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD where board_id = ? and board_type = 1 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, boardId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardDate2(rset.getString("board_date2"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				b.setDogKind(rset.getString("dog_kind"));
				b.setHappenCity(rset.getString("happen_City"));
				b.setHappenDate(rset.getString("happen_date"));
				list.add(b);
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
	public ArrayList<Board> boardAll(Connection conn,int start,int end){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD where board_Type=1 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardDate2(rset.getString("board_date2"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				b.setDogKind(rset.getString("dog_kind"));
				b.setHappenCity(rset.getString("happen_City"));
				b.setHappenDate(rset.getString("happen_date"));
				list.add(b);
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
	public int boardInsert(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,sysdate,0,0,null,null,null,null,null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getBoardType());
			pstmt.setString(2, b.getBoardId());
			pstmt.setString(3, b.getBoardName());
			pstmt.setString(4, b.getBoardTitle());
			pstmt.setString(5, b.getBoardContent());
			pstmt.setString(6, b.getBoardFilename());
			pstmt.setString(7, b.getBoardFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int boardCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_count = board_count+1 where board_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public Board boardView(Connection conn, int boardNo){
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select b.*,m.email from (select board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd HH24:mi') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date from board where board_no=?)b, member m where b.board_id=m.id ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardDate2(rset.getString("board_date2"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("email"));//비밀번호 아님 이메일 (연락처)
				b.setBoardPrdCode(rset.getString("board_PrdCode"));
				b.setDogKind(rset.getString("dog_kind"));
				b.setHappenCity(rset.getString("happen_City"));
				b.setHappenDate(rset.getString("happen_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return b;
	}
	public int boardUpdate(Connection conn, int boardNo, String boardTitle, String boardContent, String boardFilename, String boardFilepath) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_title=?, board_content=?, board_filename=?, board_filepath=? where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardFilename);
			pstmt.setString(4, boardFilepath);
			pstmt.setInt(5, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public Board boardUpdateOriginal(Connection conn, int boardNo,Board b) {
		b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				b.setDogKind(rset.getString("dog_kind"));
				b.setHappenCity(rset.getString("happen_City"));
				b.setHappenDate(rset.getString("happen_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return b;
	}
	public int boardDelete(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public ArrayList<Board> boardSearchName(Connection conn,String searchKeyword,int start,int end){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM board where board_name like ? and board_Type=1 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardDate2(rset.getString("board_date2"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				b.setDogKind(rset.getString("dog_kind"));
				b.setHappenCity(rset.getString("happen_City"));
				b.setHappenDate(rset.getString("happen_date"));
				list.add(b);
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
	public ArrayList<Board> boardSearchTitle(Connection conn,String searchKeyword,int start,int end){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT rnum,board_no,board_type,board_id,board_name,board_title,board_content,board_filename,board_filepath,board_date,to_char(board_date,'yyyy-MM-dd') as board_date2,board_count,board_secret,board_pw,board_prdcode,dog_kind,happen_city,happen_date FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM board where board_title like ? and board_Type=1 ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardDate2(rset.getString("board_date2"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				b.setDogKind(rset.getString("dog_kind"));
				b.setHappenCity(rset.getString("happen_City"));
				b.setHappenDate(rset.getString("happen_date"));
				list.add(b);
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
		String query = "select count(*) as cnt from board where board_title like ? and board_Type=1";
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
		String query = "select count(*) as cnt from board where board_name like ? and board_Type=1";
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
	public ArrayList<BoardComment> commentAll(Connection conn){
		ArrayList<BoardComment> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select board_comment_no,board_comment_type,board_comment_id,board_comment_name,board_comment_content,board_ref,board_comment_ref,board_comment_date,to_char(board_comment_date,'yyyy-MM-dd HH24:mi') as board_comment_date2 from board_comment where board_comment_type=1 order by board_comment_no";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<BoardComment>();
			while(rset.next()) {
				
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rset.getInt("board_comment_no"));
				bc.setBoardCommentType(rset.getInt("board_comment_type"));
				bc.setBoardCommentId(rset.getString("board_comment_id"));
				bc.setBoardCommentName(rset.getString("board_comment_name"));
				bc.setBoardCommentContent(rset.getString("board_comment_content"));
				bc.setBoardRef(rset.getInt("board_ref"));
				bc.setBoardCommentRef(rset.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rset.getDate("board_comment_date"));
				bc.setBoardCommentDate2(rset.getString("board_comment_date2"));
				list.add(bc);
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
	public ArrayList<BoardComment> takeCommentAll(Connection conn){
		ArrayList<BoardComment> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select board_comment_no,board_comment_type,board_comment_id,board_comment_name,board_comment_content,board_ref,board_comment_ref,board_comment_date,to_char(board_comment_date,'yyyy-MM-dd HH24:mi') as board_comment_date2 from board_comment where board_comment_type=4 order by board_comment_no";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<BoardComment>();
			while(rset.next()) {		
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rset.getInt("board_comment_no"));
				bc.setBoardCommentType(rset.getInt("board_comment_type"));
				bc.setBoardCommentId(rset.getString("board_comment_id"));
				bc.setBoardCommentName(rset.getString("board_comment_name"));
				bc.setBoardCommentContent(rset.getString("board_comment_content"));
				bc.setBoardRef(rset.getInt("board_ref"));
				bc.setBoardCommentRef(rset.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rset.getDate("board_comment_date"));
				bc.setBoardCommentDate2(rset.getString("board_comment_date2"));
				list.add(bc);
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
	public int takeBoardInsert(Connection conn, Board b) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,sysdate,0,0,null,null,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getBoardType());
			pstmt.setString(2, b.getBoardId());
			pstmt.setString(3, b.getBoardName());
			pstmt.setString(4, b.getBoardTitle());
			pstmt.setString(5, b.getBoardContent());
			pstmt.setString(6, b.getBoardFilename());
			pstmt.setString(7, b.getBoardFilepath());
			pstmt.setString(8, b.getDogKind());
			pstmt.setString(9, b.getHappenCity());
			pstmt.setString(10, b.getHappenDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public String getKindName(String dogkind, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String kindName="";
		String query = "select*from dogkind where code=?";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1,dogkind);
			rset = pstmt.executeQuery();
			
			System.out.println(dogkind);
			if(rset.next()) {
				kindName=rset.getString("kind");
				System.out.println(kindName);
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return kindName;
	}
}
