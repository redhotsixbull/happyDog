package siAdoptionBoardComment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoardComment.model.adoptionBoardCommentService.AdoptionBoardCommentService;

/**
 * Servlet implementation class SiBoardCommentUpdateServlet
 */
@WebServlet(name = "SiAdoptionBoardCommentUpdate", urlPatterns = { "/siAdoptionBoardCommentUpdate" })
public class SiAdoptionBoardCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		int adoptionBoardNo = Integer.parseInt(request.getParameter("adoptionBoardNo"));
		String adoptionBoardCommentContent = request.getParameter("adoptionBoardCommentContent");
		int adoptionBoardCommentNo = Integer.parseInt(request.getParameter("adoptionBoardCommentNo"));		
		int result = new AdoptionBoardCommentService().commentUpdate(memberId,adoptionBoardCommentContent,adoptionBoardCommentNo);
		String view = "";
		if(result>0) {
			view = "/siAdoptionBoardView?adoptionBoardNo="+adoptionBoardNo;
		}else {
			view = "/siAdoptionBoardView?adoptionBoardNo="+adoptionBoardNo;
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
