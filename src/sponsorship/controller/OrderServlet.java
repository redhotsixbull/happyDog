package sponsorship.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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


import com.oreilly.servlet.MultipartRequest;

import member.mail.MailAuth;
import member.model.vo.Member;
import sponsorship.model.service.OrderService;
import sponsorship.model.vo.*;

@WebServlet(name = "order", urlPatterns = {  "/order", "/orderIng", "/orderEnd",
		"/findOrder", "/myOrder", "/myOrderList" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO(0));
		prdList.add(new ProductVO(1));
		prdList.add(new ProductVO(2));
		
		Member member = (Member)request.getSession(false).getAttribute("member");
	
		/* 주문서 작성 페이지 */	
		if(action.equals("order")) {
			int prdCode = Integer.parseInt(request.getParameter("prdCode"));
			String amount = request.getParameter("amount");
			String price = request.getParameter("price");

			request.setAttribute("amount", amount);
			request.setAttribute("price", price);
			request.setAttribute("prd", prdList.get(prdCode));
			request.getRequestDispatcher("/WEB-INF/sponsorship/orderForm.jsp").forward(request, response);
			
		/* 주문등록(AJAX) */
		}else if(action.equals("orderIng")) {

			String root = getServletContext().getRealPath("/");//절대경로
			String saveDirectory = root+"upload";
			MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, "utf-8");

			String no = mRequest.getParameter("orderNo");
			String id = mRequest.getParameter("id");
			String name = mRequest.getParameter("name");
			String phone = mRequest.getParameter("phone1")+"-"+mRequest.getParameter("phone2")+"-"+mRequest.getParameter("phone3");
			String payMethod = mRequest.getParameter("payMethod");
			int pay = Integer.parseInt(mRequest.getParameter("pay"));
			int amount = Integer.parseInt(mRequest.getParameter("amount"));
			String productName = mRequest.getParameter("productName");
			String memo = mRequest.getParameter("memo");
			String post = mRequest.getParameter("post");
			String address = mRequest.getParameter("address")+"//"+mRequest.getParameter("address2");
			String email = mRequest.getParameter("email");
			String receiveName = mRequest.getParameter("receiveName");
			String receivePhone = mRequest.getParameter("receivePhone1")+"-"+mRequest.getParameter("receivePhone2")+"-"+mRequest.getParameter("receivePhone3");
			String vbankName = mRequest.getParameter("vbankName");
			String vbankNum = mRequest.getParameter("vbankNum");
			String vbankHolder = mRequest.getParameter("vbankHolder");
			String vbankDate = mRequest.getParameter("vbankDate");
			OrderInfoVO orderInfo = new OrderInfoVO(0,no, id, name, phone, payMethod, pay, amount, 0, null, productName, "sysdate", memo, post, address, email, receiveName, receivePhone,vbankName,vbankNum,vbankHolder,vbankDate);

			try {
				int result =  new OrderService().insertOrder(orderInfo);
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				if(result>0){
					out.print("/orderEnd?no="+no);
				}else{
					out.print("fail");
				}
			} catch (SQLException e) {
				request.setAttribute("msg", "SQL에러가 발생했습니다.");
				request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
			}
			
		/* 주문 완료 페이지 */
		}else if(action.equals("orderEnd")) {
			String no = request.getParameter("no");
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				
				/* 이메일 보내기 */
				Properties prop = System.getProperties();
				prop.put("mail.smtp.starttls.enable", "true");
				prop.put("mail.smtp.host", "smtp.naver.com");
				prop.put("mail.smtp.debug", "true");						
				prop.put("mail.smtp.auth","true");
				prop.put("mail.smtp.port", "587");
				Authenticator auth = new MailAuth();
				Session mailSession = Session.getDefaultInstance(prop,auth);	
				MimeMessage msg = new MimeMessage(mailSession);
				
				try {
					msg.setSentDate(new Date());
					msg.setFrom(new InternetAddress("wlsdh104@naver.com","해피투게독"));
					InternetAddress to = new InternetAddress(orderInfo.getEmail());		//수신자 메일
					msg.setRecipient(Message.RecipientType.TO, to);
					msg.setSubject(orderInfo.getName()+"님의 주문이 완료되었습니다.","utf-8");
					String mailCon = "";    
				    
					mailCon += "<html><head></head><body><script>alert('dfdf');</script>"
							+ "<table style=\"border-collapse: collapse;border-spacing: 0;width:100%; max-width:800px; margin:0 auto; background-color:#fff;\" >\r\n" + 
							"				<tr class=\"border-top:2px solid rgba(254,67,30);\"><td colspan=\"2\" style=\"padding:15px;line-height:20px;background-color: #f5f5f5; font-weight: bold; font-size: 15px;\">주문 정보</td></tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">주문번호</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getNo()+"</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">주문상태</td>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">\r\n"; 
					if(orderInfo.getStatus()==0) {
						mailCon += "주문 완료\r\n";
					}else if(orderInfo.getStatus()==1) {
						mailCon += "결제 완료\r\n";
					}else if(orderInfo.getStatus()==2) {
						mailCon += "배송중\r\n";
					}else if(orderInfo.getStatus()==3) {
						mailCon += "배송 완료\r\n";
					}
					mailCon += "					</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">주문상품</td>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">\r\n"; 
					String code = "";
					String img = "";
					for (ProductVO p : prdList) {
						if(p.getPrdName().equals(orderInfo.getProductName())) {
							code = p.getPrdCode();
							img = p.getPrdImg();
						}
					}

					mailCon += "						<a href=\"http://192.168.10.72/viewProduct?code="+code+"\" style=\"color:black;text-decoration:none;\">\r\n" + 
							"							<img height=\"100\" src=\"http://192.168.10.72/img/"+img+"\"><br>"+orderInfo.getProductName()+"\r\n" + 
							"						</a>\r\n" + 
							"					</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr class=\"tr-title\"><td colspan=\"2\" style=\"padding:15px;line-height:20px;background-color: #f5f5f5; font-weight: bold; font-size: 15px;\">주문자 정보</td></tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">주문자 명</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getName()+"</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">주문자 연락처</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getPhone()+"</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">주문자 이메일</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getEmail()+"</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr class=\"tr-title\"><td colspan=\"2\" style=\"padding:15px;line-height:20px;background-color: #f5f5f5; font-weight: bold; font-size: 15px;\">배송지 정보</td></tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">받으시는 분</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getReceiveName()+"</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">배송지 연락처</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getReceivePhone()+"</td>\r\n" + 
							"				</tr>\r\n" + 
							"				<tr>\r\n";
					
					
					String addr[] = orderInfo.getAddress().split("//"); 
					mailCon += "					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">배송지 주소</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">( "+orderInfo.getPost()+" )<br>"+addr[0]+" "+addr[1]+"</td>\r\n" + 
							"				</tr>\r\n";
					
					if(orderInfo.getMemo()!=null) {
					mailCon += "					<tr>\r\n" + 
							"						<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">배송 메모</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getMemo()+"</td>\r\n" + 
							"					</tr>\r\n"; 
					}
					if(orderInfo.getDeilveryNum()!=null) {
					mailCon += "					<tr>\r\n" + 
							"						<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">운송장 번호</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getDeilveryNum()+"</td>\r\n" + 
							"					</tr>\r\n";
					}
					mailCon += "				<tr class=\"tr-title\"><td colspan=\"2\" style=\"padding:15px;line-height:20px;background-color: #f5f5f5; font-weight: bold; font-size: 15px;\">결제 정보</td></tr>\r\n" + 
							"				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">결제 수단</td>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">\r\n";
					if(orderInfo.getPayMethod().equals("card")) {
						mailCon += "신용카드\r\n";
					}else if(orderInfo.getPayMethod().equals("trans")) {
						mailCon += "실시간 계좌이체\r\n";
					}else if(orderInfo.getPayMethod().equals("vbank")) {
						mailCon += "가상계좌\r\n";
					}else if(orderInfo.getPayMethod().equals("account")) {
						mailCon += "무통장입금\r\n";
					}else if(orderInfo.getPayMethod().equals("phone")) {
						mailCon += "휴대폰\r\n";
					}
				
					mailCon += "					</td>\r\n" + 
							"				</tr>\r\n";
					
					if(orderInfo.getPayMethod().equals("vbank")) {	
					mailCon += "					<tr>\r\n" + 
							"						<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">입금 은행</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getVbankName()+"</td>\r\n" + 
							"					</tr>\r\n" + 
							"					<tr>\r\n" + 
							"						<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">입금 계좌</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getVbankNum()+"</td>\r\n" + 
							"					</tr>\r\n" + 
							"					<tr>\r\n" + 
							"						<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">예금주</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getVbankHolder()+"</td>\r\n" + 
							"					</tr>\r\n" + 
							"					<tr>\r\n" + 
							"						<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">입금 기한</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getVbankDate()+"</td>\r\n" + 
							"					</tr>\r\n";
					}
					mailCon += "				<tr>\r\n" + 
							"					<td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">결제 금액</td><td style=\"border:1px solid #ddd; padding:15px; text-align:left; font-weight:normal; line-height:20px;\">"+orderInfo.getPay()+" 원</td>\r\n" + 
							"				</tr>\r\n" + 
							"			</table></body></html>";
					msg.setContent(mailCon,"text/html;charset=utf-8");		// 내용
					Transport.send(msg);
					
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("orderInfo", orderInfo);
				request.getRequestDispatcher("/WEB-INF/sponsorship/orderSuc.jsp").forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("msg", "SQL에러가 발생했습니다.");
				request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
			}
			
		/* 비회원 주문 조회  */
		}else if(action.equals("findOrder")) {
			String no = request.getParameter("no");
			String phone = request.getParameter("phone");
			try {
				int result = new OrderService().findOrder(no,phone);
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				if(result>0){
					out.print("/myOrder?no="+no);
				}else{
					out.print("fail");
				}
			} catch (SQLException e) {
				request.setAttribute("msg", "SQL에러가 발생했습니다.");
				request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
			}
			
		/* 나의 주문내역 상세 페이지 */
		}else if(action.equals("myOrder")) {
			String no = request.getParameter("no");
			
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				request.setAttribute("orderInfo", orderInfo);
				request.setAttribute("prdList", prdList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/myOrder.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("msg", "SQL에러가 발생했습니다.");
				request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
			}
		
		/* 나의 후원내역 페이지 */
		}else if(action.equals("myOrderList")){
			if(member != null) {
				int reqPage;
				try {
					reqPage = Integer.parseInt(request.getParameter("reqPage"));
				}catch (Exception e) {
					reqPage = 1;
				}
				String startDay = request.getParameter("startDay");
				String endDay = request.getParameter("endDay");
				
				/* 기본 검색 기간 1개월로 설정 */
				Date today = new Date();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				if(startDay == null) {
					Date sday = new Date();
					sday.setMonth(sday.getMonth()-1);
					
					startDay = f.format(sday);
				}
				if(endDay == null) {
					endDay = f.format(today);
				}
				
				try {
					SearchVO search = new SearchVO(reqPage, startDay, endDay, null, null, "id", member.getId(),null);
					request.setAttribute("search", search);
						
					OrderListVO orderList = new OrderService().selectOrder(search);
					request.setAttribute("orderList", orderList);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/myOrderList.jsp");
					rd.forward(request, response);
				} catch (SQLException e) {
					request.setAttribute("msg", "SQL에러가 발생했습니다.");
					request.getRequestDispatcher("/error/sqlError.jsp").forward(request, response);
				}	
			}else {
				request.setAttribute("msg", "로그인 후 이용해주세요");
				request.setAttribute("loc", "/member/login.jsp");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
