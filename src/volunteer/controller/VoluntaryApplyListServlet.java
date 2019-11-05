package volunteer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryApplyBoard;
import volunteer.model.vo.VoluntaryApplyData;

/**
 * Servlet implementation class VoluntaryApplyListServlet
 */
@WebServlet(name = "VoluntaryApplyList", urlPatterns = { "/voluntaryApplyList" })
public class VoluntaryApplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VoluntaryApplyListServlet() {
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
		
		VoluntaryApplyData vad;
		String view = "";
		try {
			vad = new VoluntaryService().myVoluntaryList(reqPage, id);
			request.setAttribute("vad", vad);
			String pageTitle = "내가 신청한 봉사활동";
			request.setAttribute("pageTitle", pageTitle);
			view = "/WEB-INF/volunteer/voluntaryApplyList.jsp";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
