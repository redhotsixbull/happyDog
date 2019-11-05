<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<style>
#user{min-width:100px; height:40px; border:1px solid #ccc; margin-bottom:10px;}
.comm-tbl.type2 th{background-color:#f8f8f8;}
.comm-tbl.type2 td > button{padding:7px 13px; background-color:rgba(254,67,30,0.95); color:#fff; border-radius:5px;}
</style>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">회원 관리</h2>
		<input type="hidden" id="a" value="${user }">
		<input type="hidden" id="b" value="${select }">
		<div class="">
		 	<form action="/seeUser" method="post" id="form"> 
		 		<select name="user" id="user">
					<option value="2">전체회원</option>
					<option value="0">일반회원</option>
					<option value="1">보호소회원</option>
				</select>
		<!--  	</form>	 --> 	
			<table class="comm-tbl type2">
				<colgroup>
					<col width="">
					<col width="">
					<col width="13%">
					<col width="">
					<col width="">
					<col width="">
					<col width="7.5%">
					<col width="7%">
				</colgroup>
				<tr>
					<th>ID</th>
					<c:if test="${user >= 1 }">
					<th>CODE</th>
					</c:if>
					<th>이름</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>EMAIL</th>
					<th>회원등급</th>
					<th>관리</th>
				</tr>
				<c:forEach items="${pd.list }" var = "m">
				<c:if test="${m.memberLevel < 2 }">
				<tr>
					<td id="id">${m.id }</td>
					<c:if test="${m.memberLevel >= 1 }">
						<td>${m.code }</td>
					</c:if>
					<c:if test="${m.memberLevel == 0 }">
						
					</c:if>
					<c:if test="${user >= 1 }">
					<c:if test="${m.memberLevel == 0 }">
					<td>-</td>
					</c:if>
					</c:if>
					<td>${m.name }</td>
					<td>${m.phone }</td>
					<td>
						(${m.post }) 
						${fn:replace(m.address,'//',' ') }
					</td>
					<td>${m.email }</td>
					<td>${m.user }</td>
				<!-- 	<td><button onclick="location.href='/adminDelete?id=${m.id}'">탈퇴</button></td>  -->
					<td><button type="button" id="btn">탈퇴</button></td>
				</tr>
				</c:if>
				</c:forEach>
			</table>
			<div id="pageNavi" class="paging">${pd.pageNavi }</div>
			<!-- 검색박스 -->
	 		<div class="board-search-box">
		<!-- 		<form action="/searchUser" method="post">	 -->
					<select name="select" id="select">
						<option value="1">아이디</option>
						<option value="2">이름,보호소</option>
						<option value="3">코드</option>
					</select>
					<input type="text" name="search" class="search-word" value="${search }">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</form>
			</div>
		</div>
	</div>
</section>	
	
<script>
		$(document).ready(function(){
			var a = $('#a').val();
			var b = $('#b').val();
			console.log(a+"이건 유저");
			console.log(b+"이건 셀렉트");
			if(a == 0){
				$('#user').children().eq(1).attr('selected','selected');
			}else if(a == 1){
				$('#user').children().eq(2).attr('selected','selected');
			}else if(a == 2){
				$('#user').children().eq(0).attr('selected','selected');
			}
			if(b == 2){
				$('#select').children().eq(1).attr('selected','selected');
			}else if(b == 3){
				$('#select').children().eq(2).attr('selected','selected');
			}else if(b == 1){
				$('#select').children().eq(0).attr('selected','selected');
			}
		
			
		});
			$('#user').change(function(){
				$('#form').submit();
				
				
	/*			var user = $('#user').val();
				
				$.ajax({
					url:"/seeUser",
					data : {user:user},
					type : "get",
					success : function(data){
						
					},
					error : function(){
						console.log("전송 실패");
					}
				});	*/
			});
			
			$('#btn').click(function(){
				var id = $('#id').text();
				$.ajax({
					url : "/adminDelete",
					type : "get",
					data : {id : id},
					success : function(data){
						if(data ==1){
							alert("탈퇴완료")
							location.href='/adminPage';
						}else{
							alert("탈퇴실패")
							location.href='/adminPage';
						}
					},
					error : function(){
						console.log("실패");
					}
				});
			});
		
</script>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />