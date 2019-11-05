<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<%pageContext.setAttribute("newLineChar", "/n"); %>	
	<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="main-comm-tit">자유게시판</h2>
			<div class="voluntary-box">
				<!-- 자유게시판 게시글 조회 -->
				<table class="comm-tbl view">
					<colgroup>
						<col width="20%">
						<col width="4%">
						<col width="/">
						<col width="100%">
					</colgroup>
					<tr>
						<th colspan="2" style="text-align:center;">${vd.b.boardTitle }</th>
					</tr>
					<tr>
						<td>작성자 : ${vd.b.boardName }(${vd.b.boardId })</td>
						<td>작성일 : ${vd.b.boardDate2 }</td>
					</tr>
					<c:if test="${not empty vd.b.boardFilename }">
						<tr>
							<td colspan="2" style="border-bottom: 0px;text-align: center;">
								<a style="float:right;" href="javascript:fileDownload('${vd.b.boardFilename }','${vd.b.boardFilepath }')">${vd.b.boardFilename }</a>
								<br/>
								<img src='/siUpload/board/${vd.b.boardFilename }'width="500px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty sessionScope.member.id }">
					<!-- 로그인을 안하면 댓글리스트만 조회가능, 등록칸이 보이지 않도록 설정 -->
						<tr>
							<td colspan="2" style="border-bottom: 0px;border-top: 0px;text-align: center;">
								<pre>${fn:replace(vd.b.boardContent,newLineChar,"<br/>")}</pre>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="border-top: 0px;">
								<button type="button" class="cmtBtn" style="float:right;">댓글</button>
								<button type="button" class="cancelBtn" style="float:right;display:none;">취소</button>
							</td>
						</tr>
					</c:if>
					<c:if test="${empty sessionScope.member.id }">
					<!-- 로그인을 안하면 댓글리스트만 조회가능, 등록칸이 보이지 않도록 설정 -->
						<tr>
							<td colspan="2" style="border-top: 0px;text-align: center;">
								<pre>${fn:replace(vd.b.boardContent,newLineChar,"<br/>")}</pre>
							</td>
						</tr>
					</c:if>
				</table>
				<form action="/siPreBoardCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="boardNo" value="${vd.b.boardNo }"/>
					<input type="hidden" name="boardType" value="1"/>
					<table class="comm-tbl view" id="commentTb" style="display:none;">
						<tr>
							<td colspan="4" style="text-align:center">
								댓글입력 <input style="width:88%;" type="text" name="boardCommentContent" value=""/>
								<button type="submit">등록</button>
							</td>
						</tr>
					</table>
				</form>
		
				<c:forEach items="${vd.list }" var="list" varStatus="i">
					<form class="cmtForm"action="/siPreBoardCommentUpdate" method="post">
						<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
						<input type="hidden" name="boardNo" value="${vd.b.boardNo }"/>
						<table class="comm-tbl view">
							<c:if test="${list.boardRef == vd.b.boardNo && list.boardCommentRef == 0 }">
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<input type="hidden" name="boardCommentNo" value="${list.boardCommentNo }"/>
								<tr>
									<td width="20%">${list.boardCommentName }(${list.boardCommentId })</td>
									<td width="65%">
										<pre><c:out value="${list.boardCommentContent }" escapeXml="true"/></pre>
										<input type="text" value="<c:out value="${list.boardCommentContent }" escapeXml="true"/>" name="boardCommentContent" style="display:none;"/>
									</td>
									<td width="11%">
										${list.boardCommentDate2 }<br/>
										<c:if test="${sessionScope.member.id==list.boardCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button class="mdfBtn" type="button">수정</button>
											<button class="cmtUpdate" type="button" style="display:none;">등록</button>
											<button style="display:none;">|</button>
											<button class="cancelBtn" type="reset" style="display:none;">취소</button>
											<span>|</span>
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.boardCommentNo }');">삭제</a>
											<span>|</span>
										</c:if>
										<c:if test="${sessionScope.member.id!=list.boardCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.boardCommentNo }');">삭제</a>
											<span>|</span>
										</c:if>
										<c:if test="${not empty sessionScope.member.id }"><!-- 로그인시 노출 -->
											<button type="button" class="reCmtBtn">답글</button>
										</c:if>
									</td>
								</tr>	
							</c:if>
							<c:forEach items="${vd.list }" var="clist"><!-- 답글 조회를 위해 forEach 내부에 forEach 사용 -->
								<c:if test="${clist.boardCommentRef == list.boardCommentNo && not empty clist.boardCommentRef && clist.boardRef == vd.b.boardNo }">
									<tr>
										<td width="20%"> └─ ${clist.boardCommentName }(${clist.boardCommentId })</td>
										<td width="65%">
											<pre><c:out value="${clist.boardCommentContent }" escapeXml="true"/></pre>
											<input type="text" value="<c:out value="${clist.boardCommentContent }" escapeXml="true"/>" class="boardReCommentModify${clist.boardCommentNo }" style="display:none;"/>
										</td>
										<td width="11%">
											${clist.boardCommentDate2 }<br/>
											<c:if test="${clist.boardCommentId == sessionScope.member.id }">
												<button class="cmtrUpdate" type="button" onclick="cmtrMfy('${clist.boardCommentRef }','${clist.boardCommentNo }')" style="display:none;">등록</button>
												<button class="mdfBtnr" type="button">수정</button>
												<button style="display:none;">|</button>
												<button class="cancelBtnr" type="reset" style="display:none;">취소</button>
												<span>|</span>
												<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.boardCommentNo }','${clist.boardCommentRef }');">삭제</a>
											</c:if>
											<c:if test="${sessionScope.member.id!=clist.boardCommentId && sessionScope.member.id eq 'admin' }">
											<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
												<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.boardCommentNo }','${clist.boardCommentRef }');">삭제</a>
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
							<tr style="display:none;"><!-- 답글 버튼 클릭시 입력창 노출 -->
								<td> -> re : ${sessionScope.member.name }(${sessionScope.member.id })</td>
								<td>
									<input type="text" name="boardReCommentContent" class="boardReCommentContent${list.boardCommentNo }"  placeholder="답글을 입력하세요" maxlenth="50">
								</td>
								<td>
									<button onclick="sendReCmt('${list.boardCommentNo }')" type="button">등록</button>
									<span>|</span>
									<button class="reCmtBtnr" type="button" >취소</button>
								</td>
							</tr>
						</table>
					</form>
				</c:forEach>
				<form action="/siPreBoardUpdateOriginal?boardNo=${vd.b.boardNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.b.boardId }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같을때만 수정버튼 생성-->
							<button type="submit" class="btn-style3">수정</button>
						</c:if>
						<c:if test='${sessionScope.member.id==vd.b.boardId || sessionScope.member.id eq "admin" }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같거나 관리자라면 삭제 버튼 생성 -->
							<button type="button" id="boardDelBtn" class="btn-style3">삭제</button>
							<!-- 게시글 번호를 siPreBoardDelete?boardNo 서블릿에 전달-->
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siPreBoard'">목록으로</button>
					</div>
				</form>
			</div>
		</div>
	</section>

<script>
	$(document).ready(function(){	//댓글 입력 취소	
		$('.cancelBtn').click(function(){
			$('#commentTb').hide();
			$('[name=boardCommentContent]').val('');
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
	function sendReCmt(boardCommentNo){	//답글 전송
		var memberId = '${sessionScope.member.id }';		
		var memberName = '${sessionScope.member.name }';
		var boardCommentContent = $(".boardReCommentContent"+boardCommentNo).val();
		var formData = "boardType=1"+"&boardRef="+${vd.b.boardNo }
			+"&memberId="+memberId+"&memberName="+memberName+"&boardNo="+${vd.b.boardNo }
			+"&boardCommentRef="+boardCommentNo+"&boardCommentContent="+encodeURI(boardCommentContent);
		$.ajax({
			url : "/siPreBoardReCommentInsert",
			type : "post",
			data : formData,
			success : function(data){
				location.href="/siPreBoardView?boardNo="+${vd.b.boardNo };
			},
			error : function(){
				location.href="/siPreBoardView?boardNo="+${vd.b.boardNo };	
			}
		});
	};
	function rcmtDelBtn(boardCommentNo,boardCommentRef){//답글 삭제확인
		if(confirm("답글을 삭제하시겠습니까?")){
			location.href="/siPreBoardReCommentDelete?boardCommentNo="+boardCommentNo+"&boardNo="+${vd.b.boardNo }+"&boardCommentRef="+boardCommentRef;
		}else{
			location.href="/siPreBoardView?boardNo="+${vd.b.boardNo };
		}
	};
	function cmtDelBtn(boardCommentNo){ //댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/siPreBoardCommentDelete?boardCommentNo="+boardCommentNo+"&boardNo="+${vd.b.boardNo };
		}else{
			location.href="/siPreBoardView?boardNo="+${vd.b.boardNo };
		}
	};
	$(document).ready(function(){	//답글 입력 tr 노출
		$('.reCmtBtn').click(function(){
			$(this).parent().parent().parent().children().last().toggle();
		});
	});
	$(document).ready(function(){	// 댓글 입력창 노출
		$('.cmtBtn').click(function(){
			$('#commentTb').show();
			$(this).hide();
			$(this).next().show();
		});
	});
	$(document).ready(function(){	//댓글 수정,취소 버튼 
		$('.mdfBtn').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).nextAll().show();
			$(this).hide();
			$(this).next().next().next().next().hide();
			$(this).next().next().next().next().next().hide();
			$(this).next().next().next().next().next().next().hide();
			$(this).next().next().next().next().next().next().next().hide();
			$('.cancelBtn').click(function(){
				$(this).parent().prev().children().eq(0).show();
				$(this).parent().prev().children().eq(1).hide();
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
			$(this).parent().prev().children().eq(1).show();
			$(this).hide();
			$(this).prev().show();	
			$(this).nextAll().show();
			$(this).next().next().next().hide();
			$(this).next().next().next().next().hide();
			$('.cancelBtnr').click(function(){
				$(this).parent().prev().children().eq(0).show();
				$(this).parent().prev().children().eq(1).hide();
				$(this).prev().hide();
				$(this).prev().prev().show();
				$(this).prev().prev().prev().hide();
				$(this).nextAll().show();
				$(this).hide();
			});
		});
	});
	function cmtrMfy(boardCommentRef,boardCommentNo){	//답글 수정
		var boardCommentContent2 = $('.boardReCommentModify'+boardCommentNo).val();
		var formData = "boardNo="+${vd.b.boardNo }+"&boardCommentRef="+boardCommentRef
		+"&boardCommentNo="+boardCommentNo+"&boardCommentContent="+boardCommentContent2;
		$.ajax({
			url : "/siPreBoardReCommentUpdate",
			type : "post",
			data : formData,
			success : function(data){
				location.href="/siPreBoardView?boardNo="+${vd.b.boardNo };
			},
			error : function(){
				location.href="/siPreBoardView?boardNo="+${vd.b.boardNo };	
			}
		});
	};
	$(document).ready(function(){	//게시글 삭제 확인
		$('#boardDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siPreBoardDelete?boardNo='+${vd.b.boardNo };
			}
		});
	});
	function fileDownload(boardFilename,boardFilepath){	//파일 다운로드
		var url = "/siPreBoardFileDownload";
		var encFilename = encodeURIComponent(boardFilename);
		var encFilepath = encodeURIComponent(boardFilepath);
		location.href=url+'?filename='+encFilename+'&filepath='+encFilename;
	}
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />