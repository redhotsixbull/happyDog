<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section name="siSection" id="content-wrapper">
	<div class="area common-tbl-box" >
		<h2 class="comm-content-tit">자유게시판</h2>
		<div class="">
			<table class="comm-tbl type2">
				<colgroup>
					<col width="5%">
					<col width="">
					<col width="13%">
					<col width="12%">
					<col width="7%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>게시글 제목</th>
						<th>작성자</th>
						<th>날짜/시간</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
		 			<c:forEach items="${bp.list }" var="list">
							<tr>
								<td>${list.boardRnum}</td>
								<td><a href="/siPreBoardView?boardNo=${list.boardNo }">${list.boardTitle }</a></td>
								<!-- name 값을 넘겨주도록 설정필요 -->
								<td>
									<c:if test="${list.boardId ne 'admin' }">
										${list.boardName }(${list.boardId })
									</c:if>
									<c:if test="${list.boardId eq 'admin' }">
										${list.boardName }
									</c:if>
								</td>
								<td>${list.boardDate2 }</td>
								<td>${list.boardCount }</td>
							</tr>
					</c:forEach>
					<c:if test="${empty bp.list }">
						<tr>
							<td colspan="5">
								<p class="none">게시물이 없습니다.</p>
							</td>
						</tr>
					</c:if>
					<!-- <tr>
						<td colspan="5" style="text-align:center;">
							<div>${bp.pageNavi }</div>
						</td>
					</tr> -->
				</tbody>
			</table>
			<!-- 글쓰기 버튼 -->
			<c:if test="${not empty sessionScope.member.id }">
				<div class="common-tbl-btn-group" style="text-align:right;">
					<!-- 로그인이 되있어야 글쓰기버튼 활성화 -->
					<button type="button" class="bbs-search-btn btn-style1" onclick="location.href='/siViews/board/siPreBoardInsert.jsp'">글쓰기</button>
				</div>
			</c:if>
			<!-- paging -->
			<div class="paging">
				${bp.pageNavi }
			</div>
			<!-- 검색박스 -->
			<%-- 
			<form action="/siPreBoardSearch" method="post">
				<div class="board-search-box">
					<select name="searchWord">
						<option value="boardName">작성자</option>
						<option value="boardTitle">글 제목</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="text" name="keyword" class="search-word" >
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
			</form>
			--%>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />