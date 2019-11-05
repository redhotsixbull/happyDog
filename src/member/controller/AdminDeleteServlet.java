package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Service.MemberService;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet(name = "AdminDelete", urlPatterns = { "/adminDelete" })
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
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
		String id = request.getParameter("id");
		System.out.println(id);
		try {
			int result = new MemberService().delete(id);
			PrintWriter out = response.getWriter();
			if(result > 0 ) {
				out.print(1);
			}else {
				out.print(0);
			}
			
//			if(result>0) {
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
//				request.setAttribute("msg", "탈퇴 완료");
//				request.setAttribute("loc", "/adminPage");
//				rd.forward(request, response);
//			}else {
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
//				request.setAttribute("msg", "탈퇴 실패");
//				request.setAttribute("loc", "/adminPage");
//				rd.forward(request, response);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
