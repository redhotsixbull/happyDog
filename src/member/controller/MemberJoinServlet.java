package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.PrintShelterService;
import member.model.Service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet(name = "MemberJoin", urlPatterns = { "/memberJoin" })
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
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
		String time = request.getParameter("time");
		String endTime = request.getParameter("endTime");
		String possibleTime = time+"시~"+endTime+"시";
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String fullAddress = address+"//"+detailAddress;
		String city = request.getParameter("careCity");
		String area = request.getParameter("careArea");
		String careAddress = city+" "+area;
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1+"-"+phone2+"-"+phone3;
		System.out.println(phone1);
		System.out.println(phone2);
		System.out.println(phone3);
		System.out.println(phone);
		int level = Integer.parseInt(request.getParameter("level"));
		Member m = new Member();
		if(level != 0) {
			m.setAddress(careAddress);
		}else if(level == 0) {
			m.setAddress(fullAddress);
		}
		System.out.println(fullAddress);
		System.out.println(possibleTime);
		m.setId(request.getParameter("id"));
		m.setPw(request.getParameter("pw"));
		m.setName(request.getParameter("name"));
		m.setPhone(phone);
		m.setPost(request.getParameter("post"));
		m.setPossibleTime(possibleTime);
		m.setEmail(request.getParameter("email"));
		m.setMemberLevel(level);
		m.setCode(request.getParameter("care"));
		System.out.println("memberjoin");
		System.out.println(m.getMemberLevel());
		try {
			int result = new MemberService().memberJoin(m);
			
			
			
			
			System.out.println(result);
			if(result > 0) {

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "가입 완료");
				request.setAttribute("loc", "/");

			System.out.println("가입완료");						//-- 보호소 찾기DB 수정이 필요함
			
				
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "가입 실패");
				request.setAttribute("loc", "/");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류");
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
