package siAdoptionBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import siAdoptionBoard.model.adoptionBoardService.AdoptionBoardService;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;

/**
 * Servlet implementation class SiPreBoardInsertServlet
 */
@WebServlet(name = "SiAdoptionBoardInsert", urlPatterns = { "/siAdoptionBoardInsert" })
public class SiAdoptionBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		//첨부파일
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/siUpload/adoptionBoard";			//파일 저장경로
		int maxSize = 1024*1024*10;								//파일 최대크기
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"utf-8",new DefaultFileRenamePolicy());
		//multipart/form-data 형식으로 보내진 정보를 받기 위해 MultipartRequest 형태로 변환
		//DefaultFileRenamePolicy -> 기존에 업로드된 파일과 이름이 같은 경우, 덮어쓰기 되는 것을 방지하기 위한 것
		int adoptionBoardType = Integer.parseInt(mRequest.getParameter("adoptionBoardType"));
		String adoptionBoardId = mRequest.getParameter("memberId");
		//회원의 로그인 정보를 가져와서 boardId에 대입
		String adoptionBoardName = mRequest.getParameter("memberName");
		String adoptionBoardTitle = mRequest.getParameter("adoptionBoardTitle");
		String adoptionBoardContent = mRequest.getParameter("adoptionBoardContent");		
		String adoptionBoardFilename = mRequest.getOriginalFileName("adoptionBoardFilename");
		//DefaultFileRenamePlicy 클래스 객체에 의해 파일명이 변경되기 전, 원래의 파일명을 리턴(원본 파일이름 유지)
		String adoptionBoardFilepath = mRequest.getFilesystemName("adoptionBoardFilename");
		AdoptionBoard a = new AdoptionBoard(0,0, adoptionBoardType, adoptionBoardId, adoptionBoardName, adoptionBoardTitle, adoptionBoardContent, adoptionBoardFilename, adoptionBoardFilepath, null,null, 0, 0, null,null,null,null,null);
		//insert가 작동하는지 확인용 / 로그인 정보와 연동 필요
		int result = new AdoptionBoardService().adoptionBoardInsert(a);
		String view = "";
		if(result>0 && a.getAdoptionBoardType()==2){
			request.setAttribute("msg", "게시글이 등록되었습니다.");
			request.setAttribute("loc", "/siAdoptionBoard");
			view = "/WEB-INF/siViews/common/siMsg.jsp";
		}else {
			request.setAttribute("msg", "게시글을 다시 등록해주세요.");
			request.setAttribute("loc", "/siAdoptionBoard");
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
