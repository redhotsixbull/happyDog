	$(function() {
		/* 주문자 정보 배송지 정보 동일하게 */
		$('input[type=checkbox]').click(function() {

			if($(this).is(":checked")==true){
				$('input[name=receiveName]').val($('input[name=name]').val());
				$('select[name=receivePhone1] option[value='+$('select[name=phone1] option:selected').val()+']').prop('selected',true);
				$('input[name=receivePhone2]').val($('input[name=phone2]').val());
				$('input[name=receivePhone3]').val($('input[name=phone3]').val());
			}else{
				$('input[name=receiveName]').val('');
				$('select[name=receivePhone1] option').prop('selected',false);
				$('input[name=receivePhone2]').val('');
				$('input[name=receivePhone3]').val('');
			}
			
		});

		$('.pay button').click(function() {
			
			event.preventDefault();
			
			var prdName = $('#prdName').html();
			var price = $('input[name=pay]').val();
			var name = $('input[name=name]').val();
			if(name==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=name]').focus();
				return false;
			}
			var phone = $('input[name=phone1]').val()+'-'+$('input[name=phone2]').val()+'-'+$('input[name=phone3]').val();
			
			if($('select[name=phone1]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('select[name=phone1]').focus();
				return false;
			}
			if($('input[name=phone2]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=phone2]').focus();
				return false;
			}
			if($('input[name=phone3]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=phone3]').focus();
				return false;
			}
			
			var email = $('input[name=email]').val();
			if($('input[name=email]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=email]').focus();
				return false;
			}
			
			if($('input[name=receiveName]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=receiveName]').focus();
				return false;
			}
			if($('select[name=receivePhone1]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('select[name=receivePhone1]').focus();
				return false;
			}
			if($('input[name=receivePhone2]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=receivePhone2]').focus();
				return false;
			}
			if($('input[name=receivePhone3]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=receivePhone3]').focus();
				return false;
			}
			
			var post = $('input[name=post]').val();
			if(post==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=post]').focus();
				return false;
			}
			
			var addr = $('input[name=address]').val()+' '+$('input[name=address2]').val();
			if($('input[name=address]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=address]').focus();
				return false;
			}
			if($('input[name=address2]').val()==''){
				alert('필수입력항목이 누락되었습니다');
				$('input[name=address2]').focus();
				return false;
			}
			
			var method = $('input[name=payMethod]:checked').val();

			var d = new Date();
			var date = d.getFullYear() + '' + (d.getMonth() + 1) + '' + d.getDate() + '' + d.getHours() + '' + d.getMinutes() + '' + d.getSeconds();
			$('input[name=orderNo]').val(date);
			IMP.init('imp20013985');
			IMP.request_pay({
				merchant_uid : prdName+"_"+ date,
				name : prdName,
				amount : price, 
				buyer_name : name,
				buyer_tel : phone,
				buyer_email : email,
				buyer_addr : addr,
				buyer_postcode : post,
				pay_method : method,
				escrow: true,
				vbank_due : d.getFullYear() + '' + (d.getMonth() + 1) + '' + (d.getDate()+3)
			}, function(response) {
				if (response.success) {
					
					if(method=='vbank'){
						$('input[name=vbankName]').val(response.vbank_name);
						$('input[name=vbankNum]').val(response.vbank_num);
						$('input[name=vbankHolder]').val(response.vbank_holder);
						$('input[name=vbankDate]').val(response.vbank_date);
					}
					var form = $('#orderForm')[0];
					var data = new FormData(form);
					$.ajax({
						url : "/orderIng",
						type : "post",
						data : data,
						enctype : "multipart/form-data",
						processData: false,
			            contentType: false,
						success : function(data){
							if(data=='fail'){
								console.log('결제실패ㅐ패패패패패ㅐ패패ㅐ주문실패');
							}else{
								location.href=data;
							}
							
						},
						error : function(){
							console.log("실패");
						}
					});
					/*
					var msg = '결제가 완료되었습니다.';
					var info1 = '고유 ID : ' + response.imp_uid;
					var info2 = '결제금액 : ' + response.paid_amount;
					var info3 = '카드 승인 번호 : ' + response.apply_num;
					$('#paymentResult').html(
							msg + "<br>" + info1 + "<br>" + info2
									+ "<br>" + info3 + "<br>");
					*/
				} else {
					alert('결제를 취소하셨습니다.');
					console.log("에러 : "+response.error_msg);
				}
			});	
			
		});
		
	});