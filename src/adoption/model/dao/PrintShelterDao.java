package adoption.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.vo.Shelter;
import adoption.model.vo.ShelterPageData;
import common.JDBCTemplate;
import member.model.vo.Member;
import member.model.vo.MemberPageData;

public class PrintShelterDao {

	public MemberPageData selectList(int city, Connection conn, int start, int end) throws SQLException {
		// TODO Auto-generated method stub
		
		
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		
		Member m = null;
		MemberPageData spd = null;
		ArrayList<Member> list = null;
		
		
		String query="";
		
		switch (city) {
		case 2:
			
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '서울%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 14:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '부산%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 15:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '대구%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 6:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '대전%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
		
			break;
		case 10:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '광주%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 50:
			
			break;
		case 3:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '경기%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
				
		
			break;
	
		case 12:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '경상남%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 8:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '경상북%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 5:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '충청남%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 7:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '충청북%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 11:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '전라남%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 9:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '전라북%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 4:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '강원%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		case 16:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '세종%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		case 1:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '인천%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		case 13:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '제주%' and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		
		}
		
		
		pstmt= conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		
		System.out.println(start+""+end+""+city);
		
		rset= pstmt.executeQuery();
		
		list= new ArrayList<>();
		spd = new MemberPageData();
		System.out.println(query);
		
		while(rset.next()) {							
			m = new Member();
			
			System.out.println("여기들어왔나요");
			String address=rset.getString("address");
			address=address.replace("//", "");
			
			m.setName(rset.getString("name"));
			m.setPhone(rset.getString("phone"));
			m.setAddress(address);
			
			list.add(m);
			
		}	
			
		
		
		spd.setList(list);
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
	
		
		return spd;
	
	}
	public int totalCount(Connection conn, int city) throws SQLException {
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		

		String query="select count(*) cnt from member where address like '서울%' and member_level=1";
		
		switch (city) {
		case 2:
			
			query="select count(*) cnt from member where address like '서울%' and member_level=1";
			
			break;
		case 14:
			
			query="select count(*) cnt from member where address like '부산%' and member_level=1";
			
			break;
		case 15:
			
			query="select count(*) cnt from member where address like '대구%' and member_level=1";
			
			break;
		case 6:
			
			query="select count(*) cnt from member where address like '대전%' and member_level=1";
		
			break;
		case 10:
			
			query="select count(*) cnt from member where address like '광주%' and member_level=1";
			
			break;
		case 50:
			
			break;
		case 3:
			query="select count(*) cnt from member where address like '경기%' and member_level=1";
			break;
	
		case 12:
			query="select count(*) cnt from member where address like '경상남%' and member_level=1";
			break;
		case 8:
			query="select count(*) cnt from member where address like '경상북%' and member_level=1";
			break;
		case 5:
			query="select count(*) cnt from member where address like '충청남%' and member_level=1";
			break;
		case 7:
			query="select count(*) cnt from member where address like '충청북%' and member_level=1";
			break;
		case 11:
			query="select count(*) cnt from member where address like '전라남%' and member_level=1";
			break;
		case 9:
			query="select count(*) cnt from member where address like '전라북%' and member_level=1";
			break;
		case 4:
			query="select count(*) cnt from member where address like '강원%' and member_level=1";
			break;	
		case 16:
			query="select count(*) cnt from member where address like '세종%' and member_level=1";
			break;	
		case 1:
			query="select count(*) cnt from member where address like '인천%' and member_level=1";
			break;	
		case 13:
			query="select count(*) cnt from member where address like '제주%' and member_level=1";
			break;	
		
		}
		
		pstmt= conn.prepareStatement(query);
		
		rset= pstmt.executeQuery();
		
		
		int result=0;
		System.out.println(query);
		
		if(rset.next()) {							
				
			System.out.println("들어옴여긴??");
			result=rset.getInt("cnt");		
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		
		return result;
	}
	
	
	public ArrayList<Member> getSearchName(Connection conn, String key, int start, int end) throws SQLException {
		ArrayList<Member>list =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where name like ? and member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
		pstmt= conn.prepareStatement(query);
		pstmt.setString(1, "%"+key+"%");
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rset = pstmt.executeQuery();
		list = new ArrayList<Member>();
		while(rset.next()) {
			Member s = new Member();
			s.setAddress(rset.getString("address"));
			s.setName(rset.getString("name"));
			s.setPhone(rset.getString("phone"));
			list.add(s);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	public int searchNameCount(Connection conn, String key) {
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		int result=0;
		String query="select count(*) cnt from member where name like ? and member_level=1";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, "%"+key+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int totalCountFirst(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		

		String query="select count(*) cnt from member where member_level=1";
		pstmt= conn.prepareStatement(query);
		
		rset= pstmt.executeQuery();
		
		
		int result=0;
		System.out.println(query);
		
		if(rset.next()) {							
				
			System.out.println("들어옴여긴??");
			result=rset.getInt("cnt");		
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	public MemberPageData selectListFirst(Connection conn, int start, int end) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		
		Member m = null;
		MemberPageData spd = null;
		ArrayList<Member> list = null;
		
		String query="";
		query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where member_level=1) M) WHERE RNUM BETWEEN ? AND ?";
		
		pstmt= conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		
		
		
		rset= pstmt.executeQuery();
		
		list= new ArrayList<>();
		spd = new MemberPageData();
		System.out.println(query);
		
		while(rset.next()) {							
			m = new Member();
			
			System.out.println("여기들어왔나요");
			String address=rset.getString("address");
			address=address.replace("//", "");
			
			m.setName(rset.getString("name"));
			m.setPhone(rset.getString("phone"));
			m.setAddress(address);
			
			list.add(m);
			
		}	
			
		
		
		spd.setList(list);
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
	
		
		return spd;
	}
	
}
