#       Вставка в новосозданныую таблицу

INSERT INTO users VALUES (1, 11,'admin@a.ru', 'admin', '$2y$10$UUDoqynp3SsJuWxK6XMQnugF.zt929JjbdRT4N6yG0QUgcV7LKkVq', 'Sokolov');
INSERT INTO users VALUES (2, 12, 'user@u.ru', 'user', '$2y$10$hDZIK07X9DFwEyDXd8u7VuaRSvF/TLffc9VHpGdcJTyMwFYNn7msC', 'Petrov');
INSERT INTO user_role VALUES (1, 'ADMIN');
INSERT INTO user_role VALUES (2, 'USER');


#       Вставка в таблицу созданную в задании 3.1.3

# REPLACE INTO users VALUES (1, 11,'admin', '$2y$10$UUDoqynp3SsJuWxK6XMQnugF.zt929JjbdRT4N6yG0QUgcV7LKkVq', 'Sokolov','admin@a.ru');
# REPLACE INTO users VALUES (2, 12, 'user', '$2y$10$hDZIK07X9DFwEyDXd8u7VuaRSvF/TLffc9VHpGdcJTyMwFYNn7msC', 'Petrov','user@u.ru');