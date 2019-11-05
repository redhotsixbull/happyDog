package finddog;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.BookApplyService;
import adoption.model.vo.DogList;

/**
 * Servlet implementation class DogDetailViewServlet
 */
@WebServlet(name = "DogDetailView", urlPatterns = { "/dogDetailView" })
public class DogDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String careNm = request.getParameter("careNm");				//보호소명 받기
		String careAddr = request.getParameter("careAddr");			//보호소주소
		String careTel = request.getParameter("careTel");			//보호소 전화번호
		String kindCd = request.getParameter("kindCd");				//품종
		String age = request.getParameter("age");					//강아지 나이
		String sexCd = request.getParameter("sexCd");				//성별
		String specialMark = request.getParameter("specialMark");	//특이사항
		String neuterYn = request.getParameter("neuterYn");			//중성화여부
		String filename = request.getParameter("filename");			//이미지 사진
		//검색값 넘겨받음
		String city = request.getParameter("city");
		String gun = request.getParameter("gun");
		String dogsize = request.getParameter("dogsize");
		String dogkind = request.getParameter("dogkind");
		String neuter = request.getParameter("neuter");
		String reqPage = request.getParameter("reqPage");
		String page1=request.getParameter("page1");
		String page2=request.getParameter("page2");
		
		//보호소 방문가능시간,보호소코드 가져오기
		ArrayList<String> careList;
		try {
			careList = new BookApplyService().careTime(careNm);
			DogList dl = new DogList(age,careAddr,careNm,careTel,filename,kindCd,sexCd,specialMark,neuterYn);
			request.setAttribute("dl", dl);
			request.setAttribute("careList", careList);		//보호소코드,보호소방문가능시간
			//검색조건 보내기
			request.setAttribute("cityCode", city);
			request.setAttribute("gun", gun);
			request.setAttribute("dogsize", dogsize);
			request.setAttribute("dogkind", dogkind);
			request.setAttribute("neuterYn", neuter);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("page1", page1);
			request.setAttribute("page2", page2);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/DogDetailView.jsp");
			rd.forward(request, response);
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
