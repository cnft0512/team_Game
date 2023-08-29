
drop table login;
drop table join;
drop table admin;
drop table HangMan;

--------------------------------------------------------------------------------
create table member (
    id varchar2(50),
    username varchar2(30),
    password varchar2(50),
    passwordre varchar2(50),
    email varchar2(100),
    phone varchar2(30)
);

insert into member values('123','¿Ã¥Ÿ¿∫','123','123','qwe123@naver.com','01012345678');
delete member;

select * from member order by id asc;

commit;
--------------------------------------------------------------------------------
create sequence num_seq start with 1 increment by 1;

create table wordslist (
    word varchar2(1000),
    num number
);

insert into wordslist values('apple',num_seq.nextval);
insert into wordslist values('banana',num_seq.nextval);
insert into wordslist values('coconut',num_seq.nextval);
insert into wordslist values('grape',num_seq.nextval);
insert into wordslist values('peach',num_seq.nextval);
insert into wordslist values('cherry',num_seq.nextval);
insert into wordslist values('mango',num_seq.nextval);
insert into wordslist values('lemon',num_seq.nextval);
insert into wordslist values('grapefruit',num_seq.nextval);
insert into wordslist values('watermelon',num_seq.nextval);

drop sequence num_seq;

select * from wordslist;

commit;
--------------------------------------------------------------------------------
