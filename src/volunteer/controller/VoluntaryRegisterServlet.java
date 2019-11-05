package volunteer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class VoluntaryRegisterServlet
 */
@WebServlet(name = "VoluntaryRegister", urlPatterns = { "/voluntaryRegister" })
public class VoluntaryRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoluntaryRegisterServlet() {
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
		
		String code = mRequest.getParameter("code");
		String name = mRequest.getParameter("name");
		String title = mRequest.getParameter("title");
		String volunDate = mRequest.getParameter("volunDate");
		String volunTime1 = mRequest.getParameter("volunTime1");
		String volunTime2 = mRequest.getParameter("volunTime2");
		String volunTime = volunTime1+","+volunTime2;
		int person = Integer.parseInt(mRequest.getParameter("person"));
		String detail = mRequest.getParameter("detail");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filename");
		
		VoluntaryRegister vr = new VoluntaryRegister(0, 0, code, name, title, volunDate, volunTime, person, detail, 0, filename, filepath, null);
		int result = new VoluntaryService().insertVoluntaryRegister(vr);
		
		if(result > 0) {
			request.setAttribute("msg", "봉사활동 공고 등록을 완료했습니다.");
		}else {
			request.setAttribute("msg", "봉사활동 공고 등록을 실패했습니다.");
		}
		request.setAttribute("loc", "/volunteerList");
		request.getRequestDispatcher("/WEB-INF/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
