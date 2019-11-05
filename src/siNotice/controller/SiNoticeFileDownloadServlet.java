package siNotice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SiAdoptionFileDownloadServlet
 */
@WebServlet(name = "SiNoticeFileDownload", urlPatterns = { "/siNoticeFileDownload" })
public class SiNoticeFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.변수저장
		String noticeFilename = request.getParameter("filename");
		String noticeFilepath = request.getParameter("filepath");
		System.out.println(noticeFilename+"/"+noticeFilepath);
		//3.다운받을 파일의 경로 생성
		String saveDirectory = getServletContext().getRealPath("/siUpload/notice/");
		//파일과 서블릿 연결
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(saveDirectory+noticeFilepath));
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);//주스트림 역할을 하는 ServletOutputStream (변수를 넣을 수 있음)
		//브라우저 확인 코드
		String resFilename = "";//브라우저마다 방법이 다르기 떄문에 공백
		boolean bool = request.getHeader("user-agent").indexOf("MSIE")!=-1||request.getHeader("user-agent").indexOf("Trident")!=-1;
		System.out.println("IE여부 : "+bool); //IE : true / 아닌경우(크롬) : false
		if(bool) {//브라우저가 IE인 경우
			resFilename = URLEncoder.encode(noticeFilename, "utf-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
			// \\는 윈도우 폴더를 구분할때 사용
		}else {//그 외 브라우저인 경우
			resFilename = new String (noticeFilename.getBytes("utf-8"),"ISO-8859-1");
		}
		//파일 다운로드를 위한 HTTP Header설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);//설정한 이름으로 다운로드 되도록
		//4.파일 전송
		int read = -1;
		while((read = bis.read())!=-1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
