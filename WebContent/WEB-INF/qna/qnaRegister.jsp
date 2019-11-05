<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/qna.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q &amp; A</h2>
		<div id="qnaRegisterBox" class="common-tbl-box">
			<form action="/insertQna" method="post" enctype="multipart/form-data" onsubmit="return chkSubmit(this);">
				<input type="hidden" name="boardType" value="5">
				<input type="hidden" name="boardPrdcode" value="${prdCode }">
				<%-- 상품코드가 있으면 해당 상품 연결 --%>
				<c:if test="${not empty prdCode }">
					<table class="comm-tbl">
						<tr>
							<td>
								<a href="/viewProduct?code=${prdCode}">
									<img height="100" src="/img/${prdList[prdCode].prdImg }">
									${prdList[prdCode].prdName }
								</a>
							</td>
						</tr>
					</table>
				</c:if>
				<br>
				<table class="comm-tbl">
					<colgroup>
						<col width="28%">
						<col width="/">
						<col width="/">
					</colgroup>
					<tr>
						<th>제목</th>
						<td colspan="2"><input type="text" name="boardTitle" value="" required="required"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<input type="hidden" name="boardId" value="${sessionScope.member.id}">
							<input type="text" name="boardName" value="${sessionScope.member.name}" required="required">
						</td>
						<td>
							<label><input type="checkbox" name="boardSecret" value="1"> 비밀글</label>
						</td>
					</tr>
					<c:if test="${empty sessionScope.member }">
						<tr>
							<th>비밀번호</th>
							<td colspan="2">
								<input type="password" name="boardPw" value="" required="required">
								<p class="comment">(※비밀번호는 수정, 삭제시 사용됩니다.)</p>
							</td>
						</tr>
					</c:if>
					<tr>
						<th>내용</th>
						<td colspan="2"><textarea name="boardContent" rows="10" required="required"></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="2"><input type="file" name="filename"><p class="comment">(※ 파일 크기는 최대 10MB까지만 가능합니다.)</p></td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">등록</button><button type="button" class="btn-style2" onclick="history.back();">취소</button>
				</div>
			</form>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />