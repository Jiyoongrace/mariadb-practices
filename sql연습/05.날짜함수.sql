--
-- 날짜 함수
--

-- curdate(), current_date: 오늘 날짜 반환
select curdate(), current_date from dual;

-- curtime(), current_time: 현재 시각 반환
select curtime(), current_time from dual;

-- now() vs sysdate(): 현 시각 반환
select now(), sysdate() from dual;
select now(), sleep(2), now() from dual;
select now(), sleep(2), sysdate() from dual;

-- current_timestamp(): 현 시각 반환
select current_timestamp() from dual;

-- date_format(date, format)
-- default format: %Y-%m-%d %h:%i:%s
select date_format(now(), '%Y년 %m월 %d일 %h시 %i분 %s초') from dual;
select date_format(now(), '%d %b \'%y %h:%i:%s') from dual;

-- period_diff(p1, p2): p1과 p2의 차이 개월 반환
-- 예제: 각 직원들에 대해 직원 이름과 근무 개월 수를 출력
select concat(first_name, ' ', last_name) as name,
	hire_date,
	period_diff(date_format(curdate(), '%Y%m'),
				date_format(hire_date, '%Y%m')) as '근무 개월 수'
	from employees;

-- date_add(=adddate), date_sub(=subdate)
-- 날짜를 date 타입의 컬럼이나 값에 type(year, month, day)의 표현식으로 더하거나 뺄 수 있다.
-- date_add(date, interval expr type)
-- date에 type 형식으로 지정한 expr 값을 더하거나 뺀다.
-- 예제: 각 사원의 근속 연수가 5년이 되는 날에 휴가를 보내준다면 각 사원의 5년 근속 휴가 날짜는?
select first_name,
	   hire_date,
       date_add(hire_date, interval 5 year)
	from employees;