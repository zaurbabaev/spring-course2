CREATE TABLE students
(
    id       integer PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(30)  NOT NULL,
    surname  VARCHAR(30)  NOT NULL,
    phone    VARCHAR(13)  NOT NULL,
    address  VARCHAR(255) NOT NULL,
    email    VARCHAR(30)  NOT NULL,
    birthday DATE         NOT NULL,
    sector   VARCHAR(20)  NOT NULL
);

INSERT INTO students (name, surname, phone, address, email, birthday, sector)
VALUES ('Zaur', 'Babayev', '+994702262052', 'Xirdalan', 'zaurik87@gmail.com', '1987-03-01', 'EDUCATION'),
       ('Vusal', 'Nagiyev', '+994702260000',  'Baki', 'vusal@gmail.com', '1990-04-07', 'HEALTHCARE'),
       ('Yusif', 'Emirov', '+994909090900', 'Gence', 'yusif@mail.ru', '2000-09-21', 'FINANCE');


