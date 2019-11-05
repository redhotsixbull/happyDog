package sponsorship.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import sponsorship.model.vo.OrderInfoVO;
import sponsorship.model.vo.OrderUpdate;
import sponsorship.model.vo.SearchVO;

public class OrderDao {
	
	/* 검색 쿼리 */
	public String makeQuery(SearchVO search){
		String query = "";
		if(search.getStartDay()!=null && search.getStartDay()!=""){
			query += " and spon_date>'"+search.getStartDay()+"'";
		}
		if(search.getEndDay()!=null && search.getEndDay()!=""){
			query += " and TO_CHAR(spon_date,'yyyy-mm-dd')<='"+search.getEndDay()+"'";
		}
		if(search.getStatus()!=null && search.getStatus()!=""){
			query += " and status="+search.getStatus();
		}
		if(search.getPayMethod()!=null && search.getPayMethod()!=""){
			query += " and pay_method='"+search.getPayMethod()+"'";
		}
		if(search.getSearchVal()!=null && search.getSearchVal()!=""){
			query += " and "+search.getSearchType()+" like '%"+search.getSearchVal()+"%'";
		}
		
		return query;
	}

	public int insertOrder(Connection conn, OrderInfoVO orderInfo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into sponsorship values(?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, orderInfo.getNo());
		pstmt.setString(2, orderInfo.getId());
		pstmt.setString(3, orderInfo.getName());
		pstmt.setString(4, orderInfo.getPhone());
		pstmt.setString(5, orderInfo.getPayMethod());
		pstmt.setInt(6, orderInfo.getPay());
		pstmt.setInt(7, orderInfo.getAmount());
		pstmt.setInt(8, orderInfo.getStatus());
		pstmt.setString(9, orderInfo.getDeilveryNum());
		pstmt.setString(10, orderInfo.getProductName());
		pstmt.setString(11, orderInfo.getMemo());
		pstmt.setString(12, orderInfo.getPost());
		pstmt.setString(13, orderInfo.getAddress());
		pstmt.setString(14, orderInfo.getEmail());
		pstmt.setString(15, orderInfo.getReceiveName());
		pstmt.setString(16, orderInfo.getReceivePhone());
		pstmt.setString(17, orderInfo.getVbankName());
		pstmt.setString(18, orderInfo.getVbankNum());
		pstmt.setString(19, orderInfo.getVbankHolder());
		pstmt.setString(20, orderInfo.getVbankDate());
		
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);

		return result;
	}

	public OrderInfoVO selectOrder(Connection conn, String no) throws SQLException {
		OrderInfoVO orderInfo = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select no,id,name,phone,pay_method,amount,pay,status,deilvery_num,product_name,TO_CHAR(spon_date,'YYYY-MM-DD HH24:MI:SS') as time,memo,post,address,email,receive_name,receive_phone,vbank_name,vbank_num,vbank_holder,vbank_date from sponsorship where no=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rset = pstmt.executeQuery();
		if(rset.next()){
			orderInfo = new OrderInfoVO();
			orderInfo.setNo(rset.getString("no"));
			orderInfo.setId(rset.getString("id"));
			orderInfo.setName(rset.getString("name"));
			orderInfo.setPhone(rset.getString("phone"));
			orderInfo.setPayMethod(rset.getString("pay_method"));
			orderInfo.setAmount(rset.getInt("amount"));
			orderInfo.setPay(rset.getInt("pay"));
			orderInfo.setStatus(rset.getInt("status"));
			orderInfo.setDeilveryNum(rset.getString("deilvery_num"));
			orderInfo.setProductName(rset.getString("product_name"));
			orderInfo.setSponDate(rset.getString("time"));
			orderInfo.setMemo(rset.getString("memo"));
			orderInfo.setPost(rset.getString("post"));
			orderInfo.setAddress(rset.getString("address"));
			orderInfo.setEmail(rset.getString("email"));
			orderInfo.setReceiveName(rset.getString("receive_name"));
			orderInfo.setReceivePhone(rset.getString("receive_phone"));
			orderInfo.setVbankName(rset.getString("vbank_name"));
			orderInfo.setVbankNum(rset.getString("vbank_num"));
			orderInfo.setVbankHolder(rset.getString("vbank_holder"));
			orderInfo.setVbankDate(rset.getString("vbank_date"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return orderInfo;
	}
	
	public ArrayList<OrderInfoVO> selectOrder(Connection conn,int start,int end,SearchVO search) throws SQLException {
		ArrayList<OrderInfoVO> orderList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select rnum,no,id,name,phone,pay_method,amount,pay,status,deilvery_num,product_name,TO_CHAR(spon_date,'YYYY-MM-DD HH24:MI:SS') as time,memo,post,address,email,receive_name,receive_phone from (select rownum rnum,s.* from (select * from sponsorship where 1=1 "+makeQuery(search)+" order by spon_date desc) s ) where rnum between ? and ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		rset = pstmt.executeQuery();
		orderList = new ArrayList<OrderInfoVO>();
		while(rset.next()){
			OrderInfoVO orderInfo = new OrderInfoVO();
			orderInfo.setNo(rset.getString("no"));
			orderInfo.setId(rset.getString("id"));
			orderInfo.setName(rset.getString("name"));
			orderInfo.setPhone(rset.getString("phone"));
			orderInfo.setPayMethod(rset.getString("pay_method"));
			orderInfo.setAmount(rset.getInt("amount"));
			orderInfo.setPay(rset.getInt("pay"));
			orderInfo.setStatus(rset.getInt("status"));
			orderInfo.setDeilveryNum(rset.getString("deilvery_num"));
			orderInfo.setProductName(rset.getString("product_name"));
			orderInfo.setSponDate(rset.getString("time"));
			orderInfo.setMemo(rset.getString("memo"));
			orderInfo.setPost(rset.getString("post"));
			orderInfo.setAddress(rset.getString("address"));
			orderInfo.setEmail(rset.getString("email"));
			orderInfo.setReceiveName(rset.getString("receive_name"));
			orderInfo.setReceivePhone(rset.getString("receive_phone"));
			orderList.add(orderInfo);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return orderList;
	}

	public int findOrder(Connection conn, String no, String phone) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from sponsorship where no=? and phone=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, phone);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result++;
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int totalPrice(Connection conn,SearchVO search) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select sum(pay) as price from sponsorship where status!=0 "+makeQuery(search);
		
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result = rset.getInt("price");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int totalCount(Connection conn,SearchVO search) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "select count(*) as cnt from sponsorship where 1=1 "+makeQuery(search);
		
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	

	public int updateOrder(Connection conn, OrderUpdate updateInfo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update sponsorship set deilvery_num=?,status=? where no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, updateInfo.getDeilveryNum());
		pstmt.setInt(2, updateInfo.getStatus());
		pstmt.setString(3, updateInfo.getNo());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);

		return result;
	}

	public int updateStatus(Connection conn, OrderUpdate updateInfo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update sponsorship set status=? where no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, updateInfo.getStatus());
		pstmt.setString(2, updateInfo.getNo());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);

		return result;
	}

}
