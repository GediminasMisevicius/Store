CREATE TABLE IF NOT EXISTS test.user
(id int AUTO_INCREMENT,
username VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS test.roles
(
user_id int NOT NULL,
name VARCHAR(50) NOT NULL,
FOREIGN KEY FK_USER (user_id) REFERENCES test.user (id)
);
