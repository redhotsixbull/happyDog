<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" type="text/css" href="/css/content.css">
<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">	<!-- 셀렉트박스 디자인하기 -->

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->
<style>
	.search-select select{padding-left:3px;}
</style>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">입양하기</h2>
		<form action="/dogAdopList" method="post">
			<div class="search-select">
				<select name="city" id="city" data-city="${cityCode}">
					<option value="">==== 도시 선택 ====</option>
				</select>
				<select name="gun" id="gun" data-gun="${gun}">
					<option value="">==== 지역구 선택 ====</option>
				</select>
				<select name="dogsize" id="dogsize" data-size="${dogsize}">
					<option value="">==== 크기 분류 ====</option>
					<option value="소">소형견</option>
					<option value="중">중형견</option>
					<option value="대">대형견</option>
					<option value="기타">기타</option>
				</select>
				<select name="kindCd" id="kindCd" data-kind="${kindCd}">
					<option value="">==== 품종 선택 ====</option>
				</select>
				<select name="neuterYn" id="neuterYn" data-neuter="${neuterYn}">
					<option value="">==== 중성화 여부 ====</option>
					<option value="Y">완료</option>
					<option value="N">미완료</option>
					<option value="U">미상</option>
				</select>
				<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
			</div>
		</form>
		<ul class="main-adopt-review-list clearfix">
			<!-- 가져온 리스트가 없을 경우 문구 띄워주기 -->
			<c:if test="${empty sdpd.list }">
				<p id="emptyNotice">검색결과가 없습니다.</p>
			</c:if>
			<c:forEach items="${sdpd.list }" var="m" varStatus="i">
				<li>
					<form action="/dogInfo" method="post" name="form_${i.count}">
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
						<input type="hidden" name="city">
						<input type="hidden" name="gun">
						<input type="hidden" name="dogsize">
						<input type="hidden" name="dogkind">
						<input type="hidden" name="neuter">
						<input type="hidden" name="reqPage" value="${reqPage}">
						<a onclick="javascript:form_${i.count}.submit();" class="send-dogInfo">		<!-- 보호소명 보내기 -->
							<div class="img-thum">
								<span style="background:url('${m.filename }') no-repeat center center; background-size:cover;"></span>
							</div>
							<div class="txt-thum">
								<span style="line-height:18px;font-size:14px;">
									보호소명  : ${m.careNm }<br>
								 	품종 : ${fn:split(m.kindCd,']')[1]}<br>
									성별 : ${m.sex}
								</span>
							</div>
						</a>
					</form>
				</li>
			</c:forEach>
		</ul>
		
		<!-- paging -->
		<div class="paging">${sdpd.pageNavi}</div>
	</div>
</section>

<script>
	$(document).ready(function(){
		/* 페이지 로딩되면 도시 가져오기 */
		getCity();	
		
		/* 도시코드 도시이름 가져오는 코드 */
		function getCity(){
			$.ajax({
				url:"/getCityCode",
				success : function(data){
					var $select = $("#city");
					$select.find("option").remove();
					$select.append("<option value=''>==== 도시 선택 ====</option>");
					
					for(var i=0;i<data.length;i++){
						var cityName = data[i].cityName;
						var cityCode = data[i].cityCode;
						$select.append("<option value='"+cityCode+"'>"+cityName+"</option>");
					}
					/* 검색후 도시 고정 */
					var city = $('select[name=city]').data('city');
					$('select[name=city]').children('option').each(function(){
						if(city == $(this).val()){
							$(this).prop("selected",true);
						}
					});
					/* 검색후 도시에 따른 군고정과 군 리스트 가져오기 */
					getGun();
				},
				error : function(){
					console.log("못가져왔다");
				}
			});
		}
		/* 도시 변경하면 지역구 리스트 가져오기 */
		$("#city").change(function(){
			getGun();
		});
	
		/* 도시에 따른 지역구 코드, 지역구명 가져오기 */
		function getGun(){
			var cityCode = $("#city").val();
			console.log(cityCode);
			$.ajax({
				url:"/getAreaCode",
				data : {cityCode:cityCode},
				success : function(data){
					var $select = $("#gun");
					$select.find("option").remove();
					$select.append("<option value=''>==== 지역구 선택 ====</option>");
						
					for(var i=0;i<data.length;i++){
						var gunName = data[i].districtName;
						var gunCode = data[i].district;;
						$select.append("<option value='"+gunCode+"'>"+gunName+"</option>");
					}
					/* 검색후 지역구 고정 */
					var city = $('select[name=gun]').data('gun');
					$('select[name=gun]').children('option').each(function(){
						if(city == $(this).val()){
							$(this).prop("selected",true);
						}
					});
				},
				error : function(){
					console.log("못가져옴");
				}
			});
		}
		/* 검색후 강아지크기 고정 */
		var size = $('select[name=dogsize]').data('size');
		$('select[name=dogsize]').children('option').each(function(){
			if(size == $(this).val()){
				$(this).prop("selected",true);
			}
		});
		/* 강아지 크기 변경하면 품종 리스트 가져오기 */
		$("#dogsize").change(function(){
			getKind();
		});
		getKind();
		//강아지 크기 선택시 해당하는 품종 가져오기
		function getKind(){
			var dogsize = $("#dogsize").val();
			$.ajax({
				url: "/getKind",
				data: {dogsize:dogsize},
				success : function(data){
					var $select = $("#kindCd");
					$select.find("option").remove();
					$select.append("<option value=''>==== 품종 선택 ====</option>");
					for(var i=0;i<data.length;i++){
						var kind = data[i].kind;
						var code = data[i].code;;
						$select.append("<option value='"+code+"'>"+kind+"</option>");
					}
					/* 검색후 품종 고정 */
					var kind = $('select[name=kindCd]').data('kind');
					$('select[name=kindCd]').children('option').each(function(){
						if(kind == $(this).val()){
							$(this).prop("selected",true);
						}
					});
				},
				error : function(){
					console.log("데이터 가져오지 못함");
				}
			});
		}
		/* 검색후 중성화여부 고정 */
		var neuter = $('select[name=neuterYn]').data('neuter');
		$('select[name=neuterYn]').children('option').each(function(){
			if(neuter == $(this).val()){
				$(this).prop("selected",true);
			}
		});
		
		/* 검색내용 input value에 넣어서 보내주기 */
		$('input[name=city]').val($('select[name=city]').data('city'));
		$('input[name=gun]').val($('select[name=gun]').data('gun'));
		$('input[name=dogsize]').val($('select[name=dogsize]').data('size'));
		$('input[name=dogkind]').val($('select[name=kindCd]').data('kind'));
		$('input[name=neuter]').val($('select[name=neuterYn]').data('neuter'));
		
	});
</script>
	
<%--footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />