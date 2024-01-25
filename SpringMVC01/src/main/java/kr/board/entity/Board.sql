Create table board(
	idx int not null auto_increment,
	title varchar(100) not null,
	content varchar(2000) not null,
	writer varchar(20) not null,
	count int default 0,	
	primary key(idx)
);

INSERT INTO Board(title, content, writer)VALUES("TEST01", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST02", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST03", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST04", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST05", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST06", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST07", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST08", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST09", "MVC01연습", "YH");
INSERT INTO Board(title, content, writer)VALUES("TEST10", "MVC01연습", "YH");

select * from Board order by idx DESC;