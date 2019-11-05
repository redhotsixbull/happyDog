<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">My Page</h2>
		<!-- 마이페이지는 회원별로 구분됩니다. (일반회원/보호소회원/관리자) -->
		<!-- 일반회원 : 후원 내역 , 봉사활동 신청 내역, 방문예약 신청 내역 / QnA, 자유게시판 -->
		<!-- 보호소회원 : 후원 내역, 봉사활동 공고 등록 내역, 방문예약 신청자 리스트 / QnA, 자유게시판 -->
		<!-- 관리자 : 회원 관리 / 주문 관리 / 공지사항 게시판 -->
		
		<!-- 일반 회원 일때 -->
		<c:if test="${member.memberLevel eq 0 }">
			<h3 class="mypage-tit">환영합니다! <b>${member.name }</b> 님</h3>
			<ul class="mypage-box clearfix">
				<li>
					<a href="/myOrderList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon01.png"></span>
						</div>
						<div class="txt-thum">
							<h4>후원</h4>
							<p>후원 상품 구매내역을 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/voluntaryApplyList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon02.png"></span>
						</div>
						<div class="txt-thum">
							<h4>봉사활동</h4>
							<p>봉사활동을 신청한 내역을 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/reservationMypage">
						<div class="img-thum">
							<span><img src="/img/mypage_icon03.png"></span>
						</div>
						<div class="txt-thum">
							<h4>방문예약 신청내역</h4>
							<p>보호소 방문 예약 신청 내역을 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/myQnaList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon04.png"></span>
						</div>
						<div class="txt-thum">
							<h4>QnA</h4>
							<p>내가 작성한 QnA를 <br> 볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/siMyPreBoard?memberId=${sessionScope.member.id }">
						<div class="img-thum">
							<span><img src="/img/mypage_icon05.png"></span>
						</div>
						<div class="txt-thum">
							<h4>자유 게시판</h4>
							<p>내가 작성한 자유게시판의 <br>게시물을 볼 수 있습니다.</p>
						</div>
					</a>
				</li>
			</ul>
		</c:if>
		
		<!-- 보호소 회원 일때 -->
		<c:if test="${member.memberLevel eq 1 }">
			<h3 class="mypage-tit">환영합니다! <b>${member.name }</b> 님</h3>
			<ul class="mypage-box clearfix">
				<li>
					<a href="/myOrderList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon01.png"></span>
						</div>
						<div class="txt-thum">
							<h4>후원</h4>
							<p>후원 상품 구매내역을 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/volunteerMyList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon02.png"></span>
						</div>
						<div class="txt-thum">
							<h4>봉사활동 공고</h4>
							<p>직접 등록한 봉사활동 공고 내역을 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/reservationCareMypage">
						<div class="img-thum">
							<span><img src="/img/mypage_icon06.png"></span>
						</div>
						<div class="txt-thum">
							<h4>방문예약 신청자 내역</h4>
							<p>보호소 방문을 신청한 신청자들을 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/myQnaList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon04.png"></span>
						</div>
						<div class="txt-thum">
							<h4>QnA</h4>
							<p>내가 작성한 QnA를 <br> 볼 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/siMyPreBoard?memberId=${sessionScope.member.id }">
						<div class="img-thum">
							<span><img src="/img/mypage_icon05.png"></span>
						</div>
						<div class="txt-thum">
							<h4>자유 게시판</h4>
							<p>내가 작성한 자유게시판의 <br>게시물을 볼 수 있습니다.</p>
						</div>
					</a>
				</li>
			</ul>
		</c:if>
		
		<!-- 관리자 일때 -->
		<c:if test="${member.memberLevel eq 2 }">
			<h3 class="mypage-tit">환영합니다! <b>관리자</b> 님</h3>
			<ul class="mypage-box clearfix">
				<li>
					<a href="/adminPage">
						<div class="img-thum">
							<span><img src="/img/join_type01.png"></span>
						</div>
						<div class="txt-thum">
							<h4>회원</h4>
							<p>전체 회원 관리를 <br>할 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/orderList">
						<div class="img-thum">
							<span><img src="/img/mypage_icon07.png"></span>
						</div>
						<div class="txt-thum">
							<h4>주문</h4>
							<p>후원 상품의 주문 관리를 <br>할 수 있습니다.</p>
						</div>
					</a>
				</li><br><br>
				<li>
					<a href="/siNotice">
						<div class="img-thum">
							<span><img src="/img/mypage_icon08.png"></span>
						</div>
						<div class="txt-thum">
							<h4>공지사항</h4>
							<p>공지사항 게시판 관리를 <br>할 수 있습니다.</p>
						</div>
					</a>
				</li>
				<li>
					<a href="/adminReservPage">
						<div class="img-thum">
							<span><img src="/img/mypage_icon06.png"></span>
						</div>
						<div class="txt-thum">
							<h4>방문예약 리스트</h4>
							<p>보호소 방문예약 전체 리스트를 <br>볼 수 있습니다.</p>
						</div>
					</a>
				</li>
			</ul>
		</c:if>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />