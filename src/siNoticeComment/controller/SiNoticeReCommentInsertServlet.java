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
 * Servlet implementation class SiNoticeReCommentInsertServlet
 */
@WebServlet(name = "SiNoticeReCommentInsert", urlPatterns = { "/siNoticeReCommentInsert" })
public class SiNoticeReCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeReCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int noticeCommentType = Integer.parseInt(request.getParameter("noticeType"));
		int noticeRef = Integer.parseInt(request.getParameter("noticeRef"));
		int noticeCommentRef = Integer.parseInt(request.getParameter("noticeCommentRef"));
		String noticeCommentContent = request.getParameter("noticeCommentContent");
		String noticeCommentId = request.getParameter("memberId");
		String noticeCommentName = request.getParameter("memberName");
		NoticeComment nc = new NoticeComment();
		nc.setNoticeRef(noticeRef);
		nc.setNoticeCommentRef(noticeCommentRef);
		nc.setNoticeCommentType(noticeCommentType);
		nc.setNoticeCommentContent(noticeCommentContent);
		nc.setNoticeCommentId(noticeCommentId);
		nc.setNoticeCommentName(noticeCommentName);
		int result = new NoticeCommentService().reCommentInsert(nc);
		String view = "";
		if(result>0) {
			view = "/siNoticeView?noticeNo="+noticeNo;
		}else {
			view = "/siNoticeView?noticeNo="+noticeNo;
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
