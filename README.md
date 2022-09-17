집-학원 마리아db 설정 다름 properties 변경 먼저!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


# MyBatis DB연결 세팅

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### MariaDB 사용자 생성 및 권한 주기
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

### 테이블 생성
```sql
drop table boards;
drop table users;

use ex;

CREATE TABLE boards(
                       id INT NOT NULL AUTO_INCREMENT,
                       title VARCHAR(100),
                       content TEXT,
                       usersId INT,
                       createdAt DATE,
                       PRIMARY KEY(id)
);

CREATE TABLE users(
                      id INT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(100),
                      password VARCHAR(100),
                      email VARCHAR(500),
                      createdAt DATE,
                      PRIMARY KEY(id)
);

# 회원 삭제 구현 조건 : 외래키 조건 빼고, 제약 조건 넣어서 (테이블 알터로 추가함)
alter table boards
    ADD CONSTRAINT boards_ibfk_1 FOREIGN KEY (usersId) REFERENCES users(id) on delete set null;

```

### 더미데이터 추가
```sql
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'ssar', '1234', 'ssar@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'cos', '1234', 'cos@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'hong', '1234', 'hong@nate.com', sysdate);
commit;
```