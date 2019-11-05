<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    
<jsp:include page="/WEB-INF/common/header.jsp" />

<section id="content-wrapper">
	<div class="area">
		<p class="main-comm-tit">${msg}</p>
		<div class="common-tbl-btn-group">
			<button class="btn-style1" onclick="history.back();">이전으로</button>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />