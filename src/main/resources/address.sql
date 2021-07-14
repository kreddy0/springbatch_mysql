
DROP TABLE if exists address;
CREATE TABLE address
(
customerPhone VARCHAR(11) NOT null,
addressType VARCHAR(2),
addressLine1 VARCHAR(100),
addressLine2 VARCHAR(100),
city VARCHAR(40),
stateCode VARCHAR(10),
zipcode int,
zipplus4 varchar(10),

addressType2 VARCHAR(2),
addressLine12 VARCHAR(100),
addressLine22 VARCHAR(100),
city2 VARCHAR(40),
stateCode2 VARCHAR(10),
zipcode2 int,
zipplus42 varchar(10),
primary key(customerPhone)

);
