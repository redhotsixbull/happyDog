<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
	<script type="text/javascript" src="/js/main.js"></script><!-- main.js -->
	<jsp:forward page="/main" />
	<%-- Wrap --%>
	<section id="wrap">
		<!-- 메인 비주얼 -->
		<section id="mainVisual">
			메인 비주얼 자리
		</section>
		<!-- 메인 컨텐츠 영역 -->
		<section id="mainContainer">
			<!-- 메인 상단 영역 -->
			<div id="mainTopBox" class="area">
				<!-- 입양 리스트 영역 -->
				<div id="mainAdoptBox">
					<h2 class="main-comm-tit">유기동물 입양하기</h2>
					<div class="main-adopt-box">
						<ul class="main-adopt-list"><!-- 입양 리스트 갯수가 최소 20개가 노출됩니다.(최신순) -->
							<li>
								<a href="">
									<div class="img-thum">
										<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
									</div>
								</a>
							</li>
							<li>
								<a href="">
									<div class="img-thum">
										<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
									</div>
								</a>
							</li>
							<li>
								<a href="">
									<div class="img-thum">
										<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
									</div>
								</a>
							</li>
							<li>
								<a href="">
									<div class="img-thum">
										<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
									</div>
								</a>
							</li>
							<li>
								<a href="">
									<div class="img-thum">
										<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
									</div>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- 입양 후기 영역 -->
				<div id="mainAdoptReviewBox">
					<h2 class="main-comm-tit">입양 후기</h2>
					<ul class="main-adopt-review-list clearfix"><!-- 입양후기는 최소/최대 8개가 노출됩니다. -->
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div class="img-thum">
									<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기 제목이 들어갑니다.입양 후기 제목이 들어갑니다.</h3>
									<p>입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.입양 후기 내용이 들어갑니다.</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 메인 하단 영역 -->
			<div id="mainBottomBox">
				<div id="mainBottomInner" class="area clearfix">
					<!-- 공지사항 & 봉사활동 -->
					<div id="mainBoardBox">
						<h2 class="main-comm-tit type2">공지사항 & 봉사활동</h2>
						<div class="main-board-tab-container tab-container">
							<ul class="main-board-tab-list tab-list clearfix">
								<li class="selected"><a href="#B-con01">공지사항</a></li>
								<li><a href="#B-con02">봉사활동</a></li>
							</ul>
							<div class="tab-content-wrapper">
								<div id="B-con01" class="tab-con">
									<ul class="main-board-list"><!-- 공지사항 게시물은 최소 5개, 최대 5개가 노출됩니다.(최신순) -->
										<li>
											<a href="" class="clearfix">
												<p>공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ공지사항 내용이 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
									</ul>
								</div>
								<div id="B-con02" class="tab-con">
									<ul class="main-board-list"><!-- 봉사활동  게시물은 최소 5개, 최대 5개가 노출됩니다.(최신순) -->
										<li>
											<a href="" class="clearfix">
												<p>봉사활동 제목이 들어갑니다아ㅏㅏㅏㅏㅏㅏ봉사활동 제목이 들어갑니다아 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>봉사활동 제목이 들어갑니다아ㅏㅏㅏㅏㅏㅏ봉사활동 제목이 들어갑니다아 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>봉사활동 제목이 들어갑니다아ㅏㅏㅏㅏㅏㅏ봉사활동 제목이 들어갑니다아 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>봉사활동 제목이 들어갑니다아ㅏㅏㅏㅏㅏㅏ봉사활동 제목이 들어갑니다아 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
										<li>
											<a href="" class="clearfix">
												<p>봉사활동 제목이 들어갑니다아ㅏㅏㅏㅏㅏㅏ봉사활동 제목이 들어갑니다아 들어갑니다아ㅏㅏㅏㅏㅏㅏ</p>
												<span>2019-05-23</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 후원 -->
					<div id="mainSponseBox">
						<h2 class="main-comm-tit type2">후원하기</h2>
						<div class="main-sponse-box">
							<ul class="main-sponse-list clearfix"><!-- 후원 게시물은 최소 2개가 노출됩니다.(최신순) -->
								<li>
									<a href="" class="clearfix">
										<div class="img-thum">
											<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
										</div>
										<div class="txt-thum">
											<h3>후원상품 명이 들어갑니다.</h3>
											<p>후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.</p>
										</div>
									</a>
								</li>
								<li>
									<a href="" class="clearfix">
										<div class="img-thum">
											<span style="background:url('/img/no_detail_img.gif') no-repeat center center; background-size:cover;"></span>
										</div>
										<div class="txt-thum">
											<h3>후원상품 명이 들어갑니다.</h3>
											<p>후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.후원상품 상세설명이 들어갑니다.</p>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />

