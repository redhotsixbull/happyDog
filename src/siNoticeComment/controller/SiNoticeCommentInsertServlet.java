package siNoticeComment.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siNoticeComment.model.noticeCommentService.NoticeCommentService;
import siNoticeComment.model.noticeCommentVo.NoticeComment;

/**
 * Servlet implementation class SiPreBoardCommentServlet
 */
@WebServlet(name = "SiNoticeCommentInsert", urlPatterns = { "/siNoticeCommentInsert" })
public class SiNoticeCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeCommentType = Integer.parseInt(request.getParameter("noticeType"));
		String noticeCommentId = request.getParameter("memberId");
		String noticeCommentName = request.getParameter("memberName");
		String noticeCommentContent = request.getParameter("noticeCommentContent");
		int noticeRef = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeComment nc = new NoticeComment(0, noticeCommentType, noticeCommentId, noticeCommentName, noticeCommentContent, noticeRef, 0, null,null);
		int result = new NoticeCommentService().commentInsert(nc);
		
		response.sendRedirect("/siNoticeView?noticeNo="+noticeRef);
		
//		String view = "";
//		if(result>0) {
//			view = "/siNoticeView?noticeNo="+noticeRef;
//		}else {
//			view = "/siNoticeView?noticeNo="+noticeRef;
//		}
//		
//		RequestDispatcher rd = request.getRequestDispatcher(view);
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
