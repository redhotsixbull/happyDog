package adoption.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.BookApplyService;

/**
 * Servlet implementation class VisitReservationServlet
 */
@WebServlet(name = "VisitReservation", urlPatterns = { "/reservation" })
public class VisitReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String careNm = request.getParameter("careNm");				//보호소명 받기
		String careAddr = request.getParameter("careAddr");			//보호소주소
		String careTel = request.getParameter("careTel");			//보호소 전화번호
		String careTime = request.getParameter("careTime");			//보호소 방문가능시간
		String code = request.getParameter("code");					//보호소 코드
		System.out.println("보호소 코드 : "+ code);
		System.out.println(careTime);
		System.out.println(careAddr);
		request.setAttribute("code", code);
		request.setAttribute("careNm", careNm);
		request.setAttribute("careAddr", careAddr);
		request.setAttribute("careTel", careTel);
		request.setAttribute("careTime", careTime);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/visitReservation.jsp");
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
