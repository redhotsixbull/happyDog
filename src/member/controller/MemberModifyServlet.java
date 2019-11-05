package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet(name = "MemberModify", urlPatterns = { "/memberModify" })
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
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
		int level = Integer.parseInt(request.getParameter("level"));
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String fullAddress = address+"//"+detailAddress;
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1+"-"+phone2+"-"+phone3;
		System.out.println(possibleTime);
		Member m = new Member();
		m.setId(request.getParameter("id"));
		m.setPw(request.getParameter("pw"));
		m.setCode(request.getParameter("code"));
		m.setName(request.getParameter("name"));
		m.setPhone(phone);
		m.setPost(request.getParameter("post"));
		m.setAddress(fullAddress);
		m.setPossibleTime(possibleTime);
		m.setEmail(request.getParameter("email"));
		m.setMemberLevel(level);
		try {
			if(level == 0 ) {
				int result = new MemberService().memberModify(m);
				System.out.println("일반회원 수정완료");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "수정 완료");
				request.setAttribute("loc", "/");
				rd.forward(request, response);
			}else if(level == 1){
				int result = new MemberService().memberModify2(m);
				System.out.println("보호소 회원 수정완료");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "수정 완료");
				request.setAttribute("loc", "/");
				rd.forward(request, response);
			}else {
				System.out.println("수정실패");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
				request.setAttribute("msg", "수정 실패");
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
