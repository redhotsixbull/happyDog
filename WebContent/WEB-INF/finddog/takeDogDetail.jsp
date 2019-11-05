<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">
<jsp:include page="/WEB-INF/common/header.jsp" />
<%-- Content --%>

	<section id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">조회 뷰</h2>
		<div id="takeDogDetail" class="common-box">
			<div class="dog-detail-top-inner clearfix">
				<div class="dog-info-left">
					<img src="/img/dog_test_bk.jpg" style="max-width:100%;">
				</div>
				<div class="dog-info-right">
					<table class="comm-tbl">
						<colgroup>
							<col width="25%">
							<col width="/">
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
				
							</td>
						</tr>
						<tr>
							<th>보호소 주소</th>
							<td>받아오기</td>
						</tr>
						<tr>
							<th>보호소 연락처</th>
							<td>받아오기</td>
						</tr>
						<tr>
							<th>보호소 <br>방문가능 시간</th>
							<td>받아오기</td>
						</tr>
						<tr>
							<th>품종</th>
							<td>받아오기(kindCd)</td>
						</tr>
						<tr>
							<th>나이</th>
							<td>받아오기(age)</td>
						</tr>
						<tr>
							<th>성별</th>
							<td>받아오기(sexCd)</td>
						</tr>
						<tr>
							<th>특징</th>
							<td colspan="3">받아오기(specialMark, neuterYn)</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
	
	</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />