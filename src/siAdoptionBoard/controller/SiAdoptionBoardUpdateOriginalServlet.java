package siAdoptionBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import siAdoptionBoard.model.adoptionBoardService.AdoptionBoardService;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siNotice.model.noticeService.NoticeService;
import siNotice.model.noticeVo.Notice;

/**
 * Servlet implementation class SiPreBoardUpdateOriginalServlet
 */
@WebServlet(name = "SiAdoptionBoardUpdateOriginal", urlPatterns = { "/siAdoptionBoardUpdateOriginal" })
public class SiAdoptionBoardUpdateOriginalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardUpdateOriginalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 원본 정보를 보여주기 위해서 생성
		request.setCharacterEncoding("utf-8");
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/siUpload/adoptionBoard";		
		int maxSize = 1024*1024*10;						
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"utf-8",new DefaultFileRenamePolicy());
		int adoptionBoardNo = Integer.parseInt(mRequest.getParameter("adoptionBoardNo"));
		String adoptionBoardName = mRequest.getParameter("adoptionBoardName");
		String adoptionBoardTitle = mRequest.getParameter("adoptionBoardTitle");
		String adoptionBoardContent = mRequest.getParameter("adoptionBoardContent");		
		String adoptionBoardFilename = mRequest.getOriginalFileName("adoptionBoardFilename");
		String adoptionBoardFilepath = mRequest.getFilesystemName("adoptionBoardFilename");
		AdoptionBoard a = new AdoptionBoard(0,0, 0, null, adoptionBoardName, adoptionBoardTitle, adoptionBoardContent, adoptionBoardFilename, adoptionBoardFilepath, null,null, 0, 0,null,null,null,null,null);
		a = new AdoptionBoardService().adoptionBoardUpdateOriginal(adoptionBoardNo,a);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/adoptionBoard/siAdoptionBoardUpdate.jsp");
		request.setAttribute("adoptionBoard", a);
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
