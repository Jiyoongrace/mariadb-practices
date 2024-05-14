--
-- 문자열 함수
--

-- upper, ucase
select upper('seoul'), ucase('Seoul') from dual;
select upper(first_name) from employees;

-- lower, lcase
select lower('SEOUL'), lcase('Seoul') from dual;
select lower(first_name) from employees;

-- substring(문자열, index, length)
-- index는 1부터 시작
select substring('Hello World', 3, 2);

-- 예제: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름',
	hire_date as '입사일'
    from employees
    where substring(hire_date, 1, 4)='1989';

-- lpad(오른쪽 정렬), rpad(왼쪽 정렬)
select lpad('hi', 5, '?'), lpad('joe', 7, '*') from dual;
select rpad('hi', 5, '?'), rpad('joe', 7, '*') from dual;
select lpad('1234', 10, '-'), rpad('1234', 10, '-') from dual;

-- 예제: 직원들의 월급을 오른쪽 정렬(빈 공간은 *)
select lpad(salary, 10, '*') from salaries;

-- trim, ltrim, rtrim
select
		concat('---', ltrim('   hello   '), '---'),
		concat('---', rtrim('   hello   '), '---'),
		concat('---', trim('   hello   '), '---'),
		concat('---', trim(both 'x' from 'xxxhelloxxx'), '---'),
		concat('---', trim(leading 'x' from 'xxxhelloxxx'), '---'),
		concat('---', trim(trailing 'x' from 'xxxhelloxxx'), '---')
	from dual;

-- length
select length("Hello World") from dual; 