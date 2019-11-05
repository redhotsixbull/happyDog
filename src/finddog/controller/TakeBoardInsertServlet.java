package finddog.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finddog.model.service.SearchDogService;
import finddog.model.vo.Kind;
import openApi.model.dao.OpenApiDao;
import openApi.model.vo.cityCode;

/**
 * Servlet implementation class TakeBoardInsertServlet
 */
@WebServlet(name = "TakeBoardInsert", urlPatterns = { "/takeBoardInsert" })
public class TakeBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> mlist = new ArrayList<>();
		ArrayList<Integer> ylist = new ArrayList<>();
		ArrayList<Integer> dlist = new ArrayList<>();
		ArrayList<cityCode> city=null;	
		ArrayList<Kind> kind = new ArrayList<>();
		
		city=new OpenApiDao().getCityCode();
		
		
		for(int i=0;i<10;i++) {
			ylist.add(2010+i);
		}
		
		for(int i=1;i<13;i++) {
			mlist.add(i);
		
		}
		for(int i=0;i<31;i++) {
			dlist.add(i);
		}
		
		
		try {
			kind= new SearchDogService().getKindCode();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		request.setAttribute("mList", mlist);
		request.setAttribute("yList", ylist);
		request.setAttribute("dList", dlist);
		request.setAttribute("kind", kind);
		request.setAttribute("city", city);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/TakeBoardInsert.jsp");
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
