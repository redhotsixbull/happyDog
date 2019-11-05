<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="volunteer.model.vo.VoluntaryRegister" %>
<%@ page import="volunteer.model.vo.VoluntaryListData" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<script>
$(function(){
	//검색
	var type = '${param.type}';
	
	if(type != ''){
		$('option').each(function(){
			if($(this).val() == type){
				$(this).attr('selected','selected');
			}
		});
	}
	
})
</script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">${pageTitle }</h2>
		<div id="voluntaryListBox"><!-- class="common-tbl-box" id는 바꿔서 복붙 -->
			<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
				<colgroup>
					<col width="5%">
					<col width="">
					<col width="12%">
					<col width="13%">
					<col width="18%">
					<col width="11%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>봉사활동 제목</th>
						<th>봉사활동 <br>날짜 / 시간</th>
						<th>신청한 인원 수 / <br>신청 가능 인원 수</th>
						<th>보호소명</th>
						<th>공고 등록일</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty vld.list }">
						<c:forEach items="${vld.list }" var="volunRegister" varStatus="i">
							<tr>
								<td>${i.count }</td>
								<td><p class="volun-tit"><a href="/voluntaryView?no=${volunRegister.no }">${volunRegister.title}</a></p></td>
								<td>${volunRegister.volunDate } <br>${volunRegister.startTime }시 ~ ${volunRegister.endTime }시</td>
								<td>${volunRegister.applyNum }명 / ${volunRegister.person }명</td>
								<td class="name">${volunRegister.name}</td>
								<td>${volunRegister.enrollDate }</td><!-- ${volunRegister.status } -->
								<td>
									<!-- ${volunRegister.applyNum } --> <!-- 신청한 인원 -->
									<c:set var="today"><fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd"/></c:set>
									<c:if test="${volunRegister.volunDate >= today }">
										<c:if test="${volunRegister.status eq '모집중' }">
											<span class="volun-status ing">${volunRegister.status }</span>
										</c:if>
										<c:if test="${volunRegister.status eq '모집마감' }">
											<span class="volun-status end">${volunRegister.status }</span>
										</c:if>
									</c:if>
									<c:if test="${volunRegister.volunDate < today }">
										<span class="volun-status end">모집마감</span>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty vld.list }">
						<tr>
							<td colspan="7">
								<p class="none">봉사활동 공고가 없습니다.</p>
							</td>
						</tr>
					</c:if>
					<!-- <tr>
						<td>1</td>
						<td><p class="volun-tit"><a href="/voluntaryView">봉사활동 제목이 들어갑니다.봉사활동 제목이 들어갑니다.봉사활동 제목이 들어갑니다.</a></p></td>
						<td>2019-05-28 <br>9시 ~ 16시</td>
						<td>20명</td>
						<td class="name">보호소이름이 들어갑니다.</td>
						<td>2019-05-24</td>
						<td><span class="volun-status ing">모집중</span></td>
					</tr>
					<tr>
						<td>2</td>
						<td><p class="volun-tit"><a href="/voluntaryView">봉사활동 제목이 들어갑니다.봉사활동 제목이 들어갑니다.봉사활동 제목이 들어갑니다.</a></p></td>
						<td>2019-05-28 <br>9시 ~ 16시</td>
						<td>20명</td>
						<td class="name">보호소이름이 들어갑니다.</td>
						<td>2019-05-24</td>
						<td><span class="volun-status end">모집마감</span></td>
					</tr> -->
				</tbody>
			</table>
			<!-- paging -->
			<div class="paging">
	 			<!-- <a href="" class="paging-arrow prev-arrow"><img src="/img/left_arrow.png" style="width:30px;height:30px;"></a>
	 			<a href="" class="cur">1</a>
	 			<a href="">2</a>  
	 			<a href="">3</a>
	 			<a href="">4</a>
	 			<a href="">5</a>
	 			<a href="" class="paging-arrow next-arrrow"><img src="/img/right_arrow.png" style="width:30px;height:30px;"></a> -->
	 			${vld.pageNavi }
	 		</div>
	 		<!-- 검색박스 -->
	 		<div class="board-search-box">
	 			<form action="/searchVoluntary" method="get">
					<select name="type"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
						<option value="title">제목</option>
						<option value="name">보호소명</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="keyword" class="search-word" value="${param.keyword }">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</form>
			</div>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />