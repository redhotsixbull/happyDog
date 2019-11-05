<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<style>
	.common-tbl-box{padding:70px;}
	.comm-tbl input[type='text'].middle, .comm-tbl input[type='password'].middle{width:50%;}
	.comm-tbl select.short{width:30%;}
	#post{display:inline-block; width:75%; vertical-align:middle;}
	.star.join{color:rgba(254,67,30); font-size:15px;}
</style>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h1 id="h_title1" class="comm-content-tit">일반 회원정보 입력</h1>
		<h1 id="h_title2" style="display:none" class="comm-content-tit">보호소 회원정보 입력</h1>
		<form action="/memberJoin" method="post" onsubmit="return check()">
			<div class="common-tbl-box">
				<p class="necessary join"><b class="star join">*</b> 는 필수입력항목입니다. </p>
				<table class="comm-tbl view">
					<colgroup>
						<col width="20%">
						<col width="/">
					</colgroup>
					<tr>
						<th>아이디 <b class="star join">*</b></th>
						<td>
							<input type="text" name="id" id="id" class="middle" placeholder="4~12자리 영/숫자">
							<p id="p_checkId" style="display:none">아이디 입력양식을 확인하세요</p>
							<div class="common-tbl-btn-group join-btn-group">
								<button type="button" id="checkId" value="중복체크" class="btn-style2 small">중복체크</button>
							</div>
						</td>
					</tr>
					<tr>
						<th>비밀번호 <b class="star join">*</b></th>
						<td>
							<input type="password" name="pw" id="pw" class="middle" placeholder="영/숫자를 포함한 8~13자리">
							<p id="p_checkPw" style="display:none">비밀번호 입력양식을 확인하세요</p>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인 <b class="star join">*</b></th>
						<td>
							<input type="password" name="pw_re" id="pw_re" class="middle">
							<p id="p_checkPw_re" style="display:none">비밀번호가 일치하지 않습니다</p>
						</td>
					</tr>
					<c:if test="${level == 1}">
					<tr>
						<th>시 선택 <b class="star join">*</b></th> 
						<td>
							<select name="city" id="city">
								<option>도시선택</option>
								<c:forEach items="${list }" var="m" varStatus="i">
										<option value="${m.cityCode }">${m.cityName }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>구 선택 <b class="star join">*</b></th>
						<td>
							<select name="area" id="area">
								<option>지역구선택</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>보호소 선택  <b class="star join">*</b></th>
						<td>
							<select name="care" id="care">
								<option>보호소선택</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>보호소코드 입력  <b class="star join">*</b></th>
						<td>
							<input type="text" name="code" id="code" class="middle" placeholder="코드를 입력해주세요">
						</td>
					</tr>
					<input type="hidden" id="code_re"> 
					</c:if>
					<tr>
						<th>이름 <b class="star join">*</b></th>
						<td><input type="text" name="name" id="name"></td>
					</tr>
					<tr>
						<th>전화번호 <b class="star join">*</b></th>
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
							</select>
							-
							<input type="text" name="phone2" id="phone2" maxlength="4" class="short">
							-
							<input type="text" name="phone3" id="phone3" maxlength="4" class="short">
						</td>
					</tr>
					<c:if test="${level == 0 }">
					<tr>
						<th>우편번호</th>
						<td>
							<input type="text" id="post" placeholder="우편번호" name="post">
							<div class="common-tbl-btn-group join-btn-group">
								<button type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="btn-style2 small">우편번호 찾기</button>
							</div>
						</td>
					</tr>
					<tr>
						<th>주소 <b class="star join">*</b></th>
						<td>
							<input type="text" id="address" placeholder="도로명주소" name="address" style="margin-bottom:5px;">
							<span id="guide" style="color:#999;display:none"></span>
							<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress" value="">
						</td>
					</tr>
					</c:if>
					<input type="hidden" id="careCity" name="careCity" value="">
					<input type="hidden" id="careArea" name="careArea" value="">
					<tr>
						<th>EMAIL <b class="star join">*</b></th>
						<td><input type="text" name="email" id="email" value="${email }" readonly></td>
					</tr>
					<input type="hidden" name="level" id="level" value="${level}">
					<c:if test="${level > 0 }">
					<tr id="selectTime">
						<th>방문시간 <b class="star join">*</b></th>
						<td>
							<select name="time" id="time" class="middle">
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
							<select name="endTime" id="endTime" class="middle">
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
								<option value="24" selected>24시</option>
							</select>
						</td>
					</tr>	
					</c:if>	
					
				</table>
				
			</div>
			<div class="common-tbl-btn-group">
				<button type="submit" value="회원가입" class="btn-style1" id="sub">회원가입</button>
				<button type="reset" value="취소" class="btn-style2" id="reset">취소</button>
			</div>
		</form>
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
	$("#city").change(function(){
		var value = $("#city").val();
		$.ajax({
			url:"/areaCode",		
			data : {value:value},
			type : "get",
			
			success : function(data){
				var $select = $("#area");
				$select.find("option").remove();
				$select.append("<option>지역구선택</option>");
				
				for(var i=0;i<data.length;i++){
					var areaName=data[i].districtName;
					var areaValue=data[i].district;
					var selected="";
					
					$select.append("<option value='"+areaValue+"''"+selected+">"+areaName+"</option>");
					
				}
				
				
				
			},
			error : function(){
			console.log("실패");	
			}
		});
		
	});
	
	$("#care").change(function(){
		$('#name').text($('#care').val());
		$('#name').val($('#care option:selected').text());
		$('#code_re').val($('#care').val());
		
	});
	$("#area").change(function(){
		$('#careArea').val($('#area option:selected').text());
	});
	$("#city").change(function(){
		$('#careCity').val($('#city option:selected').text());
	});

	
	$("#area").change(function(){
		var citValue = $("#city").val();
		var areValue =	$("#area").val();
		$.ajax({
			url:"/careCode",		
			data : {citValue:citValue,areValue:areValue},
			type : "get",
			
			success : function(data){
				var $select = $("#care");
				$select.find("option").remove();
				$select.append("<option>보호소선택</option>");
				
				for(var i=0;i<data.length;i++){
					var careName=data[i].careNm;
					var careRegNo=data[i].careRegNo;
					var selected="";
					
					$select.append("<option value='"+careRegNo+"''"+selected+">"+careName+"</option>");
					
				}
				
			},
			error : function(){
			console.log("실패");	
			}
		});
		
	});
	
	
		$(document).ready(function(){
			$('#time').change(function(){
				var time = $('#time').val();
				var no = $('#time').children().eq();
				
				for(var i=0;i<17;i++){
					if(time>=$('#endTime').children().eq(i).val()){					
						$('#endTime').children().eq(i).css('display','none');		//select #time 을 선택했을때 #time의 value값보다 #endTime의 value값이 작을때 작은 value의 display를 none
					}else if(time<=$('#endTime').children().eq(i).val()){			
						$('#endTime').children().eq(i).css('display','block');		//select #time 을 선택했을때 #time의 value값보다 #endTime의 value값이 클때 큰 value의 display를 block
					}
				}
			});
			$('#endTime').change(function(){
				var endTime = $('#endTime').val();
				for(var j=0;j<17;j++){
				if(endTime<=$('#time').children().eq(j).val()){				//select #endTime을 선택했을때 #time의 value 값보다 작을때 #time의 display를 none
					$('#time').children().eq(j).css('display','none');
				}else if(endTime>=$('#time').children().eq(j).val()){		//select #endTime을 선택했을때 #time의 value 값보다 클때 #time의 display를 block
					$('#time').children().eq(j).css('display','block');
				}
				}
			});
			var level = $('#level').val();
			if(level > 0){
				$('#p_code').css('display','block');		//level이 0보다 클때 block
				$('#h_title2').css('display','block');		//level이 0보다 클때 block
				$('#h_title1').css('display','none');		//level이 0보다 클때 none
				
			}
			
			$('#id').keyup(function(){
				var id = $('#id').val();
				var id_re = /^[a-zA-Z0-9]{4,12}$/;
				if(id_re.test(id)==true){
					$('#p_checkId').css('display','none');
					
				}else if(id_re.test(id)==false){
					$('#p_checkId').css('display','inline-block');
					$('#p_checkId').css('color','red');
				}
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
			
			$('#reset').click(function(){
				$('#p_checkId').css('display','none');
				$('#p_checkPw').css('display','none');
				$('#p_checkPw_re').css('display','none');
			});
			
		});
		
		$('#checkId').click(function(){
			var memberId = $('#id').val();
			$.ajax({
				url : "/checkId",
				type : "get",
				data : {memberId : memberId},
				success : function(data){
					if(data ==1){
						alert("사용가능")
					}else{
						alert("사용불가")
					}
				},
				error : function(){
					console.log("실패");
				}
			});
		});
		function check(){
			var id_re = /^[a-zA-Z0-9]{4,12}$/;
			var checkPw =  /^.*(?=.{8,13})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
			var code_re = $('#code_re').val();
			if($('#id').val() == ""){
				alert("아이디를 입력해주세요");
				$('#id').focus();
				return false;
			}
			if(id_re.test($('#id').val())==false){
				alert("아이디 양식이 틀렸습니다");
				$('#id').focus();
				return false;
			}
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
				alert("주소를 입력해주세요");
				$('#detailAddress').focus();
				return false;
			}
			if($('#email').val() == ""){
				alert("이메일을 입력해주세요");
				$('#email').focus();
				return false;
			}
			if(code_re != $('#code').val()){
				alert("코드가 일치하지 않습니다");
				$('#code').focus();
				return false;
			}
		}
	
	</script>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />