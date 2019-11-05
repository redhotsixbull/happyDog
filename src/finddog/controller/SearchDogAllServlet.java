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

import adoption.model.service.FindDogService;
import adoption.model.vo.SearchDogPageData;
import finddog.model.dao.SearchDogDao;
import finddog.model.service.SearchDogService;
import finddog.model.vo.Kind;
import openApi.model.dao.OpenApiDao;
import openApi.model.vo.cityCode;
import siBoard.model.boardVo.BoardPageData;

/**
 * Servlet implementation class searchDogAllServlet
 */
@WebServlet(name = "searchDogAll", urlPatterns = { "/searchDogAll" })
public class SearchDogAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDogAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		int count =0;
		
	
		
			String sDay=new SearchDogDao().preMonth();
			String kind = "";
			String cityCode="";
			
			String eDay=new SearchDogDao().date();
		
	
		
		
		System.out.println(sDay+","+eDay);
		
	
	
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(NumberFormatException e){ // 처음요청시 숫자가 아니므로 1을 줘서 첫페이지로 향하게한다.
			page = 1;
		}
		int page2;
		try {
			page2 = Integer.parseInt(request.getParameter("page2"));
		}catch(NumberFormatException e){ // 처음요청시 숫자가 아니므로 1을 줘서 첫페이지로 향하게한다.
			page2 = 1;
		}
		
		boolean b= true;
		SearchDogPageData sdpd = new SearchDogPageData();
		while(b) {
			sdpd = new SearchDogService().selectListAPI(page2,page,sDay,eDay,kind,cityCode);
			if(!sdpd.getList().isEmpty()) {
				if(sdpd.getList().size()==4) { 
					count++;
					b=false;
					if(count==1) {
						b=false;
					}
				}else if(sdpd.getList().size()==3||sdpd.getList().size()==2||sdpd.getList().size()==1){
					count++;
					if(count==1) {
						b=false;
					}
				}
			}else {
				break;
			}
		
			
		}
		
		

	
		
		ArrayList<cityCode> city=null;	
		ArrayList<Kind> kindds = new ArrayList<>();
		
		try {
			city=new FindDogService().getCityCode();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			kindds= new SearchDogService().getKindCode();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		request.setAttribute("kind", kindds);
		request.setAttribute("city", city);
		
		
		
		
		
		BoardPageData sdpd2 = new BoardPageData();
		
		
		sdpd2 = new SearchDogService().selectListDB(page2,page, sDay, eDay, kind, cityCode);
		
		
		
		
		
		request.setAttribute("sdpd2", sdpd2);
		request.setAttribute("sdpd", sdpd);   //pagedata저장
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/searchDog.jsp");
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
