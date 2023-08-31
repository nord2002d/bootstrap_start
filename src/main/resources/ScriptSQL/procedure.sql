DELIMITER //

CREATE PROCEDURE create_users(
             IN   id bigint,
             IN   `age` int,
<<<<<<< HEAD
             IN  email  varchar(60),
=======
>>>>>>> origin/main
             IN  username  varchar(30),
             IN  `password` varchar(255),
             IN  sur_name varchar(30),
             IN  roles varchar(30)
)
BEGIN

<<<<<<< HEAD
    REPLACE INTO users VALUES ( id,`age`, email, username, `password`, sur_name);
=======
    REPLACE INTO users VALUES ( id,`age`, username, `password`, sur_name);
>>>>>>> origin/main
    REPLACE INTO user_role VALUES ( id, roles);

END;
DELIMITER ;