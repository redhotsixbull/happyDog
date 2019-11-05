<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/js/script.js"></script>
<script type="text/javascript" src="/js/pay.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<p class="main-comm-tit">주문서 작성</p>
		<div class="order-form">
			<div class="order-product-box">
				<table class="order-product-tbl comm-tbl type2">
					<colgroup>
						<col width="25%"/>
						<col width="25%"/>
						<col width="25%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th>상품</th>
						<th>상품 이름</th>
						<th>수량</th>
						<th>금액</th>
					</tr>
					<tr>
						<td><img src="/img/${prd.prdImg }" width="150" onclick="location.href='/viewProduct?code=${prd.prdCode }'"></td>
						<td id="prdName">${prd.prdName }</td>
						<td>${amount} 개</td>
						<td><fmt:formatNumber value="${price}" pattern="#,###" /> 원</td>
					</tr>
				</table>
			</div>
			
			<form id="orderForm">
				<input type="hidden" name="orderNo">
				<input type="hidden" name="productName" value="${prd.prdName }">
				<div class="order">
					<p class="main-comm-tit">주문자 정보</p>
					<p class="necessary"><b>*</b> 는 필수입력항목입니다. </p>
					<table class="comm-tbl view order-tbl">
						<colgroup>
							<col width="15%"/>
							<col width="/"/>
						</colgroup>
						<tr>
							<th>이름 <b class="star">*</b></th>
							<td><input type="hidden" name="id" value="${sessionScope.member.id }"><input type="text" class="middle" name="name" value="${sessionScope.member.name }"></td>
						</tr>
						<tr>
							<th>연락처 <b class="star">*</b></th>
							<td>
								<c:set var="phone" value="${fn:split(sessionScope.member.phone,'-')[0] }" />
								<select name="phone1" class="phone short">
									<option value="${phone }" selected>${phone }</option>
									<option value="02">02</option>
									<option value="031">031</option>
									<option value="032">032</option>
									<option value="033">033</option>
									<option value="041">041</option>
									<option value="042">042</option>
									<option value="043">043</option>
									<option value="044">044</option>
									<option value="051">051</option>
									<option value="052">052</option>
									<option value="053">053</option>
									<option value="054">054</option>
									<option value="055">055</option>
									<option value="061">061</option>
									<option value="062">062</option>
									<option value="063">063</option>
									<option value="064">064</option>
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
								</select>
								 - <input type="text" name="phone2" class="phone num short" maxlength="4" value="${fn:split(sessionScope.member.phone,'-')[1] }"> 
								 - <input type="text" name="phone3" class="phone num short" maxlength="4" value="${fn:split(sessionScope.member.phone,'-')[2] }">
							</td>
						</tr>
						<tr>
							<th>이메일 <b class="star">*</b></th>
							<td><input type="text" class="middle" name="email" value="${sessionScope.member.email }"></td>
						</tr>
					</table>
				</div>
				
				<div class="delivery">
					<p class="main-comm-tit">배송지 정보</p>
					<div class="clearfix">
						<p class="necessary fr"><b class="star">*</b>는 필수입력항목입니다. </p>
						<p class="delivery-check fl"><label><input type="checkbox" id="chk"> 주문자 정보와 동일</label></p>
					</div>
					<table class="comm-tbl view order-tbl">
						<colgroup>
							<col width="15%"/>
							<col width="/"/>
						</colgroup>
						<tr>
							<th>이름 <b class="star">*</b></th>
							<td><input type="text" class="middle" name="receiveName"></td>
						</tr>
						<tr>
							<th>연락처 <b class="star">*</b></th>
							<td>
								<select name="receivePhone1" class="short phone">
									<option value="" selected></option>
									<option value="02">02</option>
									<option value="031">031</option>
									<option value="032">032</option>
									<option value="033">033</option>
									<option value="041">041</option>
									<option value="042">042</option>
									<option value="043">043</option>
									<option value="044">044</option>
									<option value="051">051</option>
									<option value="052">052</option>
									<option value="053">053</option>
									<option value="054">054</option>
									<option value="055">055</option>
									<option value="061">061</option>
									<option value="062">062</option>
									<option value="063">063</option>
									<option value="064">064</option>
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
								</select>
								 - <input type="text" name="receivePhone2" class="phone num short" maxlength="4"> 
								 - <input type="text" name="receivePhone3" class="phone num short" maxlength="4">
							</td>
						</tr>
						<tr>
							<th>배송지 주소 <b class="star">*</b></th>
							<td>
								<input type="text" name="post" class="post short" onclick="getAddr(this.form);" value="${sessionScope.member.post }" readonly><br><br>
								<input type="text" name="address" class="address" onclick="getAddr(this.form);" value="${fn:split(sessionScope.member.address,'//')[0] }" readonly><br><br>
								<input type="text" name="address2" class="address" placeholder="상세 주소를 입력하세요" value="${fn:split(sessionScope.member.address,'//')[1] }">
							</td>
						</tr>
						<tr>
							<th>배송 메모</th>
							<td>
								<input type="text" name="memo" class="memo">
							</td>
						</tr>
					</table>
				</div>
				
				<div class="pay">
					<p class="main-comm-tit">결제 정보</p>
					<table class="comm-tbl view order-tbl">
						<colgroup>
							<col width="15%"/>
							<col width="/"/>
						</colgroup>
						<tr>
							<th>결제 수단</th>
							<td class="pay">
								<label><input type="radio" name="payMethod" value="card" checked> 신용카드</label>&nbsp;&nbsp;
								<label><input type="radio" name="payMethod" value="trans"> 실시간 계좌이체</label>&nbsp;&nbsp;
								<label><input type="radio" name="payMethod" value="vbank"> 가상계좌</label>&nbsp;&nbsp;
								<!-- <label><input type="radio" name="payMethod" value="account"> 무통장입금</label> -->
								<label><input type="radio" name="payMethod" value="phone"> 휴대폰</label>
								<input type="hidden" name="vbankName">
								<input type="hidden" name="vbankNum">
								<input type="hidden" name="vbankHolder">
								<input type="hidden" name="vbankDate">
							</td>
						</tr>
						<tr>
							<td colspan="2" class="pay-final">
								<input type="hidden" name="amount" value="${amount}">
								<input type="hidden" name="pay" value="${price}">총 <span id="total"><fmt:formatNumber value="${price}" pattern="#,###" /></span> 원 &nbsp;&nbsp;
								<button class="order-btn">결제하기</button>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />