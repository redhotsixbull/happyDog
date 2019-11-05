package volunteer.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryApplyData;
import volunteer.model.vo.VoluntaryListData;

/**
 * Servlet implementation class VolunteerMyListServlet
 */
@WebServlet(name = "VolunteerMyList", urlPatterns = { "/volunteerMyList" })
public class VolunteerMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VolunteerMyListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		
		//String id = request.getParameter("id");
		//session.setAttribute("id", id);
		String id = ((Member)session.getAttribute("member")).getId();
		
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		
		VoluntaryListData vld;
		String view = "";
		try {
			vld = new VoluntaryService().voluntaryList(reqPage, id);
			request.setAttribute("vld", vld);
			String pageTitle = "내가 올린 봉사활동 공고";
			request.setAttribute("pageTitle", pageTitle);
			view = "/WEB-INF/volunteer/voluntaryList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "해당 페이지가 존재하지 않습니다.");
			request.setAttribute("loc", "/totalMyPage");
			view = "/WEB-INF/common/msg.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
