<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/common/header.jsp" />


	<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">강아지를 찾습니다</h2>
			<div class="common-tbl-box">
				<form action="/findBoardEnroll" method="post" enctype="multipart/form-data"><!-- 파일 첨부를 하기위해 enctype 설정 -->
					<input type="hidden" name="boardType" value="4"/>
					<!-- 보호게시판 구분번호인 1을 submit -->
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<!-- 등록한 사람의 아이디 정보를 전달 -->
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th>품종</th>
							<th>
								<select name="kind" required="required"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
									<option value="">품종</option>
									<c:forEach items="${kind }" var="k">
										<option value="${k.code }">${k.kind }</option>
									</c:forEach>
								</select>
							</th>
						</tr>
						<tr>
							<th>잃어버린 도시</th>
							<th>
								<select name="happenCity" required="required">
									<option value="">도시</option>
									<c:forEach items="${city }" var="c">
										<option value="${c.cityCode }">${c.cityName }</option>
									</c:forEach>
								</select>
							</th>
						</tr>
						<tr>
							<th>잃어버린 날짜</th>
							<th>
								<input type="text" name="startDay" class="datepicker3 search-day short" autocomplete="off" required="required">
							</th>
							
						</tr>
						
						<tr>
							<th>제목</th>
							<td><input type="text" name="boardTitle" required="required"/></td>
						</tr>
						<tr>
							<th>사진</th>
							<td><input type="file" name="boardFilename" required="required"/></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea type="text" name="boardContent" required="required"></textarea></td>
						</tr>
					</table>
					<br>
					<table class="comm-tbl" >
						<tr style="display: none;"></tr>
						<tr>
							<td>
								<p>※ 유실 동물을 찾기위한 연락책의 일환으로 회원정보에 등록된 이메일이 게시글에 노출됩니다.</p>
							</td>
						</tr>
					</table>
					
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">등록하기</button>
						<button type="reset" class="btn-style2" onclick="history.back();">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />