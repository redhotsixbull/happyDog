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
 * Servlet implementation class VoluntaryUpdateServlet
 */
@WebServlet(name = "VoluntaryUpdate", urlPatterns = { "/voluntaryUpdate" })
public class VoluntaryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));
		VoluntaryRegister vr = new VoluntaryService().voluntaryView(no);
		vr.setDetail(vr.getDetail().replaceAll("<br>", "\r\n"));
		if(vr != null) {
			request.setAttribute("vr", vr);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/volunteer/voluntaryUpdate.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
