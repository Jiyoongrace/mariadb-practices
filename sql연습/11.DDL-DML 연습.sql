-- DDL/DML 연습

-- create
drop table member;
create table member(
	no int not null auto_increment,
    email varchar(200) not null default '',
    password varchar(64) not null,
    name varchar(50) not null,
    department varchar(100),
    primary key(no)
);
desc member;

alter table member add column juminbunho char(13) not null;
desc member;

alter table member drop juminbunho;
desc member;

alter table member add column juminbunho char(13) not null after email;
desc member;

alter table member change column department dept varchar(100) not null;
desc member;

alter table member add column self_intro text;
desc member;

alter table member drop juminbunho;
desc member;

-- insert
insert
	into member
  values (null, 'giyun3632@naver.com', password('1234'), '배지윤', '개발팀', null);
select * from member;

insert
	into member(no, email, name, dept, password)
  values (null, 'giyun36320@gmail.com', '배지윤2', '개발팀2', password('1234'));
select * from member;

-- update
update member
   set email='giyun3632@sookmyung.ac.kr', password=password('4321')
 where no = 2;
select * from member;

-- delete
delete
  from member
 where no = 2;
select * from member;

-- transaction (ACID)
-- 업무 단위이므로 service에 트랜잭션 메소드 위치함
-- cache/buffer, log 트랜잭션 과정 구조도
select no, email from member;

select @@autocommit; -- 1
insert
  into member(no, email, name, dept, password)
values (null, 'giyun3632@sookmyung.ac.kr', '배지윤2', '개발팀2', password('1234'));
select no, email from member;

-- tx begin
set autocommit = 0;
select @@autocommit; -- 0
insert
  into member(no, email, name, dept, password)
values (null, 'giyun36320@gmail.com', '배지윤3', '개발팀3', password('1234'));
select no, email from member;

-- tx end
commit;
select no, email from member;
delete from member where no = 3;