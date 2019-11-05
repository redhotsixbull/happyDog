package finddog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import siBoard.model.boardService.BoardService;
import siBoard.model.boardVo.Board;

/**
 * Servlet implementation class TakeBoardUpdateServlet
 */
@WebServlet(name = "TakeBoardUpdate", urlPatterns = { "/takeBoardUpdate" })
public class TakeBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/siUpload/board";		
		int maxSize = 1024*1024*10;						
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"utf-8",new DefaultFileRenamePolicy());
		int boardNo = Integer.parseInt(mRequest.getParameter("boardNo"));
		String boardName = mRequest.getParameter("boardName");
		String boardTitle = mRequest.getParameter("boardTitle");
		String boardContent = mRequest.getParameter("boardContent");		
		String boardFilename = mRequest.getOriginalFileName("boardFilename");
		String boardFilepath = mRequest.getFilesystemName("boardFilepath");
		Board b = new Board(0,0, 0, null, boardName, boardTitle, boardContent, boardFilename, boardFilepath, null,null, 0, 0,null,null,null,null,null);
		b = new BoardService().boardUpdateOriginal(boardNo,b);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/BoardUpdate.jsp");
		request.setAttribute("board", b);
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
