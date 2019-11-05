package volunteer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryRegister;

/**
 * Servlet implementation class VoluntaryApplyFormServlet
 */
@WebServlet(name = "VoluntaryApplyForm", urlPatterns = { "/voluntaryApplyForm" })
public class VoluntaryApplyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryApplyFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		request.setAttribute("no", no);
		
		VoluntaryRegister vr = new VoluntaryService().voluntaryView(no);
		String view="";
		if(vr != null) {
			request.setAttribute("vr", vr);
			view = "/WEB-INF/volunteer/voluntaryApply.jsp";
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
