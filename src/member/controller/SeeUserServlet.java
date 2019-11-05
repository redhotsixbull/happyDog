package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.Service.MemberService;
import member.model.vo.Member;
import member.model.vo.MemberPageData;

/**
 * Servlet implementation class SeeUserServlet
 */
@WebServlet(name = "SeeUser", urlPatterns = { "/seeUser" })
public class SeeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		int user = Integer.parseInt(request.getParameter("user"));
		System.out.println(user);
		HttpSession session = request.getSession(false);
		String id = ((Member)session.getAttribute("member")).getId();
		int select = Integer.parseInt(request.getParameter("select"));
		String search = request.getParameter("search");
		System.out.println(select+"셀렉트");
		if(search == null) {
			search = "";
		}
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		try {
			if(id.equals("admin")) {
				MemberPageData pd = new MemberService().seeUser(reqPage,user,select,search);
				request.setAttribute("pd", pd);
				request.setAttribute("user", user);
				request.setAttribute("select", select);
				request.setAttribute("search", search);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/member/adminPage.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
		}		
		} catch (SQLException e) {
			System.out.println("sql문 오류임");
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
