<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/content.css">
<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->
	

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">입양하기</h2>
		<ul class="main-adopt-review-list clearfix"><!-- 입양후기는 최소/최대 8개가 노출됩니다. -->
			<c:forEach items="${sdpd.list }" var="m" varStatus="i">
				<li>
					<a href="/dogInfo">
						<div class="img-thum">
						<span style="background:url('${m.filename }') no-repeat center center; background-size:cover;"></span>
						</div>
						<div class="txt-thum">
						
						<p>보호소명  : ${m.careNm }, 품종 : ${m.kindCd }</p><br>
						<p>성별 :${m.sexCd }
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<!-- paging -->
		<div class="paging">${sdpd.pageNavi }</div>
		
		<div id="searchDog" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<!-- search -->
			 	<form action="/printSearchDog">
		 	<div class="board-search-box">
				<select name="kind"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
					<option>품종</option>
					<c:forEach items="${kind }" var="k">
						<option value="${k.code }">${k.kind }</option>
					</c:forEach>
				</select>
				<select name="happenCity" style="margin-right:7px;">
					<option>도시</option>
					<c:forEach items="${city }" var="c">
						<option value="${c.cityCode }">${c.cityName }</option>
					</c:forEach>
				</select>
				<input type="date" name="startDay" class="datepicker search-day"> ~ <input type="date" name="endDay" class="datepicker search-day">
				<button type="submit" class="bbs-search-btn" title="검색" style="margin-left:5px;"><img src="/img/search_icon.png" style="width:30px;"></button>
			</div>
			</form>
		</div>
	</div>
</section>


	
	
	
	
	
	
	
	
	
	
	
	<%--footer --%>
	<jsp:include page="/WEB-INF/common/footer.jsp" />