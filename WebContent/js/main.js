/* *******************************************************
 * filename : main.js
 * description : main 관련 JS
 * date : 2019-05-23
******************************************************** */

$(document).ready(function(){

	// 메인에서는 서브비주얼 없애기
	$("#subVisual").remove();
	
	/* *********************** 메인 비주얼 ************************ */
	// 임의의 영역을 만들어 스크롤바 크기 측정
	function getScrollBarWidth(){
		if($(document).height() > $(window).height()){
			$('body').append('<div id="fakescrollbar" style="width:50px;height:50px;overflow:hidden;position:absolute;top:-200px;left:-200px;"></div>');
			fakeScrollBar = $('#fakescrollbar');
			fakeScrollBar.append('<div style="height:100px;">&nbsp;</div>');
			var w1 = fakeScrollBar.find('div').innerWidth();
			fakeScrollBar.css('overflow-y', 'scroll');
			var w2 = $('#fakescrollbar').find('div').html('html is required to init new width.').innerWidth();
			fakeScrollBar.remove();
			return (w1-w2);
		}
		return 0;
	}

	// 메인 비주얼 높이값 설정
	$("#mainVisual.full-height").each(function  () {
		scrollWidth = getScrollBarWidth();
		var win_width = $(window).outerWidth() + getScrollBarWidth();

		var visual_height = $(window).height()	- 100;
		
		if ( win_width > 800 ) {
			$("#mainVisual").height(visual_height);
		}/*else {
			$("#mainVisual").css("height","auto");
		}*/
		
		$(window).resize(function  () {
			var win_width = $(window).outerWidth() + getScrollBarWidth();

			var visual_height = $(window).height()	- 100;

			if ( win_width > 800 ) {
				$("#mainVisual").height(visual_height);
			}/*else {
				$("#mainVisual").css("height","auto");
			}*/
		});
	});
	
	/* *********************** 메인 입양 유기견 리스트  ************************ */
	$('.main-adopt-list').slick({
		slidesToShow:4,
		slideToScroll:1,
		arrows:true,
		fade:false,
		dots:false,
		autoplay:true,
		spped:2000,
		infinite:true,
		autoplaySpeed:3000,
		pauseOnHover:true
	});
	
	/* *********************** 메인 후원 상품 리스트  ************************ */
	$('.main-sponse-list').slick({
		slideToShow:1,
		slideToScroll:1,
		arrows:false,
		dots:true,
		fade:false,
		autoplay:true,
		autoplay:true,
		spped:2000,
		infinite:true,
		autoplaySpeed:3000,
		pauseOnHover:true
	});
	
	/* *********************** 메인 공지사항&봉사활동 탭  ************************ */
	$(".tab-container").each(function  () {
		var $cmTabList = $(this).children(".tab-list");
		var $cmTabListli = $cmTabList.find("li");
		var $cmConWrapper = $(this).children(".tab-content-wrapper");
		var $cmContent = $cmConWrapper.children(".tab-con");
		
		
		// 탭 영역 숨기고 selected 클래스가 있는 영역만 보이게
		var $selectCon = $cmTabList.find("li.selected").find("a").attr("href");
		$cmContent.hide();
		$($selectCon).show();

		$cmTabListli.children("a").click(function  () {
			if ( !$(this).parent().hasClass("selected")) {
				var visibleCon = $(this).attr("href");
				$cmTabListli.removeClass("selected");
				$(this).parent("li").addClass("selected");
				$cmContent.hide();
				$(visibleCon).fadeIn();
			}
			return false;
		});
	});

});