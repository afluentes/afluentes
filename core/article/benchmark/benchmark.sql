drop table if exists MESSAGE_RECIPIENT;
drop table if exists MESSAGE;
drop table if exists USER;

create table USER (
	ID int auto_increment,
	NAME varchar(100) not null,
	primary key(ID)
);

create table MESSAGE (
	ID int auto_increment,
	SUBJECT varchar(100),
	BODY text,
	SENDER_ID int,
	primary key(ID),
	foreign key (SENDER_ID) references USER(ID)
);

create table MESSAGE_RECIPIENT (
	MESSAGE_ID int,
	RECIPIENT_ID int,
	primary key (MESSAGE_ID, RECIPIENT_ID),
	foreign key (MESSAGE_ID) references MESSAGE(ID),
	foreign key (RECIPIENT_ID) references USER(ID)	
); 



insert into USER (NAME) values ('User 1');
insert into USER (NAME) values ('User 2');
insert into USER (NAME) values ('User 3');
insert into USER (NAME) values ('User 4');
insert into USER (NAME) values ('User 5');
insert into USER (NAME) values ('User 6');
insert into USER (NAME) values ('User 7');
insert into USER (NAME) values ('User 8');
insert into USER (NAME) values ('User 9');
insert into USER (NAME) values ('User 10');



insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 1', 'Body 1', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 2', 'Body 2', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 3', 'Body 3', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 4', 'Body 4', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 5', 'Body 5', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 6', 'Body 6', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 7', 'Body 7', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 8', 'Body 8', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 9', 'Body 9', 1);
insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values ('Message 10', 'Body 10', 1);



insert into MESSAGE_RECIPIENT values (1, 1);
insert into MESSAGE_RECIPIENT values (1, 2);
insert into MESSAGE_RECIPIENT values (1, 3);

insert into MESSAGE_RECIPIENT values (2, 4);
insert into MESSAGE_RECIPIENT values (2, 5);
insert into MESSAGE_RECIPIENT values (2, 6);

insert into MESSAGE_RECIPIENT values (3, 7);
insert into MESSAGE_RECIPIENT values (3, 8);
insert into MESSAGE_RECIPIENT values (3, 9);
insert into MESSAGE_RECIPIENT values (3, 10);

insert into MESSAGE_RECIPIENT values (4, 1);
insert into MESSAGE_RECIPIENT values (4, 2);
insert into MESSAGE_RECIPIENT values (4, 3);

insert into MESSAGE_RECIPIENT values (5, 4);
insert into MESSAGE_RECIPIENT values (5, 5);
insert into MESSAGE_RECIPIENT values (5, 6);

insert into MESSAGE_RECIPIENT values (6, 7);
insert into MESSAGE_RECIPIENT values (6, 8);
insert into MESSAGE_RECIPIENT values (6, 9);
insert into MESSAGE_RECIPIENT values (6, 10);

insert into MESSAGE_RECIPIENT values (7, 1);
insert into MESSAGE_RECIPIENT values (7, 2);
insert into MESSAGE_RECIPIENT values (7, 3);

insert into MESSAGE_RECIPIENT values (8, 4);
insert into MESSAGE_RECIPIENT values (8, 5);
insert into MESSAGE_RECIPIENT values (8, 6);

insert into MESSAGE_RECIPIENT values (9, 7);
insert into MESSAGE_RECIPIENT values (9, 8);
insert into MESSAGE_RECIPIENT values (9, 9);
insert into MESSAGE_RECIPIENT values (9, 10);

insert into MESSAGE_RECIPIENT values (10, 1);
insert into MESSAGE_RECIPIENT values (10, 2);
insert into MESSAGE_RECIPIENT values (10, 3);