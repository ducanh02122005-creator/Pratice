-- @Block
DROP DATABASE IF EXISTS Prototype1;
CREATE DATABASE Prototype1;
USE Prototype1;

-- @Block
CREATE TABLE User (
                      id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      name varchar(100) not null,
                      email varchar(255) not null,
                      password varchar(1000) not null
);
-- @Block
INSERT INTO User(name, email, password) Value(
                                              'adam',
                                              'adam@gmail.com',
                                              'adampass'
    ),('John', 'john@gmail.com','123'),
    ('Anna', 'anna@gmail.com', '456')
;

