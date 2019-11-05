<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<style>
.comm-tbl td img{max-width:100%;}
</style>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">봉사활동 신청</h2>
		<div id="voluntaryViewBox" class="voluntary-box">
			<!-- 봉사활동 상세정보 -->
			<div class="voluntary-top-box">
				<table class="comm-tbl view"><!-- 공고등록은 보호소회원 전용메뉴(보호소회원 로그인시에만 사용가능) :: 로그인 후 등록하려는 보호소 회원의 보호소 코드, 보호소명 fix -->
					<colgroup>
						<col width="20%">
						<col width="/">
						<col width="20%">
						<col width="/">
					</colgroup>
					<tr>
						<th colspan="4">
							<p class="volun-view-tit">${vr.title }</p>
							<p class="volun-view-sub-tit">
								<span><b>작성일</b>${vr.enrollDate }</span><!-- 공고 등록일이 노출됩니다. -->
							</p>
						</th>
					</tr>
					<tr>
						<th>보호소 명</th>
						<td colspan="3">${vr.name }</td>
					</tr>				
					<tr>
						<th>봉사 날짜</th>
						<td>${vr.volunDate }</td>
						<th>봉사 시간</th>
						<td>${vr.startTime }시 ~ ${vr.endTime }시</td>
					</tr>
					<tr>
						<th>봉사 신청한 인원 수</th>
						<td>${vr.applyNum }명</td>
						<th>봉사 가능 인원 수</th>
						<td>${vr.person }명</td>
					</tr>
					<tr>
						<th>봉사 상세설명</th>
						<td colspan="3">
							<c:if test="${empty vr.filepath}">
								${vr.detail }
							</c:if>
							<c:if test="${not empty vr.filepath}">
								<c:set var="fileLength" value="${fn:length(vr.filename)}"></c:set>
								<c:set var="fileType" value="${fn:substring(vr.filename,fileLength-3,fileLength) }"></c:set>
								<c:if test="${fileType eq 'jpg' || fileType eq 'png' || fileType eq 'gif' || fileType eq 'JPG' }">
									<img src="/upload/volunteer/${vr.filename }"><br><br>
								</c:if>
								${vr.detail }
							</c:if>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<c:if test="${vr.filepath != null }">
								<p><img src="/img/file.png" width="20px"> <a href="javascript:fileDownload('${vr.filename }','${vr.filepath }');"><span>${vr.filename }</span></a></p>
							</c:if>
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group" style="text-align:right;">
					<c:set var="today"><fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd"/></c:set>
					<c:if test="${vr.volunDate != today }">
						<c:if test="${vr.status eq '모집중' }">
							<c:if test="${not empty member && member.memberLevel == 0 }">
								<button type="button" class="btn-style1" onclick="javascript:layerLoad('/voluntaryApplyForm?no=${vr.no}');">신청하기</button><!-- 신청 마감시 버튼 변경->신청 마감 누르면 alert('신청이 마감되었습니다.') -->
							</c:if>
							<c:if test="${empty member && member.memberLevel != 1 }">
								<button type="button" id="applyCheckBtn" class="btn-style1">신청하기</button><!-- 신청 마감시 버튼 변경->신청 마감 누르면 alert('신청이 마감되었습니다.') -->
							</c:if>
						</c:if>
					</c:if>
					<button type="button" class="btn-style2" onclick="location.href='/volunteerList'">목록으로</button>
					<!-- <button type="button" class="btn-style2" onclick="goBack();">목록으로</button> -->
					<c:if test="${sessionScope.member.code == vr.code}">
						<button type="button" class="btn-style3" onclick="location.href='/voluntaryUpdate?no=${vr.no}'">수정</button>
						<button type="button" id="deleteBtn" class="btn-style3">삭제</button>
					</c:if>
				</div>
				
				<c:if test="${sessionScope.member.code == vr.code }">
					<h3 class="sub-title">해당 공고에 봉사활동을 신청한 회원 목록</h3>
					<table class="comm-tbl type2">
						<thead>
							<tr>
								<th>신청자 ID</th>
								<th>신청자 전화번호</th>
								<th>신청 인원 수</th>
								<th>신청일</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty list }">
								<c:forEach items="${list }" var="personList" varStatus="i">
									<tr>
										<td>${personList.id }</td>
										<td>${personList.phone }</td>
										<td>${personList.person }</td>
										<td>${personList.enrollDate }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list }">
								<tr>
									<td colspan="4"><p class="none">신청자가 없습니다.</p></td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</c:if>
			</div>
			<!-- 봉사활동 신청 폼 --><!-- 봉사활동 신청은 회원 전용 -->
			<!-- <div class="voluntary-bottom-box">
				<h2 class="comm-content-tit">봉사활동 신청하기</h2>
				<div class="voluntary-bottom-inner">
					<form action="" method="post">
						<table class="comm-tbl">
							<colgroup>
								<col width="20%">
								<col width="/">
								<col width="20%">
								<col width="/">
							</colgroup>
							<tr>
								<th>보호소 명</th>
								<td colspan="3"><input type="text" name="" readonly></td>
							</tr>
							<tr class="hidden">
								<th>보호소 코드</th>
								<td colspan="3"><input type="text" name="" readonly></td>
							</tr>
							<tr>
								<th>신청자 아이디</th>
								<td>신청자 아이디가 들어갑니다.</td>
								<th>신청자 전화번호</th>
								<td>신청자 전화번호가 들어갑니다.</td>
							</tr>				
							<tr>
								<th>봉사 날짜</th>
								<td>봉사 날짜가 들어갑니다.(ex. 2019-05-26)</td>
								<th>봉사 시간</th>
								<td>봉사 시간이 들어갑니다.(ex. 9시 ~ 16시)</td>
							</tr>
							<tr>
								<th>봉사 신청 인원 수</th>
								<td colspan="3"><input type="text" name="person" class="short num"> 명</td>
							</tr>
						</table>
						<div class="common-tbl-btn-group">
							<button type="submit" class="btn-style1">신청</button>
						</div>
					</form>
				</div>
			</div> -->
		</div>
	</div>
</section>

<%-- 모달 레이어 팝업 --%>
<article class="modal-fixed-pop-wrapper">
	<div class="modal-fixed-pop-inner">
		<div class="modal-loading"><span class="loading"></span></div>
		<div class="modal-inner-box">
			<div class="modal-inner-content"></div>
		</div>
	</div>
</article>

<%-- script --%>
<script>
//목록으로 버튼 누를시 뒤로가기
function goBack(){
	window.history.back();
}

// 파일 다운로드
function fileDownload(filename,filepath){
	var url = "/voluntaryFileDownload";
	var encFilename = encodeURIComponent(filename);
	var encFilepath = encodeURIComponent(filepath);
	location.href=url+'?filename='+encFilename+'&filepath='+encFilepath;
}

// 로그인 전 봉사신청 버튼 눌렀을 때 & 삭제 버튼 눌렀을 때
$(document).ready(function(){
	//로그인 전 봉사신청 버튼 눌렀을 때
	var $applyChkBtn = $("#applyCheckBtn");
	$applyChkBtn.on("click",function(){
		var choice = confirm("로그인 후 이용가능합니다. 로그인 하시겠습니까?");
		if(choice == true){
			location.href="/member/login.jsp";
			return true;
		} else{
			return false;
		}
	});
	
	//해당 공지 올린 보호소회원이 삭제 버튼 눌렀을 때
	var $deleteBtn = $("#deleteBtn");
	$deleteBtn.on("click",function(){
		var deleteChk = confirm("삭제하시겠습니까?");
		if(deleteChk == true){
			location.href="/voluntaryDelete?no=${vr.no}";
			return true;
		}else{
			return false;
		}
	});
});

// 로그인 후 봉사신청 버튼 눌렀을 때
// 모달 레이어 팝업 띄우기
function layerLoad(strUrl){
	var $modalWrap = $(".modal-fixed-pop-wrapper");

	$("html").css({
		"margin-right":"17px",
		"overflow-y":"hidden"
	});
	$modalWrap.fadeIn();
	
	$.ajax({
		type: "POST",
		url: strUrl,
		data: "",
		success: function(resultText){
			$modalWrap.find(".modal-inner-content").html(resultText);
		},
		error: function() {
			alert("호출에 실패했습니다.");
			$(".modal-fixed-pop-wrapper").hide();
			$("html").css({
				"margin-right":"0",
				"overflow-y":"scroll"
			});
		},
		beforeSend:function(){ 
			$('.modal-loading').fadeIn(); 
		},
		complete:function(){ 
			$('.modal-loading').hide();
		}
	});
}

// 모달 창 닫기
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
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />