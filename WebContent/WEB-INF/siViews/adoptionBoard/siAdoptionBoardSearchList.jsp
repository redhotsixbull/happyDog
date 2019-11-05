<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<script type="text/javascript" src="/js/main.js"></script><!-- main.js -->

	<section name="siSection" id="content-wrapper" style="margin:20px;">
		<div id="mainTopBox" class="area">
			<div id="mainAdoptReviewBox">
				<h2 class="main-comm-tit">입양 후기</h2>
				<ul class="main-adopt-review-list clearfix"><!-- 입양후기는 최소/최대 8개가 노출됩니다. -->
					<c:forEach items="${ap.list }" var="list">
						<c:if test="${list.adoptionBoardType == 2 }">
							<li>
								<a href="/siAdoptionBoardView?adoptionBoardNo=${list.adoptionBoardNo }">
									<div class="img-thum">
										<span style="background:url('/siUpload/adoptionBoard/${list.adoptionBoardFilename }') no-repeat center center; background-size:cover;"></span>
									</div>
									<div class="txt-thum">
										<h3>${list.adoptionBoardTitle }</h3>
										<p>${list.adoptionBoardContent }</p>
										<h5 class="clearfix">
											<span>
												${list.adoptionBoardName }(${list.adoptionBoardId })
											</span>
											<span>
												${list.adoptionBoardDate }
											</span>
										</h5>
									</div>
								</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
				<!-- <ul>
					<li colspan="5" style="text-align:center;">
						<div>${ap.pageNavi }</div>
					</li>
				</ul> -->
			</div>
			<!-- 글쓰기 버튼 -->
			<c:if test="${not empty sessionScope.member.id }">
				<div class="common-tbl-btn-group" style="text-align:right;padding-top:0;">
					<!-- 로그인이 되있어야 글쓰기버튼 활성화 -->
					<button type="button" class="bbs-search-btn btn-style1" onclick="location.href='location.href='/siViews/adoptionBoard/siAdoptionBoardInsert.jsp'">글쓰기</button>
				</div>
			</c:if>
			<!-- paging -->
			<div class="paging">
				${ap.pageNavi }
			</div>
			<form action="/siAdoptionBoardSearch" method="get">
				<div class="board-search-box">
					<select name="searchWord">
						<option value="adoptionBoardName">작성자</option>
						<option value="adoptionBoardTitle">글 제목</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="text" name="keyword" class="search-word">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
			</form>
		</div>
		
	</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />