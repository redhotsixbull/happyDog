<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<%-- Content --%>
<style>
	.viewOne{cursor:pointer;}
</style>
<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">강아지를 찾습니다</h2>
			<div class="">
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="15%">
						<col width="">
						<col width="15%">
						<col width="10%">
						<col width="7%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>사진</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
			 			<c:forEach items="${bp.list }" var="list" varStatus="i">

							<tr onclick="location.href='/detailFindBoard?boardNo=${list.boardNo }'" class="viewOne">
								<td>${list.boardRnum}</td>
								<td><img src="/siUpload/board/${list.boardFilepath }" style="height: 200px; width: 200px;"></td>
								<td>${list.boardTitle }</td>
								<!-- name 값을 넘겨주도록 설정필요 -->
								<td>${list.boardName }(${list.boardId })</td>
								<td>${list.boardDate }</td>
								<td>${list.boardCount }</td>
							</tr>
						</c:forEach>
						<c:if test="${empty bp.list }">
							<tr>
								<td colspan="6">
								<p class="none">게시물이 없습니다.</p>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<!-- 글쓰기 버튼 -->
				<c:if test="${not empty sessionScope.member.id }">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<!-- 로그인이 되있어야 글쓰기버튼 활성화 -->
						<button type="button" class="bbs-search-btn btn-style1" onclick="location.href='/findBoardInsert'">글쓰기</button>
					</div>
				</c:if>
				<div class="paging">
					${bp.pageNavi }
				</div>
				<form action="/findBoardSearch" method="post">
					<div class="board-search-box">
						<select name="searchWord">
							<option value="boardName">작성자</option>
							<option value="boardTitle">글 제목</option>
						</select>
						<input type="text" name="keyword" class="search-word" >
						<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					</div>
				</form>
			</div>
		</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />