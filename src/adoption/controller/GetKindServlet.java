package adoption.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adoption.model.service.BookApplyService;
import adoption.model.vo.DogKind;

/**
 * Servlet implementation class GetKindServlet
 */
@WebServlet(name = "GetKind", urlPatterns = { "/getKind" })
public class GetKindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public GetKindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dogsize = request.getParameter("dogsize");
		System.out.println("강아지크기 : "+dogsize);
		ArrayList<DogKind> list;
		try {
			list = new BookApplyService().getKind(dogsize);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			new Gson().toJson(list,response.getWriter());
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/error/sqlError.jsp");
			request.setAttribute("msg", "SQL 에러가 발생했습니다.");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
