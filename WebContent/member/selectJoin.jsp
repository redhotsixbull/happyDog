<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">회원가입 Step1</h2>
		<div class="join-type-container clearfix">
			<div class="join-type-box">
				<form action="/join" method="post">
					<div class="join-type-con">
						<input type="hidden" name="level" value="0">
						<button type="submit">
							<span><img src="/img/join_type01.png"></span>
							<p>일반회원 가입</p>
						</button>
					</div>
				</form>
			</div>
			<div class="join-type-box">
				<form action="/join" method="post">
					<div class="join-type-con">
						<input type="hidden" name="level" value="1">
						<button type="submit">
							<span><img src="/img/join_type02.png"></span>
							<p>보호소회원 가입</p>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />

