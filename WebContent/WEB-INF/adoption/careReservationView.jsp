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
							<th>방문예약 승인 여부</th>
							<td>
								<c:choose>
									<c:when test="${ba.status ne 0}">
										${ba.result}
									</c:when>
									<c:otherwise>
										<select name="status" style="width:30%;" data-val="${ba.status}">
											<option value="0">대기중</option>
											<option value="1">승인</option>
											<option value="2">거절</option>
										</select>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>신청자 이름</th>
							<td>${ba.name}</td>
						</tr>
						<tr>
							<th>신청자 아이디</th>
							<td>${ba.id}</td>
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
					</table>
					<div class="common-tbl-btn-group">
						<c:choose>
							<c:when test="${ba.status eq 0}">
								<button type="submit" class="btn-style1">수정</button>
								<button type="button" class="btn-style2" onclick="location.href='/reservationCareMypage?startDay=${startDay}&endDay=${endDay}&reqPage=${reqPage}'">목록으로</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn-style2" onclick="location.href='/reservationCareMypage?startDay=${startDay}&endDay=${endDay}&reqPage=${reqPage}'">목록으로</button>
							</c:otherwise>
						</c:choose>
					</div>
			</form>
		</div>
	</div>
</section>

<script>
	$(function(){
		/* 방문예약 신청상태 값 세팅 */
		var status = $('select[name=status]').data('val');
		$('select[name=status]').children('option').each(function(){
			if(status == $(this).val()){
				$(this).prop("selected",true);
			}
		});
	});
	/* 승인 여부 수정 실패 일 알람 */
	if(${not empty msg}){
		alert("${msg}");
	}
</script>
	
<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />