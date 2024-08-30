CREATE TABLE computers
(
    id    integer PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(30)  NOT NULL,
    brand VARCHAR(30)  NOT NULL,
    price NUMBER(8, 2) NOT NULL
);

INSERT INTO computers(model, brand, price)
VALUES ('EVO 14', 'MSI', 850),
       ('Legion 5', 'Lenovo', 1200),
       ('Vector', 'HP', 900);