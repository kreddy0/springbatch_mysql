DROP TABLE if exists customer;
CREATE TABLE customer
(
phone VARCHAR(11) PRIMARY KEY,
first_name VARCHAR(40),
last_name VARCHAR(40),
title VARCHAR(100),
email VARCHAR(100),
designation VARCHAR(100)
);