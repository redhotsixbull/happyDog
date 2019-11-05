package finddog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoardComment.model.boardCommentService.BoardCommentService;
import siBoardComment.model.boardCommentVo.BoardComment;

/**
 * Servlet implementation class TakeBoardReCommentInsertServlet
 */
@WebServlet(name = "TakeBoardReCommentInsert", urlPatterns = { "/takeBoardReCommentInsert" })
public class TakeBoardReCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeBoardReCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int boardCommentType = Integer.parseInt(request.getParameter("boardType"));
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		String boardCommentContent = request.getParameter("boardCommentContent");
		String boardCommentId = request.getParameter("memberId");
		String boardCommentName = request.getParameter("memberName");
		BoardComment bc = new BoardComment();
		bc.setBoardRef(boardRef);
		bc.setBoardCommentRef(boardCommentRef);
		bc.setBoardCommentType(boardCommentType);
		bc.setBoardCommentContent(boardCommentContent);
		bc.setBoardCommentId(boardCommentId);
		bc.setBoardCommentName(boardCommentName);
		int result = new BoardCommentService().reCommentInsert(bc);
		String view = "";
		if(result>0) {
			view = "/detailTakeBoard?boardNo="+boardNo;
		}else {
			view = "/detailTakeBoard?boardNo="+boardNo;
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
