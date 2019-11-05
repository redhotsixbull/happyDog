<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="http://localhost/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="">
			<p class="main-comm-tit">주문 상세</p>
			<table class="comm-tbl order-view">
				<tr class="tr-title"><td colspan="2">주문 정보</td></tr>
				<tr>
					<td>주문번호</td><td>${orderInfo.no }</td>
				</tr>
				<tr>
					<td>주문상태</td>
					<td>
						<c:if test="${orderInfo.status eq 0 }">주문 완료</c:if>
						<c:if test="${orderInfo.status eq 1 }">결제 완료</c:if>
						<c:if test="${orderInfo.status eq 2 }">배송중</c:if>
						<c:if test="${orderInfo.status eq 3 }">배송완료</c:if>
					</td>
				</tr>
				<tr>
					<td>주문상품</td>
					<td>
						<c:set var="code" value="" />
						<c:set var="img" value="no_detail_img.gif" />
						<c:forEach items="${prdList }" var="prd">
							<c:if test="${prd.prdName eq orderInfo.productName}">
								<c:set var="code" value="${prd.prdCode }" />
								<c:set var="img" value="${prd.prdImg }" />
							</c:if>
						</c:forEach>
						<a href="/viewProduct?code=${code}">
							<img height="100" src="/img/${img }">${orderInfo.productName }
						</a>
					</td>
				</tr>
				<tr>
					<td>주문수량</td>
					<td>${orderInfo.amount } 개
					</td>
				</tr>
				<tr class="tr-title"><td colspan="2">주문자 정보</td></tr>
				<tr>
					<td>주문자 명</td><td>${orderInfo.name }</td>
				</tr>
				<tr>
					<td>주문자 연락처</td><td>${orderInfo.phone }</td>
				</tr>
				<tr>
					<td>주문자 이메일</td><td>${orderInfo.email }</td>
				</tr>
				<tr class="tr-title"><td colspan="2">배송지 정보</td></tr>
				<tr>
					<td>받으시는 분</td><td>${orderInfo.receiveName }</td>
				</tr>
				<tr>
					<td>배송지 연락처</td><td>${orderInfo.receivePhone }</td>
				</tr>
				<tr>
					<td>배송지 주소</td><td>( ${orderInfo.post } )<br>${fn:split(orderInfo.address,'//')[0] } ${fn:split(orderInfo.address,'//')[1] }</td>
				</tr>
				<c:if test="${not empty orderInfo.memo }">
					<tr>
						<td>배송 메모</td><td>${orderInfo.memo }</td>
					</tr>
				</c:if>
				<c:if test="${not empty orderInfo.deilveryNum }">
					<tr>
						<td>운송장 번호</td><td>${orderInfo.deilveryNum } <a class="file-del-btn delFile" href="javascript:window.open('http://nexs.cjgls.com/web/info.jsp?slipno=${orderInfo.deilveryNum }','');">배송조회</a></td>
					</tr>
				</c:if>
				<tr class="tr-title"><td colspan="2">결제 정보</td></tr>
				<tr>
					<td>결제 수단</td>
					<td>
						<c:if test="${orderInfo.payMethod eq 'card' }">신용카드</c:if>
						<c:if test="${orderInfo.payMethod eq 'trans' }">실시간 계좌이체</c:if>
						<c:if test="${orderInfo.payMethod eq 'vbank' }">가상계좌</c:if>
						<c:if test="${orderInfo.payMethod eq 'account' }">무통장입금</c:if>
						<c:if test="${orderInfo.payMethod eq 'phone' }">휴대폰</c:if>
					</td>
				</tr>
				<c:if test="${orderInfo.payMethod eq 'vbank' }">
					<tr>
						<td>입금 은행</td><td>${orderInfo.vbankName }</td>
					</tr>
					<tr>
						<td>입금 계좌</td><td>${orderInfo.vbankNum }</td>
					</tr>
					<tr>
						<td>예금주</td><td>${orderInfo.vbankHolder }</td>
					</tr>
					<tr>
						<td>입금 기한</td><td>${orderInfo.vbankDate }</td>
					</tr>
				</c:if>
				<tr>
					<td>결제 금액</td><td><fmt:formatNumber value="${orderInfo.pay }" pattern="#,###" /> 원</td>
				</tr>
			</table>
			<div class="common-tbl-btn-group">
				<c:if test="${not empty sessionScope.member }">
					<button class="btn-style2" onclick="location.href='/myOrderList'">목록으로</button>
				</c:if>
			</div>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />