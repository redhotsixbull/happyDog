<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
					<colgroup>
						<col width="35%">
						<col width="">
						<col width="35%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>이름</th>
							<th>코드</th>
	
						</tr>
					</thead>
					<tbody >
					<c:forEach items="${gcc.list }" var="m" varStatus="i">
					${gcc.areaCode }
					<tr>
						<td>${i.count }</td>
						<td>${m.district }</td>
						<td>${m.districtName }</td>
					</tr>
					
					</c:forEach>
					</tbody>
				</table>
</body>
</html>