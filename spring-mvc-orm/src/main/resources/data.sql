CREATE TABLE students
(
    id       integer PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(30)  NOT NULL,
    surname  VARCHAR(30)  NOT NULL,
    username VARCHAR(50)  NOT NULL,
    phone    VARCHAR(13)  NOT NULL,
    address  VARCHAR(255) NOT NULL,
    email    VARCHAR(30)  NOT NULL,
    birthday DATE         NOT NULL,
    sector   VARCHAR(20)  NOT NULL
);

INSERT INTO students (name, surname, username, phone, address, email, birthday, sector)
VALUES ('Zaur', 'Babayev', 'zaur','+994702262052', 'Xirdalan', 'zaurik87@gmail.com', '1987-03-01', 'EDUCATION'),
       ('Vusal', 'Nagiyev', 'vusal','+994702260000',  'Baki', 'vusal@gmail.com', '1990-04-07', 'HEALTHCARE'),
       ('Yusif', 'Emirov', 'yusif','+994909090900', 'Gence', 'yusif@mail.ru', '2000-09-21', 'FINANCE');


create table users
(
    username varchar(255) not null
        primary key,
    password varchar(255) null,
    enabled  int(1)   not null
);

insert into java_course_db3.users (username, password, enabled)
values ('kenan', '{noop}123', 1),
       ('vusal', '{noop}123', 1),
       ('zaur', '{noop}123', 1);


create table authorities
(
    id        int auto_increment
        primary key,
    username  varchar(255) null,
    authority varchar(255) null
);

insert into authorities(username, authority)
    values
    ('zaur', 'ROLE_STUDENT_ADD')
    ,
    ('zaur', 'ROLE_STUDENT_VIEW')
    ,
    ('zaur', 'ROLE_STUDENT_UPDATE')
    ,
    ('zaur', 'ROLE_STUDENT_DELETE')
    ,
    ('vusal', 'ROLE_STUDENT_ADD')
    ,
    ('vusal', 'ROLE_STUDENT_VIEW')
    ,
    ('vusal', 'ROLE_STUDENT_UPDATE')
    ,
    ('kenan', 'ROLE_STUDENT_VIEW');

alter table authorities
    drop column username;

alter table authorities
    add column username VARCHAR(40) NOT NULL
        references users (username);

create table authority_list
(
    id        int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    authority VARCHAR(50)     NOT NULL
)
