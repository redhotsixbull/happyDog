package volunteer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryListData;

/**
 * Servlet implementation class SearchVoluntaryServlet
 */
@WebServlet(name = "SearchVoluntary", urlPatterns = { "/searchVoluntary" })
public class SearchVoluntaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchVoluntaryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type= request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		
		VoluntaryListData vld = new VoluntaryService().searchVoluntary(reqPage, type, keyword);
		
		request.setAttribute("vld", vld);
		String pageTitle = "봉사활동 신청";
		request.setAttribute("pageTitle", pageTitle);
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
		
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
