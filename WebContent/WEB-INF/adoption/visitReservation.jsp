<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">	<!-- css -->
<!-- Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />

<!-- 보호소 코드 디비에 저장해야하기 때문에 전 페이지에서 받아와야함, 아이디는 세션에서 받아오기 -->

<!-- Content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">보호소 방문 예약</h2>
		<div id="reservationBox" class="common-tbl-box">
			<form action="/visitReservationComplete" method="post">
				<input type="hidden" name="code" value="${code }">	<!-- 보호소 코드받아서 보내기 -->
				<div id="reservForm">
					<p class="necessary"><b class="star">*</b> 는 필수입력항목입니다. </p>
					<table class="comm-tbl view">
						<colgroup>
							<col width="35%">
							<col width="/">
						</colgroup>
						<tr>
							<th>보호소명</th>
							<td><input type="hidden" name="careNm" value="${careNm}" readonly>${careNm} </td>
						</tr>
						<tr>
							<th>보호소 주소</th>
							<td><input type="hidden" name="careAddr" value="${careAddr }" readonly>${careAddr }</td>	
						</tr>
						<tr>
							<th>보호소 전화번호</th>
							<td><input type="hidden" name="careTel" value="${careTel }" readonly>${careTel }</td>	
						</tr>
						<tr>
							<th>보호소 이용가능 시간</th>
							<td><input type="hidden" name="possibleTime" value="${careTime }" readonly>${careTime }</td>	
						</tr>
						<tr>
							<th>신청자 이름</th>
							<td><input type="hidden" name="name" value="${sessionScope.member.name}" readonly>${sessionScope.member.name}</td>
						</tr>
						<tr>
							<th>신청자 전화번호<b class="star">*</b></th>
							<td><input type="text" name="phone" value="${sessionScope.member.phone }" id="phone" placeholder="ex) 010-0000-0000"></td>
						</tr>
						<tr>
							<th>마당이 있습니까?</th>
							<td>
							<label><input type="radio" id="yard1" name="yard" value="있음">있음 </label>
							<label><input type="radio" id="yard2" name="yard" value="없음"> 없음</label>
							</td>
						</tr>
						<tr>
							<th>키우고 있는 애완동물이 있습니까?</th>
							<td>
								<input type="text" name="animal" placeholder="키우고 있다면 애완동물의 종류, 수 등 대해 상세히 적어주세요." maxlength="333">
							</td>
						</tr>
						<tr>
							<th>가족 구성원</th>
							<td>
								<input type="text" name="family" placeholder="가족구성원, 수 등 대해 상세히 적어주세요." maxlength="333">
							</td>
						</tr>
						<tr>
							<th>강아지를 키워본 경험이 있습니까?</th>
							<td>
								<input type="text" name="experience" maxlength="333">
							</td>
						</tr>
						<tr>
							<th>강아지와 함께 있어줄 수 있는 <br>평균시간은 얼마입니까?</th>
							<td>
								<input type="text" name="avgTime" maxlength="333">
							</td>
						</tr>
						<tr>
							<th>방문 날짜<b class="star">*</b></th>
							<td>
								<input type="text" id="datepicker2" name="visitDate" class="visitDate">
							</td>
						</tr>
						<tr>
							<th>방문 시간<b class="star">*</b></th>
							<td>
								<select name="visitTime" id="select-visitTime">
									<c:if test="${not empty careTime}">
										<c:forEach var="i" begin="${fn:substring(careTime,0,2)}" step="1" end="${fn:substring(careTime,4,6)-1}">
											<option value="${i}시~${i+1}시">${i}시~${i+1}시</option>
											<!-- 보호소  방문가능시간 데이터 받아와서 option값 정하기 -->
										</c:forEach>
									</c:if>
								</select>
							</td>
						</tr>
					</table>
					<br>
					<table class="comm-tbl view" >
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
						<button type="submit" class="btn-style1">등록</button>
						<button type="button" class="btn-style2" onclick="history.back();">취소</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>	
	
<script>
	/* 방문날짜 선택에 따른 방문가능 시간 구해오기 */
	function getTime(){
		var date = $(".visitDate").val();
		if(date==''){
			var d = new Date();
			/* console.log(d.format('yyyy-MM-dd'));
			date = d.format('yyyy-MM-dd'); */
			date = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
		}
		console.log(date);
		var careNm ='${careNm}';
		$.ajax({
			url: "/getPossibleTime",
			type:"post",
			data : {visitDate:date, careNm:careNm},
			success : function(data){
				/* alert(data); */
				$('#select-visitTime option').each(function(){
					$(this).prop("disabled",false);
				});
				console.log(data.length);
				for(var i=0;i<data.length;i++){
					var time = data[i];
					/* console.log(time); */
					$("#select-visitTime option[value='"+time+"']").prop("disabled",true);
				}
				console.log(data.length);
			},
			error : function(){
				/* console.log("실패다아아아"); */
				alert("실패");
			}
		});	/* ajax종료 */
	}/* getTime() 종료 */
	
	getTime();
		
	$(".visitDate").change(function(){
		getTime();
	});
	
	//필수사항 입력 확인
	$(".btn-style1").click(function(){
		var phone = $('#phone').val();
		if(phone==''){
			alert('필수입력항목이 누락되었습니다');
			$('input[name=phone]').focus();
			return false;
		}
	});
</script>
	
<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />