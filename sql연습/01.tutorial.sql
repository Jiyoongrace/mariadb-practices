-- 커서 위치한 쿼리 실행: Ctrl+Enter
-- 전체 쿼리 실행: Ctrl+Shift+Enter
-- 쿼리 주석: Ctrl+/

SELECT VERSION(), current_date, now() from dual;

-- 수학함수, 사칙연산도 된다.
select sin(pi()/4), 1 + 2 * 3 - 4 / 5 from dual;

-- 대소문자 구분이 없다.
select versioN(), current_dAtE, now() from DUAL;

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
insert into pet values('토리', '배지윤', 'dog', 'f', '2017-04-05', Null);

-- select: DML(R)
select * from pet;

-- update: DMl(U)
update pet set name='토리이' where name='토리';

-- delete: DML(D)
delete from pet where name='토리이';

-- load data: mysql(CLI) 전용
load data local infile '/root/pet.txt' into table pet;

-- select 연습
select name, species, birth
	from pet
	where birth >= '1998-01-01';

select name, species, gender
	from pet
    where species = 'dog'
		and gender = 'f';

select name, species, gender
	from pet
	where species = 'bird' or species = 'snake';

select name, birth
	from pet
    order by birth asc;

select name, birth
	from pet
    order by birth desc;

select name, birth, death
	from pet
    where death is not null;

select name
	from pet
    where name like '%fy';

select name
	from pet
    where name like '%w%';

select name
	from pet
    where name like '_____';

select *
	from pet
    where name like '_____';

select name
	from pet
    where name like 'b____';

-- 논리 오류(오라클은 바로 에러가 남.)
-- select name, count(*), max(birth)
-- 	from pet;

select count(*), max(birth)
	from pet;
