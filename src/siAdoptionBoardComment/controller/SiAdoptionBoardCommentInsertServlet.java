package siAdoptionBoardComment.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoardComment.model.adoptionBoardCommentService.AdoptionBoardCommentService;
import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;

/**
 * Servlet implementation class SiPreBoardCommentServlet
 */
@WebServlet(name = "SiAdoptionBoardCommentInsert", urlPatterns = { "/siAdoptionBoardCommentInsert" })
public class SiAdoptionBoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int adoptionBoardCommentType = Integer.parseInt(request.getParameter("adoptionBoardType"));
		String adoptionBoardCommentId = request.getParameter("memberId");
		String adoptionBoardCommentName = request.getParameter("memberName");
		String adoptionBoardCommentContent = request.getParameter("adoptionBoardCommentContent");
		int adoptionBoardRef = Integer.parseInt(request.getParameter("adoptionBoardNo"));
		AdoptionBoardComment ac = new AdoptionBoardComment(0, adoptionBoardCommentType, adoptionBoardCommentId, adoptionBoardCommentName, adoptionBoardCommentContent, adoptionBoardRef, 0,null,null);
		int result = new AdoptionBoardCommentService().commentInsert(ac);
		String view = "";
		if(result>0) {
			view = "/siAdoptionBoardView?adoptionBoardNo="+adoptionBoardRef;
		}else {
			view = "/siAdoptionBoardView?adoptionBoardNo="+adoptionBoardRef;
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
