package siAdoptionBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoard.model.adoptionBoardService.AdoptionBoardService;
import siNotice.model.noticeService.NoticeService;

/**
 * Servlet implementation class SiPreBoardDeleteServlet
 */
@WebServlet(name = "SiAdoptionBoardDelete", urlPatterns = { "/siAdoptionBoardDelete" })
public class SiAdoptionBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int adoptionBoardNo = Integer.parseInt(request.getParameter("adoptionBoardNo"));
		int result = new AdoptionBoardService().adoptionBoardDelete(adoptionBoardNo);
		String view = "";
		if(result>0) {
			view = "/siAdoptionBoard";
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
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
