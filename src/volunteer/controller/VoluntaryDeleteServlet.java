package volunteer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import volunteer.model.service.VoluntaryService;

/**
 * Servlet implementation class VoluntaryDeleteServlet
 */
@WebServlet(name = "VoluntaryDelete", urlPatterns = { "/voluntaryDelete" })
public class VoluntaryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int result = new VoluntaryService().deleteVoluntary(no);
		HttpSession session = request.getSession(false);
		
		if(result > 0) {
			request.setAttribute("msg", "공지사항 게시물 삭제 성공");
		}else {
			request.setAttribute("msg", "공지사항 게시물 삭제 실패");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
		request.setAttribute("loc", "/volunteerList");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
