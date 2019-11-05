package siNotice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoardComment.model.boardCommentService.BoardCommentService;
import siBoardComment.model.boardCommentVo.BoardComment;
import siNotice.model.noticeService.NoticeService;
import siNotice.model.noticeVo.NoticeViewData;

/**
 * Servlet implementation class SiPreBoardViewServlet
 */
@WebServlet(name = "SiNoticeView", urlPatterns = { "/siNoticeView" })
public class SiNoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/notice/siNoticeView.jsp");
		NoticeViewData vd = new NoticeService().noticeView(noticeNo);
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
