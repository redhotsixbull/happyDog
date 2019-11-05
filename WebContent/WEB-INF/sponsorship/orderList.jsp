<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/order.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">주문 관리</h2>
		<div id="orderListBox">
			<!-- 검색박스 -->
		 	<div class="board-search-box order-search">
		 		<form action="/orderList" method="post" name="search">
		 			<input type="hidden" name="reqPage" value="">
					<input type="search" name="startDay" class="datepicker search-day" value="${search.startDay }" autocomplete="off"> ~ <input type="search" name="endDay" class="datepicker search-day" value="${search.endDay }" autocomplete="off">
					<br><br>
					<select name="status" data-val="${search.status }">
						<option value="">---주문상태---</option>
						<option value="0">주문 완료</option>
						<option value="1">결제 완료</option>
						<option value="2">배송중</option>
						<option value="3">배송 완료</option>
					</select>
			 		<br><br>
			 		<input type="hidden" name="method" value="${search.payMethod }">
			 		<label><input type="radio" name="payMethod" value="card"> 신용카드</label>
					<label><input type="radio" name="payMethod" value="trans"> 실시간 계좌이체</label>
					<label><input type="radio" name="payMethod" value="vbank"> 가상계좌</label>
					<%-- <label><input type="radio" name="payMethod" value="account"> 무통장입금</label>--%>
					<label><input type="radio" name="payMethod" value="phone"> 휴대폰</label>
					<br><br>
					<select name="searchType" data-val="${search.searchType }">
						<option value="no">주문번호</option>
						<option value="name">주문자명</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="searchVal" class="search-word" value="${search.searchVal }">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					&nbsp;<button type="button" onclick="location.href='/orderList'" class="bbs-search-btn" title="검색">초기화</button>
				</form>
			</div>
				
			<p class="total">총 주문 수 : ${total.count } / 총 후원 금액 : <fmt:formatNumber value="${total.price }" pattern="#,###" /> 원</p>
			<table class="comm-tbl type2">
				<colgroup>
					<col width="15%">
					<col width="10%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>주문자명</th>
						<th>주문상품</th>
						<th>결제수단</th>
						<th>후원금액</th>
						<th>주문날짜</th>
						<th>주문상태</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty orderList.orderinfoList }">
						<tr>
							<td colspan="7">
								<p class="none">후원내역이 없습니다.</p>
							</td>
						</tr>
					</c:if>
					<c:forEach items="${orderList.orderinfoList}" var="order">
						<tr>
							<td><a href="/orderView?no=${order.no}">${order.no }</a></td>
							<td>
								${order.name }<br>
								<c:if test="${not empty order.id}">(${order.id })</c:if>
								<c:if test="${empty order.id}">(비회원)</c:if>
							</td>
							<td>
								<c:set var="code" value="" />
								<c:forEach items="${prdList }" var="prd">
									<c:if test="${prd.prdName eq order.productName}">
										<c:set var="code" value="${prd.prdCode }" />
									</c:if>
								</c:forEach>
								<a href="/viewProduct?code=${code}">
									${order.productName }
								</a>
							</td>
							<td>
								<c:if test="${order.payMethod eq 'card'}">신용카드</c:if>
								<c:if test="${order.payMethod eq 'trans' }">실시간 계좌이체</c:if>
								<c:if test="${order.payMethod eq 'vbank' }">가상계좌</c:if>
								<c:if test="${order.payMethod eq 'account' }">무통장입금</c:if>
								<c:if test="${order.payMethod eq 'phone' }">휴대폰</c:if>
							</td>	
							<td><fmt:formatNumber value="${order.pay }" pattern="#,###" /> 원</td>	
							<td>${order.sponDate }</td>
							<td>
								<select class="status" data-status="${order.status}" data-no="${order.no}">
									<option>---주문상태---</option>
									<option value="0">주문 완료</option>
									<option value="1">결제 완료</option>
									<option value="2">배송중</option>
									<option value="3">배송 완료</option>
								</select>
							</td>						
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- paging -->
		 	<div class="paging">
		 		${orderList.pageNavi }	
		 	</div>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />