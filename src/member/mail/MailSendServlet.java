package member.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Service.MemberService;

/**
 * Servlet implementation class MailSendServlet
 */
@WebServlet(name = "MailSend", urlPatterns = { "/mailSend" })
public class MailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("send_email");
		int level = Integer.parseInt(request.getParameter("send_level"));
		try {
			int result = new MemberService().emailOverlap(email);
			
		if(result >0 ) {
			request.setAttribute("result", result);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/emailSend.jsp");
			rd.forward(request, response);
		}else {
		System.out.println("이메일서블릿"+level);
		Random random = new Random();
		int num = random.nextInt(899999)+100001;
		System.out.println(num);
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");				//로그인시 TLS를 사용할 것인지 설정
		prop.put("mail.smtp.host", "smtp.naver.com");				// 이메일 발송을 처리해줄 SMTP서버
		prop.put("mail.smtp.debug", "true");						
		prop.put("mail.smtp.auth","true");							// SMTP서버의 인증을 사용한다
		prop.put("mail.smtp.port", "587");							// TLS의 포트번호, SSL의 포트번호는 465
		Authenticator auth = new MailAuth();		//MailAuth.class에서 Authenticator를 상속
		Session session = Session.getDefaultInstance(prop,auth);	//getDefaultInstance의 첫 번째 파라미터는 앞에 설정한 메일 처리 환경
		MimeMessage msg = new MimeMessage(session);			//MimeMessage는 Message(추상) 클래스를 상속받은 인터넷 메일을 위한 클래스이며 위에서 생성한 세션을 담아 msg객체를 생성한다
		String send =
		"<html><head><title></title>"+
		    "</head><body>"+
		    "<div id='div' style='text-align : center;padding : 50px 20px;}'>"
		    + "<table id='table' style='width:50%; max-width: none;margin: 0 auto;background-color: #fff;padding: 0;border-spacing: 0;border-collapse: collapse; font-size:20px;'>"+
		     "<tr>"
		     + "<th style='border: 1px solid #ddd;border-top: 2px solid rgba(254,67,30);height: 50px;'>인증번호</th>"
		     + "<td style='border: 1px solid #ddd;border-top: 2px solid rgba(254,67,30);height: 50px;'>"+num+"</td></tr></table></div>"+
		"</body></html>";
		
		
		try {
			msg.setSentDate(new Date());		//보내는 날짜 지정
			msg.setFrom(new InternetAddress("wlsdh104@naver.com","해피투게독"));	//Message 클래스의 setFrom()메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명 InternetAddress클래스는 이메일 주소를 나타날 때 사용하는 클래스이다
			InternetAddress to = new InternetAddress(email);		//수신자의 메일
			msg.setRecipient(Message.RecipientType.TO, to);			//Message 클래스의 setRecipient()메소드를 사용하여 수신자를 설정한다. setRecipient()메소드로 수신자,참조,숨은 참조 설정이 가능하다.
			msg.setSubject("해피투게독 메일인증입니다","utf-8");		//메일의 제목		
			msg.setContent(send, "text/html; charset=utf-8");	//메일의 내용
			Transport.send(msg);		//Transport는 메일을 최종적으로 보내는 클래스로 메일을 보내는 부분이다.
			
		} catch (MessagingException e) {			//메일 계정인증 관련 예외 처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {		//지원되지 않는 인코딩을 사용할 경우 예외 처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(num);
		request.setAttribute("result", result);
		request.setAttribute("num", num);
		request.setAttribute("level", level);
		RequestDispatcher rd = request.getRequestDispatcher("/member/emailSend.jsp");
		rd.forward(request, response);
		}
			
		} catch (SQLException e1) {
			System.out.println("이메일 sql 오류");
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
