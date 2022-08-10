-- member(회원정보 저장) 테이블 만들기.
 --자동증가 num auto_increment --
create table member(
	num int primary key auto_increment,
	id varchar(20) not null,
	pass varchar(20) not null,
	name varchar(30) not null,
	age int not null,
	email varchar(30) not null,
	phone varchar(30) not null,
	unique key(id)
)

--데이터 집어넣기--
select * from member;
insert into member(id,pass,name,age,email,phone) 
values('admin','admin','관리자',40,'pol963@naver.com','010-1111-1111');

--수정하기--
update member set age=45 , phone='010-1111-0000' where id='admin';

--삭제하기.--
delete from member where id='admin';

drop table member;


create table member1(
	num int primary key auto_increment,
	id varchar(20) not null,
	pass varchar(20) not null,
	name varchar(30) not null,
	age int not null,
	email varchar(30) not null,
	phone varchar(30) not null,
	filename varchar(100),
	unique key(id)
);

select * from member1;
