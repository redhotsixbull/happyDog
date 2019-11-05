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
import adoption.model.vo.BookApply;
import member.model.vo.Member;

/**
 * Servlet implementation class CareReservMypageViewServlet
 */
@WebServlet(name = "CareReservMypageView", urlPatterns = { "/reservationView" })
public class CareReservMypageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CareReservMypageViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String code = ((Member)session.getAttribute("member")).getCode();
		int no = Integer.parseInt(request.getParameter("no"));
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		try {
			BookApply ba = new BookApplyService().viewOne(no);
			request.setAttribute("ba", ba);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
			request.setAttribute("msg", request.getAttribute("msg"));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/careReservationView.jsp");
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
