package adoption.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.BookApplyService;
import adoption.model.vo.BookApply;

/**
 * Servlet implementation class AdminReservMypageViewServlet
 */
@WebServlet(name = "AdminReservMypageView", urlPatterns = { "/adminReservView" })
public class AdminReservMypageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminReservMypageViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		try {
			BookApply ba = new BookApplyService().adminViewOne(no);
			request.setAttribute("ba", ba);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
			request.setAttribute("msg", request.getAttribute("msg"));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/adminReservationView.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
			/*RequestDispatcher rd = request.getRequestDispatcher("/error/sqlError.jsp");
			rd.forward(request, response);*/
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
