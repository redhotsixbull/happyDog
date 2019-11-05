package openApi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import openApi.model.dao.OpenApiDao;
import openApi.model.vo.CareCode;

/**
 * Servlet implementation class CareCodeServlet
 */
@WebServlet(name = "CareCode", urlPatterns = { "/careCode" })
public class CareCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CareCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String citcode=request.getParameter("citValue");
		String arecode=request.getParameter("areValue");	
		ArrayList<CareCode> list =null;
		
		boolean b =true;
		int count=0;
		
		while(b) {
			list = new OpenApiDao().getCareCode(arecode,citcode);
			
			if(list.size()!=0) {
				b=false;
			}
			count++;
			
			
			if(count==30) {
				b=false;
			}
			
		}
		
		
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
