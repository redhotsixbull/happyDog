package siBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoard.model.boardService.BoardService;
import siBoard.model.boardVo.Board;
import siBoard.model.boardVo.BoardPageData;

/**
 * Servlet implementation class SiPreBoardSearchServlet
 */
@WebServlet(name = "SiPreBoardSearch", urlPatterns = { "/siPreBoardSearch" })
public class SiPreBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiPreBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		String searchType = request.getParameter("searchWord");
		String searchKeyword = request.getParameter("keyword");
		BoardPageData bp = new BoardService().boardSearch(reqPage,searchType,searchKeyword);
		String view = "";
		if(bp.getList().size()>0){
			request.setAttribute("bp", bp);
			view = "/WEB-INF/siViews/board/siPreBoardSearchList.jsp";
			request.setAttribute("searchKeyword", searchType);
			request.setAttribute("keyword", searchKeyword);
		}else {
			request.setAttribute("msg", "일치하는 게시글이 없습니다.");
			request.setAttribute("loc", "/siPreBoard");
			view = "/WEB-INF/siViews/common/siMsg.jsp";
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
