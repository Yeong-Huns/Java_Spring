Create table board(
	idx int not null auto_increment,
	title varchar(100) not null,
	content varchar(2000) not null,
	writer varchar(20) not null,
	count int default 0,	
	primary key(idx)
);

select * from Board order by idx DESC;

CREATE TABLE Member(
	memIdx int auto_increment,
	memId VARCHAR(20) not null,
 	memPwd VARCHAR(20) not null,
	memName VARCHAR(20) not null,
	memAge int,
	memGender VARCHAR(20),
    memEmail VARCHAR(50),
    memProfile varchar(50),
    primary key(memIdx)
);