create database if not exists EasyShopper;

use EasyShopper;

create table if not exists products(
id int not null auto_increment,
name varchar(80) not null,
price decimal(10,2) not null,
quantity int,
cost decimal(10,2) not null,
primary key(id)
);

SELECT * FROM products;

create table if not exists users (
id int not null auto_increment,
name varchar(40) not null,
email varchar(50) not null,
password varchar(30) not null,
balance int,
primary key(id)
);

SELECT * FROM users;
