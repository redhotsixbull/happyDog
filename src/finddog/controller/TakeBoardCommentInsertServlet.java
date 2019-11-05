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
 * Servlet implementation class TakeBoardCommentInsertServlet
 */
@WebServlet(name = "TakeBoardCommentInsert", urlPatterns = { "/takeBoardCommentInsert" })
public class TakeBoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeBoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int boardCommentType = Integer.parseInt(request.getParameter("boardType"));
		String boardCommentId = request.getParameter("memberId");
		String boardCommentName = request.getParameter("memberName");
		String boardCommentContent = request.getParameter("boardCommentContent");
		int boardRef = Integer.parseInt(request.getParameter("boardNo"));
		BoardComment bc = new BoardComment(0, boardCommentType, boardCommentId, boardCommentName, boardCommentContent, boardRef, 0,null,null);
		int result = new BoardCommentService().commentInsert(bc);
		if(result>0) {
			request.setAttribute("msg", "댓글등록성공");
		}else {
			request.setAttribute("msg", "댓글등록실패");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/common/siMsg.jsp");
		
		
		request.setAttribute("loc", "/detailTakeBoard?boardNo="+boardRef);
		
		
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
