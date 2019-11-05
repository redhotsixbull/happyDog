package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import openApi.model.dao.OpenApiDao;
import openApi.model.vo.cityCode;

/**
 * Servlet implementation class EmailJoinServlet
 */
@WebServlet(name = "EmailJoin", urlPatterns = { "/emailJoin" })
public class EmailJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailJoinServlet() {
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
		int level = Integer.parseInt(request.getParameter("level"));
		String email = request.getParameter("email");
		ArrayList<cityCode> list=null;	
		boolean b= true;
		int count=0;
		while(b) {
			list=new OpenApiDao().getCityCode();
			System.out.println("list의 길이는 :"+list.size());
			if(list.size()>10) {
				b=false;
			}
			count++;
			if(count>30) {
				b=false;
				
			}
		}
		
		
		
		
		
		
		request.setAttribute("level", level);
		request.setAttribute("email", email);
		request.setAttribute("list", list);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/member/join.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
