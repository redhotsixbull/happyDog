<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">	<!-- css -->
<!-- Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />
<!-- Content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">보호소 방문 예약 내용</h2>
		<div id="reservationBox" class="common-tbl-box">
			<form action="careUpdateStatus" method="post">
				<input type="hidden" name="no" value="${ba.no }">
				<input type="hidden" name="reqPage" value="${reqPage }">
				<input type="hidden" name="startDay" value="${startDay}">
				<input type="hidden" name="endDay" value="${endDay}">
				<table class="comm-tbl">
						<colgroup>
							<col width="35%">
							<col width="/">
						</colgroup>
						<tr>
							<th>보호소명</th>
							<td>${ba.code}</td>
						</tr>
						<tr>
							<th>신청자 이름</th>
							<td>${ba.name}</td>
						</tr>
						<tr>
							<th>신청자 아이디</th>
							<td>${ba.id }</td>
						</tr>
						<tr>
							<th>신청자 전화번호</th>
							<td>${ba.phone }</td>
						</tr>
						<tr>
							<th>마당이 있습니까?</th>
							<td>${ba.yard }
							</td>
						</tr>
						<tr>
							<th>키우고 있는 애완동물이 있습니까?</th>
							<td>
								${ba.animal }
							</td>
						</tr>
						<tr>
							<th>가족 구성원</th>
							<td>
								${ba.family }
							</td>
						</tr>
						<tr>
							<th>강아지를 키워본 경험이 있습니까?</th>
							<td>
								${ba.experience }
							</td>
						</tr>
						<tr>
							<th>강아지와 함께 있어줄 수 있는 <br>평균시간은 얼마입니까?</th>
							<td>
								${ba.avgTime }
							</td>
						</tr>
						<tr>
							<th>방문 날짜/시간</th>
							<td>
								${fn:substring(ba.visitDate,0,4)}년  
								${fn:substring(ba.visitDate,5,7)}월  
								${fn:substring(ba.visitDate,8,10)}일  
							 	 ${ba.visitTime}
							</td>
						</tr>
						<tr>
							<th>방문예약 승인 상태</th>
							<td>${ba.result}</td>
						</tr>
						<tr>
							<th>신청 날짜</th>
							<td>
								${fn:substring(ba.applyDate,0,4)}년  
								${fn:substring(ba.applyDate,5,7)}월  
								${fn:substring(ba.applyDate,8,10)}일  
							</td>
						</tr>
						
					</table>
					<div class="common-tbl-btn-group">
						<button type="button" class="btn-style1" onclick="location.href='/adminReservPage?startDay=${startDay}&endDay=${endDay}&reqPage=${reqPage}'">목록으로</button>
					</div>
			</form>
		</div>
	</div>
</section>

<script>
$(function(){
	
	/* msg있으면 alert띄워주기 */
	alert(msg);
});
</script>
	
<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />