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
 * Servlet implementation class ReservationMypageServlet
 */
@WebServlet(name = "ReservationMypage", urlPatterns = { "/reservationMypage" })
public class ReservationMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReservationMypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		String id = ((Member)session.getAttribute("member")).getId();
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage=1;
		}
		BookApplyPageData bp;
		
		try {
			bp = new BookApplyService().selectList(reqPage,id);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("bp", bp);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/reservationMypage.jsp");
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
