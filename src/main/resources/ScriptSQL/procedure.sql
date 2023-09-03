
CREATE TABLE IF NOT EXISTS  users(
                                     id INT NOT NULL AUTO_INCREMENT,
                                     name varchar(30) NOT NULL,
                                     sur_name varchar(30) NOT NULL,
                                     age INT NOT NULL,
                                     password varchar(255) NOT NULL,
                                     email varchar(255) NOT NULL,
                                     PRIMARY KEY(id));

INSERT INTO users VALUES (1,'admin','Sokolov', 11,'$2y$10$UUDoqynp3SsJuWxK6XMQnugF.zt929JjbdRT4N6yG0QUgcV7LKkVq','admin@a.ru');
INSERT INTO users VALUES (2, 'user','Petrov', 12,'$2y$10$hDZIK07X9DFwEyDXd8u7VuaRSvF/TLffc9VHpGdcJTyMwFYNn7msC','user@u.ru');


CREATE TABLE IF NOT EXISTS user_role (
                                         user_id INT NOT NULL,
                                         roles  varchar(255) NOT NULL );
INSERT INTO user_role VALUES (1, 'ADMIN');
INSERT INTO user_role VALUES (2, 'USER');