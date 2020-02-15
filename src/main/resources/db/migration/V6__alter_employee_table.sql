alter table employee add column profession varchar(50);
update employee set profession = 'BANKER' where birth_year = 1984;
update employee set profession = 'BUS_DRIVER' where birth_year = 1978;
update employee set profession = 'SOLIDER' where birth_year = 1953;
