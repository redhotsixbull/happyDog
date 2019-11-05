package finddog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoard.model.boardService.BoardService;
import siBoard.model.boardVo.BoardViewData;

/**
 * Servlet implementation class DetailFindBoardServlet
 */
@WebServlet(name = "DetailFindBoard", urlPatterns = { "/detailFindBoard" })
public class DetailFindBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailFindBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int no=Integer.parseInt(request.getParameter("boardNo"));
		
		
		BoardViewData bvd  = new BoardService().takeBoardView(no);
		
		request.setAttribute("vd", bvd);
		String dogkind=bvd.getB().getDogKind();
		String kindName= new BoardService().getDogKindName(dogkind);
		
		
		request.setAttribute("kindNm", kindName);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/DetailFindBoard.jsp");
		
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
