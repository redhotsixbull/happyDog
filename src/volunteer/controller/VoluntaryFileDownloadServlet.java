package volunteer.controller;

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
 * Servlet implementation class VoluntaryFileDownloadServlet
 */
@WebServlet(name = "VoluntaryFileDownload", urlPatterns = { "/voluntaryFileDownload" })
public class VoluntaryFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryFileDownloadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String filename = request.getParameter("filename");
		String filepath = request.getParameter("filepath");
		
		String saveDirectory = getServletContext().getRealPath("/upload/volunteer/");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(saveDirectory+filepath));
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		//브라우저가 IE인지 판단
		String resFilename = ""; //브라우저마다 다르게 설정해야해서 비워둠
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1; //브라우저 구분방법
		System.out.println("IE 여부 : "+bool);
		//브라우저에 따라 파일명 세팅
		if(bool) { //브라우저가 IE인 경우
			resFilename = URLEncoder.encode(filename, "UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
			System.out.println(resFilename);
		}else { //그 외 브라우저인 경우
			resFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//파일 다운로드를 위한 Http Header 설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		
		//파일전송(파일다운로드)
		int read = -1;
		while((read=bis.read()) != -1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
