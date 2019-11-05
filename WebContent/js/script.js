
/* 후원물품 수량,금액 체크 */
function check(){
	var chkPrice = $('input[name=chkPrice]').val();
	var amount = $('input[name=amount]').val();
	var price = $('input[name=price]').val();
	if(amount==''){
		alert('수량을 입력해주세요');
		$('input[name=amount]').focus();
		return false;
	}
	if(price==''){
		alert('후원 금액을 입력해주세요');
		$('input[name=price]').focus();
		return false;
	}
	if(chkPrice*amount>price){
		alert('최소금액 이상 후원이 가능합니다.');
		$('input[name=price]').focus();
		return false;
	}
	return true;
}

/* daum 주소 api */
/*
 * http://postcode.map.daum.net/guide
 */
function getAddr(f){
    new daum.Postcode({
        oncomplete: function(data) {
        	f.post.value = data.zonecode;
            f.address.value = data.address;
        },
 		theme : {
 			bgColor: "#FFFFFF", //바탕 배경색
 		   searchBgColor: "#FE431E", //검색창 배경색
 		   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
 		   pageBgColor: "#FE431E", //페이지 배경색
 		   textColor: "#000000", //기본 글자색
 		   queryTextColor: "#FFFFFF", //검색창 글자색
 		   postcodeTextColor: "#FE431E", //우편번호 글자색
 		   emphTextColor: "#059DEB", //강조 글자색
 		   outlineColor: "#9F9F9F" //테두리
 		}
    }).open();
}



/* 숫자만 입력 */
$(function(){

	$('input[type=text].num').keyup(function(){
		var num = $(this).val();
		var check = /^[0-9]*$/;
		if(!check.test(num)){
			alert('숫자만 입력해 주세요');
			$(this).val('');
		}
	});

});