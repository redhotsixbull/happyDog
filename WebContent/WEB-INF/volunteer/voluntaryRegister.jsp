<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/script.js"></script><!-- script.js -->

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">봉사활동 공고등록</h2>
		<div id="voluntaryRegisterBox" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<form action="/voluntaryRegister" method="post" enctype="multipart/form-data" onsubmit="return numCheck();">
				<table class="comm-tbl"><!-- 공고등록은 보호소회원 전용메뉴(보호소회원 로그인시에만 사용가능) :: 로그인 후 등록하려는 보호소 회원의 보호소 코드, 보호소명 fix -->
					<colgroup>
						<col width="28%">
						<col width="/">
					</colgroup>
					<tr>
						<th>보호소 코드</th>
						<td><input type="text" name="code" value="${sessionScope.member.code}" readonly></td>
					</tr>
					<tr>
						<th>보호소 명</th>
						<td><input type="text" name="name" value="${sessionScope.member.name}" readonly></td>
					</tr>
					<tr>
						<th>봉사활동 공고 제목</th>
						<td><input type="text" name="title" required></td>
					</tr>				
					<tr>
						<th>봉사 날짜</th>
						<td><input type="text" id="datepicker2" name="volunDate" required></td>
					</tr>
					<tr>
						<th>봉사 시간</th>
						<td>
							<input type="text" name="volunTime1" class="short num" required> 시&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text" name="volunTime2" class="short num" required> 시 &nbsp;&nbsp;
							(ex. 9시 ~ 15시)
						</td>
					</tr>
					<tr>
						<th>봉사 가능 인원 수</th>
						<td><input type="text" name="person" class="short num" required> 명</td>
					</tr>
					<tr>
						<th>봉사 상세설명</th>
						<td><textarea name="detail" rows="10" required></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="filename"><p class="comment">(※ 파일 크기는 최대 10MB까지만 가능합니다.)</p></td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">등록</button><button type="reset" class="btn-style2" onclick="goBack();">취소</button>
				</div>
			</form>
		</div>
	</div>
</section>

<script>
//취소 버튼 누를시 뒤로가기
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

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />