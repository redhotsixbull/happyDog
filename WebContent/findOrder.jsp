<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/script.js"></script>
<script type="text/javascript" src="/js/order.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="find-order">
			<p class="main-comm-tit">주문조회</p>
			<p class="order-no">주문번호와 핸드폰 번호로 주문조회가 가능합니다.</p>
			<table>
				<tr>
					<td>주문번호</td><td><input type="text" name="no" class="num"></td>
				</tr>
				<tr>
					<td>주문자 연락처</td><td><input type="text" name="phone1" class="phone num" maxlength="4"> - <input type="text" name="phone2" class="phone num" maxlength="4"> - <input type="text" name="phone3" class="phone num" maxlength="4"></td>
				</tr>
			</table>
			<button class="order-btn">비회원 주문조회</button>
		</div>
		
	</div>
</section>
<script>
	$(function() {
		
		/* 비회원 주문조회 */
		$('.find-order button').click(function() {
			var no = $('input[name=no]').val();
			var phone = $('input[name=phone1]').val()+'-'+$('input[name=phone2]').val()+'-'+$('input[name=phone3]').val();
			if(no==''){
				alert('주문번호를 입력하세요');
				$('input[name=no]').focus();
				return false;
			}
			if(phone=='--'){
				alert('연락처를 입력하세요');
				$('input[name=phone1]').focus();
				return false;
			}
			$.ajax({
				url : "/findOrder",
				type : "post",
				data : {no:no,phone:phone},
				success : function(data){
					if(data=='fail'){
						alert('조회 결과가 없습니다.');
					}else{
						location.href=data;
					}
					
				},
				error : function(){
					console.log("실패");
				}
			});
		});
		
	});
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />