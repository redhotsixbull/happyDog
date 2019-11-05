<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<style>
	.common-tbl-box{padding:70px;}
	.comm-tbl input[type='text'].middle, .comm-tbl input[type='password'].middle{width:50%;}
	.comm-tbl select.short{width:30%;}
	#post{display:inline-block; width:75%; vertical-align:middle;}
</style>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">회원 정보 수정/탈퇴</h2>
		<form action="/memberModify" method="post" onsubmit="return check()">
			<div class="common-tbl-box">
				<p class="necessary join"><b class="star join">*</b> 는 필수입력항목입니다. </p>
				<input type="hidden" name="level" value="${m.memberLevel }">
				<table class="comm-tbl view">
					<tr>
						<th>아이디</th>
						<td><input type="text" name="id" value="${m.id }" readonly></td>
					</tr>
					<tr>
						<th>비밀번호 <b class="star join">*</b></th>
						<td>
							<input type="password" id="pw" name="pw" value="${m.pw }" class="modify middle">
							<p id="p_checkPw" style="display:none">비밀번호 입력양식 확인</p>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인 <b class="star join">*</b></th>
						<td>
							<input type="password" id="pw_re" name="pw_re" value="${m.pw }" class="modify middle">
							<p id="p_checkPw_re" style="display:none">비밀번호가 일치하지 않습니다</p>
						</td>
					</tr>
					<c:if test="${m.memberLevel == 1 }">
					<tr>
						<th>코드</th>
						<td><input type="text" name="code" value="${m.code }" readonly></td>
					</tr>
					</c:if>
					<tr>
						<th>이름 <b class="star join">*</b></th>
						<td><input type="text" id="name" name="name" value="${m.name }" class="modify middle"></td>
					</tr>
					<tr>
						<th>전화번호 <b class="star join">*</b></th>

					<c:choose>
    					<c:when test="${m.phone1 eq 'phone'}">
       						<td>
							<select name="phone1" id="phone1" class="short">
								<option></option>
								<option>02</option>
								<option>031</option>
								<option>032</option>
								<option>033</option>
								<option>041</option>
								<option>042</option>
								<option>043</option>
								<option>044</option>
								<option>051</option>
								<option>052</option>
								<option>053</option>
								<option>054</option>
								<option>055</option>
								<option>061</option>
								<option>062</option>
								<option>063</option>
								<option>064</option>
								<option>010</option>
								<option>011</option>
								<option>016</option>
							</select> - 
								<input type="text" id="phone2" name="phone2" value="" class="modify short" maxlength="4"> - 
								<input type="text" id="phone3" name="phone3" value="" class="modify short" maxlength="4">
							</td>
   						</c:when>
    					<c:otherwise>
        					<td>
								<select name="phone1" id="phone1" class="short">
									<option>${m.phone1 }</option>
									<option>02</option>
									<option>031</option>
									<option>032</option>
									<option>033</option>
									<option>041</option>
									<option>042</option>
									<option>043</option>
									<option>044</option>
									<option>051</option>
									<option>052</option>
									<option>053</option>
									<option>054</option>
									<option>055</option>
									<option>061</option>
									<option>062</option>
									<option>063</option>
									<option>064</option>
									<option>010</option>
									<option>011</option>
									<option>016</option>
								</select> - 
								<input type="text" id="phone2" name="phone2" value="${m.phone2 }" class="modify short" maxlength="4"> - 
								<input type="text" id="phone3" name="phone3" value="${m.phone3 }" class="modify short" maxlength="4">
							</td>
    					</c:otherwise>
    				</c:choose>
				
					</tr>
					<c:if test="${m.memberLevel == 0 }">
					<tr>
						<th>우편번호</th>
						<td>
							<input type="text" id="post" name="post" value="${m.post }" class="modify">
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type="text" id="address" name="address" value="${m.mainAddress }" class="modify" style="margin-bottom:5px;">
							<span id="guide" style="color:#999;display:none"></span>
							<input type="text" id="detailAddress" placeholder="상세주소" class="modify" name="detailAddress" value="${m.detailAddress }">
						</td>
					</tr>
					</c:if>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" value="${m.email }" readonly></td>
					</tr>
					<c:if test="${m.memberLevel == 1}">
					<tr id="selectTime">
						<th>시간 <b class="star join">*</b></th>
						<td>
							<select name="time" id="time" class="short">
								<option id="startTime">${m.startTime }시</option>
								<option value="07">07시</option>
								<option value="08">08시</option>
								<option value="09">09시</option>
								<option value="10">10시</option>
								<option value="11">11시</option>
								<option value="12">12시</option>
								<option value="13">13시</option>
								<option value="14">14시</option>
								<option value="15">15시</option>
								<option value="16">16시</option>
								<option value="17">17시</option>
								<option value="18">18시</option>
								<option value="19">19시</option>
								<option value="20">20시</option>
								<option value="21">21시</option>
								<option value="22">22시</option>
								<option value="23">23시</option>
							</select> 
							~
							<select name="endTime" id="endTime" class="short">
								<option id="endTime2">${m.endTime }시</option>
								<option value="08">08시</option>
								<option value="09">09시</option>
								<option value="10">10시</option>
								<option value="11">11시</option>
								<option value="12">12시</option>
								<option value="13">13시</option>
								<option value="14">14시</option>
								<option value="15">15시</option>
								<option value="16">16시</option>
								<option value="17">17시</option>
								<option value="18">18시</option>
								<option value="19">19시</option>
								<option value="20">20시</option>
								<option value="21">21시</option>
								<option value="22">22시</option>
								<option value="23">23시</option>
								<option value="24">24시</option>
							</select>
						</td>
					</tr>
					</c:if>
				</table>
			</div>
			<div class="common-tbl-btn-group">
				<button type="submit" class="btn-style1" value="수정하기">수정하기</button>
				<c:if test="${m.memberLevel < 2}">
				<button type="button" class="btn-style2" onclick="location.href='/delete?id=${m.id}'">탈퇴하기</button>
				</c:if>
				<button type="reset" class="btn-style3" value="취소">취소</button>
			</div>
		</form>
	</div>
</section>
	
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample4_execDaumPostcode() {
        new daum.Postcode({
        	
            oncomplete: function(data) {
            	
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('post').value = data.zonecode;
                document.getElementById("address").value = roadAddr;
                
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
              

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            },
 		theme : {
 			bgColor: "#FFFFFF", //바탕 배경색
 		   searchBgColor: "#FE431E", //검색창 배경색
 		   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
 		   pageBgColor: "#FE431E", //페이지 배경색
 		   textColor: "#000000", //기본 글자색
 		   queryTextColor: "#FFFFFF", //검색창 글자색
 		   postcodeTextColor: "#FE431E", //우편번호 글자색
 		   emphTextColor: "#059DEB", //강조 글자색
 		   outlineColor: "#9F9F9F" //테두리
 		}
 		
        }).open();
    }
</script>
	
<script>
	$(document).ready(function(){
		$('.modify').click(function(){
			$(this).val("");
			
			
		});
		$('#time').change(function(){
			var time = $('#time').val();
			var no = $('#time').children().eq();
			
			for(var i=0;i<17;i++){
			
				if(time>=$('#endTime').children().eq(i).val()){
					$('#endTime').children().eq(i).css('display','none');
				}else if(time<=$('#endTime').children().eq(i).val()){
					$('#endTime').children().eq(i).css('display','block');
				}
			}
		});
		$('#endTime').change(function(){
			var endTime = $('#endTime').val();
			for(var j=0;j<17;j++){
			if(endTime<=$('#time').children().eq(j).val()){
				$('#time').children().eq(j).css('display','none');
			}else if(endTime>=$('#time').children().eq(j).val()){
				$('#time').children().eq(j).css('display','block');
			}
			}
		});

		$("#time").mouseover(function(){
			$('#startTime').css('display','none');
		});
		$("#endTime").mouseover(function(){
			$('#endTime2').css('display','none');
		});
		
		$('#pw').keyup(function(){
			var pw = $('#pw').val();
			var checkPw = /^.*(?=.{8,13})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
			if(checkPw.test(pw)==true){
				$('#p_checkPw').css('display','none');
				
			}else if(checkPw.test(pw)==false){
				$('#p_checkPw').css('display','inline-block');
				$('#p_checkPw').css('color','red');
			}

		});
		
		$('#pw_re').keyup(function(){
			var pw = $('#pw').val();
			var pw_re = $('#pw_re').val();
			if(pw != pw_re){
				$('#p_checkPw_re').css('display','inline-block');
				$('#p_checkPw_re').css('color','red');
			}else{
				$('#p_checkPw_re').css('display','none');
			}
		});

	});
	function check(){
		var id_re = /^[a-zA-Z0-9]{4,12}$/;
		var checkPw = /^.*(?=.{8,13})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		if($('#pw').val() == ""){
			alert("비밀번호를 입력해주세요");
			$('#pw').focus();
			return false;
		}
		if(checkPw.test($('#pw').val())==false){
			alert("비밀번호 양식이 틀렸습니다");
			$('#pw').focus();
			return false;
		}
		if($('#pw').val() != $('#pw_re').val()){
			alert("비밀번호가 일치하지 않습니다");
			$('#pw').focus();
			return false;
		}
		if($('#name').val() == ""){
			alert("이름을 입력해주세요");
			$('#name').focus();
			return false;
		}
		if($('#phone2').val() == ""){
			alert("전화번호를 입력해주세요");
			$('#phone2').focus();
			return false;
		}
		if($('#phone3').val() == ""){
			alert("전화번호를 입력해주세요");
			$('#phone3').focus();
			return false;
		}
		if($('#post').val() == ""){
			alert("우편번호를 입력해주세요");
			$('#post').focus();
			return false;
		}
		if($('#address').val() == ""){
			alert("주소를 입력해주세요");
			$('#address').focus();
			return false;
		}
		if($('#detailAddress').val() == ""){
			alert("상세주소를 입력해주세요");
			$('#detailAddress').focus();
			return false;
		}
	}
</script>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />