package siAdoptionBoard.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoard.model.adoptionBoardService.AdoptionBoardService;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoardViewData;

/**
 * Servlet implementation class SiPreBoardViewServlet
 */
@WebServlet(name = "SiAdoptionBoardView", urlPatterns = { "/siAdoptionBoardView" })
public class SiAdoptionBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int adoptionBoardNo = Integer.parseInt(request.getParameter("adoptionBoardNo"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/adoptionBoard/siAdoptionBoardView.jsp");
		AdoptionBoardViewData vd = new AdoptionBoardService().adoptionBoardView(adoptionBoardNo);
		request.setAttribute("vd", vd);
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
