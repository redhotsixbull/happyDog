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
import volunteer.model.vo.VoluntaryListData;

/**
 * Servlet implementation class VolunteerListServlet
 */
@WebServlet(name = "VolunteerList", urlPatterns = { "/volunteerList" })
public class VolunteerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		
		VoluntaryListData vld = new VoluntaryService().voluntaryList(reqPage);
		request.setAttribute("vld", vld);
		String pageTitle = "봉사활동 신청";
		request.setAttribute("pageTitle", pageTitle);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/volunteer/voluntaryList.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
