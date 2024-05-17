desc emaillist;

-- (C)reate - insert
insert into emaillist values(null, '마', '이콜', 'mychol@gmail.com');

-- (R)ead - select
select no, first_name, last_name, email from emaillist order by no desc;

-- (D)elete - delete
delete from emaillist where email = 'dooly@gmail.com';