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
 * Servlet implementation class ReservationMyViewServlet
 */
@WebServlet(name = "ReservationMyView", urlPatterns = { "/reservMyView" })
public class ReservationMyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReservationMyViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String id = ((Member)session.getAttribute("member")).getId();
		int no = Integer.parseInt(request.getParameter("no"));
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage=1;
		}		
		System.out.println(no);
		System.out.println(reqPage);
		BookApply ba;
		try {
			ba = new BookApplyService().myViewOne(no,id);
			request.setAttribute("ba", ba);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("no", no);
			request.setAttribute("msg", request.getAttribute("msg"));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/reservationView.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/error/sqlError.jsp");
			request.setAttribute("msg", "SQL 에러가 발생했습니다.");
			rd.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
