CREATE
DATABASE java_course;

CREATE TABLE users
(
    username VARCHAR(100) PRIMARY KEY,
    name VARCHAR(40) NOT NULL ,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

CREATE TABLE authorities
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    username  VARCHAR(100) NOT NULL REFERENCES users (username),
    authority VARCHAR(100) NOT NULL
);

CREATE TABLE authority_list
(
    id          int PRIMARY KEY AUTO_INCREMENT,
    authority   VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO users(username, name,password, enabled)
VALUES ('zaur', 'Zaur','{bcrypt}$2a$12$ILnU3XSUP4Ph1raU8Msv1e9EjW1fWXA5Qp.UP398wEJ.porFO7vlu', 1),
       ('vusal', 'Vusal','{bcrypt}$2a$12$GY8qLH/a4EZuRZl5pnlZBOKZlNgPvvcGMbUl/cENrqUUtY4vgUiva', 1);

INSERT INTO authorities (id, username, authority)
VALUES (1, 'zaur', 'ROLE_ADMIN'),
       (2, 'vusal', 'ROLE_MANAGER');