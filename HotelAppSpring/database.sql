-- @Block
CREATE DATABASE IF NOT EXISTS HotelDatabase;
USE HotelDatabase;
-- @Block
CREATE TABLE IF NOT EXISTS customers(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_Name CHAR(50),
    last_Name CHAR(50),
    email CHAR(255)
);
-- dùng format a_b để tránh xung đột khi dùng HibernateJpaDialect
-- @BLock
CREATE TABLE IF NOT EXISTS rooms(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    room_Number INT,
    room_Price FLOAT,
    room_Type CHAR(50),
    is_Free BOOLEAN
);
-- @Block
CREATE TABLE IF NOT EXISTS roomTypes(roomType CHAR(50));
-- @Block
CREATE TABLE IF NOT EXISTS reservation(
    customer_Name CHAR(255),
    room_Number INT,
    -- format yyyy-mm-dd
    check_In_Date DATE,
    check_Out_Date DATE
);
-- @BLock
CREATE TABLE IF NOT EXISTS freeRooms(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    room_Number INT,
    room_Price FLOAT,
    room_Type CHAR(50)
);
