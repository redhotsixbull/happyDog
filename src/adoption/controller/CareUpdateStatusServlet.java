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

/**
 * Servlet implementation class CareUpdateStatusServlet
 */
@WebServlet(name = "CareUpdateStatus", urlPatterns = { "/careUpdateStatus" })
public class CareUpdateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CareUpdateStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int status = Integer.parseInt(request.getParameter("status"));
		int no = Integer.parseInt(request.getParameter("no"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("ebdDay");
		System.out.println("updateStatusServlet no : "+no);
		System.out.println("updateStatusServlet reqPage : "+reqPage);
		int result;
		try {
			result = new BookApplyService().updateStatus(status,no);
			if(result>0) {
				request.setAttribute("no", no);
				request.setAttribute("startDay", startDay);
				request.setAttribute("endDay", endDay);
				request.setAttribute("reqPage", reqPage);
				RequestDispatcher rd = request.getRequestDispatcher("/reservationView");
				rd.forward(request, response);
			}else {
				request.setAttribute("no", no);
				request.setAttribute("startDay", startDay);
				request.setAttribute("endDay", endDay);
				request.setAttribute("reqPage", reqPage);
				request.setAttribute("msg", "상태 수정 실패했습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/reservationView");
				rd.forward(request, response);
			}
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
