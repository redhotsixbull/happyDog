<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">전국 보호소 찾기</h2>
		<div id="findShelterBox" class="clearfix">
			<div class="find-shelter-left-box">
				<table class="comm-tbl type2"><!-- 보호소 리스트는 최대 7개씩 노출됩니다. -->
					<thead>
						<tr>
							<th>보호소명</th>
							<th>주소</th>
							<th>연락처</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${mpd.list }" var="i">
							<tr>
								<td>${i.name }</td>
								<td>${fn:split(i.address,'//')[0]}</td>
								<td>${i.phone }</td>
							</tr>
						</c:forEach>
						<c:if test="${empty mpd.list }">
							<tr height="100px">
								<td colspan="3">검색하신 보호소가 없습니다.</td>
							</tr>
						</c:if>	
					</tbody>
				</table>
				<!-- paging -->
				<div class="paging">
					${mpd.pageNavi }
		 		</div>
				
		 		<!-- 검색박스 -->
		 		<div class="board-search-box">
		 			<form action="/nameSearchShelter" method="get">
						<select name="type" style="display:none;"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
							<option value="name">보호소명</option>
						</select>
						<input placeholder="보호소명을 입력해주세요." type="search" name="keyword" class="search-word" value="${keyword}">
						<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					</form>
				</div>
			</div>
			<div class="find-shelter-right-box">
				<div class="map">	
					<a href="/printShelter?city=2" class="loc2" id="loc2"><p class="loc-name">서울<span id="locCnt02"></span></p></a>
					<a href="/printShelter?city=3" class="loc3" id="loc3"><p class="loc-name">경기도<span id="locCnt03"></span></p></a>
					<a href="/printShelter?city=4" class="loc4" id="loc4"><p class="loc-name">강원도<span id="locCnt04"></span></p></a>
					<a href="/printShelter?city=5" class="loc5" id="loc5"><p class="loc-name">충청남도<span id="locCnt05"></span></p></a>
					<a href="/printShelter?city=6" class="loc6" id="loc6"><p class="loc-name">대전<span id="locCnt06"></span></p></a>
					<a href="/printShelter?city=7" class="loc7" id="loc7"><p class="loc-name">충청북도<span id="locCnt07"></span></p></a>
					<a href="/printShelter?city=8" class="loc8" id="loc8"><p class="loc-name">경상북도<span id="locCnt08"></span></p></a>
					<a href="/printShelter?city=9" class="loc9" id="loc9"><p class="loc-name">전라북도<span id="locCnt09"></span></p></a>
					<a href="/printShelter?city=10" class="loc10" id="loc10"><p class="loc-name">광주<span id="locCnt10"></span></p></a>
					<a href="/printShelter?city=11" class="loc11" id="loc11"><p class="loc-name">전라남도<span id="locCnt11"></span></p></a>
					<a href="/printShelter?city=12" class="loc12" id="loc12"><p class="loc-name">경상남도<span id="locCnt12"></span></p></a>
					<a href="/printShelter?city=13" class="loc13" id="loc13"><p class="loc-name">제주도<span id="locCnt13"></span></p></a>
					<a href="/printShelter?city=14" class="loc14" id="loc14"><p class="loc-name">부산<span id="locCnt14"></span></p></a>
					<a href="/printShelter?city=15" class="loc15" id="loc15"><p class="loc-name">대구<span id="locCnt15"></span></p></a>
					<a href="/printShelter?city=16" class="loc16" id="loc16"><p class="loc-name">세종시<span id="locCnt16"></span></p></a>															
				</div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
$(function(){
	$("#loc${param.city}").addClass("on");
/* 	$(".map > a").click(function(){
		
	
		if($(this).hasClass("on") === true) {
			$(".map > a").removeClass("on");
		}else{
			$(".map > a").removeClass("on");
			$(this).addClass("on");
		}
	}); */
});


</script>	

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />