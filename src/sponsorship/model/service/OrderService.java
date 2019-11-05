package sponsorship.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import sponsorship.model.dao.OrderDao;
import sponsorship.model.vo.OrderInfoVO;
import sponsorship.model.vo.OrderListVO;
import sponsorship.model.vo.OrderUpdate;
import sponsorship.model.vo.SearchVO;
import sponsorship.model.vo.TotalOrder;

public class OrderService {

	public int insertOrder(OrderInfoVO orderInfo) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new OrderDao().insertOrder(conn,orderInfo);
		if(result>0){
			JDBCTemplate.commit(conn);
		}else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public OrderInfoVO selectOrder(String no) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		OrderInfoVO orderInfo = new OrderDao().selectOrder(conn,no);
		JDBCTemplate.close(conn);
		return orderInfo;
	}
	
	public OrderListVO selectOrder(SearchVO search) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int reqPage = search.getReqPage();
		int total = new OrderDao().totalCount(conn,search);
		int pageNum = 10;
		int totalPage = (total%pageNum==0)?(total/pageNum):(total/pageNum)+1;
		int start = (reqPage*pageNum-pageNum)+1;
		int end  = reqPage*pageNum;
		/* 리스트 */
		ArrayList<OrderInfoVO> orderinfoList = new OrderDao().selectOrder(conn,start,end,search);
		
		/* 페이지 네비 */
		int totalNavi = 5;
		String pageNavi = "";
		int pageNo = ((reqPage-1)/totalNavi)*totalNavi+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='javascript:list("+(pageNo-1)+");'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a> ";
		}
		int i = 1;
		while(!(i++>totalNavi || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<a class='cur'>"+pageNo+"</a> ";
			}else {
				pageNavi += "<a href='javascript:list("+pageNo+");'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='javascript:list("+pageNo+");'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		OrderListVO orderList = new OrderListVO(orderinfoList, pageNavi);
		JDBCTemplate.close(conn);
		return orderList;
	}

	public int findOrder(String no, String phone) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new OrderDao().findOrder(conn,no,phone);
		JDBCTemplate.close(conn);
		return result;
	}

	public TotalOrder totalOrder(SearchVO search) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int price = new OrderDao().totalPrice(conn,search);
		int count = new OrderDao().totalCount(conn,search);
		JDBCTemplate.close(conn);
		TotalOrder total = new TotalOrder(price, count);
		return total;
	}

	public int updateOrder(OrderUpdate updateInfo) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = 0;
		if(updateInfo.getDeilveryNum() == null){
			result = new OrderDao().updateStatus(conn,updateInfo);
		}else{
			result = new OrderDao().updateOrder(conn,updateInfo);
		}
		if(result>0){
			JDBCTemplate.commit(conn);
		}else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
