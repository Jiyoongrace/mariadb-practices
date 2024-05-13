--
-- select 연습
--
-- DB index는 1부터 시작.


-- 예제1: departments 테이블의 모든 데이터를 출력.
select *
	from departments;

-- 프로젝션(projection)
-- 예제2: employees 테이블에서 직원의 이름, 성별, 입사일을 출력
-- mysql workbench에서 자동으로 limit을 걸어준다.
select first_name, gender, hire_date
	from employees;
--  from employees limit 0, 5;

-- as(alias, 생략 가능)
-- 예제3: employees 테이블에서 직원의 이름, 성별, 입사일을 출력
select first_name as 이름,
	gender as 성별,
	hire_date as 입사일
    from employees;

-- 문자열 결합 함수 concat 사용
-- 예제4: employees 테이블에서 직원의 전체 이름, 성별, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름',
	gender as '성별',
    hire_date as '입사일'
    from employees;


-- distinct
-- 예제5: titles 테이블에서 모든 직급의 이름 출력
select title
	from titles;

-- 예제5-1: titles 테이블에서 직급은 어떤 것들이 있는지 직급 이름을 한 번씩만 출력
select distinct title
	from titles;

select distinct(title)
	from titles;

--
-- where
--

-- 비교연산자
-- 예제1: employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름',
	gender as '성별',
    hire_date as '입사일'
    from employees
    where hire_date < '1991-01-01';

-- 논리연산자
-- 예제2: employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름',
	hire_date as '입사일'
    from employees
    where gender='f'
    and hire_date < '1989-01-01';

-- SQL 비교 연산자
-- in 연산자
-- 예제3: dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의 사번, 부서번호 출력
select emp_no, dept_no
	from dept_emp
    where dept_no in('d005', 'd009');

-- Like 검색
-- 예제4: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름',
	hire_date as '입사일'
    from employees
    where hire_date Like '1989-%';

-- where '1989-01-01' <= hire_date
--  and hire_date <= '1989-12-31';
-- where hire_date between '1989-01-01' and '1989-12-31';

--
-- order by
--
-- order by c1 asc, c2 desc
-- asc는 생략 가능

-- 예제1: employees 테이블에서 직원의 전체 이름, 성별, 입사일을 입사일 순으로 출력
select concat(first_name, ' ', last_name) as '이름',
	gender as '성별',
    hire_date as '입사일'
    from employees
    order by hire_date;

-- 예제2: salaries 테이블에서 2001년 월급을 가장 높은 순으로 사번, 월급 순으로 출력
select emp_no, salary
	from salaries
    where from_date like '2001-%'
    or to_date like '2001-%'
    order by salary desc;

-- 예제3: 남자 직원의 이름, 성별, 입사일을 입사일 순(선임)으로 출력
select concat(first_name, ' ', last_name) as '이름',
	gender as '성별',
    hire_date as '입사일'
    from employees
    where gender = 'm'
    order by hire_date asc;

-- 예제4: 직원의 사번, 월급을 사번(asc), 월급(desc) 순으로 출력
select emp_no, salary
	from salaries
    order by emp_no asc, salary desc;


--
--
-- 예제: employees 테이블에서 last_name이 acton인 사원의 이름, 성별, 입사일 출력
select upper(concat(first_name, ' ', last_name)) as '이름',
	gender as '성별',
    hire_date as '입사일'
	from employees
    where last_name = 'acton';

-- 예제: salaries 테이블에서 2001년 급여가 70000불 이하의 직원만 사번, 급여로 출력하되 급여는 10자리로 부족한 자리수는 *로 표시
select emp_no as '사번',
	lpad(cast(salary as char), 10, '*') as '급여'
	from salaries
    where from_date like '2001-%'
    and salary <= 70000;