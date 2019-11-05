package adoption.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adoption.model.service.BookApplyService;
import adoption.model.vo.BookApply;
import member.model.vo.Member;

/**
 * Servlet implementation class VisitReservationCompleteServlet
 */
@WebServlet(name = "VisitReservationComplete", urlPatterns = { "/visitReservationComplete" })
public class VisitReservationCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitReservationCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		System.out.println(((Member)session.getAttribute("member")).getId());
		String id = ((Member)session.getAttribute("member")).getId();	//로그인 할때 아이디, 이름, 전화번호 세션에 저장하기
		String name = ((Member)session.getAttribute("member")).getName();
		String phone = ((Member)session.getAttribute("member")).getPhone();
		BookApply ba = new BookApply();
		ba.setCode(request.getParameter("code"));
		System.out.println("보호소코드 : "+request.getParameter("code"));
		ba.setName(name);	//session에서 가져오기
		ba.setId(id);		//session에서 가져오기
		ba.setPhone(phone);	//session에서 가져오기
		ba.setYard(request.getParameter("yard"));
		ba.setAnimal(request.getParameter("animal"));
		ba.setFamily(request.getParameter("family"));
		ba.setExperience(request.getParameter("experience"));
		ba.setAvgTime(request.getParameter("avgTime"));
		ba.setVisitDate(Date.valueOf(request.getParameter("visitDate")));
		ba.setVisitTime(request.getParameter("visitTime"));
		try {
			int result = new BookApplyService().reservation(ba);
			if(result>0) {
				String[] care = new String[3];
				care[0] = request.getParameter("careNm");
				care[1] = request.getParameter("careAddr");
				care[2] = request.getParameter("careTel");
				request.setAttribute("care", care);
				request.setAttribute("ba", ba);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/visitComplete.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("msg", "오류가 발생하였습니다.");
				request.setAttribute("loc", "/WEB-INF/adoption/visitReservation.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/error/sqlError.jsp");
			request.setAttribute("msg", "SQL 에러가 발생했습니다.");
			rd.forward(request, response);
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
