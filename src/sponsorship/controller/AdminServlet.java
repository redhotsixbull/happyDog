package sponsorship.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import sponsorship.model.service.OrderService;
import sponsorship.model.vo.OrderInfoVO;
import sponsorship.model.vo.OrderListVO;
import sponsorship.model.vo.OrderUpdate;
import sponsorship.model.vo.ProductVO;
import sponsorship.model.vo.SearchVO;
import sponsorship.model.vo.TotalOrder;

@WebServlet(name = "Admin", urlPatterns = { "/orderView", "/orderList", "/updateOrder", "/updateStatus" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO(0));
		prdList.add(new ProductVO(1));
		prdList.add(new ProductVO(2));
		
		Member member = (Member)request.getSession(false).getAttribute("member");
		if(member!=null) {
			if(member.getMemberLevel()==2) {
				/* 주문 관리 상세 페이지*/
				if(action.equals("orderView")) {
					String no = request.getParameter("no");
					try {
						OrderInfoVO orderInfo = new OrderService().selectOrder(no);
						request.setAttribute("orderInfo", orderInfo);
						request.setAttribute("prdList", prdList);
						request.getRequestDispatcher("/WEB-INF/sponsorship/orderView.jsp").forward(request, response);
					} catch (SQLException e) {
						request.setAttribute("msg", "SQL에러가 발생했습니다.");
						request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
					}
					
				/* 주문 관리 리스트 페이지 */
				}else if(action.equals("orderList")){
					int reqPage;
					try {
						reqPage = Integer.parseInt(request.getParameter("reqPage"));
					}catch (Exception e) {
						reqPage = 1;
					}
					String startDay = request.getParameter("startDay");
					String endDay = request.getParameter("endDay");
					String status = request.getParameter("status");
					String payMethod = request.getParameter("payMethod");
					String searchType = request.getParameter("searchType");
					String searchVal = request.getParameter("searchVal");
					
					SearchVO search = new SearchVO(reqPage, startDay, endDay, status, payMethod, searchType, searchVal,null);
					try {
						request.setAttribute("search", search);
						
						TotalOrder total = new OrderService().totalOrder(search);
						request.setAttribute("total", total);
						
						OrderListVO orderList = new OrderService().selectOrder(search);
						request.setAttribute("orderList", orderList);
						request.setAttribute("prdList", prdList);
						request.getRequestDispatcher("/WEB-INF/sponsorship/orderList.jsp").forward(request, response);
					} catch (SQLException e) {
						request.setAttribute("msg", "SQL에러가 발생했습니다.");
						request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
					}
					
				/* 주문 관리 상세 페이지 - 주문상태 및 운송장 번호 변경 */
				}else if(action.equals("updateOrder")){
					String no = request.getParameter("no");
					String deilveryNum = request.getParameter("deilveryNum");
					int status = Integer.parseInt(request.getParameter("status"));
					
					OrderUpdate updateInfo = new OrderUpdate(no, deilveryNum, status);
					try {
						new OrderService().updateOrder(updateInfo);
						response.sendRedirect("/orderView?no="+no);
					} catch (SQLException e) {
						request.setAttribute("msg", "SQL에러가 발생했습니다.");
						request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
					}
					
				/* 주문관리 리스트 - 주문상태 변경 */
				}else if(action.equals("updateStatus")){
					String no = request.getParameter("no");
					int status = Integer.parseInt(request.getParameter("status"));
					OrderUpdate updateInfo = new OrderUpdate(no, null, status);
					try {
						int result = new OrderService().updateOrder(updateInfo);
		
						response.setCharacterEncoding("utf-8");
						PrintWriter out = response.getWriter();
						if(result>0){
							out.print("success");
						}else{
							out.print("fail");
						}
						
					} catch (SQLException e) {
						request.setAttribute("msg", "SQL에러가 발생했습니다.");
						request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
					}
					
				}
		
			}else {
				request.setAttribute("msg", "접근 권한이 없습니다.");
				request.setAttribute("loc", "/");
				request.getRequestDispatcher("/WEB-INF/common/msg.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("msg", "로그인 후 이용해주세요");
			request.setAttribute("loc", "/member/login.jsp");
			request.getRequestDispatcher("/WEB-INF/common/msg.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
