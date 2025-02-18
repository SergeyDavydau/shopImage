



INSERT INTO t_user (id,username, password) VALUES ( 2,'Gringo', '$2a$10$R0kgv9zUdfFUGfVhbDvdwe8WuguZWAQg/vjKY1zJKAuZ1BU8GoGYK');

INSERT INTO NEWS (name, create_date, description, author_id) VALUES ('Local news', NOW(),'news obout then', 2);
INSERT INTO NEWS (name, create_date,description, author_id) VALUES ('World news', NOW(),'dollar are not going to stop', 2);
INSERT INTO NEWS (name, create_date,description, author_id) VALUES ('Beer fest', NOW(),'drink cold beer', 2);
INSERT INTO NEWS (name, create_date,description, author_id) VALUES ('help', NOW(),'help your self', 2);

-- INSERT INTO NEWS (name, create_date, description) VALUES ('Local news', NOW(),'news obout then');
-- INSERT INTO NEWS (name, create_date,description) VALUES ('World news', NOW(),'dollar are not going to stop');
-- INSERT INTO NEWS (name, create_date,description) VALUES ('Beer fest', NOW(),'drink cold beer');
-- INSERT INTO NEWS (name, create_date,description) VALUES ('help', NOW(),'help your self');
--
-- INSERT INTO T_USER_NEWS_LIST VALUES (1,2);
-- INSERT INTO T_USER_NEWS_LIST VALUES (2,2);
-- INSERT INTO T_USER_NEWS_LIST VALUES (3,2);
-- INSERT INTO T_USER_NEWS_LIST VALUES (4,2);