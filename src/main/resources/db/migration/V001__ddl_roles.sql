CREATE TABLE roles(
	id integer not null primary key unique,
	role_name varchar(128) not null unique,
	role_descriptions text,
);