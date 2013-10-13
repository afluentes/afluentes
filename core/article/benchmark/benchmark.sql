drop table if exists MESSAGE_RECIPIENT;
drop table if exists MESSAGE_FILE;
drop table if exists MESSAGE;
drop table if exists USER;
drop table if exists FILE;
drop table if exists MEDIA_TYPE;

create table MEDIA_TYPE (
	ID int auto_increment,
	TYPE varchar(100),
	SUBTYPE varchar(100),
	primary key(ID)
);

create table FILE (
	ID int auto_increment,
	NAME varchar(100),
	MEDIA_TYPE_ID int,
	primary key(ID),
	foreign key (MEDIA_TYPE_ID) references MEDIA_TYPE(ID)
);

create table USER (
	ID int auto_increment,
	NAME varchar(100),
	PICTURE_ID int,
	primary key(ID),
	foreign key (PICTURE_ID) references FILE(ID)
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
	foreign key (MESSAGE_ID) references MESSAGE(ID),
	foreign key (RECIPIENT_ID) references USER(ID)	
);

create table MESSAGE_FILE (
	MESSAGE_ID int,
	FILE_ID int,
	foreign key (MESSAGE_ID) references MESSAGE(ID),
	foreign key (FILE_ID) references FILE(ID)	
);