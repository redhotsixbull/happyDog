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

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(name = "Delete", urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		try {
			int result = new MemberService().delete(id);
			if(result > 0) {
				HttpSession session = request.getSession(false);
				session.invalidate();
				System.out.println("탈퇴완료");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "탈퇴 완료");
				request.setAttribute("loc", "/");
				rd.forward(request, response);
			}else {
				System.out.println("탈퇴실패");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "탈퇴 실패");
				request.setAttribute("loc", "/");
				rd.forward(request, response);
			}
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
