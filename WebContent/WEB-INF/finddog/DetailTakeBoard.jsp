<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">
<jsp:include page="/WEB-INF/common/header.jsp" />

<style>
.comm-tbl{max-width:none;}
.comm-tbl.view tr:first-child th, .comm-tbl.view tr:first-child td{border-top:0;}
</style>
<%-- Content --%>
<section id="content-wrapper">
		<div class="area">
		<h2 class="comm-content-tit">보호중인 유기견</h2>
		<div id="DetailTake" class="">
			<div class="common-tbl-btn-group type2">
			</div>
			<div class="voluntary-box">
				<table class="comm-tbl" style="border-bottom:2px solid #ddd; ">
					<tr>
						<th colspan="2" style="text-align:center;">${vd.b.boardTitle }</th>
					</tr>
					<tr>
						<td width="50%">작성자 : ${vd.b.boardName }(${vd.b.boardId })</td>
						<td width="50%">작성일 : ${vd.b.boardDate2 }
					</tr>
					<tr>
						<td>품종 : ${kindNm }</td>
						<td>발견 날짜 : ${vd.b.happenDate }</td>
					</tr>
					<tr>
						<td colspan="2" style="">이메일 : <a href="mailto:${vd.b.boardPw }">${vd.b.boardPw }<br>(해당 글에 대한 문의는 위의 이메일로 연락주시기 바랍니다.)</a></td>
					</tr>
					
					<!-- 파일이 있을 때 -->
						<c:if test="${not empty sessionScope.member.id }">
						<!-- 로그인 된 경우 사진과 내용, 댓글 버튼 노출 -->
						<tr>
							<td colspan="2" style="text-align:center;border-bottom: 0px;">
								<a style="float:right;" href="javascript:fileDownload('${vd.b.boardFilename }','${vd.b.boardFilepath }');">${vd.b.boardFilename }</a>
								<br/>
								<c:if test="${not empty vd.b.boardFilename }">
									<img src='/siUpload/board/${vd.b.boardFilename }'width="500px"/>
								</c:if>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
								<br/><br/><br/>
								${fn:replace(vd.b.boardContent,newLineChar,"<br/>")}
							</td>
						</tr>
							<tr>
								<td colspan="2" class="cmtBtn" style="border-top: 0px;">
									<button type="button" style="float:right;">댓글</button>
								</td>
							</tr>
						</c:if>
						<c:if test="${empty sessionScope.member.id }">
						<!-- 로그인 안된 경우 사진과 내용만 -->
							<tr>
								<td colspan="2" style="text-align:center;">
									<a style="float:right;" href="javascript:fileDownload('${vd.b.boardFilename }','${vd.b.boardFilepath }');">${vd.b.boardFilename }</a>
									<br/>
									<img src='/siUpload/board/${vd.b.boardFilename }'width="500px"/>
									<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
									<br/><br/><br/>
									${fn:replace(vd.b.boardContent,newLineChar,"<br/>")}
								</td>
							</tr>
						</c:if>
					
				</table>
				<form action="/takeBoardCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="boardNo" value="${vd.b.boardNo }"/>
					<input type="hidden" name="boardType" value="4"/>
					<table class="comm-tbl view" id="commentTb" style="display:none;">
						<tr>
							<td colspan="4" style="text-align:center">
								댓글입력 <input type="text" name="boardCommentContent" value=""/>
								<button type="submit">등록</button>
							</td>
						</tr>
					</table>
				</form>
				
				<c:forEach items="${vd.list }" var="list">
					<form action="/takeCommentUpdate" method="post">
						<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
						<input type="hidden" name="boardNo" value="${vd.b.boardNo }"/>
						<table class="comm-tbl view">
							<c:if test="${list.boardRef == vd.b.boardNo && list.boardCommentRef == 0 }">
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<input type="hidden" name="boardCommentNo" value="${list.boardCommentNo }"/>
								<tr>
									<td width="20%">${list.boardCommentName }(${list.boardCommentId })</td>
									<td width="65%">
										<span>${list.boardCommentContent }</span>
										<input type="text" value="${list.boardCommentContent }" name="boardCommentContent" style="display:none;"/>
									</td>
									<td width="11%">
										${list.boardCommentDate2 }<br/>
										<c:if test="${sessionScope.member.id==list.boardCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button class="mdfBtn" type="button">수정</button>
											<button type="text" style="display:none;">/</button>
											<button class="cancelBtn" type="reset" style="display:none;">취소</button>
											/
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.boardCommentNo }');">삭제</a>
											/
										</c:if>
										<c:if test="${sessionScope.member.id!=list.boardCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.boardCommentNo }');">삭제</a>
											/
										</c:if>
										<c:if test="${not empty sessionScope.member.id }"><!-- 로그인시 노출 -->
											<button type="button" class="reCmtBtn">대댓글</button>
										</c:if>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${vd.list }" var="clist"><!-- 대댓글 조회를 위해 forEach 내부에 forEach 사용 -->
									<c:if test="${clist.boardCommentRef == list.boardCommentNo && not empty clist.boardCommentRef && clist.boardRef == vd.b.boardNo }">
									<tr>
										<td width="20%"> └─ ${clist.boardCommentName }(${clist.boardCommentId })</td>
										<td width="65%">
											<span>${clist.boardCommentContent }</span>
											<input type="text" value="${clist.boardCommentContent }" class="boardReCommentModify${clist.boardCommentNo }" style="display:none;"/>
										</td>
										<td width="11%">
											${clist.boardCommentDate2 }<br/>
											<c:if test="${clist.boardCommentId == sessionScope.member.id }">
												<button class="cmtrUpdate" type="button" onclick="cmtrMfy('${clist.boardCommentRef }','${clist.boardCommentNo }')" style="display:none;">등록</button>
												<button class="mdfBtnr" type="button">수정</button>
												<button type="text" style="display:none;">/</button>
												<button class="cancelBtnr" type="reset" style="display:none;">취소</button>
												/
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
							<tr style="display:none;"><!-- 대댓글 버튼 클릭시 입력창 노출 -->
								<td> -> re : ${sessionScope.member.name }(${sessionScope.member.id })</td>
								<td>
									<input type="text" name="boardReCommentContent" class="boardReCommentContent${list.boardCommentNo }"  placeholder="대댓글을 입력하세요" maxlenth="50">
								</td>
								<td>
									<button onclick="sendReCmt('${list.boardCommentNo }')" type="button">대댓글 등록하기</button>
								</td>
							</tr>
						</table>
					</form>
				</c:forEach>
					<form action="/takeBoardUpdate?boardNo=${vd.b.boardNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.b.boardId }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같을때만 수정버튼 생성-->
							<button type="submit" class="btn-style3">수정</button>
						</c:if>
						<c:if test='${sessionScope.member.id==vd.b.boardId || sessionScope.member.id eq "admin" }'>
							<button type="button" id="boardDelBtn" class="btn-style3">삭제</button>
							<!-- 게시글 번호를 siPreBoardDelete?boardNo 서블릿에 전달-->
						</c:if>
								
						<button type="button" class="btn-style2" onclick="location.href='/takeBoard'">목록으로</button>
	
					</div>
				</form>
				
			</div>
		</div>
	</div>
</section>

<script>
	function sendReCmt(boardCommentNo){	//대댓글 전송
		var memberId = '${sessionScope.member.id }';		
		var memberName = '${sessionScope.member.name }';
		var boardCommentContent = $(".boardReCommentContent"+boardCommentNo).val();
		location.href="/takeBoardReCommentInsert?boardType=4"+"&boardRef="+${vd.b.boardNo }
			+"&memberId="+memberId+"&memberName="+memberName+"&boardCommentContent="+boardCommentContent
			+"&boardNo="+${vd.b.boardNo }+"&boardCommentRef="+boardCommentNo;
	}
	function rcmtDelBtn(boardCommentNo,boardCommentRef){//대댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/takeBoardReCommentDelete?boardCommentNo="+boardCommentNo+"&boardNo="+${vd.b.boardNo }+"&boardCommentRef="+boardCommentRef;
		}
	};
	
	function cmtDelBtn(boardCommentNo){ //댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/takeBoardCommentDelete?boardCommentNo="+boardCommentNo+"&boardNo="+${vd.b.boardNo };
		}
	};
	$(document).ready(function(){	//대댓글 입력 tr 노출
		$('.reCmtBtn').click(function(){
			$(this).hide();
			$(this).parent().parent().parent().children().last().show();
		});
	});

	$(document).ready(function(){
		$('.cmtBtn').click(function(){
			$('#commentTb').show();
		});
	});
	
	$(document).ready(function(){	//댓글 수정,취소 버튼 
		$('.mdfBtn').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).html('등록').attr("class","cmtUpdate");
			$(this).nextAll().show();
			$('.cancelBtn').click(function(){
				location.href='/detailTakeBoard?boardNo='+${vd.b.boardNo };
			});
			$(".cmtUpdate").click(function(){
				$(this).parents('form').submit();
			});
		});
	});
	
	
	$(document).ready(function(){	//대댓글 수정,취소 버튼 
		$('.mdfBtnr').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).hide();
			$('.cmtrUpdate').show();
			$(this).nextAll().show();
			$('.cancelBtnr').click(function(){
				location.href='/detailTakeBoardView?boardNo='+${vd.b.boardNo };
			});
		});
	});
	
	function cmtrMfy(boardCommentRef,boardCommentNo){	//대댓글 수정
		var boardCommentContent2 = $('.boardReCommentModify'+boardCommentNo).val();
		location.href="/takeBoardReCommentUpdate?boardCommentContent="+boardCommentContent2
			+"&boardNo="+${vd.b.boardNo }+"&boardCommentRef="+boardCommentRef+"&boardCommentNo="+boardCommentNo;
	}
	
	$(document).ready(function(){	//게시글 삭제 확인
		$('#boardDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/takeBoardDelete?boardNo='+${vd.b.boardNo };
			}
		});
	});
	
	function fileDownload(boardFilename,boardFilepath){
		var url = "/siPreBoardFileDownload";
		var encFilename = encodeURIComponent(boardFilename);
		var encFilepath = encodeURIComponent(boardFilepath);
		location.href=url+'?filename='+encFilename+'&filepath='+encFilename;
	}
	
	
	
	
	
	
	
	
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />