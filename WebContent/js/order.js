$(function(){
	
	/* 검색박스의 주문상태 값 세팅 */
	var status = $('select[name=status]').data('val');
	$('select[name=status]').children('option').each(function(){
		if(status == $(this).val()){
			$(this).prop("selected",true);
		}
	});
	
	/* 검색박스의 검색타입 값 세팅 */
	var searchType = $('select[name=searchType]').data('val');
	$('select[name=searchType]').children('option').each(function(){
		if(searchType == $(this).val()){
			$(this).prop("selected",true);
		}
	});
	
	/* 검색박스의 결제수단 값 세팅 */
	var method = $('input[name=method]').val();
	$('input[type=radio]').each(function(){
		if(method == $(this).val()){
			$(this).prop("checked",true);
		}
	});
	
	
	/* 각 주문의 주문상태 selectbox 세팅 */
	$('.status').each(function(){
		var st = $(this).data('status');
		$(this).children('option').each(function(){
			if(st == $(this).val()){
				$(this).prop("selected",true);
			}
		});
	});
	
	/* 각 주문의 주문상태 selectbox변경하면 update */
	$('#orderListBox .status').change(function(){
		var req
		var no = $(this).data('no');
		var status = $(this).val();
		$.ajax({
			url : 'updateStatus',
			type : 'post',
			data : {no:no,status:status},
			success : function(data){
				if(data == 'fail'){
					alert(data);
				}else{
					list('');
					//alert('변경 완료');
				}
				
			},
			error : function(){
				console.log("error");
			}
		});
		
	});
	
	/* 상품 상세페이지 수량,가격 표시 */
	var chkPrice = $('input[name=chkPrice]').val();
	
	$('#realAmount').text(addComma($('input[name=amount].num').val()));
	
	$('input[name=amount].num').keyup(function(){
		
		$('#limitPrice').text(addComma($(this).val()*chkPrice)+"원 ( "+addComma($(this).val()*1)+" X "+addComma(chkPrice)+" )");
		$('#realAmount').text(addComma($(this).val()*1));
	});
	
	$('input[name=price].num').keyup(function(){
		$('#realPrice').text(addComma($(this).val()*1));
	});
	
	
	
	
});

/* 페이지 이동 */
function list(reqPage){
	
	$('input[name=reqPage]').val(reqPage);
	search.submit();
}

/* 숫자 형식 */
function addComma(num) {
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
	return num.toString().replace(regexp, ',');
}

/* 검색 기간 설정 */
function setDay(period){
	var end = new Date();//오늘날짜
	if(period==0){//1주일
		var start = new Date();
		start.setDate(start.getDate() - 7);
		
	}else if(period==1||period==3||period==6){//1,3,6 개월
		var start = new Date();
		start.setMonth(start.getMonth() - period);
		
	}	
	$('input[name=startDay]').val(start.format('yyyy-MM-dd'));
	$('input[name=endDay]').val(end.format('yyyy-MM-dd'));

}


/* 날짜 포맷 변환 함수 */
Date.prototype.format = function(f) {
	if (!this.valueOf()) return " ";

	var weekKorName = [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ];
	var weekKorShortName = [ "일", "월", "화", "수", "목", "금", "토" ];
	var weekEngName = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday" ];
	var weekEngShortName = [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ];
	var d = this;
	return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi,
			function($1) {
				switch ($1) {
				case "yyyy":
					return d.getFullYear(); // 년 (4자리)
				case "yy":
					return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
				case "MM":
					return (d.getMonth() + 1).zf(2); // 월 (2자리)
				case "dd":
					return d.getDate().zf(2); // 일 (2자리)
				case "KS":
					return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
				case "KL":
					return weekKorName[d.getDay()]; // 요일 (긴 한글)
				case "ES":
					return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
				case "EL":
					return weekEngName[d.getDay()]; // 요일 (긴 영어)
				case "HH":
					return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
				case "hh":
					return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간
																		// (12시간
																		// 기준,
																		// 2자리)

				case "mm":
					return d.getMinutes().zf(2); // 분 (2자리)
				case "ss":
					return d.getSeconds().zf(2); // 초 (2자리)
				case "a/p":
					return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
				default:
					return $1;
				}
			});
};

String.prototype.string = function(len) {
	var s = '', i = 0;
	while (i++ < len) {
		s += this;
	}
	return s;
};

String.prototype.zf = function(len) {
	return "0".string(len - this.length) + this;
};

Number.prototype.zf = function(len) {
	return this.toString().zf(len);
};