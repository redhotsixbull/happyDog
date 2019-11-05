<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/qna.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q &amp; A</h2>
		<div id="qnaModifyBox" class="common-tbl-box">
			<form action="/updateQna" method="post" enctype="multipart/form-data" onsubmit="return chkSubmit(this);">
				<input type="hidden" name="boardType" value="5">
				<input type="hidden" name="boardNo" value="${qna.boardNo }">
				<input type="hidden" name="boardPrdcode" value="${qna.boardPrdcode }">
				<%-- 상품코드가 있으면 해당 상품 연결 --%>
				<c:if test="${not empty qna.boardPrdcode }">
					<table class="comm-tbl">
						<tr>
							<td>
								<a href="/viewProduct?code=${prdCode}">
									<img height="100" src="/img/${prdList[qna.boardPrdcode].prdImg }">
									${prdList[qna.boardPrdcode].prdName }
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
						<td colspan="2"><input type="text" name="boardTitle" value="${qna.boardTitle}" required="required"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<input type="hidden" name="boardId" value="${qna.boardId}">
							<input type="text" name="boardName" value="${qna.boardName}" required="required">
						</td>
						<td>
							<c:if test="${qna.boardSecret eq 1 }">
								<c:set var="secret" value="checked" />
							</c:if>
							
							<label><input type="checkbox" name="boardSecret" value="1" ${secret }> 비밀글</label>
						</td>
					</tr>
					<c:if test="${not empty qna.boardPw }">
						<tr>
							<th>비밀번호</th>
							<td colspan="2">
								<input type="password" name="boardPw" value="" required="required">
								<p class="comment">(※글 작성시 사용한 비밀번호를 입력해주세요.)</p>
							</td>
						</tr>
					</c:if>
					<tr>
						<th>내용</th>
						<td colspan="2"><textarea name="boardContent" rows="10" required="required">${fn:replace(qna.boardContent,'<br>','')}</textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="2">
							<input type="hidden" name="oldFilename" value="${qna.boardFilename }">
							<input type="hidden" name="oldFilepath" value="${qna.boardFilepath }">
							<c:if test="${not empty qna.boardFilepath }">
								<span id="nowFile">${qna.boardFilename } <button type="button" id="fileDelBtn" class="file-del-btn delFile">삭제</button>
								<input type="hidden" name="deleteFile" ></span>
								<span id="upload"><input type="file" name="filename"><p class="comment">(※ 파일 크기는 최대 10MB까지만 가능합니다.)</p></span>
							</c:if>
							<c:if test="${empty qna.boardFilepath }">
								<input type="file" name="filename"><p class="comment">(※ 파일 크기는 최대 10MB까지만 가능합니다.)</p>
							</c:if>
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">수정</button><button type="button" class="btn-style2" onclick="history.back();">취소</button>
				</div>
			</form>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />