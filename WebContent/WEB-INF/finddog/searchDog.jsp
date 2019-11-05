<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<style>
.board-search-box{margin-bottom:30px;}
.board-search-box input{height:40px; border:1px solid #ccc; background:#fff;}
</style>

	
<%-- Content --%>
<script type="text/javascript">
    $(function() {
       test.init();
    });
 </script>
	
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">실종 유기견 찾기</h2>
		<div id="searchDog" class=""><!-- id는 바꿔서 복붙 -->

			

			 		<!-- 검색박스 -->
	 		 	<!-- 검색박스 -->
		 	<form action="/printSearchDog">
		 	<div class="board-search-box" style="margin-bottom:20px">
		 	
		 		<select name="happenCity" style="margin-right:7px;" id="city">
					<option>도시</option>
					<c:forEach items="${city }" var="c">
						<option value="${c.cityCode }"  <c:if test="${citya==c.cityCode }">selected</c:if>>${c.cityName }</option>
					</c:forEach>
				</select>
				
				<select name="kind" id="kind"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
					<option value="content">품종</option>
					<c:forEach items="${kind }" var="k">
						<option value="${k.code }"  <c:if test="${kinda==k.code}">selected</c:if>       >${k.kind }</option>
					</c:forEach>
				</select>
			
				<input type="text" name="startDay" class="datepicker search-day" id="sDay" placeholder="${sDay }" autocomplete="off"> ~ <input type="text" name="endDay" class="datepicker search-day" id="eDay" placeholder="${eDay }" autocomplete="off">
				
				<button type="submit" class="bbs-search-btn" title="검색" style="margin-left:5px;"><img src="/img/search_icon.png" style="width:30px;"></button>
			</div>
			</form>

			<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
				<colgroup>
					<col width="">
					<col width="15%">
					<col width="18%">
					<col width="10%">
					<col width="25%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>사진</th>
						<th>보호센터</th>
						<th>발견장소</th>
						<th>발견날짜</th>
						<th>공고번호</th>
					</tr>
				</thead>
				<tbody >
					<c:forEach items="${sdpd.list }" var="m" varStatus="i">
					
							<tr onclick="javascript:form_${i.count}.submit();" style="cursor:pointer">
					<form action="/dogDetailView" method="post" name="form_${i.count}">
						<input type="hidden" name="careNm" value="${m.careNm }">
						<c:if test="${fn:contains(m.careAddr,'(')}">
						<input type="hidden" name="careAddr" value="${fn:split(m.careAddr,'(')[0] }">
						</c:if>
						<c:if test="${fn:contains(m.careAddr,'(')==false}">
						<input type="hidden" name="careAddr" value="${m.careAddr}">
						</c:if>
						<input type="hidden" name="careTel" value="${m.careTel }">
						<input type="hidden" name="kindCd" value="${m.kindCd }">
						<input type="hidden" name="age" value="${m.age }">
						<input type="hidden" name="sexCd" value="${m.sexCd }">
						<input type="hidden" name="specialMark" value="${m.specialMark }">
						<input type="hidden" name="neuterYn" value="${m.neuterYn }">
						<input type="hidden" name="filename" value="${m.filename }">
						<input type="hidden" name="city" value="${m.careNm }">
						<input type="hidden" name="gun" >
						<input type="hidden" name="dogsize">
						<input type="hidden" name="dogkind">
						<input type="hidden" name="neuter">
						<input type="hidden" name="reqPage" value="${reqPage}">
					</form>
						
						<td><a onclick="javascript:form_${i.count}.submit();" class="send-dogInfo"><img src=${m.filename } style="height: 200px; width: 200px;" ></a></td>
						<td>${m.careNm }</td>
						<td>${m.happenPlace }</td>
						<td>${m.happenDt }</td>
						<td>${m.noticeNo }</td>	
					</tr>
				
					</c:forEach>
					<c:if test="${empty sdpd.list }">
						<tr>
							<td colspan="6">
								<p class="none">조회 결과가 없습니다.</p>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<!-- paging -->
			<div class="paging">
				${sdpd.pageNavi }
	 		</div>
	 		
	 		<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
				<colgroup>
					<col width="">
					<col width="15%">
					<col width="18%">
					<col width="10%">
					<col width="25%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						
						<th>사진</th>
						<th>보호자</th>
						<th>제목</th>
						<th>발견도시</th>
						<th>발견날짜</th>
						
					</tr>
				</thead>
				<tbody >
					<c:forEach items="${sdpd2.list }" var="m" varStatus="i">



					<tr onclick="location.href='/dogDetailView2?boardNo=${m.boardNo }'" style="cursor:pointer">
							<td><img src="/siUpload/board/${m.boardFilepath }" style="height: 200px; width: 200px;" ></td>
							<td>${m.boardName }</td>
							<td>${m.boardTitle }</td>
							<td>${m.happenCity }</td>
							<td>${m.happenDate }</td>
						</tr>
					</c:forEach>
					<c:if test="${empty sdpd2.list }">
						<tr>
							<td colspan="6">
								<p class="none">조회 결과가 없습니다.</p>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
				<!-- paging -->
			<div class="paging">
				${sdpd2.pageNavi }
	 		</div>
	 		
		</div>
	</div>
</section>


	
	
	<%--footer --%>
	<jsp:include page="/WEB-INF/common/footer.jsp" />