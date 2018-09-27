CREATE DATABASE users;
GRANT ALL PRIVILEGES ON DATABASE users TO postgres;

\c users

DROP TABLE IF EXISTS users;
CREATE TABLE users(
       id serial PRIMARY KEY,
       firstname VARCHAR(100),
       lastname VARCHAR(100)
);

INSERT INTO users(id, firstname, lastname) VALUES(1, 'Ari', 'Rajamaki');
INSERT INTO users(id, firstname, lastname) VALUES(2, 'Ari2', 'Rajamaki');
INSERT INTO users(id, firstname, lastname) VALUES(3, 'Ari3', 'Rajamaki');


