<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/script.js"></script><!-- script.js -->

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">봉사활동 공고 수정</h2>
		<div id="voluntaryRegisterBox" class="common-tbl-box">
			<!-- 봉사활동 수정하기 -->
			<form action="/voluntaryUpdateEnd" method="post" enctype="multipart/form-data" onsubmit="return numCheck();">
				<table class="comm-tbl">
					<input type="hidden" name="no" value="${vr.no }">
					<colgroup>
						<col width="28%">
						<col width="/">
					</colgroup>
					<tr>
						<th>보호소 코드</th>
						<td>${sessionScope.member.code}</td>
					</tr>
					<tr>
						<th>보호소 명</th>
						<td>${sessionScope.member.name}</td>
					</tr>
					<tr>
						<th>봉사활동 공고 제목</th>
						<td><input type="text" name="title" value="${vr.title }" required></td>
					</tr>				
					<tr>
						<th>봉사 날짜</th>
						<td><input type="text" id="datepicker2" name="volunDate" value="${vr.volunDate }" required></td>
					</tr>
					<tr>
						<th>봉사 시간</th>
						<td>
							<input type="text" name="volunTime1" class="short num" value="${vr.startTime }" required> 시&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text" name="volunTime2" class="short num" value="${vr.endTime }" required> 시 &nbsp;&nbsp;
							(ex. 9시 ~ 15시)
						</td>
					</tr>
					<tr>
						<th>봉사 가능 인원 수</th>
						<td><input type="text" name="person" class="short num" value="${vr.person }" required> 명</td>
					</tr>
					<tr>
						<th>봉사 상세설명</th>
						<td><textarea name="detail" rows="10" required>${vr.detail }</textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="hidden" id="fileStatus" name="fileStatus" value="stay"><!-- 파일 삭제 유무 구별 -->
							<c:if test="${not empty vr.filepath }">
								<input type="file" name="filename" id="file" style="display:none;">
								<span class="delFile">${vr.filename }</span>
								<button type="button" id="fileDelBtn" class="file-del-btn delFile">삭제</button>
								
								<input type="hidden" name="oldFileName" value="${vr.filename }">
								<input type="hidden" name="oldFilePath" value="${vr.filepath }">
							</c:if>
							<c:if test="${empty vr.filepath }">
								<!-- 첨부파일이 없는 경우 -->
								<input type="file" name="filename">
							</c:if>
							<p class="comment">(※ 파일 크기는 최대 10MB까지만 가능합니다.)</p>
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">수정</button><button type="reset" class="btn-style2" onclick="goBack();">취소</button>
				</div>
			</form>
		</div>
	</div>
</section>

<script>
$(document).ready(function(){
	// 첨부 파일 삭제
	$('#fileDelBtn').click(function(){
		if(confirm('첨부파일을 삭제하시겠습니까?')){
			$(".delFile").hide();
			$("#file").show();
			$("#fileStatus").val("delete");
		}
	});
});

//목록으로 버튼 누를시 뒤로가기
function goBack(){
	window.history.back();
};

//0명 방지
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