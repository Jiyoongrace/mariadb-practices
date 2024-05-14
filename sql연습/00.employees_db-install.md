## employees_db 설치
1. employees db 생성
```sh
# mysql -p
MariaDB [(none)]> create database employees;
MariaDB [(none)]> create user 'hr'@'192.168.%' idenitified by 'hr';
MariaDB [(none)]> grant all privileges on employees.* to 'hr'@'192.168.%';
MariaDB [(none)]> flush privileges;
# exit
# exit
```

2. upload employees_db.zip
```sh
c:\ sftp jiyun@192.168.*.***
sftp:/home/jiyun> put C:\poscodx2024\자료\employees_db.zip
exit
open
```

3. restore employees db
```sh
# su -
# mv /home/jiyun/employees_db.zip .
# unzip employees_db.zip
# cd employees_db
# mysql -u root -D employees -p < employees.sql
```
    