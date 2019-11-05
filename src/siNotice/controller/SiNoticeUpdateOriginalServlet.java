package siNotice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import siNotice.model.noticeService.NoticeService;
import siNotice.model.noticeVo.Notice;

/**
 * Servlet implementation class SiPreBoardUpdateOriginalServlet
 */
@WebServlet(name = "SiNoticeUpdateOriginal", urlPatterns = { "/siNoticeUpdateOriginal" })
public class SiNoticeUpdateOriginalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeUpdateOriginalServlet() {
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
		String saveDirectory = root+"/siUpload/notice";		
		int maxSize = 1024*1024*10;						
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"utf-8",new DefaultFileRenamePolicy());
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeName = mRequest.getParameter("noticeName");
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String noticeContent = mRequest.getParameter("noticeContent");		
		String noticeFilename = mRequest.getOriginalFileName("noticeFilename");
		String noticeFilepath = mRequest.getFilesystemName("noticeFilename");
		Notice n = new Notice(0,0, 0, null, noticeName, noticeTitle, noticeContent, noticeFilename, noticeFilepath, null,null, 0, 0, null,null,null,null,null);
		n = new NoticeService().noticeUpdateOriginal(noticeNo,n);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/notice/siNoticeUpdate.jsp");
		request.setAttribute("notice", n);
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
