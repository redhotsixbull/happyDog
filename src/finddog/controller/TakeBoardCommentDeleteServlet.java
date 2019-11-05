package finddog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoardComment.model.adoptionBoardCommentService.AdoptionBoardCommentService;

/**
 * Servlet implementation class TakeBoardCommentDeleteServlet
 */
@WebServlet(name = "takeBoardCommentDelete", urlPatterns = { "/takeBoardCommentDelete" })
public class TakeBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeBoardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int adoptionBoardCommentNo = Integer.parseInt(request.getParameter("boardCommentNo"));
		int adoptionBoardNo = Integer.parseInt(request.getParameter("boardNo"));
		int result = new AdoptionBoardCommentService().commentDelete(adoptionBoardCommentNo);
		String view = "";
		if(result>0) {
			view = "/detailTakeBoard?boardNo="+adoptionBoardNo;
		}else {	
			view = "/detailTakeBoard?boardNo="+adoptionBoardNo;
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
