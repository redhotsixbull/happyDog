package volunteer.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryRegister;

/**
 * Servlet implementation class VoluntaryUpdateEndServlet
 */
@WebServlet(name = "VoluntaryUpdateEnd", urlPatterns = { "/voluntaryUpdateEnd" })
public class VoluntaryUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryUpdateEndServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지사항 작성 오류[enctype]");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/common/msg.jsp").forward(request, response);
			return; //return 통해서 서블릿 종료
		}
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/volunteer";
		int maxSize = 10*1024*1024;	//최대 10MB
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		int no = Integer.parseInt(mRequest.getParameter("no"));
		String title = mRequest.getParameter("title");
		String volunDate = mRequest.getParameter("volunDate");
		String volunTime1 = mRequest.getParameter("volunTime1");
		String volunTime2 = mRequest.getParameter("volunTime2");
		String volunTime = volunTime1+","+volunTime2;
		int person = Integer.parseInt(mRequest.getParameter("person"));
		String detail = mRequest.getParameter("detail");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filename");
		String oldFilename = request.getParameter("oldfilename");
		String oldFilepath = request.getParameter("oldFilepath");
		String fileStatus = mRequest.getParameter("fileStatus");
		File f = mRequest.getFile("filename");
		if(f != null && f.length() > 0) {
			if(oldFilename != null) {
				File deleteFile = new File(saveDirectory+"/"+oldFilepath);
				boolean bool = deleteFile.delete();
			}
		}else {
			if(fileStatus.equals("stay")) {
				filename = oldFilename;
				filepath = oldFilepath;
			}else {
				File deleteFile = new File(saveDirectory+"/"+oldFilepath);
				boolean bool = deleteFile.delete();
			}
		}
		
		VoluntaryRegister vr = new VoluntaryRegister(0, no, null, null, title, volunDate, volunTime, person, detail, 0, oldFilename, oldFilepath, null);
		int result = new VoluntaryService().updateVoluntary(vr);
		
		if(result > 0) {
			request.setAttribute("msg", "봉사활동 공고 수정 완료");
		}else {
			request.setAttribute("msg", "봉사활동 공고 수정 실패");
		}
		request.setAttribute("loc", "/voluntaryView?no="+no);
		request.getRequestDispatcher("/WEB-INF/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
