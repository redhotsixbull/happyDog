package volunteer.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryApplyBoard;
import volunteer.model.vo.VoluntaryRegister;

/**
 * Servlet implementation class VoluntaryViewServlet
 */
@WebServlet(name = "VoluntaryView", urlPatterns = { "/voluntaryView" })
public class VoluntaryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));
		VoluntaryRegister vr = new VoluntaryService().voluntaryView(no);
		String view="";
		if(vr != null) {
			request.setAttribute("vr", vr);
			ArrayList<VoluntaryApplyBoard> list = new VoluntaryService().VoluntaryApplyPerson(no);
			request.setAttribute("list", list);
			view = "/WEB-INF/volunteer/voluntaryView.jsp";
		}else {
			request.setAttribute("msg", "해당 봉사활동 신청 공고가 존재하지 않습니다.");
			request.setAttribute("loc", "/voluntaryList");
			view = "/WEB-INF/common/msg.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
