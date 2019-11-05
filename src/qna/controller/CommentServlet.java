package qna.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.CommentService;
import qna.model.vo.CommentVO;

@WebServlet(name = "Comment", urlPatterns = { "/insertComment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member member = (Member)request.getSession(false).getAttribute("member");
		
		String boardRef = request.getParameter("boardRef");
		String boardCommentContent = request.getParameter("boardCommentContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
		CommentVO comment = new CommentVO(0, 3, boardCommentContent, Integer.parseInt(boardRef), null);
		
		if(member!=null && member.getMemberLevel()==2) {
			try {
				int result = new CommentService().insertComment(comment);
				if(result<1) {
					System.out.println("Q&A 답변 등록 실패~~~");
				}
				response.sendRedirect("/qnaView?boardNo="+boardRef);
			} catch (SQLException e) {
				request.setAttribute("msg", "SQL에러가 발생했습니다.");
				request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
			}
			
		/* 관리자 아닐때 예외처리 */
		}else {
			request.setAttribute("msg", "답변 권한이 없습니다. 로그인상태를 확인해주세요.");
			request.setAttribute("loc", "/qnaList");
			request.getRequestDispatcher("/WEB-INF/common/msg.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
