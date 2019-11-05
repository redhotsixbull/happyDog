<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<script type="text/javascript" src="/js/email.js"></script><!-- email.js -->
<style>
	#email{display:inline-block; width:75%; vertical-align:middle;}
	.comm-tbl td > h3{font-weight:500;}
</style>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">회원가입 Step2</h2>	
		<form name="send_emailFrm">								<!-- email.js 로 보낼 폼 -->
			<input type="hidden" name="send_email">
			<input type="hidden" name="send_level" value="${level }">
		</form>
		<form action="/emailJoin" method="post" onsubmit="return check()">
			<table class="comm-tbl">
				<colgroup>
					<col width="20%">
					<col width="/">
				</colgroup>
				<tr>
					<th>회원 유형</th>
					<td>
						<c:if test="${level == 0 }">
							<h3>일반 회원</h3>
						</c:if>
						<c:if test="${level == 1 }">
							<h3>보호소 회원</h3>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>이메일 입력</th>
					<td>
						<input type="text" name="email" id="email">
						<div class="common-tbl-btn-group join-btn-group">
							<button type="button" onclick="send_email()" id="btn" class="btn-style2 small">인증하기</button> <!-- email.js로  -->
						</div>
					</td>
				</tr>
			</table>	<!-- email.js로  -->
				
				<input type="text" name="level" id="level" value="${level }" style="display:none">	<br>					<!-- 가입자 레벨 -->
				<input type="text" name="a" id="a" value="0" style="display:none">	<br>		<!-- 이메일 인증 완료시 1 미완료시 0 -->
			
			<div class="common-tbl-btn-group">
				<button type="submit" id="btn2" class="btn-style1" style="display:none">계속하기</button>
			</div>
		</form>
	</div>
</section>
	
	<script>
	
		function check(){
			if($('#a').val() != 1){
				alert("이메일 인증 필요");
				return false;
			}
		}
		
	</script>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />
