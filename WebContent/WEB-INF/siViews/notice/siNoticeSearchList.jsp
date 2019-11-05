<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<style>
	.viewNotice{cursor:pointer;}
</style>
	<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">공지사항</h2>
			<div class="">
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="">
						<col width="15%">
						<col width="10%">
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
			 			<c:forEach items="${np.list }" var="list">
							<tr onclick="viewOne(${list.noticeNo})" class="viewNotice">
								<td>${list.noticeRnum}</td>
								<td>${list.noticeTitle }</td>
								<!-- name 값을 넘겨주도록 설정필요 -->
								<td>
									<c:if test="${list.noticeId ne 'admin' }">
										${list.noticeName }(${list.noticeId })
									</c:if>
									<c:if test="${list.noticeId eq 'admin' }">
										${list.noticeName }
									</c:if>
								</td>
								<td>${list.noticeDate }</td>
								<td>${list.noticeCount }</td>
							</tr>
						</c:forEach>
						<!-- <tr>
							<td colspan="5" style="text-align:center;">
								<div>${np.pageNavi }</div>
							</td>
						</tr> -->
					</tbody>
				</table>
				<!-- 글쓰기 버튼 -->
				<c:if test="${not empty sessionScope.member.id && sessionScope.member.id eq 'admin' }">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<!-- 로그인이 되있어야 글쓰기버튼 활성화 -->
						<button type="button" class="bbs-search-btn btn-style1" onclick="location.href='/siViews/notice/siNoticeInsert.jsp'">글쓰기</button>
					</div>
				</c:if>
				<!-- paging -->
				<div class="paging">
					${np.pageNavi }
				</div>
				<form action="/siNoticeSearch" method="get">
					<div class="board-search-box">
						<select name="searchWord">
							<option value="noticeName">작성자</option>
							<option value="noticeTitle">글 제목</option>
						</select>
						<input placeholder="검색어를 입력해주세요." type="text" name="keyword" class="search-word" >
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					</div>
				</form>
			</div>
		</div>
	</section>
<script>
	function viewOne(no){
		location.href="/siNoticeView?noticeNo="+no;
	}
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />