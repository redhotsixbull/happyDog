package volunteer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryApplyBoard;

/**
 * Servlet implementation class VoluntaryApplyServlet
 */
@WebServlet(name = "VoluntaryApply", urlPatterns = { "/voluntaryApply" })
public class VoluntaryApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryApplyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int possiblePerson = Integer.parseInt(request.getParameter("possiblePerson"));
		System.out.println("봉사 가능 인원수1 : "+possiblePerson); // 봉사 가능 인원수
		
		int currentApplyNum = Integer.parseInt(request.getParameter("applyNum"));
		System.out.println("현재까지 신청한  인원수1 : "+currentApplyNum); // 현재 신청한 인원수
		
		int no = Integer.parseInt(request.getParameter("no")); //공고 번호
		String code = request.getParameter("code"); //보호소 코드
		String name = request.getParameter("name"); //보호소 이름
		String id = request.getParameter("id"); 	  //신청자 아이디
		String phone = request.getParameter("phone"); //신청자 전화번호
		String volunDate = request.getParameter("volunDate"); //봉사 날짜
		String volunTime1 = request.getParameter("volunTime1");	//봉사 시작 시간
		String volunTime2 = request.getParameter("volunTime2"); //봉사 마감 시간
		String volunTime = volunTime1+","+volunTime2; //봉사 시간
		int person = Integer.parseInt(request.getParameter("person")); //지금 봉사 신청한 인원 수
		
		VoluntaryApplyBoard vab = new VoluntaryApplyBoard(0, no, code, name, null, id, phone, person, volunDate, volunTime, null);
		int result = new VoluntaryService().voluntaryApply(vab, possiblePerson, currentApplyNum);
		
		if(result > 0) {
			request.setAttribute("msg", "봉사활동 신청이 완료되었습니다.");
		}else {
			if(result == -1) {
				request.setAttribute("msg", "해당 공고의 신청 인원이 초과하였습니다.");
			}else {
			request.setAttribute("msg", "해당 공고의 신청이 실패했습니다.");
			}
		}
		
		request.setAttribute("loc", "/voluntaryView?no="+no);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
