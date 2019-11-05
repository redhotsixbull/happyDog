<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HappyTogeDog</title>
<link rel="Shortcut Icon" href="/img/favicon.ico">
<link rel="stylesheet" type="text/css" href="/css/reset.css"> <!-- 태그 초기화 css(민주) -->
<link rel="stylesheet" type="text/css" href="/css/layout.css"> <!-- 레이아웃 css(민주) -->
<link rel="stylesheet" type="text/css" href="/css/content.css"> <!-- 컨텐츠 css(민주) -->
<!-- script -->
<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->

<style type="text/css">
	#checked-container{
		text-align : center;
		padding : 50px 20px;
	}
	#checked{
		color: red;
		font-weight: bold;
	}
	.count-time{padding-top:30px; text-align:center; font-size:15px; font-weight:400;}
	#countdown{display:inline-block; font-size:18px; color:red; font-weight:500;}
</style>

</head>
<body>
	<input type="hidden" id="check" value="${result }">
	<c:if test="${result == 0 }">
	<div id="checked-container">
		<table class="comm-tbl view">
			<colgroup>
				<col width="">
				<col width="">
			</colgroup>
			<tr>
				<th>인증번호 입력</th>
				<td><input type="text" name="email_num" id="email_num"></td><!-- 인증번호 입력받는 곳 -->
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="hidden" name="serverNum" id="serverNum" value="${num }" class="short"><!-- 서버 인증번호 -->
					<div class="common-tbl-btn-group join-btn-group">
						<button type="button" id="btn" class="btn-style2 small">확인</button>
					</div>
				</td>
			</tr>
		</table>
		<div class="count-time">
			<span>제한시간 </span><div id="countdown"></div>
		</div>
		
		<!-- 인증번호 입력 <input type="text" name="email_num" id="email_num"><br>			
		<input type="text" name="serverNum" id="serverNum" value="${num }">		
		<input type="button" id="btn" value="확인"> -->
		
	
	</div>
	</c:if>
	
	<script>
		$(document).ready(function(){
			var check = $('#check').val();
			if(check>0){
				alert("중복된 이메일 입니다.");
				self.close();
			}else{
			function countdown( elementId, seconds ){
				  var element, endTime, hours, mins, msLeft, time;

				  function updateTimer(){
				    msLeft = endTime - (+new Date);
				    if ( msLeft < 0 ) {
				      console.log('done');
				      alert("다시 인증받으세요");
				      $('#serverNum').val("356854651654");
				      self.close();
				    } else {
				      time = new Date( msLeft );
				      hours = time.getUTCHours();
				      mins = time.getUTCMinutes();
				      element.innerHTML = (hours ? hours + ':' + ('0' + mins).slice(-2) : mins) + ':' + ('0' + time.getUTCSeconds()).slice(-2);
				      setTimeout( updateTimer, time.getUTCMilliseconds());
				      // if you want this to work when you unfocus the tab and refocus or after you sleep your computer and come back, you need to bind updateTimer to a $(window).focus event^^
				    }
				  }

				  element = document.getElementById( elementId );
				  endTime = (+new Date) + 1000 * seconds;
				  updateTimer();
				}

				countdown('countdown', 300);	 // second base
			
			
			}
		});
		
		$('#btn').click(function(){
			var serverNum = $('#serverNum').val();
			var email_num = $('#email_num').val();
			
			if(serverNum == email_num){
				alert("인증완료");
				var a = opener.document.getElementById("a");		//email.jsp 의 input a 저장
				var con = opener.document.getElementById("btn2");	//email.jsp 의 btn2 con 지정
				a.value=1;											// value값 1로 변경
				con.style = 'display:inline-block';					//인증 성공시 btn2 block
				self.close();										//팝업창 닫기
			}else{
				alert("인증실패");
			}
		});
		
	</script>
	
</body>
</html>