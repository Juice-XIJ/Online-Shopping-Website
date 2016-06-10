drop database project;

CREATE DATABASE `project`;

use project;


create table users (
	id int primary key auto_increment,
	email varchar(1000) unique not null,
	username varchar(1000) unique not null,
	passwords varchar(1000) not null,
    street varchar(5000) not null,
    city varchar(100) not null,
    state varchar(100) not null,
    zipcode varchar(10) not null
);
create table admin (
	id int primary key,
	foreign key(id) references users(id)
	on update cascade on delete cascade
);
create table customer (
	id int primary key,
	foreign key(id) references users(id)
	on update cascade on delete cascade
);
create table seller (
	id int primary key,
	foreign key(id) references users(id)
	on update cascade on delete cascade,
	isSeller int default 0, -- 0 pending, 1 approved
    nameOfShop varchar(1000) not null
);
create table goods (
	id int primary key auto_increment,
    nameOfGoods varchar(5000) not null,
    seller int not null,
    price float not null,
    type enum('Books', 'Electronics', 'Sports', 'Home') not null,
    description varchar(5000)    
);
create table image (
	id int,
    foreign key(id) references goods(id) 
    on update cascade on delete cascade,
    path varchar(1000),
    primary key(id, path)
);
create table buy (
	customer int,
    foreign key(customer) references customer(id) 
    on update cascade on delete cascade,
    goods int,
    foreign key(goods) references goods(id) 
    on update cascade on delete cascade,
    dealDate datetime,
    amount int,
    dealPrice float not null,
    rate float default null,
    
    finish enum('canceled', 'pending', 'delivering', 'delivered') not null,
    commentOfGoods varchar(1000),
    primary key(customer, goods, dealDate)    
);

create view rate(goods_id, rates) as 
	select g.id, avg(b.rate) 
		from buy b, goods g 
			where b.goods = g.id 
            and b.rate is not null 
            group by g.id;

-- INSERTIONS
-- USERS
insert into users (email, username, passwords, street, city, state, zipcode) values ("1@1.com", "user1", "111111", "464 Massachusetts 9", "Boston", "MA", "02115");
insert into users (email, username, passwords, street, city, state, zipcode) values ("2@2.com", "user2", "222222", "464 Massachusetts 9", "Boston", "MA", "02115");
insert into users (email, username, passwords, street, city, state, zipcode) values ("3@3.com", "user3", "333333", "364 Massachusetts 9", "Boston", "MA", "02110");
insert into users (email, username, passwords, street, city, state, zipcode) values ("4@4.com", "user4", "444444", "564 Massachusetts 9", "Boston", "MA", "02114");
insert into users (email, username, passwords, street, city, state, zipcode) values ("5@5.com", "user5", "555555", "664 Massachusetts 9", "Boston", "MA", "02118");
insert into users (email, username, passwords, street, city, state, zipcode) values ("6@6.com", "user6", "666666", "764 Massachusetts 9", "Boston", "MA", "02117");
insert into users (email, username, passwords, street, city, state, zipcode) values ("7@7.com", "user7", "777777", "864 Massachusetts 9", "Boston", "MA", "02119");

-- ADMIN
insert into admin (id) values (1);

-- CUSTOMER
insert into customer (id) values (2);
insert into customer (id) values (3);
insert into customer (id) values (4);
insert into customer (id) values (5);
insert into customer (id) values (6);
insert into customer (id) values (7);

-- SELLER
insert into seller (id, isSeller, nameOfShop) values (4, 0, "shop1");
insert into seller (id, isSeller, nameOfShop) values (5, 1, "shop2");
insert into seller (id, isSeller, nameOfShop) values (6, 1, "shop3");
insert into seller (id, isSeller, nameOfShop) values (7, 1, "shop4");

-- GOODS
insert into goods (nameOfGoods, seller, price, type, description) values ("goods1", 5, 101, 'Books', "sold by shop2");
insert into goods (nameOfGoods, seller, price, type, description) values ("goods2", 7, 102, 'Electronics', "sold by shop4");
insert into goods (nameOfGoods, seller, price, type, description) values ("goods3", 7, 103, 'Sports', "sold by shop4");

-- IMAGE 
insert into image (id, path) values (1, "image/test/book1.jpg");
insert into image (id, path) values (2, "image/test/electronics1.jpg");
insert into image (id, path) values (2, "image/test/electronics1_1.jpg");
insert into image (id, path) values (3, "image/test/sport1.jpg");
insert into image (id, path) values (3, "image/test/sport1_1.jpg");

-- BUY
insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (4, 1, '2016-04-07 11:11:11', 1, 101, 'pending');
insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (5, 1, '2016-04-07 22:22:22', 2, 101, 'pending');
insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (5, 2, '2016-04-07 22:22:23', 3, 101, 'pending');
insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (6, 2, '2016-04-07 22:22:24', 4, 101, 'delivering');
insert into buy (customer, goods, dealDate, amount, dealPrice, finish, commentOfGoods, rate) values (6, 3, '2016-04-07 22:22:25', 5, 101, 'delivered', "good!", 5);
insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (7, 1, '2016-04-07 22:22:26', 6, 101, 'pending');
insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (7, 2, '2016-04-07 22:22:27', 7, 101, 'delivering');
insert into buy (customer, goods, dealDate, amount, dealPrice, finish, commentOfGoods, rate) values (7, 3, '2016-04-07 22:22:28', 8, 101, 'delivered', "very good!", 4);
