package volunteer.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import volunteer.model.vo.VoluntaryApplyBoard;
import volunteer.model.vo.VoluntaryRegister;

public class VoluntaryDao {

	// 봉사활동 공고 등록
	public int insertVoluntaryRegister(Connection conn, VoluntaryRegister vr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into volunteer_register values(volunteer_register_seq.nextval,?,?,?,?,?,?,0,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vr.getCode());
			pstmt.setString(2, vr.getTitle());
			pstmt.setString(3, vr.getVolunDate());
			pstmt.setString(4, vr.getVolunTime());
			pstmt.setInt(5, vr.getPerson());
			pstmt.setString(6, vr.getDetail());
			pstmt.setString(7, vr.getFilename());
			pstmt.setString(8, vr.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 봉사활동 리스트 총 게시물 수
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register";
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 봉사활동 리스트
	public ArrayList<VoluntaryRegister> voluntaryList(Connection conn, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) order by no desc) v) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setApplyNum(rset.getInt("apply_num"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	// 봉사활동 신청 게시판 총 갯수 :: 검색 :: 제목 
	public int totalCountTitle(Connection conn, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register where title like(?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 봉사활동 신청 게시판 총 개수 :: 검색 :: 보호소명 
	public int totalCountName(Connection conn, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register left join member using(code) where name like(?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 봉사활동 신청 게시판 :: 검색 :: 제목 
	public ArrayList<VoluntaryRegister> searchKeywordTitle(Connection conn, String keyword, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) where title like(?) order by title) v) where rnum between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setApplyNum(rset.getInt("apply_num"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	// 봉사활동 신청 게시판 :: 검색 :: 보호소명 
	public ArrayList<VoluntaryRegister> searchKeywordName(Connection conn, String keyword, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) where name like(?) order by name) v) where rnum between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setApplyNum(rset.getInt("apply_num"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	// 봉사활동 뷰
	public VoluntaryRegister voluntaryView(Connection conn, int no) {
		VoluntaryRegister vr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from volunteer_register left join member using(code) where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setApplyNum(rset.getInt("apply_num"));
				vr.setDetail(rset.getString("detail"));
				vr.setFilename(rset.getString("filename"));
				vr.setFilepath(rset.getString("filepath"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return vr;
	}

	// 봉사활동 공고 수정
	public int updateVoluntary(Connection conn, VoluntaryRegister vr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update volunteer_register set title=?, volun_date=?, volun_time=?, person=?, detail=?, filename=?, filepath=? where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vr.getTitle());
			pstmt.setString(2, vr.getVolunDate());
			pstmt.setString(3, vr.getVolunTime());
			pstmt.setInt(4, vr.getPerson());
			pstmt.setString(5, vr.getDetail());
			pstmt.setString(6, vr.getFilename());
			pstmt.setString(7, vr.getFilepath());
			pstmt.setInt(8, vr.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 봉사활동 공고 삭제
	public int deleteVoluntary(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from volunteer_register where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//신청 인원수 파악
	public int totalApply(Connection conn, VoluntaryApplyBoard vab) {
		int possible = 0;
		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		//String query = "select count(*) cnt from (select apply_num from volunteer_register where no=?)";
		String query = "select apply_num from volunteer_register where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vab.getNo());
			possible = pstmt.executeUpdate();
			/*rset = pstmt.executeQuery();
			if(rset.next()) {
				possible = rset.getInt("cnt");
			}*/
			//possible = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return possible;
	}

	//신청하기
	public int voluntaryApply(Connection conn, VoluntaryApplyBoard vab) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into volunteer_board values(volunteer_board_seq.nextval,?,?,?,?,?,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vab.getNo());
			pstmt.setString(2, vab.getCode());
			pstmt.setString(3, vab.getId());
			pstmt.setString(4, vab.getPhone());
			pstmt.setInt(5, vab.getPerson());
			pstmt.setString(6, vab.getVolunDate());
			pstmt.setString(7, vab.getVolunTime());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 신청 누적 인원수
	public int voluntaryCurrentPerson(Connection conn, VoluntaryApplyBoard vab, int currentApplyNum) {
		int currentPerson = 0;
		PreparedStatement pstmt = null;
		String query = "update volunteer_register set apply_num=? where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, currentApplyNum);
			pstmt.setInt(2, vab.getNo());
			//pstmt.setInt(2, vab.getPerson());
			//pstmt.setInt(3, vab.getNo());
			currentPerson = pstmt.executeUpdate();
			System.out.println("신청 누적 인원 결과(성공 1) : "+currentPerson);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return currentPerson;
	}

	// 마이페이지 :: 일반회원 봉사활동 신청내역 :: 총 갯수
	public int totalMyApplyCount(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_board where id=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// 마이페이지 :: 일반회원 봉사활동 신청내역
	public ArrayList<VoluntaryApplyBoard> myVoluntaryList(Connection conn, String id, int start, int end) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, vapply.apply_no, vapply.no, vr.code, vr.title, vapply.id, vapply.phone, vbPerson, vbDate, vbTime, vbEnroll_date, m.name from "+
				"(select apply_no, no, code, id, phone, person as vbPerson, volun_date as vbDate, " + 
				"volun_time as vbTime, enroll_date as vbEnroll_date from volunteer_board vb where id=?) vapply left join volunteer_register vr on (vapply.no = vr.no) "+
				"left join member m on (vr.code = m.code)) where rnum between ? and ? order by vbEnroll_date desc";
		ArrayList<VoluntaryApplyBoard> list = null;
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rset = pstmt.executeQuery();
		list = new ArrayList<VoluntaryApplyBoard>();
		while(rset.next()) {
			VoluntaryApplyBoard vab = new VoluntaryApplyBoard();
			vab.setApplyNo(rset.getInt("apply_no"));
			vab.setNo(rset.getInt("no"));
			vab.setCode(rset.getString("code"));
			vab.setTitle(rset.getString("title"));
			vab.setId(rset.getString("id"));
			vab.setPhone(rset.getString("phone"));
			vab.setPerson(rset.getInt("vbPerson"));
			vab.setVolunDate(rset.getString("vbDate"));
			vab.setVolunTime(rset.getString("vbTime"));
			vab.setEnrollDate(rset.getDate("vbEnroll_date"));
			vab.setName(rset.getString("name"));
			list.add(vab);
		}
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return list;
	}

	// 마이페이지 :: 보호소회원 봉사활동 공고등록내역 :: 총 갯수
	public int totalCount(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register left join member using(code) where id=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 마이페이지 :: 보호소회원 봉사활동 공고등록내역
	public ArrayList<VoluntaryRegister> voluntaryList(Connection conn, String id, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) where id=? order by no desc) v) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setApplyNum(rset.getInt("apply_num"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	//해당공고에 신청한 사람들 목록
	public ArrayList<VoluntaryApplyBoard> VoluntaryApplyPerson(Connection conn, int no) {
		ArrayList<VoluntaryApplyBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from volunteer_board where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryApplyBoard>();
			while(rset.next()) {
				VoluntaryApplyBoard vab = new VoluntaryApplyBoard();
				vab.setApplyNo(rset.getInt("apply_no"));
				vab.setNo(rset.getInt("no"));
				vab.setCode(rset.getString("code"));
				vab.setId(rset.getString("id"));
				vab.setPhone(rset.getString("phone"));
				vab.setPerson(rset.getInt("person"));
				vab.setVolunDate(rset.getString("volun_date"));
				vab.setVolunTime(rset.getString("volun_time"));
				vab.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	/* 메인 :: 봉사활동 게시판 공고 노출 */
	public ArrayList<VoluntaryRegister> mainVoluntaryList(Connection conn, int start, int end) {
		ArrayList<VoluntaryRegister> volunList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, t.* from (select name, volunteer_register.* from volunteer_register left join member on (volunteer_register.code = member.code) order by volunteer_register.enroll_date desc) t) where rnum between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			volunList = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setApplyNum(rset.getInt("apply_num"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				volunList.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return volunList;
	}

	
	
	

}
