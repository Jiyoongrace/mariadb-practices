desc emaillist;
desc guestbook;
select * from guestbook;
insert into guestbook values(1, "배지윤", "1234", "hi", now());

-- (C)reate - insert
insert into emaillist values(null, '지', '윤', 'jiyoon@gmail.com');

-- (R)ead - select
select no, first_name, last_name, email from emaillist order by no desc;

-- (D)elete - delete
delete from emaillist where email = 'dooly@gmail.com';