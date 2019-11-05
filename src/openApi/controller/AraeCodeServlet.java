package openApi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import openApi.model.dao.OpenApiDao;
import openApi.model.vo.cityCode;
import openApi.model.vo.getCareCode;

/**
 * Servlet implementation class AraeCodeServlet
 */
@WebServlet(name = "AraeCode", urlPatterns = { "/areaCode" })
public class AraeCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AraeCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<cityCode> list=null;
		String code=request.getParameter("value");  //선택되는 시도 의 코드값
		System.out.println("선택된 구군청 의 코드값:"+code);
		boolean b =true;
		int count=0;
		while(b) {
			list = new OpenApiDao().getAreaCode(code);
			
			if(list.size()!=0) {
				b=false;
			}
			count++;
			if(count==15) {
				b=false;
			}
		}
	
		
		System.out.println("지역 구군청 까지왔음\n 리스트수"+list.size());
		
		
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(list,response.getWriter());
		

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
