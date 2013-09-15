drop table MESSAGE_FILE;
drop table MESSAGE_RECIPIENT;
drop table MESSAGE;

drop table USER;
drop table PICTURE;

drop table FILE;
drop table MEDIA_TYPE;



insert into PICTURE values ();
insert into USER (PICTURE_ID) VALUES (1);
insert into MEDIA_TYPE values ();
insert into FILE (MEDIA_TYPE_ID) values (1);
insert into MESSAGE(SENDER_ID) values (1);
insert into MESSAGE_RECIPIENT (MESSAGE_ID, RECIPIENT_ID) values (1, 1);
insert into MESSAGE_FILE (MESSAGE_ID, FILE_ID) values (1, 1);