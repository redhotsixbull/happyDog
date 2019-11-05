<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="/js/script.js"></script><!-- script.js -->
<section class="modal-content">
	<h1>봉사활동 신청하기</h1>
	<div class="modal-inner-con">
		<div class="modal-inner">
			<div class="voluntary-bottom-inner">
				<form action="/voluntaryApply?no=${vr.no }" method="post" onsubmit="return numCheck();">
					<input type="hidden" name="possiblePerson" value="${vr.person }"> <!-- 봉사 가능 인원수 -->
					<input type="hidden" name="applyNum" value="${vr.applyNum }"> <!-- 봉사 신청한 인원수 -->
					<table class="comm-tbl"><!-- 봉사활동 신청은 회원 전용 -->
						<colgroup>
							<col width="20%">
							<col width="30%">
							<col width="20%">
							<col width="/">
						</colgroup>
						<tr>
							<th>보호소 명</th>
							<td colspan="3"><input type="hidden" name="name" value="${vr.name}" readonly>${vr.name}</td>
						</tr>
						<tr class="hidden">
							<th>보호소 코드</th>
							<td colspan="3"><input type="hidden" name="code" value="${vr.code}" readonly>${vr.code}</td>
						</tr>
						<tr>
							<th>신청자 아이디</th>
							<td><input type="hidden" name="id" value="${sessionScope.member.id}" readonly>${sessionScope.member.id}</td>
							<th>신청자 전화번호</th>
							<td><input type="hidden" name="phone" value="${sessionScope.member.phone}" readonly>${sessionScope.member.phone}</td>
						</tr>				
						<tr>
							<th>봉사 날짜</th>
							<td><input type="hidden" name="volunDate" value="${vr.volunDate }" readonly>${vr.volunDate }</td>
							<th>봉사 시간</th>
							<td>
								<input type="hidden" name="volunTime1" class="short" value="${vr.startTime }" readonly>${vr.startTime }시 ~ 
								<input type="hidden" name="volunTime2" class="short" value="${vr.endTime }" readonly>${vr.endTime }시</td>
						</tr>
						<tr>
							<th>봉사 신청 인원 수</th>
							<td colspan="3"><input type="text" name="person" class="short num" required> 명</td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">신청하기</button>
						<button type="reset" onclick="javascript:;" class="modal-close-btn btn-style2">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script>
// 봉사활동 신청 모달 창 닫기
$(document).ready(function  () {
	var $modalWrap = $(".modal-fixed-pop-wrapper");
	$(".modal-close-btn").click(function  () {
		$(".modal-inner-content").empty();
		$modalWrap.css("display","none");
		$("html").css({
			"margin-right":"0",
			"overflow-y":"scroll"
		});
		$modalWrap.fadeOut();
		return false;
	});
	
});
// 0명 방지
function numCheck(){
	var num = $('input[type=text].num').val();
	if(num==0){
		alert('1명 이상을 입력해주세요.');
		$(this).val('');
		return false;
	}else{
		return true;
	}
	
}
</script>