package finddog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finddog.model.service.SearchDogService;
import siBoard.model.boardVo.BoardPageData;

/**
 * Servlet implementation class FindBoardSearchServlet
 */
@WebServlet(name = "FindBoardSearch", urlPatterns = { "/findBoardSearch" })
public class FindBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("searchWord");
		String word=request.getParameter("keyword");
		BoardPageData bp=null;
		int reqPage;
		int type=4;
		int sel=0;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		//sel==1이면 이름 sel==2이면 제목
		if(search.equals("boardName")) {
			//이름 검색
			sel=1;
			 bp = new SearchDogService().findSearchBoard(reqPage,type,word,sel);
			
			
		}else if(search.equals("boardTitle")) {
			//제목검색
			sel=2;
			bp = new SearchDogService().findSearchBoard(reqPage,type,word,sel);
			
		}
		
		request.setAttribute("bp", bp);	
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/FindBoard.jsp");
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
