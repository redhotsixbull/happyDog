package siAdoptionBoard.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import siAdoptionBoard.model.adoptionBoardService.AdoptionBoardService;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoardPageData;

/**
 * Servlet implementation class SiPreBoardServlet
 */
@WebServlet(name = "SiAdoptionBoard", urlPatterns = { "/siAdoptionBoard" })
public class SiAdoptionBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		AdoptionBoardPageData ap = new AdoptionBoardService().adoptionBoardAll(reqPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/adoptionBoard/siAdoptionBoardList.jsp");
		request.setAttribute("ap", ap);	
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
