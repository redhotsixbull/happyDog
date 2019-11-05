<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="order-success">
			<p class="main-comm-tit">주문이 완료되었습니다.</p>

			<p class="order-no">주문번호 : <span> ${orderInfo.no }</span><p>
			<c:if test="${empty orderInfo.id }">
				<p class="order-no">비회원은 주문번호로 주문조회가 가능합니다.</p>
			</c:if>
			<c:if test="${not empty orderInfo.id }">
				<p class="order-no">마이페이지에서 주문조회가 가능합니다.</p>
			</c:if>
			<c:if test="${orderInfo.payMethod eq 'vbank' }">
				<table class="comm-tbl">
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
				</table>
			</c:if>
			<div class="common-tbl-btn-group">
				<c:if test="${empty orderInfo.id }">
					<button class="btn-style1" onclick="location.href='/findOrder.jsp'">주문 내역 확인</button>
				</c:if>
				<c:if test="${not empty orderInfo.id }">
					<button class="btn-style1" onclick="location.href='/myOrder?no=${orderInfo.no}'">주문 내역 확인</button>
				</c:if>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/common/footer.jsp" />