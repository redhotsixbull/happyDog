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
		<h2 class="comm-content-tit">나의 후원 내역</h2>
		<div id="orderListBox" class="">
			<!-- 검색박스 -->	
		 	<div class="board-search-box order-search">
		 		<form action="/myOrderList" method="post" name="search">
		 			<input type="hidden" name="reqPage" value="">
		 			&nbsp;<button type="button" onclick="setDay(0);" class="bbs-search-btn period" title="검색">1주일</button>
					&nbsp;<button type="button" onclick="setDay(1);" class="bbs-search-btn period" title="검색">1개월</button>
					&nbsp;<button type="button" onclick="setDay(3);" class="bbs-search-btn period" title="검색">3개월</button>
					&nbsp;<button type="button" onclick="setDay(6);" class="bbs-search-btn period" title="검색">6개월</button>
					&nbsp;<input type="search" name="startDay" class="datepicker search-day" value="${search.startDay }" autocomplete="off"> ~ <input type="search" name="endDay" class="datepicker search-day" value="${search.endDay }" autocomplete="off"> 			
					&nbsp;<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</form>
			</div>
			<br>
			<table class="comm-tbl type2">
				<colgroup>
					<col width="15%">
					<col width="40%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>주문상품</th>
						<th>후원금액</th>
						<th>주문날짜</th>
						<th>주문상태</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty orderList.orderinfoList }">
						<tr>
							<td colspan="5">
								<p class="none">검색 기간내의 후원내역이 없습니다.</p>
							</td>
						</tr>
					</c:if>
					<c:forEach items="${orderList.orderinfoList}" var="order">
						<tr onclick="location.href='/myOrder?no=${order.no}'" style="cursor:pointer;">
							<td>${order.no }</td>
							<td>${order.productName }</td>
							<td><fmt:formatNumber value="${order.pay }" pattern="#,###" /> 원</td>	
							<td>${order.sponDate }</td>
							<td>
								<c:if test="${order.status eq 0 }">주문 완료</c:if>
								<c:if test="${order.status eq 1 }">결제 완료</c:if>
								<c:if test="${order.status eq 2 }">배송중</c:if>
								<c:if test="${order.status eq 3 }">배송완료</c:if>
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