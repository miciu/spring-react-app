CREATE DATABASE employee_db DEFAULT CHARACTER SET utf8;
CREATE USER 'spring-user'@'localhost' IDENTIFIED BY 'qwert123';
GRANT ALL PRIVILEGES ON employee_db.* TO 'spring-user'@'localhost';
FLUSH PRIVILEGES;