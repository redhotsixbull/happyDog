package siNotice.controller;

import java.io.File;
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

/**
 * Servlet implementation class SiPreBoardUpdateServlet2
 */
@WebServlet(name = "SiNoticeUpdate", urlPatterns = { "/siNoticeUpdate" })
public class SiNoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		//첨부파일
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/siUpload/notice";			//파일 저장경로
		int maxSize = 1024*1024*10;								//파일 최대크기
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"utf-8",new DefaultFileRenamePolicy());
		//multipart/form-data 형식으로 보내진 정보를 받기 위해 MultipartRequest 형태로 변환
		//DefaultFileRenamePolicy -> 기존에 업로드된 파일과 이름이 같은 경우, 덮어쓰기 되는 것을 방지하기 위한 것
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String noticeContent = mRequest.getParameter("noticeContent");		
		String noticeFilename = mRequest.getOriginalFileName("noticeFilename");
		//DefaultFileRenamePlicy 클래스 객체에 의해 파일명이 변경되기 전, 원래의 파일명을 리턴(원본 파일이름 유지)
		String noticeFilepath = mRequest.getFilesystemName("noticeFilename");
		//insert가 작동하는지 확인용 / 로그인 정보와 연동 필요
		//기존 파일 경로
		String noticeOldFilename = mRequest.getParameter("noticeOldFilename");
		String noticeOldFilepath = mRequest.getParameter("noticeOldFilepath");
		//파일 삭제 로직
		String status = mRequest.getParameter("status");
		//파일 객체 생성
		File f = mRequest.getFile("noticeFilename");
		//파일 업로드 판단, 없으면 null
		if(f!=null && f.length()>0) {//첨부파일이 있는 경우
			if(noticeOldFilename != null) {
				//기존 파일을 삭제하고 새로운 첨부파일 추가
				File deleteFile = new File(saveDirectory+"/"+noticeOldFilepath);
				boolean bool = deleteFile.delete();
				System.out.println(bool?"삭제성공":"삭제실패");
				noticeOldFilename = noticeFilename;
				noticeOldFilename = noticeFilepath;
			}
		}else {//첨부파일이 없는 경우
			if(status.equals("stay")) {//새로운 첨부파일이 없고 기존의 파일을 삭제하지 않은 경우
				noticeFilename = noticeOldFilename;
				noticeFilepath = noticeOldFilename;
			}else {//삭제한 경우
				//파일 삭제 로직
				File deleteFile = new File(saveDirectory+"/"+noticeOldFilepath);
				boolean bool = deleteFile.delete();
				System.out.println(bool?"삭제성공":"삭제실패");
			}
		}
		int result = new NoticeService().noticeUpdate(noticeNo, noticeTitle, noticeContent, noticeFilename, noticeFilepath);
		String view = "";
		if(result>0) {
			view = "/WEB-INF/siViews/common/siMsg.jsp";
			request.setAttribute("msg", "게시글 수정이 완료되었습니다.");
			request.setAttribute("loc", "/siNoticeView?noticeNo="+noticeNo);
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
