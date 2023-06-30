use facility_management;
 
 create table gender(
 id int primary key,
 `name` varchar(30) not null
 );
 
 create table customerType(
 id int primary key,
 `name` varchar(30) not null
 );
 
 create table customers(
 id int primary key,
 `name` varchar(30) not null,
 address varchar(30) not null,
 dob date not null,
 phone int not null,
 genderID int not null,
 cusTypeID int not null,
 foreign key (genderID) references gender(id),
 foreign key (cusTypeID) references customerType(id)
 );
 
alter table customers modify column id varchar(30);

insert into customers
value ('CU1001','Neymar', 'Brazil', '1994-11-11', 1090, 1, 1),
 ('CU1003','Shen Nana', 'Chinese', '1986-11-11', 1090, 1, 2),
 ('CU1004','Thuy Top', 'Chinese', '1986-11-11', 1090, 1, 2),
 ('Cu1005','Luis Nani', 'Portugal', '1984-11-11', 1090, 2, 3),
 ('Cu1006','Bruno Fernandes', 'Portugal', '1985-11-11', 1090, 1, 2),
 ('CU1012','Hoang Yen', 'Viet Nam', '1999-11-11', 1090, 1, 1),
('CU1010','Thuy Linh', 'Viet Nam', '1995-11-11', 1090, 1, 3),
 ('CU1011','Donal Trump', 'USA', '1998-11-11', 1090, 1, 2),
 ('CU1112','Kenus Reeves', 'England', '1987-11-11', 1090, 2, 1);
 
 insert into gender values (1,"Nam"), (2,"Nu");
 insert into customerType values (1,"VIP"), (2,"DIAMOND"), (3,"NORMAL");
 
 -- Create table Employee
 create table employee_degree(
 id int primary key,
 name varchar(30) not null
 );
 
 create table employee_position(
 id int primary key,
 name varchar(30)
 );
 
 create table employee (
 id varchar(30) primary key,
 name varchar(30) not null,
 address varchar(30) not null,
 dob date not null,
 phone int not null,
 gender int not null,
 degree int not null,
 position int not null,
 salary int not null,
 foreign key (gender) references gender(id),
 foreign key (degree) references employee_degree(id),
 foreign key (position) references employee_position(id)
 );
 
 insert into employee_degree value (1,'Postgraduate'), (2,'University'), (3,'High school'); 
 insert into employee_position value (1,'Manager'),(2,'Reception'),(3,'Accountant'),(4,'Security');
 insert into employee value ('EM0001','Nhan','Da Nang','1995-12-12',1000,1,3,4,5000000),
 ('EM0002','Uyen','Quang Nam','1995-12-27',1011,2,2,2,10000000),
 ('EM0003','Tuan','Da Nang','1990-12-12',1002,1,2,1,15000000);

 create table period(
 `id` int primary key,
 name varchar(30)
 );
 
 create table facility_type(
 `id` int primary key,
 name varchar(30)
 );
 create table facility_times(
	`id` varchar(30) primary key,
    times int not null,
    foreign key (id) references facility(id)
);
alter table facility_times modify column times date not null;
-- drop table facility_times;
insert into period value (1,'By date'),(2,'By month'),(3,'By year');
insert into facility_type value (1,'Villa'),(2,'House'),(3,'Room');
insert into facility_times value ('SVRO0001',1),('SVRO0003',2),('SVRO0004',4),('SVRO0005',4),
('SVVI0001',1),('SVVI0002',2),('SVVI0003',2),('SVVI0004',2),
('SVHO0001',4),('SVHO0003',4),('SVHO0004',4);

create table facility(
	`id` varchar(30) primary key,
    `period` int not null,
	`area` int not null,
    `max_person` int not null,
    `price` int not null,
    `type` int not null,
    `floor` int,
    `pool_area` int,
     foreign key (period) references period(id),
     foreign key (type) references facility_type(id)
);

insert into facility value ('SVRO0001',1,50,4,200,3,null,null),
							('SVRO0002',1,30,2,100,3,null,null),
                            ('SVRO0003',2,35,2,2000,3,null,null),
                            ('SVRO0004',2,40,2,2200,3,null,null),
                            ('SVRO0005',3,35,2,9000,3,null,null),
                            ('SVVI0001',1,200,10,800,1,3,15),
					        ('SVVI0002',1,150,8,600,1,2,15),
							('SVVI0003',1,150,8,600,1,2,15),
                            ('SVHO0001',1,150,10,600,2,2,null),
                            ('SVHO0002',1,160,10,600,2,2,null),
                            ('SVHO0003',2,100,5,600,2,2,null),
                            ('SVHO0004',2,150,10,600,2,2,null);
                            
create table book(
	`id` varchar(30) primary key,
    `customer_name` varchar(30) not null,
    `facility_name` varchar(30) not null,
    `companion` int not null, -- so nguoi di cung
    `date_in` date not null,
    `date_out` date not null,
    constraint fk_customer_name foreign key (customer_name) references customers(id) on delete cascade,
    constraint fk_facility_name foreign key (facility_name) references facility(id) on delete cascade
);
-- alter table book drop foreign key (customer_name);
-- alter table book add constraint fk_customer_name foreign key (customer_name) references customers(id) on delete cascade;
-- alter table book add constraint fk_facility_name foreign key (facility_name) references facility(id) on delete cascade;
-- drop table book;gender
update book set customer_name = ?, facility_name = ?, companion = ?, date_in= ?, date_out = ? where id = ?;
insert book value ('BO1001','CU1002','SVRO0001',2,'2022-10-10','2022-11-10');
insert book value ('BO1009','CU1004','SVRO0001',2,'2022-11-10','2022-11-11'),
				('BO1003','CU1004','SVRO0003',2,'2022-10-12','2022-10-14'),('BO1004','CU1012','SVRO0003',2,'2022-10-15','2022-10-16'),
                ('BO1011','CU1011','SVHO0003',8,'2022-08-08','2022-09-08'),('BO1010','CU1012','SVHO0001',8,'2022-08-10','2022-08-13'),
                ('BO1007','CU1008','SVRO0004',2,'2022-10-14','2022-10-16'),('BO1008','CU1002','SVRO0004',2,'2022-10-18','2022-10-20');
-- select * from book where datediff(curdate(),date_out) <= 0 order by date_in, date_out;
-- select facility_name, count(*) as `count` from book where 0 > datediff(curdate(),date_in) group by facility_name;


 
 
 