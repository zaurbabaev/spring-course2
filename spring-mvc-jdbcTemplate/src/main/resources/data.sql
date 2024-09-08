CREATE TABLE students
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(30) NOT NULL ,
    surname  VARCHAR(30) NOT NULL ,
    birthday DATE        NOT NULL ,
    phone    VARCHAR(13) NOT NULL ,
    address  VARCHAR(50) NOT NULL ,
    email    VARCHAR(30) NOT NULL ,
    sector   varchar(30) NOT NULL
);



INSERT INTO students(name, surname, birthday, phone, address, email, sector)
values ('Zaur', 'Babayev', '1987-03-01', '+994702262052', 'Xirdalan', 'zaurik87@gmail.com', 'AZ'),
       ('Vusal', 'Nagiyev', '1990-01-01', '+994702300000', 'Baki', 'vusal@mail.ru', 'EN'),
       ('Nail', 'Kazimov', '1999-05-01', '+994705632000', 'Seki', 'nail@gmail.com', 'RU'),
       ('Yusif', 'Yusifov', '1997-04-03', '+994554435000', 'Gence', 'yusif@mail.ru', 'DE');




-- Computer

CREATE TABLE computers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(30) NOT NULL ,
    model VARCHAR(30) NOT NULL ,
    price DECIMAL(6,2) NOT NULL
);

INSERT INTO computers (brand, model, price) VALUES
                                                ('Acer','Nitro',800.9),
                                                ('MSI','Evo 14',650),
                                                ('Lenovo','Legion',1100.9);
