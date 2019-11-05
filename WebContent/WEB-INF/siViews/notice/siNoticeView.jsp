<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<%pageContext.setAttribute("newLineChar", "\n"); %>
<style>
.cmt-txt{display:inline-block; vertical-align:middle; font-size:14px; font-weight:500;}
/* .cmtBtn, .reCmtBtn, .cmtDelBtn, .rcmtDelBtn, 
.cmtrUpdate, .mdfBtnr, .cancelBtnr{display:inline-block; vertical-align:middle; font-size:13px; background:rgba(254,67,30); padding:5px 10px; color:#fff; border-radius:5px;}
.cmtBtn.big, .reCmtBtn.big, .reCmtBtn.big{font-size:14px; padding:8px 20px;}
.cmtDelBtn, .rcmtDelBtn, .cmtDelBtn:hover, .rcmtDelBtn:hover{background:#444; color:#fff;} */
.applyBtn{display:inline-block; vertical-align:middle; font-size:13px; background:rgba(254,67,30); padding:5px 10px; color:#fff; border-radius:5px;}
.cancelBtn2{background:#444; color:#fff;}
.noticeCommentContent, .noticeReCommentModify{max-width:85%;}
.cmt-content{display:inline-block; float:left; max-width:80%;}
.cmt-date{display:inline-block; float:right; max-width:15%;}
.comm-tbl.view2 tr:first-child th, .comm-tbl.view2 tr:first-child td{border-top:0;}
</style>
	<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="main-comm-tit">공지사항</h2>
			<div class="voluntary-box">
				<!-- 공지사항 게시글 조회 -->
				<table class="comm-tbl view">
					<colgroup>
						<col width="20%">
						<col width="4%">
						<col width="/">
						<col width="100%">
					</colgroup>
					<tr>
						<th colspan="2" style="text-align:center;">${vd.n.noticeTitle }</th>
					</tr>
					<tr>
						<td>작성자 : ${vd.n.noticeName }(${vd.n.noticeId })</td>
						<td>작성일 : ${vd.n.noticeDate2 }</td>
					</tr>
					<c:if test="${not empty vd.n.noticeFilename }">
						<tr>
							<td colspan="2" style="border-bottom: 0px;text-align: center;">
								<a style="float:right;" href="javascript:fileDownload('${vd.n.noticeFilename }','${vd.n.noticeFilepath }');">${vd.n.noticeFilename }</a>
								<br/>
								<img src='/siUpload/notice/${vd.n.noticeFilename }' width="500px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty sessionScope.member.id }">
						<tr>
							<td colspan="2" style="border-bottom: 0px;border-top: 0px;text-align: center;">
								<pre>${fn:replace(vd.n.noticeContent,newLineChar,"<br/>")}</pre>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="border-top: 0px;text-align:right;">
								<button type="button" class="cmtBtn big">댓글</button>
								<button type="button" class="cancelBtn" style="display:none;">취소</button>
							</td>
						</tr>
					</c:if>
					<c:if test="${empty sessionScope.member }">
						<tr>
							<td colspan="2" style="border-top: 0px;text-align: center;">
								<pre>${fn:replace(vd.n.noticeContent,newLineChar,"<br/>")}</pre>
							</td>
						</tr>
					</c:if>
				</table>
				<form action="/siNoticeCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="noticeNo" value="${vd.n.noticeNo }"/>
					<input type="hidden" name="noticeType" value="0"/>
					<table class="comm-tbl view" id="commentTb" style="display:none;">
						<tr>
							<td colspan="4" style="text-align:center;">
								<span class="cmt-txt">댓글 입력</span>&nbsp;&nbsp;<input type="text" name="noticeCommentContent" value="" maxlength="50" class="noticeCommentContent"/>
								<button type="submit" class="cmtBtn applyBtn">등록</button>
							</td>
						</tr>
					</table>
				</form>
				<c:forEach items="${vd.list }" var="list" varStatus="i">
					<form action="/siNoticeCommentUpdate" method="post">
						<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
						<input type="hidden" name="noticeNo" value="${vd.n.noticeNo }"/>
						<table class="comm-tbl view view2">
							<c:if test="${list.noticeRef == vd.n.noticeNo && list.noticeCommentRef == 0 }">
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<input type="hidden" name="noticeCommentNo" value="${list.noticeCommentNo }"/>
								<tr>
									<td width="20%">${list.noticeCommentName }(${list.noticeCommentId })</td>
									<td width="65%" class="clearfix">${clist.noticeCommentContent }
										<pre><c:out value="${list.noticeCommentContent }" escapeXml="true"/></pre>
										<em class="cmt-date">${list.noticeCommentDate2 }</em>
										<input type="text" value="<c:out value="${list.noticeCommentContent }" escapeXml="true"/>" class="noticeCommentContent" name="noticeCommentContent" style="display:none;"/>
									</td>
									<%-- 회원일때만 노출 --%>
									<c:if test="${not empty sessionScope.member }">
										<td width="12%" style="text-align:center;">
											<c:if test="${sessionScope.member.id==list.noticeCommentId }">
											<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
												<button class="mdfBtn" type="button">수정</button>
												<button class="cmtUpdate" type="button" style="display:none;">등록</button>
												<button style="display:none;">|</button>
												<button class="cancelBtn" type="reset" style="display:none;">취소</button>
												<span>|</span>
												<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.noticeCommentNo }');">삭제</a>
												<span>|</span>
											</c:if>
											<c:if test="${sessionScope.member.id!=list.noticeCommentId && sessionScope.member.id eq 'admin' }">
											<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
												<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.noticeCommentNo }');">삭제</a>
												<span>|</span>
											</c:if>
											<c:if test="${not empty sessionScope.member.id }"><!-- 로그인시 노출 -->
												<button type="button" class="reCmtBtn">답글</button>
											</c:if>
										</td>
									</c:if>
								</tr>
							</c:if>
							<c:forEach items="${vd.list }" var="clist"><!-- 답글 조회를 위해 forEach 내부에 forEach 사용 -->
								<c:if test="${clist.noticeCommentRef == list.noticeCommentNo && not empty clist.noticeCommentRef && clist.noticeRef == vd.n.noticeNo }">
									<tr>
										<td width="20%"> └─ ${clist.noticeCommentName }(${clist.noticeCommentId })</td>
										<td width="65%">
											<pre class="cmt-content"><c:out value="${clist.noticeCommentContent }" escapeXml="true"/></pre>
											<em class="cmt-date">${clist.noticeCommentDate2 }</em>
											<input type="text" value="<c:out value="${clist.noticeCommentContent }" escapeXml="true"/>" class="noticeReCommentModify${clist.noticeCommentNo } noticeReCommentModify" style="display:none;"/>
										</td>
										<%-- 회원일때만 노출 --%>
										<c:if test="${not empty sessionScope.member }">
											<td width="12%" style="text-align:center;">
												<c:if test="${clist.noticeCommentId == sessionScope.member.id }">
													<button class="cmtrUpdate" type="button" onclick="cmtrMfy('${clist.noticeCommentRef }','${clist.noticeCommentNo }')" style="display:none;">등록</button>
													<button class="mdfBtnr" type="button">수정</button>
													<button style="display:none;">|</button>
													<button class="cancelBtnr" type="reset" style="display:none;">취소</button>
													<span>|</span>
													<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.noticeCommentNo }','${clist.noticeCommentRef }');">삭제</a>
												</c:if>
												<c:if test="${sessionScope.member.id!=clist.noticeCommentId && sessionScope.member.id eq 'admin' }">
												<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
													<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.noticeCommentNo }','${clist.noticeCommentRef }');">삭제</a>
												</c:if>
											</td>
										</c:if>
									</tr>
								</c:if>
							</c:forEach>
							<tr style="display:none;"><!-- 답글 버튼 클릭시 입력창 노출 -->
								<td> ⇒ re : ${sessionScope.member.name }(${sessionScope.member.id })</td>
								<td>
									<input type="text" name="noticeReCommentContent" class="noticeReCommentContent${list.noticeCommentNo }"  placeholder="답글을 입력하세요" maxlenth="50">
								</td>
								<td style="text-align:center;">
									<button onclick="sendReCmt('${list.noticeCommentNo }')" type="button" class="reCmtBtn">등록</button>
									<span>|</span>
									<button class="reCmtBtnr" type="button" >취소</button>
								</td>
							</tr>
						</table>							
					</form>
				</c:forEach>
				<form action="/siNoticeUpdateOriginal?noticeNo=${vd.n.noticeNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.n.noticeId }'>
							<button type="submit" class="btn-style3">수정</button>
						</c:if>
						<c:if test='${sessionScope.member.id==vd.n.noticeId || sessionScope.member.id eq "admin" }'>
							<button type="button" id="noticeDelBtn" class="btn-style3">삭제</button>
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siNotice'">목록으로</button>
					</div>
				</form>
			</div>
		</div>
	</section>
<script>
	$(document).ready(function(){	//댓글 입력 취소	
		$('.cancelBtn').click(function(){
			$('#commentTb').hide();
			$('[name=noticeCommentContent]').val('');
			$(this).hide();
			$(this).prev().show();
		});
	});
	$(document).ready(function(){	//답글 입력 취소	
		$('.reCmtBtnr').click(function(){
			$(this).parent().parent().hide();
			$(this).parent().prev().children().val('');
			$('.reCmtBtn').show();
		});
	});
	function sendReCmt(noticeCommentNo){	//답글 전송
		var memberId = '${sessionScope.member.id }';		
		var memberName = '${sessionScope.member.name }';
		var noticeCommentContent = $(".noticeReCommentContent"+noticeCommentNo).val();
		var formData = "noticeType=0"+"&noticeRef="+${vd.n.noticeNo }
		+"&memberId="+memberId+"&memberName="+memberName+"&noticeNo="+${vd.n.noticeNo }
		+"&noticeCommentRef="+noticeCommentNo+"&noticeCommentContent="+encodeURI(noticeCommentContent);
		$.ajax({
			url : "/siNoticeReCommentInsert",
			type : "post",
			data : formData,
			success : function(data){
				location.href="/siNoticeView?noticeNo="+${vd.n.noticeNo };
			},
			error : function(){
				location.href="/siNoticeView?noticeNo="+${vd.n.noticeNo };
			}
		});
	}
	function rcmtDelBtn(noticeCommentNo,noticeCommentRef){//답글 삭제확인
		if(confirm("답글을 삭제하시겠습니까?")){
			location.href="/siNoticeReCommentDelete?noticeCommentNo="+noticeCommentNo+"&noticeNo="+${vd.n.noticeNo }+"&noticeCommentRef="+noticeCommentRef;
		}else{
			location.href="/siNoticeView?noticeNo="+${vd.n.noticeNo };
		}
	};
	function cmtDelBtn(noticeCommentNo){ //댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/siNoticeCommentDelete?noticeCommentNo="+noticeCommentNo+"&noticeNo="+${vd.n.noticeNo };
		}else{
			location.href="/siNoticeView?noticeNo="+${vd.n.noticeNo };
		}
	};
	$(document).ready(function(){	//답글 입력 tr 노출
		$('.reCmtBtn').click(function(){
			$(this).parent().parent().parent().children().last().toggle();
		});
	});
	$(document).ready(function(){// 댓글 입력창 노출
		$('.cmtBtn').click(function(){
			$('#commentTb').show();
			$(this).hide();
			$(this).next().show();
		});
	});
	$(document).ready(function(){//댓글 수정,취소 버튼  
		$('.mdfBtn').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(2).show();
			$(this).nextAll().show();
			$(this).hide();
			$(this).next().next().next().next().hide();
			$(this).next().next().next().next().next().hide();
			$(this).next().next().next().next().next().next().hide();
			$(this).next().next().next().next().next().next().next().hide();
			$('.cancelBtn').click(function(){
				$(this).parent().prev().children().eq(0).show();
				$(this).parent().prev().children().eq(2).hide();
				$(this).prev().hide();
				$(this).prev().prev().hide();
				$(this).prev().prev().prev().show();
				$(this).hide();
				$(this).nextAll().show();
			});
			$(".cmtUpdate").click(function(){
				$(this).parents('form').submit();
			});
		});
	});
	$(document).ready(function(){	//답글 수정,취소 버튼 
		$('.mdfBtnr').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(2).show();
			$(this).hide();
			$(this).prev().show();
			$(this).nextAll().show();
			$(this).next().next().next().hide();
			$(this).next().next().next().next().hide();
			$('.cancelBtnr').click(function(){
				$(this).parent().prev().children().eq(0).show();
				$(this).parent().prev().children().eq(2).hide();
				$(this).prev().hide();
				$(this).prev().prev().show();
				$(this).prev().prev().prev().hide();
				$(this).nextAll().show();
				$(this).hide();
			});
		});
	});
	function cmtrMfy(noticeCommentRef,noticeCommentNo){//답글 수정
		var noticeCommentContent2 = $('.noticeReCommentModify'+noticeCommentNo).val();
		var formData = "noticeNo="+${vd.n.noticeNo }+"&noticeCommentRef="+noticeCommentRef
		+"&noticeCommentNo="+noticeCommentNo+"&noticeCommentContent="+noticeCommentContent2;
		$.ajax({
			url : "/siNoticeReCommentUpdate",
			type : "post",
			data : formData,
			success : function(data){
				location.href="/siNoticeView?noticeNo="+${vd.n.noticeNo };
			},
			error : function(){
				location.href="/siNoticeView?noticeNo="+${vd.n.noticeNo };
			}
		});
	}
	$(document).ready(function(){	//게시글 삭제확인
		$('#noticeDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siNoticeDelete?noticeNo='+${vd.n.noticeNo };
			}
		});
	});
	function fileDownload(noticeFilename,noticeFilepath){	//파일 다운로드
		var url = "/siNoticeFileDownload";
		var encFilename = encodeURIComponent(noticeFilename);
		var encFilepath = encodeURIComponent(noticeFilepath);
		location.href=url+'?filename='+encFilename+"&filepath="+encFilename;
	}
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />