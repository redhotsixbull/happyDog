<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">아이디 / 비밀번호 찾기</h2>
		<div id="loginBox" class="common-tbl-box" style="padding-bottom:50px;"><!-- class="common-tbl-box" id는 바꿔서 복붙 -->
			<form action="/findUser" method="post">
				<div class="login-inner clearfix">
					<table class="comm-tbl login-tbl">
						<colgroup>
							<col width="30%">
							<col width="/">
						</colgroup>
						<tr>
							<th>이메일 입력</th>
							<td><input type="text" name="email"></td>
						</tr>
					</table>
					<div class="login-btn-box type2">
						<button type="submit" class="login-btn">찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />