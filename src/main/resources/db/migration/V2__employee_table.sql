CREATE TABLE employee (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name varchar(50),
    last_name varchar(50),
    age int,
    CONSTRAINT employee_pk PRIMARY KEY (id)
);