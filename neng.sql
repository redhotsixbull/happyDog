select * from member;

insert into member values('admin','1234','admin','관리자','010-1234-1234','','',null,'admin@test.com',2);

select * from sponsorship ;
select * from board;


ALTER TABLE sponsorship ADD(vbank_name varchar2(50)); 
ALTER TABLE sponsorship ADD(vbank_num varchar2(50));
ALTER TABLE sponsorship ADD(vbank_holder varchar2(50));
ALTER TABLE sponsorship ADD(vbank_date varchar2(50));

select rnum,no,id,name,phone,pay_method,amount,pay,status,deilvery_num,product_name,TO_CHAR(spon_date,'YYYY/MM/DD HH24:MI:SS') as time,memo,post,address,email,receive_name,receive_phone from (select rownum rnum,s.* from (select * from sponsorship where 1=1 and spon_date>'2019-05-29' order by spon_date desc) s ) ;