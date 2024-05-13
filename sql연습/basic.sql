-- 커서 위치한 쿼리 실행: Ctrl+Enter
-- 전체 쿼리 실행: Ctrl+Shift+Enter
-- 쿼리 주석: Ctrl+/

SELECT VERSION(), current_date, now() from dual;

-- 수학함수, 사칙연산도 된다.
select sin(pi()/4), 1 + 2 * 3 - 4 / 5 from dual;

-- 대소문자 구분이 없다.
sElecT VERSION(), current_DATE, NOW() From DUAL;

-- table 생성: DDL
create table pet(
	name varchar(100),
    owner varchar(50),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
describe pet;
desc pet;

-- table 삭제
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('토리', '배지윤', 'dog', 'f', '2017-04-05', NULL);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='토리이' where name='토리';

-- delete: DML(D)
delete from pet where name='토리이';

-- load data: mysql(CLI) 전용
load data local infile '/root/pet.txt' into table pet;