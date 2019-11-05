package adoption.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import adoption.model.vo.BookApply;
import adoption.model.vo.DogKind;
import adoption.model.vo.DogList;
import common.JDBCTemplate;
import openApi.model.vo.cityCode;

public class BookApplyDao {
	//유기견 리스트 가져오기
	public ArrayList<DogList> dogList(int reqPage,String cityCode,String gunCode,String kindCd,String neuterYn){
		String today = date();			//오늘
		String preMonth = preMonth();	//육개월전
		String sql="";
		if(cityCode!=null && !cityCode.equals("")){
			sql += "&upr_cd="+cityCode;
		}
		if(gunCode!=null && !gunCode.equals("")){
			sql += "&org_cd="+gunCode;
		}
		if(kindCd!=null && !kindCd.equals("")){
			sql += "&kind="+kindCd;
		}
		if(neuterYn!=null && !neuterYn.equals("")){
			sql += "&neuter_yn="+neuterYn;
		}
		ArrayList<DogList> list = null;
		try {
			while (true) {
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde="+preMonth+"&endde="+today+"&pageNo="
						+ reqPage
						+ "&upkind=417000"+sql+"&numOfRows=12&ServiceKey=aLiSUfKw3hrZNSZrqXuG6iJtNr0ufMlgmB8Y%2Fh93hFuOk5E%2Brl8bd8mxxl%2Fcga%2B6i2CP7lD5%2BGBnLYmmVm%2BkFw%3D%3D";
				//보경 서비스키 : TZzGtB8BZdZ0VsTPgpNVa1IQMCBLU9%2FlEriT0S4AFcqcswb4YiOAqJiR7So%2BJMbWd5fB0P6%2B8JQsI7EpN4KKrg%3D%3D
				//지영 서비스키 : aLiSUfKw3hrZNSZrqXuG6iJtNr0ufMlgmB8Y%2Fh93hFuOk5E%2Brl8bd8mxxl%2Fcga%2B6i2CP7lD5%2BGBnLYmmVm%2BkFw%3D%3D
				//민주 서비스키 : 9foRMY8t3j0MRIsmBCTWOiLUVaW4yJivGOtPfYE9x8yYsPcPCkCUZgGm39bZZGdQQc1ZT9MN87KHULUH8aLpMg%3D%3D
				System.out.println(url);
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc;
				NodeList nList2;
				
				while(true) {	//null이 나올경우 오류발생하기 때문에 not null일 때까지 반목문실행
					doc = dBuilder.parse(url);
					// root tag
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); // XML의 최상위 tag값 가져오기
					
					//총 유기견 마리수 구하기(totalCount)
					nList2 = doc.getElementsByTagName("body");
					if(nList2.item(0) !=null) {
						break;
					}
				}
				Node nNode2 = nList2.item(0);
//				System.out.println(nList2.item(0));
				Element eElement2 = (Element) nNode2;
//				System.out.println(eElement2);
				System.out.println("API에서 totalCount: "+getTagValue("totalCount",eElement2));
				String totalCount = getTagValue("totalCount",eElement2);
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
//				Node node1 = doc.getElementsByTagName("totalCount").item(0);
				// System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				int count = 0;
				list = new ArrayList<DogList>();
				for (int temp = 0; temp < nList.getLength(); temp++) {
					DogList dl = new DogList();
					Node nNode = nList.item(temp);					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						System.out.println("######################");
						// System.out.println(eElement.getTextContent()); getTextContent(): 전체 정보
						dl.setAge(getTagValue("age", eElement));
						dl.setCareAddr(getTagValue("careAddr", eElement));
						dl.setCareNm(getTagValue("careNm", eElement));
						dl.setCareTel(getTagValue("careTel", eElement));
						dl.setColorCd(getTagValue("colorCd", eElement));
						dl.setFilename(getTagValue("popfile", eElement));
						dl.setHappenDt(getTagValue("happenDt", eElement));
						dl.setHappenPlace(getTagValue("happenPlace", eElement));
						dl.setKindCd(getTagValue("kindCd", eElement));
						dl.setNoticeEdt(getTagValue("noticeEdt", eElement));
						dl.setNoticeNo(getTagValue("noticeNo", eElement));
						dl.setOrgNm(totalCount);						//관할기관대신 총 갯수 전달
						dl.setSexCd(getTagValue("sexCd", eElement));
						dl.setSpecialMark(getTagValue("specialMark", eElement));
						dl.setWeight(getTagValue("weight", eElement));
						dl.setNoticeSdt(getTagValue("noticeSdt", eElement));
						dl.setProcessState(getTagValue("processState", eElement));
						dl.setNeuterYn(getTagValue("neuterYn", eElement));
						count++;
						System.out.println("데이터 담은수:" + count);
						list.add(dl);
					} // for end
				} // if end
				System.out.println("page number : " + reqPage);
				break;
			} // while end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}
	
	//전체 도시 리스트 가져오기
	public ArrayList<cityCode> getCityCode(Connection conn) throws SQLException {
		Statement stmt =null;
		ResultSet rset = null;
		ArrayList<cityCode> list = null;
		String query = "select * from city";
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		list = new ArrayList<cityCode>();
		int i=0;
		while(rset.next()) {
			i++;
			cityCode c = new cityCode();
			c.setCityCode(rset.getString("city_code"));
			c.setCityName(rset.getString("city_name"));
			System.out.println("도시 담은 수: "+i);
			list.add(c);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(stmt);
		return list;
	}
	
	//도시코드로 지역구 리스트 가져오기
	public ArrayList<cityCode> getCityCode(Connection conn, String cityCode) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<cityCode> list = null;
		String query = "select * from area where citycode=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, cityCode);
		rset = pstmt.executeQuery();
		list = new ArrayList<cityCode>();
		while(rset.next()) {
			cityCode c = new cityCode();
			c.setDistrict(rset.getString("areacode"));
			c.setDistrictName(rset.getString("areaname"));
			list.add(c);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	//강이지 크기 받아서 해당 품종 가져오기
	public ArrayList<DogKind> getKind(Connection conn, String dogsize) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<DogKind> list = null;
		String query = "select * from dogkind where dogsize=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, dogsize);
		rset = pstmt.executeQuery();
		list = new ArrayList<DogKind>();
		while(rset.next()) {
			DogKind d = new DogKind();
			d.setCode(rset.getString("code"));
			d.setKind(rset.getString("kind"));
			d.setDogsize(rset.getString("dogsize"));
			list.add(d);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	///////////////////////////////////////////////일반회원 유기견 입양 방문 신청///////////////////////////////////////////////////////////
	
	//보호소 방문가능시간,보호소 코드 가져오기
	public ArrayList<String> careTime(Connection conn, String careNm) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> list=null;
		String query = "select code,possible_time from member where name=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, careNm);
		rset=pstmt.executeQuery();
		list = new ArrayList<String>();
		if(rset.next()) {
			list.add(rset.getString("code"));
			list.add(rset.getString("possible_time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
		
	//일반회원 방문예약 신청
	public int reservation(Connection conn, BookApply ba) throws SQLException {
		PreparedStatement pstmt = null;
		System.out.println("보호소코드 : "+ba.getCode());
		int result = 0;
		String query = "insert into book_apply values (book_apply_seq.nextval,?,?,?,?,?,?,sysdate,0,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, ba.getCode());
		pstmt.setString(2,  ba.getId());
		pstmt.setString(3, ba.getName());
		pstmt.setString(4,  ba.getPhone());
		pstmt.setDate(5, ba.getVisitDate());
		pstmt.setString(6, ba.getVisitTime());
		pstmt.setString(7, ba.getYard());
		pstmt.setString(8, ba.getAnimal());
		pstmt.setString(9, ba.getFamily());
		pstmt.setString(10, ba.getExperience());
		pstmt.setString(11, ba.getAvgTime());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	//예약된 방문 시간 구해오기
	public ArrayList<String> possibleTime(Connection conn, String visitDate, String careNm) throws SQLException {
		System.out.println("2Dao");
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<String> list = new ArrayList<String>();
		String query = "select visit_time from book_apply join member using(code) where visit_date=? and member.name=? and status!=2";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, visitDate);
		pstmt.setString(2, careNm);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			list.add(rset.getString("visit_time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	///////////////////////////////////////////////일반회원 마이페이지/////////////////////////////////////////////////////////
	
	//일반회원 방문예약 신청 내역 갯수 구하기
	public int reservationCount(Connection conn,String id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from book_apply Join member using(code) where book_apply.id=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		rset=pstmt.executeQuery();
		if(rset.next()) {
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	//일반회원 방문예약 신청 리스트 가져오기
	public ArrayList<BookApply> selectList(Connection conn, int start, int end, String id) throws SQLException{
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		String query = "select * from (select rownum as rnum, b.* from (select ba.no, m.name careNm, ba.id, ba.name, ba.visit_date, ba.visit_time, ba.apply_date, ba.status from ((select * from book_apply order by 1 desc)ba) Join member m Using(code) where ba.id=?) b) where rnum BETWEEN ? and ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			BookApply ba = new BookApply();
			ba.setRnum(rset.getInt("rnum"));
			ba.setNo(rset.getInt("no"));
			ba.setCode(rset.getString("careNm"));	//보호소 코드에 보호소 이름 넣기
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			list.add(ba);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	//일반회원 방문예약 페이지 하나 보기
	public BookApply myViewOne(Connection conn, int no, String id) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, b.* from (select ba.no, m.name careNm, ba.id, ba.name, ba.phone, ba.visit_date, ba.visit_time, ba.apply_date, ba.status,ba.yard, ba.animal,ba.family,ba.experience, ba.avg_time from ((select * from book_apply order by 1 desc)ba) Join member m Using(code) where ba.id=?) b) where no=?";
		BookApply ba = null;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.setInt(2, no);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			ba = new BookApply();
			ba.setRnum(rset.getInt("rnum"));
			ba.setNo(rset.getInt("no"));
			ba.setCode(rset.getString("careNm"));	//보호소이름 코드에 저장하기
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			ba.setYard(rset.getString("yard"));
			ba.setAnimal(rset.getString("animal"));
			ba.setFamily(rset.getString("family"));
			ba.setExperience(rset.getString("experience"));
			ba.setAvgTime(rset.getString("avg_time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return ba;
	}
	
	//일반회원 보호소 방문예약 취소
	public int cancelReservation(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt =null;
		int result=0;
		String query = "update book_apply set status=3 where no=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, no);
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	///////////////////////////////////////////////보호소 회원 마이페이지/////////////////////////////////////////////////////////
	
	//보호소 회원 방문예약리스트 갯수 구하기
	public int reservationCareCount(Connection conn, String code,String startDay, String endDay) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " and visit_date>'"+startDay+"'";
		}
		if(endDay!=null && endDay!=""){
			sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select count(*) as cnt from book_apply where code=?"+sql;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, code);
		rset=pstmt.executeQuery();
		if(rset.next()) {
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	//보호소회원 방문예약 신청내역 리스트 확인
	public ArrayList<BookApply> reservationCareList(Connection conn, int start, int end, String code, String startDay, String endDay) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " and visit_date>'"+startDay+"'";
		}
		if(endDay!=null && endDay!=""){
			sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select * from (select rownum as rnum, b.* from (select ba.no, m.name careNm, ba.id, ba.name,ba.phone, ba.visit_date, ba.visit_time, ba.apply_date, ba.status from ((select * from book_apply where code=?"+ sql +" order by 1 desc)ba) Join member m Using(code)) b) where rnum BETWEEN ? and ?";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, code);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			BookApply ba = new BookApply();
			ba.setRnum(rset.getInt("rnum"));
			ba.setNo(rset.getInt("no"));
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			list.add(ba);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	//보호소회원이 방문예약 신청내역 내용 확인
	public BookApply viewOne(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "select * from (select ROWNUM as rNum,b.* from (select * from book_apply order by 1 desc) b) where no=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, no);
		rset = pstmt.executeQuery();
		BookApply ba = null;
		if(rset.next()) {
			ba = new BookApply();
			ba.setRnum(rset.getInt("rnum"));
			ba.setNo(rset.getInt("no"));
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			ba.setYard(rset.getString("yard"));
			ba.setAnimal(rset.getString("animal"));
			ba.setFamily(rset.getString("family"));
			ba.setExperience(rset.getString("experience"));
			ba.setAvgTime(rset.getString("avg_time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return ba;
	}
	
	//보호소 회원이 예약상태 업데이트
	public int updateStatus(Connection conn, int status, int no) throws SQLException {
		PreparedStatement pstmt =null;
		int result=0;
		String query = "update book_apply set status=? where no=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, status);
		pstmt.setInt(2, no);
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	///////////////////////////////////////////////관리자 마이페이지/////////////////////////////////////////////////////////
	
	//관리자가 방문예약리스트 갯수 구하기
	public int adminReservationCount(Connection conn, String startDay, String endDay) throws SQLException {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " where visit_date>'"+startDay+"'";
			if(endDay!=null && endDay!=""){
				sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
			}
		}
		else if(endDay!=null && endDay!=""){
			sql += " where TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select count(*) as cnt from book_apply"+sql;
		System.out.println(query);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		if(rset.next()) {
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(stmt);
		return result;
	}
	
	//관리자가 방문예약 신청내역 리스트 확인
	public ArrayList<BookApply> adminReservationList(Connection conn, int start, int end, String startDay,String endDay) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " where visit_date>'"+startDay+"'";
			if(endDay!=null && endDay!=""){
				sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
			}
		}
		else if(endDay!=null && endDay!=""){
			sql += " where TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select * from (select rownum as rnum, b.* from (select ba.no, m.name careNm, ba.id, ba.name,ba.phone, ba.visit_date, ba.visit_time, ba.apply_date, ba.status from ((select * from book_apply"+ sql +" order by 1 desc)ba) Join member m Using(code)) b) where rnum BETWEEN ? and ?";
		System.out.println(query);
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			BookApply ba = new BookApply();
			ba.setRnum(rset.getInt("rnum"));
			ba.setNo(rset.getInt("no"));
			ba.setCode(rset.getString("careNm"));	//보호소 코드에 보호소 이름 넣기
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			list.add(ba);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	//관리자가 방문예약 리스트 내용 확인
	public BookApply adminViewOne(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, b.* from (select ba.no, m.name careNm, ba.id, ba.name, ba.phone, ba.visit_date, ba.visit_time, ba.apply_date, ba.status,ba.yard, ba.animal,ba.family,ba.experience, ba.avg_time from ((select * from book_apply order by 1 desc)ba) Join member m Using(code)) b) where no=?";
		BookApply ba = null;
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, no);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			ba = new BookApply();
			ba.setRnum(rset.getInt("rnum"));
			ba.setNo(rset.getInt("no"));
			ba.setCode(rset.getString("careNm"));	//보호소이름 코드에 저장하기
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			ba.setYard(rset.getString("yard"));
			ba.setAnimal(rset.getString("animal"));
			ba.setFamily(rset.getString("family"));
			ba.setExperience(rset.getString("experience"));
			ba.setAvgTime(rset.getString("avg_time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return ba;
	}
	public String date() {
//		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		System.out.println("date메소드 : "+date.format(cal.getTime()));
		return date.format(cal.getTime());
	}
	public String preMonth() {
//		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		System.out.println("preMonth메소드 : "+date.format(cal.getTime()));
		return date.format(cal.getTime());
	}
	
	
}
