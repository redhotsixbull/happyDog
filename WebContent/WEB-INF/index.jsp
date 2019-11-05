<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

	<script type="text/javascript" src="/js/main.js"></script><!-- main.js -->
	<style>
		#subVisual{display:none;}
	</style>
	
	<%-- Wrap --%>
	<section id="wrap">
		<!-- 메인 비주얼 -->
		<section id="mainVisual" class="full-height">
			<div id="background-video" class="background-video">
				<div id="video_area">
					<video type="video/mp4" autoplay loop controls muted>
						<source src="/img/mainVideo05.mp4" type="video/mp4">
					</video>
				</div>
			</div>
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
								<a href="/dogInfo?careNm=%ED%95%B4%EB%82%A8%EA%B5%B0%EC%9C%A0%EA%B8%B0%EB%8F%99%EB%AC%BC%EB%B3%B4%ED%98%B8%EC%86%8C&careAddr=%EC%A0%84%EB%9D%BC%EB%82%A8%EB%8F%84+%ED%95%B4%EB%82%A8%EA%B5%B0+%ED%95%B4%EB%82%A8%EC%9D%8D+%ED%95%B4%EB%A6%AC+355-4&careTel=061-533-1141&kindCd=%5B%EA%B0%9C%5D+%ED%8F%AC%EB%A9%94%EB%9D%BC%EB%8B%88%EC%95%88&age=2018%28%EB%85%84%EC%83%9D%29&sexCd=M&specialMark=%EC%84%B1%EA%B2%A9%EC%9D%B4+%EC%98%A8%EC%88%9C%ED%95%A8&neuterYn=U&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F201906071006888.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog1.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=제주유기동물보호센터&careAddr=제주특별자치도+제주시+첨단동길+184-14+&careTel=064-710-4067&kindCd=%5B개%5D+라브라도+리트리버&age=2016%28년생%29&sexCd=M&specialMark=%28개체관리번호+2961%29+파랑%2F노랑+목줄+착용%2C+심장사상충&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F20190607100621.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog19.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=%ED%95%9C%EB%B9%9B%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90&careAddr=%EA%B2%BD%EA%B8%B0%EB%8F%84+%EC%88%98%EC%9B%90%EC%8B%9C+%EC%9E%A5%EC%95%88%EA%B5%AC+%EC%B2%9C%EC%B2%9C%EB%8F%99+527-3%EB%B2%88%EC%A7%80&careTel=031-252-2119&kindCd=%5B%EA%B0%9C%5D+%EB%B9%84%EC%88%91+%ED%94%84%EB%A6%AC%EC%A0%9C&age=2016%28%EB%85%84%EC%83%9D%29&sexCd=F&specialMark=%EB%B3%BC%ED%84%B0%EC%B9%98%EC%97%BC%EC%83%89&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F201906071406519.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog2.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=%EC%84%9C%EC%B4%88%EB%8F%99%EB%AC%BC%EC%82%AC%EB%9E%91%EC%84%BC%ED%84%B0&careAddr=%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%84%9C%EC%B4%88%EA%B5%AC+%EC%96%91%EC%9E%AC%EC%B2%9C%EB%A1%9C19%EA%B8%B8+22+&careTel=02-6956-7980&kindCd=%5B%EA%B0%9C%5D+%EB%AF%B9%EC%8A%A4%EA%B2%AC&age=2016%28%EB%85%84%EC%83%9D%29&sexCd=F&specialMark=%EB%85%B9%EC%83%89+%EB%AA%A9%EA%B1%B8%EC%9D%B4+%EC%B0%A9%EC%9A%A9%2C+%EA%BC%AC%EB%A6%AC+%EC%A4%91%EA%B0%84%EC%97%90+%EA%B2%80%EC%9D%80+%ED%84%B8&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F05%2F201906041506902.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog3.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=%ED%95%9C%EA%B5%AD%EB%8F%99%EB%AC%BC%EA%B5%AC%EC%A1%B0%EA%B4%80%EB%A6%AC%ED%98%91%ED%9A%8C&careAddr=%EA%B2%BD%EA%B8%B0%EB%8F%84+%EC%96%91%EC%A3%BC%EC%8B%9C+%EB%82%A8%EB%A9%B4+%EC%83%81%EC%88%98%EB%A6%AC+410-1&careTel=031-867-9119&kindCd=%5B%EA%B0%9C%5D+%ED%8F%AC%EB%A9%94%EB%9D%BC%EB%8B%88%EC%95%88&age=2015%28%EB%85%84%EC%83%9D%29&sexCd=M&specialMark=%EB%85%B8%EB%9E%80%EB%B0%94%ED%83%95%2B%EB%AC%B4%EB%8A%AC+%EB%AA%B8%EC%A4%84.%EB%88%88%EA%B3%B1%EC%95%BD%EA%B0%84.%EC%BD%94%EA%B2%80%EC%A0%95.%ED%94%BC%EB%B6%80%EC%A7%88%ED%99%98.%EC%86%8C%EC%8B%AC%ED%95%A8.%EC%96%8C%EC%A0%84%ED%95%A8.%EB%8B%A8%EB%AF%B8%EC%95%88%EB%90%A8.%EB%B0%B1%EB%82%B4%EC%9E%A5.%EC%96%91%EC%8A%AC%EA%B0%9C%EA%B3%A8.&neuterYn=Y&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F05%2F201905291805301.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog4.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=베스트동물병원&careAddr=인천광역시+강화군+강화읍+강화대로+217+&careTel=032-934-9340&kindCd=%5B개%5D+사모예드&age=2017%28년생%29&sexCd=F&specialMark=앞다리+부분+수로에+빠졌던+흔적&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F04%2F201904271504339.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog14.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=%EC%A7%84%ED%95%B4%EC%9C%A0%EA%B8%B0%EB%8F%99%EB%AC%BC%EB%B3%B4%ED%98%B8%EC%86%8C&careAddr=%EA%B2%BD%EC%83%81%EB%82%A8%EB%8F%84+%EC%B0%BD%EC%9B%90%EC%8B%9C+%EC%A7%84%ED%95%B4%EA%B5%AC+%EC%9B%85%EC%B2%9C%EB%A1%9C+218+&careTel=055-225-5701&kindCd=%5B%EA%B0%9C%5D+%EB%85%B8%EB%A5%B4%ED%8F%AC%ED%81%AC+%ED%85%8C%EB%A6%AC%EC%96%B4&age=2019%28%EB%85%84%EC%83%9D%29&sexCd=F&specialMark=%EC%B9%A9x+2~3%EA%B0%9C%EC%9B%94.+%EC%B6%94%EC%A0%95+%EB%82%98%EC%9D%B4%EC%97%90+%EB%B9%84%ED%95%B4+%EB%AA%B8%EC%A7%91%EC%9D%B4+%EC%9E%91%EC%9D%8C.+%EB%88%84%EC%9A%B4%EA%B7%80%2B%EC%A7%A7%EC%9D%80+%EC%9E%85%2B%EC%B4%88%EB%A1%B1%EC%B4%88%EB%A1%B1%ED%95%9C+%EB%88%88%EC%9D%B4+%EC%A7%B1+%EC%98%88%EC%81%A8.+%EB%84%88%EB%AC%B4+%EC%96%8C%EC%A0%84%ED%95%A8.&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2018%2F10%2F201902181702616.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog5.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							
							<li>
								<a href="/dogInfo?careNm=%28%EC%82%AC%29%ED%95%9C%EB%8F%99%EB%B3%B4&careAddr=%EA%B2%BD%EA%B8%B0%EB%8F%84+%EC%95%88%EC%82%B0%EC%8B%9C+%EC%83%81%EB%A1%9D%EA%B5%AC+%EC%B2%AD%EA%B3%A1%EA%B8%B8+50+&careTel=031-296-0124&kindCd=%5B%EA%B0%9C%5D+%EB%B9%A0%EC%82%90%EC%9A%A9&age=2017%28%EB%85%84%EC%83%9D%29&sexCd=F&specialMark=%EC%B9%A9%EC%9E%88%EC%9C%BC%EB%82%98+%EC%97%86%EB%8A%94%EC%97%B0%EB%9D%BD%EC%B2%98%2C%EC%9D%B4%EB%A6%84+%EB%B9%A0%EC%82%90&neuterYn=U&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F04%2F201905021005946.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog6.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=위더스+동물보호센터&careAddr=경기도+여주시+능서면+능서공원길+34+&careTel=031-882-4381&kindCd=%5B개%5D+시베리안+허스키&age=2018%28년생%29&sexCd=F&specialMark=온순&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F05%2F20190529210542.jpg">
									<div class="img-thum">
                                        <span style="background:url('/img/main_dog16.jpg') no-repeat center center; background-size:cover;"></span>
                                        <em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=한국동물구조관리협회&careAddr=경기도+양주시+남면+상수리+410-1&careTel=031-867-9119&kindCd=%5B개%5D+스피츠&age=2016%28년생%29&sexCd=F&specialMark=눈곱약간.+코검정.+털빠짐.+짖음.+낑낑거림.+사람좋아함.+활발함.+단미안됨.+털상태양호.&neuterYn=U&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F201906051906133.jpg">
									<div class="img-thum">
                                        <span style="background:url('/img/main_dog15.jpg') no-repeat center center; background-size:cover;"></span>
                                        <em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=%EC%9C%84%EB%8D%94%EC%8A%A4+%EB%8F%99%EB%AC%BC%EB%B3%B4%ED%98%B8%EC%84%BC%ED%84%B0&careAddr=%EA%B2%BD%EA%B8%B0%EB%8F%84+%EC%97%AC%EC%A3%BC%EC%8B%9C+%EB%8A%A5%EC%84%9C%EB%A9%B4+%EB%8A%A5%EC%84%9C%EA%B3%B5%EC%9B%90%EA%B8%B8+34+&careTel=031-882-4381&kindCd=%5B%EA%B0%9C%5D+%EC%9A%94%ED%81%AC%EC%85%94+%ED%85%8C%EB%A6%AC%EC%96%B4&age=2017%28%EB%85%84%EC%83%9D%29&sexCd=F&specialMark=%EB%A7%A4%EC%9A%B0%EC%98%A8%EC%88%9C&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F20190604190616.jpg">
									<div class="img-thum">
                                        <span style="background:url('/img/main_dog7.jpg') no-repeat center center; background-size:cover;"></span>
                                        <em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							
							<li>
								<a href="/dogInfo?careNm=%ED%99%94%EC%B2%9C%EA%B5%B0%EB%86%8D%EC%97%85%EA%B8%B0%EC%88%A0%EC%84%BC%ED%84%B0&careAddr=%EA%B0%95%EC%9B%90%EB%8F%84+%ED%99%94%EC%B2%9C%EA%B5%B0+%ED%99%94%EC%B2%9C%EC%9D%8D+%EC%82%B0%EC%B2%9C%EC%96%B4%EA%B8%B8+206+%ED%99%94%EC%B2%9C%EA%B5%B0%EB%86%8D%EC%97%85%EA%B8%B0%EC%88%A0%EC%84%BC%ED%84%B0&careTel=033-440-2941&kindCd=%5B%EA%B0%9C%5D+%EB%AF%B9%EC%8A%A4%EA%B2%AC&age=2016%28%EB%85%84%EC%83%9D%29&sexCd=M&specialMark=%ED%8A%B9%EC%9D%B4%EC%82%AC%ED%95%AD%EC%97%86%EC%9D%8C.&neuterYn=U&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F201906070906562.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog8.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=%EB%88%84%EB%A6%AC%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90&careAddr=%EB%B6%80%EC%82%B0%EA%B4%91%EC%97%AD%EC%8B%9C+%ED%95%B4%EC%9A%B4%EB%8C%80%EA%B5%AC+%EC%86%A1%EC%A0%952%EB%A1%9C13%EB%B2%88%EA%B8%B8+46+&careTel=051-701-7599&kindCd=%5B%EA%B0%9C%5D+%EB%AF%B9%EC%8A%A4%EA%B2%AC&age=2019%28%EB%85%84%EC%83%9D%29&sexCd=F&specialMark=3%EA%B0%9C%EC%9B%94%EB%A0%B9%2C+%EA%B8%B0%EB%A0%A5%EB%A7%A4%EC%9A%B0%EC%A0%80%ED%95%98&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F201906071306268.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog9.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=용인시+동물보호센터&careAddr=경기도+용인시+처인구+중부대로+1074-1+&careTel=031-324-3463&kindCd=%5B개%5D+골든+리트리버&age=2016%28년생%29&sexCd=M&specialMark=체인목줄착용%2C+훈련이+되어있는+아이&neuterYn=U&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F06%2F201906071006191.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog17.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=광주+동물보호소&careAddr=광주광역시+북구+본촌마을길+25+&careTel=062-571-2808&kindCd=%5B개%5D+믹스견&age=2017%28년생%29&sexCd=F&specialMark=19-0856+총총%2C+하네스착용%2C경계%2C꼬리부분은+황색&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F05%2F201906061006530.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog10.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=용인시+동물보호센터&careAddr=경기도+용인시+처인구+중부대로+1074-1+&careTel=031-324-3463&kindCd=%5B개%5D+도베르만&age=2016%28년생%29&sexCd=M&specialMark=검은+색+바탕에+쇠장식이+달린+목줄+착용&neuterYn=U&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F03%2F201903191503245.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog18.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=양평군유기동물보호소&careAddr=경기도+양평군+양평읍+농업기술센터길+59+&careTel=031-770-2337&kindCd=%5B개%5D+보더+콜리&age=2015%28년생%29&sexCd=M&specialMark=온순함.+&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F03%2F201903251603544.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog11.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=광주+동물보호소&careAddr=광주광역시+북구+본촌마을길+25+&careTel=062-571-2808&kindCd=%5B개%5D+불독&age=2017%28년생%29&sexCd=M&specialMark=19-0747+꿀탄%2C+갈색목줄착용&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F05%2F201905221605820.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog12.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=위더스+동물보호센터&careAddr=경기도+여주시+능서면+능서공원길+34+&careTel=031-882-4381&kindCd=%5B개%5D+비글&age=2016%28년생%29&sexCd=M&specialMark=비만%2C+온순&neuterYn=Y&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F03%2F201904021904300.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog13.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
							<li>
								<a href="/dogInfo?careNm=자인동물보호소&careAddr=경상북도+경산시+용성면+육동로+220-18+&careTel=010-3506-3661&kindCd=%5B개%5D+로트바일러&age=2018%28년생%29&sexCd=M&specialMark=건강하고+활기참&neuterYn=N&filename=http%3A%2F%2Fwww.animal.go.kr%2Ffiles%2Fshelter%2F2019%2F05%2F201906030806838.jpg">
									<div class="img-thum">
										<span style="background:url('/img/main_dog20.jpg') no-repeat center center; background-size:cover;"></span>
										<em class="best-prd-plus btn-effect01 effect"><img src="/img/paw_icon.png"></em>
									</div>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- 입양 후기 영역 -->
				<div id="mainAdoptReviewBox">
					<h2 class="main-comm-tit">입양 후기</h2>
					<ul class="main-adopt-review-list clearfix main"><!-- 입양후기는 최소/최대 8개가 노출됩니다. -->
						<c:forEach items="${adoptionBoardList }" var="list">
							<li>
								<a href="/siAdoptionBoardView?adoptionBoardNo=${list.adoptionBoardNo }">
									<div class="img-thum">
										<span style="background:url('/siUpload/adoptionBoard/${list.adoptionBoardFilename }') no-repeat center center; background-size:cover;"></span>
									</div>
									<div class="txt-thum">
										<h3>${list.adoptionBoardTitle }</h3>
										<p>${list.adoptionBoardContent }</p>
										<h5 class="clearfix"><span>${list.adoptionBoardName }</span><span>${list.adoptionBoardDate2 }</span></h5>
									</div>
								</a>
							</li>
						</c:forEach>
						<c:if test="${fn:length(adoptionBoardList) == 0 }">
							<li class="review-none">
								<span class="paw1"></span><span class="paw2"></span>
								<p>등록된 후기가 없습니다.</p>
							</li>
						</c:if>
					<%-- 	<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=64&adoptionBoardName=한국동물구조관리협회&adoptionBoardDate2=2019/06/07 09:39&adoptionBoardContent=후기 2번 글&adoptionBoardTitle=후기2">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/20180422_174743637_226.jpg') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>${adoptionBoardTitle }</a></h3>
									<p>adoptionBoardContent</p>
									<h5 class="clearfix"><span>adoptionBoardName</span><span>adoptionBoardDate2</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=65&adoptionBoardTilte=${vd.a.adoptionBoardTitle }">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3></h3>
									<p>adoptionBoardContent</p>
									<h5 class="clearfix"><span>adoptionBoardName</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=66">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기4</h3>
									<p>4</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=67">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기5</h3>
									<p>5</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=68">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기6</h3>
									<p>6</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=69">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기7</h3>
									<p>7</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li>
						<li>
							<a href="/siAdoptionBoardView?adoptionBoardNo=70">
								<div class="img-thum">
									<span style="background:url('/siUpload/adoptionBoard/') no-repeat center center; background-size:cover;"></span>
								</div>
								<div class="txt-thum">
									<h3>입양 후기8</h3>
									<p>8</p>
									<h5 class="clearfix"><span>작성자</span><span>2019-05-23</span></h5>
								</div>
							</a>
						</li> --%>
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
										<c:forEach items="${noticeList }" var="list">
											<li>
												<a href="/siNoticeView?noticeNo=${list.noticeNo }" class="clearfix">
													<p>${list.noticeTitle }</p>
													<span>${list.noticeDate2 }</span>
												</a>
											</li>
										</c:forEach>
										<c:if test="${fn:length(noticeList) == 0 }">
											<li class="none">
												<p class="none" style="padding-top:90px;">등록된 공지사항이 없습니다.</p>
											</li>
										</c:if>
	<!-- 									<li>
											<a href="/siNoticeView?noticeNo=21" class="clearfix">
												<p>공지사항2</p>
												<span>2019/06/05 12:12</span>
											</a>
										</li>
										<li>
											<a href="/siNoticeView?noticeNo=61" class="clearfix">
												<p>공지사항3</p>
												<span>2019/06/07 09:33</span>
											</a>
										</li>
										<li>
											<a href="/siNoticeView?noticeNo=62" class="clearfix">
												<p>공지사항4</p>
												<span>2019/06/07 09:33</span>
											</a>
										</li>
										<li>
											<a href="/siNoticeView?noticeNo=63" class="clearfix">
												<p>공지사항5</p>
												<span>2019/06/07 09:33</span>
											</a>
										</li> -->
									</ul>
								</div>
								<div id="B-con02" class="tab-con">
									<ul class="main-board-list"><!-- 봉사활동  게시물은 최소 5개, 최대 5개가 노출됩니다.(최신순) -->
										<c:forEach items="${volunList }" var="volunList">
											<li>
												<a href="/voluntaryView?no=${volunList.no }" class="clearfix">
													<p>${volunList.title }</p>
													<span>${volunList.enrollDate }</span>
												</a>
											</li>
										</c:forEach>
										<c:if test="${fn:length(volunList) == 0 }">
											<li class="none">
												<p class="none" style="padding-top:90px;">등록된 봉사활동 신청 공고가 없습니다.</p>
											</li>
										</c:if>
										<!-- <li>
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
										</li> -->
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
								
								<c:forEach items="${prdList }" var="prd">
								
									<li>
										<a href="/viewProduct?code=${prd.prdCode }" class="clearfix">
											<div class="img-thum">
												<span style="background:url('/img/${prd.prdImg}') no-repeat center center; background-size:cover;"></span>
											</div>
											<div class="txt-thum">
												<h3>${prd.prdName}</h3>
												<p>${prd.prdCon}</p>
											</div>
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>
	
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />

