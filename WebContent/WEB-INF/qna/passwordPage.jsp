<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/qna.js"></script>
<c:if test="${not empty msg }">
	<script>alert('${msg}');</script>
</c:if>
<c:if test="${not empty loc }">
	<script>location.href='${loc}';</script>
</c:if>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q &amp; A</h2>
		<div class="qna-view">
			<form action="/checkPw" method="post" onsubmit="return check(this);">
				<input type="hidden" name="boardNo" value="${boardNo }">
				<input type="hidden" name="checkType" value="${checkType }">
				<table class="comm-tbl">
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="password" name="boardPw">
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">확인</button>
				</div>
			</form>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />