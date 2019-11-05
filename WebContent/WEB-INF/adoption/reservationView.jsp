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
				<table class="comm-tbl">
						<colgroup>
							<col width="35%">
							<col width="/">
						</colgroup>
						<tr>
							<th>보호소명</th>
							<td>${ba.code }</td>
						</tr>
						<tr>
							<th>방문예약 승인 상태</th>
							<td>${ba.result }</td>
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
							<th>신청날짜</th>
							<td>
								${fn:substring(ba.applyDate,0,4)}년  
								${fn:substring(ba.applyDate,5,7)}월  
								${fn:substring(ba.applyDate,8,10)}일  
							</td>
						</tr>
					</table>
					<br>
					<table class="comm-tbl" >
						<tr style="display: none;"></tr>
						<tr>
							<td>
								<p>입양절차 안내
									입양상담은 법적 공고기간 10일 이후부터 진행됩니다.<br>
									입양신청서가 접수되면, 입양업무 담당자가 서류심사를 한 후, 입양이 가능하다고 판단된 분께만 개별연락을 드리며,<br>
									1차 전화상담, 2차 방문상담 후 입양여부를 최종 결정하게 됩니다.<br>
									또한, 미성년자나 대학생의 경우는 부모님의 동의여부를 확인해야만 입양이 가능합니다.<br>
									입양이 결정된 경우, 보호소를 방문하실 때는 신분증과 입양동물의 운송에 필요한 물품(이동가방, 목걸이, 리드줄 등)을 지참하시고<br>
									선택하신 방문시간까지 방문해 주셔야 합니다.</p>
							</td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<c:choose>
							<c:when test="${ba.status ne 0}">
								<button type="button" class="btn-style2" onclick="location.href='/reservationMypage?reqPage=${reqPage}'">목록으로</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn-style1" id="cancel">신청 취소</button>
								<button type="button" class="btn-style2" onclick="location.href='/reservationMypage?reqPage=${reqPage}'">목록으로</button>
							</c:otherwise>
						</c:choose>
					</div>
		</div>
	</div>
</section>

<script>
	$("#cancel").click(function(){
		location.href="/cancelReservation?no=${no}";
	});
</script>
	
<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />