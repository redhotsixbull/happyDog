package adoption.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adoption.model.service.BookApplyService;
import adoption.model.vo.BookApplyPageData;
import member.model.vo.Member;

/**
 * Servlet implementation class CareReservMypageServlet
 */
@WebServlet(name = "CareReservMypage", urlPatterns = { "/reservationCareMypage" })
public class CareReservMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CareReservMypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String code = ((Member)session.getAttribute("member")).getCode();
		System.out.println(code);
		int reqPage;
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage=1;
		}
		BookApplyPageData bp;
		try {
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("start", startDay);
			request.setAttribute("end", endDay);
			bp = new BookApplyService().reservationCareMypage(reqPage, code, startDay, endDay);
			request.setAttribute("bp", bp);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/careMypage.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/error/sqlError.jsp");
			request.setAttribute("msg", "SQL 에러가 발생했습니다.");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
