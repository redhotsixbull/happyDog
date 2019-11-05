<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/order.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="product clearfix">
			<div class="product-top clearfix">
				<div class="product-img">
					<img src="/img/${prd.prdImg }">
				</div>
				<div class="product-info">
					<form action="/order" method="post" onsubmit="return check();">
						<input type="hidden" name="chkPrice" value="${prd.prdPrice}">
						<input type="hidden" name="prdCode" value="${prd.prdCode}">
						<div class="info-inner">
							<h2 class="title">${prd.prdName }</h2>
							<p class="detail">${prd.prdCon }</p>
							<p class="price">최소 후원금액 : <b><span id="limitPrice"><fmt:formatNumber value="${prd.prdPrice}" pattern="#,###" /> 원</span></b></p>
							<p class="option">수량<input type="text" name="amount" class="num" value="1"> 개</p>
							<p class="option">금액<input type="text" name="price" class="num"> 원</p>
							<p class="price type2"><span>총 수량  <b><span id="realAmount">0</span> 개</b></span> / &nbsp;&nbsp;<span>총 금액 <b><span id="realPrice">0</span> 원</b></span></p>
							<c:choose>
								<%-- 관리자 제외하고 후원 가능 --%>
								<c:when test="${empty sessionScope.member || sessionScope.member.memberLevel ne 2}">
									<p class="order-btn-box"><button type="submit" class="order-btn">후원하기</button></p>
								</c:when>
								<c:otherwise />
							</c:choose>
						</div>
					</form>
				</div>	
			</div>
			
			<div class="tab clearfix">
				<a href="javascript:$('#detail')[0].scrollIntoView();">상세설명</a>
				<a href="javascript:$('#qna')[0].scrollIntoView();">Q &amp; A</a>
			</div>
			<div id="detail" class="product-bottom">
				<p class="main-comm-tit">상세설명</p>
				<p class="se_textarea">
					<c:if test="${prd.prdCode eq 0 }">
						<span>&lt;해피투게독&nbsp;라이프백&gt; 에는&nbsp;<br></span>
						<span>사람과&nbsp;동물이&nbsp;손을&nbsp;맞잡은&nbsp;<br></span>
						<span><b>해피투게독의&nbsp;의미</b></span>
						<span>가&nbsp;담겨있습니다.<br></span>
						<span></span><br><span>해피투게독는&nbsp;굿즈를&nbsp;통해&nbsp;</span>
						<span>일상&nbsp;속에서&nbsp;<br></span><span><b>사지않고&nbsp;입양하는&nbsp;문화</b></span>
						<span>를&nbsp;알리고,<br></span><span>수익금으로&nbsp;유기동물&nbsp;입양을&nbsp;<br></span>
						<span>위한&nbsp;환경을&nbsp;만들어가고&nbsp;있습니다.</span><br><br><span>따듯한&nbsp;봄, 해피투게독&nbsp;라이프백과&nbsp;함께하세요.<br></span>
						<span><br></span><span></span>
						<img id="SEDOC-1553589953132-1560122654_image_0_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20190326_275/101057211_1553589820181rJceV_JPEG/1.jpg" width="60%" data-attachment-id="IEEltJT3O__mVZQFSTvL8gtVBkWw" alt="">
						<p class="se_textarea"><strong style=" color: rgb(0, 0, 0)">해피투게독 시그니처 라이프 백</strong><br>
						<strong style=" color: rgb(0, 0, 0)">[품질경영 및 공산품안전관리법에 의한 표시]</strong><br><span style="color: rgb(0, 0, 0);">1. 소재 :&nbsp;면(생지) 100% / 10수2합&nbsp;</span><br><span style="color: rgb(0, 0, 0);">2. 수입자명 : 해피투게독</span>
						<br><span style="color: rgb(0, 0, 0);">3. 제조국명 : 대한민국</span><br><span style="color: rgb(0, 0, 0);">4. 제조연월 : 2018년 4월</span>
						<br><span style="color: rgb(0, 0, 0);">5. 치수 : ONE SIZE (실측 사이즈 참고)</span><br><span style="color: rgb(0, 0, 0);">6. 취급상 주의사항&nbsp;</span>
						<br><span style="color: rgb(0, 0, 0);">- 표준 물세탁 권장</span>
					</c:if>
					<c:if test="${prd.prdCode eq 1 }">
						<img id="SEDOC-1515764157033--387637738_image_0_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20180112_154/101057211_1515760332939D1c0Q_JPEG/KakaoTalk_20170614_194722654.jpg" width="60%" data-attachment-id="IyW1K8HRDbY4eHrjnA3iwrONMckE" alt="">
						<br>
						<img id="SEDOC-1515764157033--387637738_image_1_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20180112_273/101057211_1515759412425uukrm_JPEG/%C5%EB%B0%E81.jpg" width="60%" data-attachment-id="Idlc2ePKD-k_2Kx85UkDtaA2CPoY" alt="">
						<br>
						<p class="se_textarea"><!-- SE3-TEXT { --><span><br></span><span><br></span><span><span style="color: rgb(0, 0, 0);">2016년, 8만9천 마리의 유기&nbsp;·&nbsp;유실동물들이 보호소로 구조됐고</span><br></span><span><span style="color: rgb(0, 0, 0);">그 중<span>&nbsp;</span></span><strong style=" color: rgb(0, 0, 0)">25%는 자연사, 20%는 안락사</strong><span style="color: rgb(0, 0, 0);"><span>&nbsp;</span>되었습니다.</span><br></span><span><span style="color: rgb(0, 0, 0);">매 년 지자체 보호소로 구조된</span><br></span><span><span style="color: rgb(0, 0, 0);"></span><strong>40%이상의 동물들은 보호소를</strong><br></span><span><strong>벗어나지 못하고 죽어가고 있습니다.</strong></span><span><br></span><span><br></span><span></span><!-- } SE3-TEXT --></p>
						<h5 class="se_textarea"><!-- SE3-TEXT { -->유기동물들을 구하는<br>'해피투게독 굿즈'를 소개합니다.<!-- } SE3-TEXT --></h5>
						<p class="se_textarea"><!-- SE3-TEXT { --><b>Q.</b><span><b>&nbsp;</b></span><strong>해피투게독 굿즈로 정말 유기동물들을 구할 수 있나요?</strong><br><b>A.</b> 네. 할 수 있습니다!<span>&nbsp;</span><span><strong>해피투게독 굿즈 펀딩 수익금은</strong><br></span><span><strong></strong></span>해피투게독를 통해 입양된<span>&nbsp;</span><span><strong>유기동물들의 건강검진비 지원</strong><br></span><span><strong></strong></span>그리고 해피투게독 봉사자들에 의해 구조된<span>&nbsp;</span><strong>유기동물들의 치료</strong><span>를 위해 사용됩니다.<br></span><span><br></span><span></span><!-- } SE3-TEXT --></p>
						<img id="SEDOC-1515764157033--387637738_image_4_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20180112_232/101057211_1515759715544cPvSF_JPEG/97be951e-681e-4184-bef4-532143df1ec4.jpeg" width="60%" data-attachment-id="IvyobHWbNXajxCgUqHLPz9x1jMcQ" alt="">
						<p class="se_textarea"><!-- SE3-TEXT { --><span><br></span><span><br></span><span><em style=" color: rgb(0, 0, 0)">"포인핸드 뱃지에는 유기동물들의 이야기가 담겨 있습니다."</em><br></span><span><br></span><span><br></span><span></span><!-- } SE3-TEXT --></p>
						<img id="SEDOC-1515764157033--387637738_image_5_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20180112_251/101057211_1515759830482DXt8H_JPEG/image_364160721515759808652.jpg" width="60%" data-attachment-id="I3sW641aJv2w4rA1TJxv6gBe7VDM" alt="">
						
						<img id="SEDOC-1515764157033--387637738_image_12_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20180112_13/101057211_1515760495183xyHVz_JPEG/image_881474541515760479589.jpg" width="60%"  data-attachment-id="IjGKVXPx4FX3vuEr-AYXVHO6kZfY" alt="">
					</c:if>
					<c:if test="${prd.prdCode eq 2 }">
						<p class="se_textarea"><!-- SE3-TEXT { --><span><strong></strong><br></span><span><strong>사람들의 무책임과 편견</strong></span><span>이 만들어낸<br></span><span>아프고 우울한 유기동물의 모습은<br></span><span>잠시뿐 입양된 유기동물들은</span><span>&nbsp;</span><strong>사랑과 보살핌</strong><span>을<br></span><span>받으며 하루가 다르게</span><span>&nbsp;</span><span><strong>건강해지고</strong><br></span><span><strong>사랑스러운 본래 모습</strong></span>을 되찾고 있습니다.<br><br><span>2018년 한해동안 발생한 유기동물의 숫자는<br></span><span>11만 마리,</span><span>&nbsp;</span><strong>그 중 43%</strong><span>는 여전히 사람들의<br></span><span><strong>무관심과 편견 속에 자연사하거나</strong><br></span><span><strong>안락사</strong></span><span>되어 잊혀져갔습니다.<br></span><span><br></span><span><strong>이제는 그 편견을 벗어던지고</strong><br></span><span><strong>동물들의 사랑스런 진짜 모습을</strong><br></span><span><strong>바라봐야할 때가 아닐까요?</strong><br></span><span><strong></strong><br></span><span><strong></strong></span><!-- } SE3-TEXT --></p>
						<img id="SEDOC-1545290878349-1094945445_image_0_img" class="se_mediaImage __se_img_el" src="https://tumblbug-psi.imgix.net/59e4705fa23235cff63a8ffd4eced13035809d9a/6555b552d38276ad6632b4de1af1187c1bf71b72/5fc3995fd96049b8f19b5bc462391c773266ff4f/c16213b3-1b40-4dee-a38c-28093a0ab8b3.jpg?ixlib=rb-1.1.0&amp;w=620&amp;auto=format%2C%20compress&amp;lossless=true&amp;ch=save-data&amp;s=00212386c72b1ab934a90bddc24a5328" width="60%"  data-attachment-id="" alt=""><p class="se_textarea"><strong style=" color: rgb(0, 0, 0)">해피투게독 시그니처 라이프 백</strong><br>
						<p class="se_textarea"><!-- SE3-TEXT { --><strong>품명</strong><span>&nbsp;</span>: 2019 해피투게독 유기견 사진전 기념 캘린더&nbsp;<br><strong>사이즈</strong><span>&nbsp;</span>: 150 * 240mm<br><strong>재질</strong><span>&nbsp;</span>: 랑데뷰 210g<br><strong>페이지</strong><span>&nbsp;</span>: 14장, 28페이지&nbsp;<br><strong>개별 봉투 포장</strong><!-- } SE3-TEXT --></p>
					</c:if>
				</p>
			</div>
			<p class="common-tbl-btn-group">
					<button class="btn-style1" onclick="location.href='/sponsorship'">목록으로</button>
				</p>
			<div id="qna" class="product-bottom">
				<p class="main-comm-tit">Q &amp; A</p>
				<table class="comm-tbl type2">
					<colgroup>
						<%--<col width="5%">--%>
						<col width="10%">
						<col width="">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<%--<th>No.</th>--%>
							<th>답변상태</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty qnaList }">
							<tr>
								<td colspan="5">
									<p class="none">해당 상품에 대해 등록된 게시물이 없습니다.</p>
								</td>
							</tr>
						</c:if>
						<c:forEach items="${qnaList}" var="qna">
							<tr>
							<%-- <td>${qna.boardRnum }</td>--%>
							<td>
								<c:if test="${qna.boardCount eq 0 }">
									<span class="volun-status ing">답변대기</span>
								</c:if>
								<c:if test="${qna.boardCount eq 1 }">
									<span class="volun-status end">답변완료</span>
								</c:if>
							</td>
							<td>
								<p class="volun-tit">
									<a href="/qnaView?boardNo=${qna.boardNo }">
										${qna.boardTitle }
										<c:if test="${qna.boardSecret eq 1 }"><img src="/img/lock.png"></c:if>
										<c:set var="today"><fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" /></c:set>
										<c:if test="${qna.boardDate eq today }"><img src="/img/new.png"></c:if>
									</a>
								</p>
							</td>
							<td>
								<%-- 이름이 1글자 --%>
								<c:if test="${fn:length(qna.boardName) eq 1 }">
									${fn:substring(qna.boardName,0,1) }
								</c:if>
								<%-- 이름이 2글자 --%>
								<c:if test="${fn:length(qna.boardName) eq 2 }">
									${fn:substring(qna.boardName,0,1) }*
								</c:if>
								<%-- 이름이 2글자 이상 --%>
								<c:if test="${fn:length(qna.boardName) > 2 }">
									${fn:substring(qna.boardName,0,2) }<c:forEach var="i" begin="1"  end="${fn:length(qna.boardName) - 2 }">*</c:forEach>
								</c:if>
								
								<c:if test="${not empty qna.boardId}">
									<br>(${qna.boardId })
								</c:if>
							</td>	
							<td>${qna.boardDate }</td>						
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<p class="common-tbl-btn-group right">
					<button class="btn-style2" onclick="location.href='/qnaList'">더보기</button>
					<c:choose>
						<c:when test="${empty sessionScope.member || sessionScope.member.memberLevel ne 2}">
							<button class="btn-style2" onclick="location.href='/regiQna?prdCode=${prd.prdCode}'">글쓰기</button>
						</c:when>
						<c:otherwise />
					</c:choose>
				</p>
			</div>
		</div>
	</div>
</section>
<script>
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />