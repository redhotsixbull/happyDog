<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">로그인</h2>
		<div id="loginBox" class="common-tbl-box" style="padding-bottom:50px;"><!-- class="common-tbl-box" id는 바꿔서 복붙 -->
			<form action="/login" method="post">
				<%-- 이전 페이지 url 가져오기
				<input type="hidden" name="url" value="<%=request.getHeader("referer").split("localhost/")[1]%>">
				--%>
				<div class="login-inner clearfix">
					<div class="login-tbl-box">
						<table class="comm-tbl login-tbl">
							<colgroup>
								<col width="20%">
								<col width="/">
							</colgroup>
							<tr>
								<th>아이디</th>
								<td><input type="text" name="id"></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="pw"></td>
							</tr>
						</table>
					</div>
					<div class="login-btn-box">
						<button type="submit" class="login-btn">LOGIN</button>
					</div>
				</div>
			</form>
			<div class="login-util-box">
				<span><a href="/member/findUser.jsp">아이디/비밀번호 찾기</a></span>|
				<span><a href="/member/terms.jsp">회원가입</a></span>
			</div>
		</div>
	</div>
</section>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />