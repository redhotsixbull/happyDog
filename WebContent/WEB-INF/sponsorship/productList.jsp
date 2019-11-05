<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">후원하기</h2>
		<div class="product-list clearfix">
			<c:forEach items="${prdList }" var="prd">
				<div class="product" onclick="location.href='/viewProduct?code=${prd.prdCode}'">
					<div class="product-inner">
						<div class="prd-img-thum">
							<span style="background:url(/img/${prd.prdImg}) no-repeat center center; background-size:cover;"></span>
						</div>
						<div class="prd-txt-thum">
							<div class="info">
								<h3>${prd.prdName}</h3>
								<p class="price">최소 후원금액 <b><fmt:formatNumber value="${prd.prdPrice}" pattern="#,###" /> 원</b></p>
								<p class="txt">${prd.prdCon}</p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />