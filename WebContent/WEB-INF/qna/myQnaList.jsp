<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/qna.js"></script>
<link rel="stylesheet" type="text/css" href="/css/qna.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">내가 쓴 Q &amp; A</h2>
		<div id="qnaListBox" class="common-tbl-box">
			<table class="comm-tbl type2">
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>답변상태</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty qnaList.qnainfoList }">
						<tr>
							<td colspan="4">
								<p class="none">게시물이 없습니다.</p>
							</td>
						</tr>
					</c:if>
					<c:forEach items="${qnaList.qnainfoList}" var="qna">
						<tr>
							<td>${qna.boardRnum }</td>
							<td>
								<c:if test="${qna.boardCount eq 0 }">
									<span class="volun-status ing">답변대기</span>
								</c:if>
								<c:if test="${qna.boardCount eq 1 }">
									<span class="volun-status end">답변완료</span>
								</c:if>
							</td>
							<td>
								<p class="volun-tit">
									<%-- 상품코드가 있으면 해당 상품 연결 --%>
									<c:if test="${not empty qna.boardPrdcode }">
										<a href="/viewProduct?code=${qna.boardPrdcode}">
											<img height="50" src="/img/${prdList[qna.boardPrdcode].prdImg }">
										</a>
									</c:if>
									<a href="javascript:view(${qna.boardNo });">
										${qna.boardTitle }
									</a>
								</p>
							</td>
							<td>${qna.boardDate }</td>						
						</tr>
					</c:forEach>
				</tbody>
			</table>
				
			<div class="common-tbl-btn-group" style="text-align: right;">
				<button class="btn-style1 sm" onclick="location.href='/regiQna'">작성</button>
			</div>
			
			<!-- paging -->
			<div class="paging">
		 		${qnaList.pageNavi }
		 	</div>
		 	<form action="/qnaView" method="post" name="moveView">
		 		<input type="hidden" name="reqPage" class="view" value="${search.reqPage}">
		 		<input type="hidden" name="boardNo">
		 		<input type="hidden" name="searchType" value="${search.searchType }">
		 		<input type="hidden" name="searchVal" value="${search.searchVal }">
		 		<input type="hidden" name="pageName" value="/myQnaList">
		 	</form>
		 	
		 	<!-- 검색박스 -->
		 	<form action="/myQnaList" method="post" name="search">
		 		<input type="hidden" name="reqPage" class="search">
		 	</form>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />