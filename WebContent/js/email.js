function send_email(){
    var email = $("#email").val();
    var level = $("#level").val();
    if(email==""){
        alert("이메일를 입력하세요");
        return;
    }
    var url = "/mailSend";
    var title ="send_email";
    var status ="left=300px,top=100px,width=500px,height=300px,menubar=no,status=no,scrollbars=yes";
    var popup = window.open("",title,status);    
    send_emailFrm.send_email.value=email;
    send_emailFrm.send_level.value=level;
    send_emailFrm.target=title;//popup 창과 form태그를 연결하는 속성
    send_emailFrm.action=url;
    send_emailFrm.method="post";
    send_emailFrm.submit();
}