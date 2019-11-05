package sponsorship.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import qna.model.service.QnaService;
import qna.model.vo.QnaVO;
import sponsorship.model.vo.*;

@WebServlet(name = "Product", urlPatterns = { "/sponsorship", "/viewProduct" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO(0));
		prdList.add(new ProductVO(1));
		prdList.add(new ProductVO(2));
		
		/* 후원상품 리스트 페이지 */
		if(action.equals("sponsorship")) {
			request.setAttribute("prdList", prdList);
			request.getRequestDispatcher("/WEB-INF/sponsorship/productList.jsp").forward(request, response);
			
		/* 후원상품 상세 페이지 */
		}else if(action.equals("viewProduct")) {
			String code = request.getParameter("code");
			if(code==null || code.equals("")){
				request.setAttribute("msg", "삭제된 상품입니다.");
				request.setAttribute("loc", "/sponsorship");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
				return;
				
			}else{
				int prdCode = Integer.parseInt(code);
				request.setAttribute("prd", prdList.get(prdCode));		
				try {
					ArrayList<QnaVO> qnaList = new QnaService().selectQna(1,5,new SearchVO(0, null, null, null, null, null, null, code));
					request.setAttribute("qnaList", qnaList);
				} catch (SQLException e) {
					request.setAttribute("msg", "SQL에러가 발생했습니다.");
					request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
				}
				request.getRequestDispatcher("/WEB-INF/sponsorship/productDetail.jsp").forward(request, response);
			}
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
