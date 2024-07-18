CREATE TABLE users (
    id BIGSERIAL primary key,
    username VARCHAR(30) NOT NULL,
    role VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL
);