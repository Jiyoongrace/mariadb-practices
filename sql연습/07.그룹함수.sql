-- 1) 집계 쿼리: select 절에 통계 함수(count, max, min, sum, avg, variance, stddev, ...)
select avg(salary), sum(salary)
	from salaries;

-- 2) select 절에 그룹 집계 함수(그룹 함수)가 있는 경우,
-- 어떤 컬럼도 select 절에 올 수 없다.
-- 아래처럼 쓰면 오류난다.
select emp_no, avg(salary)
	from salaries;

-- 3) query 순서
-- 1. from: 접근 테이블 확인
-- 2. where: 조건에 맞는 row를 선택(임시 테이블)
-- 3. 집계(결과 테이블)
-- 4. projection

-- 예제: salaries 테이블에서 사번이 10060번인 직원의 급여 평균과 총 합계를 출력
select avg(salary), sum(salary)
	from salaries
    where emp_no = 10060;

-- 예제: 위 예제 직원의 최저 임금을 받은 시기와 최대 임금을 받은 시기를 각각 출력해 보세요.
select min(salary), max(salary)
	from salaries
    group by emp_no
    having emp_no = 10060;

-- 예제: dept_emp 테이블에서 d008에 근무하는 인원 수는?
select count(*)
	from dept_emp
    where dept_no = 'd008';
